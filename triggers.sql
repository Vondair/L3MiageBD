Triggers :




-- ///////////////////////////////////////////////////////////////////////////////

-- création de séminaire bien checker la date 1 mois pas possible de créer un séminaire ⇒ trigger

-- une semaine avant vérifier nb participants ⇒ déclencher un action (manuelle) java (requête ou JDBC vérifier ce séminaire ... ou vérification de l’ensemble de séminaire)

-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


-- 1/ (Un séminaire est créé si on a 3 activités au moins, les activités sont créées si on a un séminaire)
-- Pour un séminaire il y a 3 (demi -journée) ou 6 (journée) activités

CREATE OR REPLACE TRIGGER contrainte_1
AFTER insert on Activite
For each row
DECLARE
	VnbActivite integer;
BEGIN 
	select count(idActivite) into VnbActivite
	from Activite A join Seminaire S on (A.idSeminaire = S.idSeminaire)
	where typeSeminaire = 0 and A.idSeminaire = :NEW.idSeminaire;
	
	if (VnbActivite > 3) then
		raise_application_error(-20100,'Il y a un probleme au niveau du nombre d’activites (3 activites pour un seminaire d’une demi-journee)');
	end if;
END ;
/


-- TOUT OK

CREATE OR REPLACE TRIGGER contrainte_2
AFTER insert on Activite
For each row
DECLARE
	VnbActivite2 integer;
BEGIN 
	select count(idActivite) into VnbActivite2
	from Activite A join Seminaire S on (A.idSeminaire = S.idSeminaire)
	where typeSeminaire = 1 and A.idSeminaire = :NEW.idSeminaire;
	
	if (VnbActivite2 > 6) then
		raise_application_error(-20100,'Il y a un probleme au niveau du nombre d’activites (3 activites pour un seminaire d’une demi-journee)');
	end if;
END ;
/

-- TOUT OK

---------------------------------------------------------------------------------------------------------------------------
-- 2/ Un déjeuner est obligatoirement prévu si un séminaire dure toute la journée

CREATE OR REPLACE TRIGGER contrainte_3
BEFORE insert on Seminaire
For each row
BEGIN
	if(:NEW.typeSeminaire = 1 and :NEW.repas = 0) then       
		raise_application_error(-20100,'Un déjeuner est obligatoirement prévu si le séminaire dure une journée entière');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;
/ 
 
 -- TOUT OK

---------------------------------------------------------------------------------------------------------------------------
-- 3/ Si une personne s’inscrit alors que le nombre maximum de participant est atteint alors il va dans la liste d’attente

CREATE OR REPLACE TRIGGER contrainte_4
AFTER insert on Participant
For each row
DECLARE
	VnbParticipants integer;
	VnbMax integer;
BEGIN
	select count(idParticipant) into VnbParticipants
	from Participant P join Seminaire S on (P.idSeminaire = S.idSeminaire)
	where P.idSeminaire = :NEW.idSeminaire;
	
	select nbMax into VnbMax
	from Seminaire
	where idSeminaire = :NEW.idSeminaire;

	if(VnbParticipants > VnbMax) then
		raise_application_error(-20100, 'Plus de places pour ce seminaire, merci de s inscrire en file d attente');
	end if;
END;
/ 

-- TOUT OK



--------------

/*CREATE OR REPLACE TRIGGER contrainte_4
BEFORE insert on Participant
For each row
DECLARE
	VnbParticipants integer;
	VnbMax integer;
BEGIN
	select count(idParticipant) into VnbParticipants
	from Participant P join Seminaire S on (P.idSeminaire = S.idSeminaire)
	where P.idSeminaire = :NEW.idSeminaire;
	
	select nbMax into VnbMax
	from Seminaire
	where idSeminaire = :NEW.idSeminaire;

	if(VnbParticipants = VnbMax) then
		EXEC enAttente(:NEW.idParticipant, :NEW.idSeminaire, :NEW.dateInscription, :NEW.nomParticipant, :NEW.prenomParticipant, :NEW.mailParticipant, :NEW.numeroParticipant, :NEW.rueParticipant, :NEW.codePostalParticipant, :NEW.villeParticipant, :NEW.telephoneParticipant);
		raise_application_error('-20500','Erreur plus de place');
	end if;
END;
/


CREATE OR REPLACE PROCEDURE enAttente(idP IN integer, idS IN integer, dateInsc IN date, nomP IN varchar(20), prenomP IN varchar(20), mailP IN varchar(50), numeroP IN integer, rueP IN varchar(50), CPP IN number(5), villeP IN varchar(20), telP IN number(10)) 
AS PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
	INSERT INTO Participant VALUES (idP, idS, dateInsc, nomP, prenomP, mailP, numeroP, rueP, CPP, villeP, telP, 1);
COMMIT;
END;*/

-----------



 -- Création OK fonctionnement à tester

---------------------------------------------------------------------------------------------------------------------------
-- 4/ check si on a le nbMax de participant
-- (estEnAttente = 0 ou 1 )
-- si on a quelqu’un dans la liste d’attente, il prend la place de celui qui annule
-- si on a personne dans la liste d’attente, on met le dernier participant du séminaire à la place de celui qui a annulé pour avoir le nombre de participants toujours à jour
-- prendre date la plus petite de la liste d’attente pour savoir qui va participer

-- Si un participant se désiste, le premier de la liste d’attente passe en participant

CREATE OR REPLACE TRIGGER contrainte_5
BEFORE delete on Participant
for each row
DECLARE
	VidPremierEnAttente integer;
	VnbEnAttente integer;
