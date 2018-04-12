-- PRESTATAIRE
-- 006  contrainte prixpause = 0
-- 007  contrainte prixsalle =0
-- 008  contrainte d’integrite violée, clé parent (le séminaire d’id 6 n’existe pas)


drop trigger contrainte_11 ;


INSERT INTO Prestataire(idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire, numeroPrestataire, ruePrestataire, codepostalPrestataire, villePrestataire, telephonePrestataire)
 VALUES
(001, 001,'Prestataire1',10,2 ,200,'prest1@prest.com',1 ,'rue des prest', 38000,'Grenoble',5555500001 );
INSERT INTO Prestataire(idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire, numeroPrestataire, ruePrestataire, codepostalPrestataire, villePrestataire, telephonePrestataire)
 VALUES
(002, 002, 'Prestataire2',15,1 ,500, 'prest2@prest.com',2 ,'rue des prest', 38000,'Grenoble',5555500002);
INSERT INTO Prestataire(idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire, numeroPrestataire, ruePrestataire, codepostalPrestataire, villePrestataire, telephonePrestataire)
 VALUES
(003, 003, 'Prestataire3',20,1 ,1, 'prest3@prest.com',3 ,'rue des prest', 38000,'Grenoble',5555500003);
INSERT INTO Prestataire(idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire, numeroPrestataire, ruePrestataire, codepostalPrestataire, villePrestataire, telephonePrestataire)
 VALUES
(004, 004, 'Prestataire4',25,4 ,200, 'prest4@prest.com',4 ,'rue des prest', 38000,'Grenoble',5555500004);
INSERT INTO Prestataire(idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire, numeroPrestataire, ruePrestataire, codepostalPrestataire, villePrestataire, telephonePrestataire)
 VALUES
(005, 005, 'Prestataire5',10,5 ,1500, 'prest5@prest.com',5 ,'rue des prest', 38000,'Grenoble',5555500005);

CREATE OR REPLACE TRIGGER contrainte_11
BEFORE insert on Prestataire
For each row
DECLARE 
	VidPrestataire integer;
BEGIN
	select distinct idPrestataire into VidPrestataire
	from Prestataire
	where :NEW.idSeminaire in (select distinct S1.idSeminaire
								from Seminaire S1 join Seminaire S2 on (S1.idSeminaire <> S2.idSeminaire)
								where S1.dateSeminaire = S2.dateSeminaire);

	if (VidPrestataire <> NULL) then
		raise_application_error(-20100, 'Deux seminaires qui se deroulent le meme jour ne peuvent pas faire appel au meme prestataire');
	end if;
END;
/





/*
-- idPrestataire different !!!
 
INSERT INTO Prestataire(idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire, numeroPrestataire, ruePrestataire, codepostalPrestataire, villePrestataire, telephonePrestataire)
 VALUES
(007, 006, 'Prestatair22',25,4 ,200, 'prest22@prest.com',22 ,'rue des prest', 38000,'Grenoble',5555500022);
*/

