Triggers :




-- ///////////////////////////////////////////////////////////////////////////////

-- création de séminaire bien checker la date 1 mois pas possible de créer un séminaire ⇒ trigger

-- une semaine avant vérifier nb participants ⇒ déclencher un action (manuelle) java (requête ou JDBC vérifier ce séminaire ... ou vérification de l’ensemble de séminaire)

-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





-- 1/ Pour un séminaire il y a 1 pause par demi journée


CREATE OR REPLACE TRIGGER contrainte_1
BEFORE insert or update on Seminaire
for each row
DECLARE
nbpause integer;
BEGIN
	if (( typeSeminaire == ‘journee’) && (nbpause != 2) )
		then raise_application_error(-20100,'Le nombre de pauses n’est pas le bon, il en faut 2');
elseif ( (typeSeminaire == ‘demi_journee’) && (nbpause != 1))
		then raise_application_error(-20100,'Le nombre de pauses n’est pas le bon, il en faut 1');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;

-- 2/ (Un séminaire est créé si on a 3 activités au moins, les activités sont créées si on a un séminaire)
-- Pour un séminaire il peut y avoir 3 (demi -journée) ou 6 (journée) activités

CREATE OR REPLACE TRIGGER contrainte_2
BEFORE insert or update on Activite
For each row
DECLARE
VnbActivite integer;
BEGIN 
	select idActivite, count(idActivite) into VnbActivite
	from Activite A join Seminaire S on (A.idSeminaire = S.idSeminaire)
	group by idActivite ;

if (( typeSeminaire == ‘demi_journee’) && (VnbActivite !=3))
		then raise_application_error(-20100,'Il y a un probleme au niveau du nombre d’activites (3 activites pour un seminaire d’une demi-journee)'); 
elseif (( typeSeminaire == ‘journee’) && (VnbActivite !=6))
		then raise_application_error(-20100,'Il y a un probleme au niveau du nombre d’activites (6 activites pour un seminaire d’une journee)'); 

	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;

---------------------------------------------------------------------------------------------------------------------------
-- 3/ Un déjeuner est obligatoirement prévu si un séminaire dure toute la journée

CREATE OR REPLACE TRIGGER contrainte_3
BEFORE insert or update on Seminaire
DECLARE
BEGIN
if(typeSeminaire == ‘journee’ AND  repas == False) then       
raise_application_error(-20100,'Un déjeuner est obligatoirement prévu si le séminaire dure une journée entière');
end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END; 

---------------------------------------------------------------------------------------------------------------------------
-- 4/ Si une personne s’inscrit alors que le nombre maximum de participant est atteint alors il va dans la liste d’attente

CREATE OR REPLACE TRIGGER contrainte_4
AFTER insert on Participant
DECLARE
VnbParticipants integer;
VnbMax integer;
BEGIN
	select nbMax into VnbMax, count(idParticipant) into VnbParticipants
	from Participant join Seminaire S on P.idSeminaire = S.idSeminaire
where idParticipant = :NEW.idParticipant;
	
	if(VnbParticipant = VnbMax) then
		update Participant
		set estEnAttente = 1
		where idParticipant = :NEW.idParticipant;
	endif;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END;


---------------------------------------------------------------------------------------------------------------------------
5/
L’entreprise ne peut organiser plus de 3 séminaires par jour

CREATE OR REPLACE TRIGGER contrainte_5
BEFORE insert or update on Seminaire
for each row
DECLARE
dateS date;
BEGIN
	if (:new.dateS < add_months(sysdate,1))
		then raise_application_error(-20100,'Un séminaire doit être créé 1 mois avant la date prévue !');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;
/



---------------------------------------------------------------------------------------------------------------------------
6/
Un séminaire ne peut être créé si la date du séminaire est inférieur à 1 mois après la date actuelle

CREATE OR REPLACE TRIGGER contrainte_7
BEFORE insert or update on Seminaire
for each row
DECLARE
dateS date;
BEGIN
	if (:new.dateS < add_months(sysdate,1))
		then raise_application_error(-20100,'Un séminaire doit être créé 1 mois avant la date prévue !');
	end if;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;
/


---------------------------------------------------------------------------------------------------------------------------
7/ 
check si on a le nbMax de participant
(estEnAttente = 0 ou 1 )
si on a quelqu’un dans la liste d’attente, il prend la place de celui qui annule
si on a personne dans la liste d’attente, on met le dernier participant du séminaire à la place de celui qui a annulé pour avoir le nombre de participants toujours à jour
prendre date la plus petite de la liste d’attente pour savoir qui va participer

Si un participant se désiste, le premier de la liste d’attente passe en participant

CREATE OR REPLACE TRIGGER contrainte_7
AFTER delete on Participant
for each row
DECLARE
VidParticipant integer;
BEGIN
select estEnAttente, idParticipant into VidParticipant
	from Participant
	where dateInscription = (select min(dateInscription)
					from Participant
					where estEnAttente = 1)
	and estEnAttente = 1);

	update Participant
	set estEnAttente = 0
	where idParticipant = VidParticipant;

EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK'); 
END ;
/


---------------------------------------------------------------------------------------------------------------------------
8/ 

