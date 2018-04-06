-- Procédures stockées

-- 0/ Suppression d'un séminaire
CREATE OR REPLACE PROCEDURE SupprSeminaire (idSemASuppr IN integer)
IS
BEGIN
	delete from Animateur
		where idSeminaire = idSemASuppr;

	delete from Conferencier
		where idActivite in (select idActivite
								from Activite
								where idSeminaire = idSemASuppr);

	delete from Participant
		where idSeminaire = idSemASuppr;

	delete from Activite
		where idSeminaire = idSemASuppr;

	delete from Prestataire
		where idSeminaire = idSemASuppr;

	delete from Seminaire
		where idSeminaire = idSemASuppr;
EXCEPTION
	when others then DBMS_OUTPUT.PUT_LINE('OK');
END;
/


-- 1/ Le programme d’un séminaire est fourni au moins un mois avant sa date de début, sinon le séminaire est supprimé.
CREATE OR REPLACE PROCEDURE ProgrammeUnMoisAvant()

-- 2/ Chaque conférencier fourni le nom et le prix de sa présentation au moins un mois avant le séminaire.

-- 3/ … et les supports (transparents) au moins 2 semaines avant le séminaire.

-- 4/ Tout séminaire doit être annoncé  un mois avant sa planification avec le nombre de places et le prix par personne.

-- 5/ S’il n’y a pas assez de participants (moins de la moitié du nbMax) une semaine avant le séminaire, celui-ci est annulé


--6/ L’entreprise confirme ses réservations une semaine avant le séminaire