BEGIN
	select count(idParticipant) into VnbEnAttente
	from Participant
	where estEnAttente = 1;

	if (VnbEnAttente > 0) then
		select idParticipant into VidPremierEnAttente
			from Participant
			where dateInscription = (select min(dateInscription)
										from Participant
										where estEnAttente = 1)
			and estEnAttente = 1;
			
		update Participant
		set estEnAttente = 0
		where idParticipant = VidPremierEnAttente;
	end if;
END ;
/

 -- Création OK fonctionnement à tester

---------------------------------------------------------------------------------------------------------------------------

-- 5/Il ne peut pas y avoir plus de 3 séminaires le même jour
CREATE OR REPLACE TRIGGER contrainte_6
BEFORE insert on Seminaire
For each row
DECLARE
	VnbSeminairesPourLaDate integer;
BEGIN
select count(*) into VnbSeminairesPourLaDate 
from Seminaire
where to_char(dateSeminaire,'YYYY-MM-DD hh:mi:ss') = to_char(:new.dateSeminaire,'YYYY-MM-DD hh:mi:ss');

IF(VnbSeminairesPourLaDate  >= 3) then
	raise_application_error(-20101,'Erreur -Il y a deja 3 seminaires a cette date ');
end if;
end;
/


-- TOUT OK

-- 6/Les inscriptions pour un séminaire ouvrent 1 mois avant la date de celui-ci, et la date d'inscription est sysdate
CREATE OR REPLACE TRIGGER contrainte_7
BEFORE insert on Participant
For each row
DECLARE
	VdateSeminaire date;
BEGIN
	select dateSeminaire into VdateSeminaire
	from Participant P join Seminaire S on (P.idSeminaire = S.idSeminaire)
	where P.idSeminaire = :NEW.idSeminaire;
	
	if (:NEW.dateInscription < ADD_MONTHS(VdateSeminaire, -1)) then
		raise_application_error(-20100, 'Les inscriptions pour un seminaire ouvrent 1 mois avant celui-ci');
	else
		update Participant
			set dateInscription = :NEW.dateInscription
			where idParticipant = :NEW.idParticipant;
	end if;
END;
/

-- Création OK fonctionnement à tester

-- 7/Le nombre de participants doit être fixé 1 semaine avant un séminaire
CREATE OR REPLACE TRIGGER contrainte_8
BEFORE insert on Participant
For each row
DECLARE
	VdateSeminaire date;
BEGIN
	select dateSeminaire into VdateSeminaire
	from Participant P join Seminaire S on (P.idSeminaire = S.idSeminaire)
	where P.idSeminaire = :NEW.idSeminaire;

	if (sysdate > VdateSeminaire - 7) then
		raise_application_error(-20100, 'Le nombre de participants pour un seminaire est fixe une semaine avant la date de debut de ce dernier');
	end if;
END;
/

-- Création OK fonctionnement à tester

-- 8/ Si le nombre max de participants n'est pas atteint, toute inscription se fait en tant que participant.
CREATE OR REPLACE TRIGGER contrainte_9
BEFORE insert on Participant
For each row
DECLARE
	VnbParticipants integer;
	VnbMax integer;
BEGIN
	select nvl(count(idParticipant), 0) into VnbParticipants
	from Participant
	where estEnAttente = 0;

	select nbMax into VnbMax
	from Seminaire
	where idSeminaire = :NEW.idSeminaire;

	if (VnbParticipants < VnbMax) then
		update Participant
			set estEnAttente = 0
			where idParticipant = :NEW.idParticipant;
	end if;
END;
/

-- Création OK fonctionnement à tester

-- 9/Il n'y a qu'un seul prestataire par séminaire
CREATE OR REPLACE TRIGGER contrainte_10
BEFORE insert on Prestataire
For each row
DECLARE
	VnbPrestatairesParSeminaire integer;
BEGIN
	select nvl(count(idPrestataire), 0) into VnbPrestatairesParSeminaire
	from Prestataire
	where idSeminaire = :NEW.idSeminaire;

	if (VnbPrestatairesParSeminaire = 1) then
		raise_application_error(-20100, 'Il ne peut y avoir qu un seul prestataire par seminaire');
	end if;
END;
/

-- FONCTIONNEMENT PAS OK

-- 10/Deux séminaires qui se déroulent le même jour ne peuvent pas avoir le même prestataire
CREATE OR REPLACE TRIGGER contrainte_11
BEFORE insert on Prestataire
For each row
DECLARE 
	VidPrestataire integer;
BEGIN
	select idPrestataire into VidPrestataire
	from Prestataire
	where :NEW.idSeminaire in (select S1.idSeminaire
								from Seminaire S1 join Seminaire S2 on (S1.idSeminaire <> S2.idSeminaire)
								where S1.dateSeminaire = S2.dateSeminaire);

	if (VidPrestataire <> NULL) then
		raise_application_error(-20100, 'Deux seminaires qui se deroulent le meme jour ne peuvent pas faire appel au meme prestataire');
	end if;
END;
/

-- Création OK fonctionnement à tester

-- 11/ Il n'y a qu'un animateur par séminaire
CREATE OR REPLACE TRIGGER contrainte_12
BEFORE insert on Animateur
For each row
DECLARE
	VnbAnimateurs integer;
BEGIN
	select nvl(count(idAnimateur), 0) into VnbAnimateurs
	from Animateur A join Seminaire S on (A.idSeminaire = S.idSeminaire)
	where A.idSeminaire = :NEW.idSeminaire;

	if (VnbAnimateurs <> 0) then
		raise_application_error(-20100, 'Il ne peut y avoir qu un seul animateur par ');
	end if;
END;
/

-- Création OK fonctionnement à tester