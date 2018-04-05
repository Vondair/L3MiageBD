Triggers :




-- ///////////////////////////////////////////////////////////////////////////////

-- création de séminaire bien checker la date 1 mois pas possible de créer un séminaire ⇒ trigger

-- une semaine avant vérifier nb participants ⇒ déclencher un action (manuelle) java (requête ou JDBC vérifier ce séminaire ... ou vérification de l’ensemble de séminaire)

-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


-- 1/ (Un séminaire est créé si on a 3 activités au moins, les activités sont créées si on a un séminaire)
-- Pour un séminaire il y a 3 (demi -journée) ou 6 (journée) activités

CREATE OR REPLACE TRIGGER troisActivitesDJ
BEFORE insert on Activite
For each row
DECLARE
	VnbActivite integer;
BEGIN 
	select count(idActivite) into VnbActivite
	from Activite A join Seminaire S on (A.idSeminaire = S.idSeminaire)
	where typeSeminaire = 'demi_journee' and A.idSeminaire = :NEW.idSeminaire;
	
	if (VnbActivite >= 3) then
		raise_application_error(-20100,'Il y a un probleme au niveau du nombre d’activites (3 activites pour un seminaire d’une demi-journee)');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;
/

-- Création OK fonctionnement à tester

CREATE OR REPLACE TRIGGER sixActivitesJ
BEFORE insert on Activite
For each row
DECLARE
	VnbActivite integer;
BEGIN 
	select count(idActivite) into VnbActivite
	from Activite A join Seminaire S on (A.idSeminaire = S.idSeminaire)
	where typeSeminaire = 'journee' and A.idSeminaire = :NEW.idSeminaire;
	
	if (VnbActivite >= 6) then
		raise_application_error(-20100,'Il y a un probleme au niveau du nombre d’activites (3 activites pour un seminaire d’une demi-journee)');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;
/

-- Création OK fonctionnement à tester

---------------------------------------------------------------------------------------------------------------------------
-- 2/ Un déjeuner est obligatoirement prévu si un séminaire dure toute la journée

CREATE OR REPLACE TRIGGER dejeunerSiJournee
BEFORE insert on Seminaire
DECLARE
	VtypeSeminaire varchar(12);
	Vrepas number(1);
BEGIN
	select idSeminaire, typeSeminaire into VtypeSeminaire, repas into Vrepas
	from Seminaire
	where typeSeminaire = :NEW.typeSeminaire and repas = :NEW.repas;

	if(VtypeSeminaire = 'journee' and Vrepas = 0) then       
		raise_application_error(-20100,'Un déjeuner est obligatoirement prévu si le séminaire dure une journée entière');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;
/ 
 --NEW or OLD references not allowed in table level triggers

---------------------------------------------------------------------------------------------------------------------------
-- 3/ Si une personne s’inscrit alors que le nombre maximum de participant est atteint alors il va dans la liste d’attente

CREATE OR REPLACE TRIGGER listeAttenteSiComplet
AFTER insert on Participant
DECLARE
	VnbParticipants integer;
BEGIN
	select nbMax, count(idParticipant) into VnbParticipants
	from Participant join Seminaire S on P.idSeminaire = S.idSeminaire
	where idParticipant = :NEW.idParticipant;
	
	if(VnbParticipant = nbMax) then
		update Participant
		set estEnAttente = 1
		where idParticipant = :NEW.idParticipant;
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;
/

---------------------------------------------------------------------------------------------------------------------------
-- 4/ check si on a le nbMax de participant
-- (estEnAttente = 0 ou 1 )
-- si on a quelqu’un dans la liste d’attente, il prend la place de celui qui annule
-- si on a personne dans la liste d’attente, on met le dernier participant du séminaire à la place de celui qui a annulé pour avoir le nombre de participants toujours à jour
-- prendre date la plus petite de la liste d’attente pour savoir qui va participer

-- Si un participant se désiste, le premier de la liste d’attente passe en participant

CREATE OR REPLACE TRIGGER desistement
AFTER delete on Participant
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
			and estEnAttente = 1);
			
		update Participant
		set estEnAttente = 0
		where idParticipant = VidPremierEnAttente;
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;
/

---------------------------------------------------------------------------------------------------------------------------

-- 5/Il ne peut pas y avoir plus de 3 séminaires le même jour
CREATE OR REPLACE TRIGGER troisSeminairesMaxParJour
BEFORE insert on Seminaire
For each row
DECLARE
	VnbSeminairesPourLaDate integer;
BEGIN
	select count(idSeminaire) into VnbSeminairesPourLaDate
	from Seminaire
	where dateSeminaire = :NEW.dateSeminaire;

	if (VnbSeminairesPourLaDate = 3) then
		raise_application_error(-20100,'Il ne peut pas y avoir plus de trois seminaires le meme jour');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;
/

-- 6/Les inscriptions pour un séminaire ouvrent 1 mois avant la date de celui-ci, et la date d'inscription est sysdate
CREATE OR REPLACE TRIGGER ouvertureInscriptionsUnMoisAvant
BEFORE insert on Participant
For each row
DECLARE
	VdateSeminairePourLequelOnSinscrit date;
BEGIN
	select dateSeminaire into VdateSeminairePourLequelOnSinscrit
	from Participant P join Seminaire S on (P.:NEW.idSeminaire = S.idSeminaire);
	
	if (:NEW.dateInscription < ADD_MONTHS(VdateSeminairePourLequelOnSinscrit, -1)) then
		raise_application_error(-20100, 'Les inscriptions pour un seminaire ouvrent 1 mois avant celui-ci');
	else
		update Participant
			set dateInscription = :NEW.dateInscription
			where idParticipant = :NEW.idParticipant;
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK');
END;
/

-- 7/Le nombre de participants doit être fixé 1 semaine avant un séminaire
CREATE OR REPLACE TRIGGER participantsFixesUneSemaineAvantSeminaire
BEFORE insert on Participant
For each row
DECLARE
	VdateSeminaire;
BEGIN
	select idParticipant, dateSeminaire into VdateSeminaire
	from Participant P join Seminaire S on (P.idSeminaire = S.idSeminaire);

	if (:NEW.dateInscription < VdateSeminaire - 7) then
		raise_application_error(-20100, 'Le nombre de participants pour un seminaire est fixe une semaine avant la date de debut de ce dernier');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;
/

-- 8/ Si le nombre max de participants n'est pas atteint, toute inscription se fait en tant que participant.
CREATE OR REPLACE TRIGGER participantSiPasNbMax
BEFORE insert on Participant
For each row
DECLARE
	VnbParticipants integer;
BEGIN
	select nvl(count(idParticipant), 0) into VnbParticipants
	from Participant
	where estEnAttente = 0;

	if (VnbParticipants < nbMax) then
		update Participant
			set estEnAttente = 0
			where idParticipant = :NEW.idParticipant;
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK');
END;
/

-- 9/Il n'y a qu'un seul prestataire par séminaire
CREATE OR REPLACE TRIGGER unPrestataireParSeminaire
BEFORE insert on Prestataire
For each row
DECLARE
	VnbPrestatairesPourUnSeminaire integer;
BEGIN
	select count(idPrestataire) into VnbPrestatairesPourUnSeminaire
	from Prestataire
	where idSeminaire = :NEW.idSeminaire;

	if (VnbPrestatairesPourUnSeminaire = 1) then
		raise_application_error(-20100, 'Il ne peut y avoir qu\'un seul prestataire par seminaire');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;
/

-- 10/Deux séminaires qui se déroulent le même jour ne peuvent pas avoir le même prestataire
CREATE OR REPLACE TRIGGER deuxSeminairesMemeJourDeuxPrestatairesDifferents
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
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;
/

-- 11/ Il n'y a qu'un animateur par séminaire
CREATE OR REPLACE TRIGGER unAnimateurParSeminaire
BEFORE insert on Animateur
For each row
DECLARE
	VnbAnimateursPourLeSeminaireConcerne integer;
BEGIN
	select nvl(count(idAnimateur), 0) into VnbAnimateursPourLeSeminaireConcerne
	from Animateur A join Seminaire S on (A.idSeminaire = S.:NEW.idSeminaire);

	if (VnbAnimateursPourLeSeminaireConcerne <> 0) then
		raise_application_error(-20100, 'Il ne peut y avoir qu\' un seul animateur par ');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('ok');
END;
/