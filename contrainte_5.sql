drop trigger contrainte_8;

INSERT INTO Participant (idParticipant,idSeminaire, dateInscription, nomParticipant, prenomParticipant,mailParticipant, numeroParticipant, rueParticipant, codePostalParticipant, villeParticipant,telephoneParticipant,estEnAttente)
 VALUES
(067, 016, to_date('2018-07-20 10:00:00','yyyy-mm-dd hh:mi:ss'), 'nom60','prenom60' , 'prenom60nom60@participant.com',  60, 'rue des participants', 38000, 'Grenoble', '2222222060',1);

delete from participant where idparticipant = 62 ;

select estenattente from participant where idparticipant =67 ;

CREATE OR REPLACE TRIGGER contrainte_8
BEFORE insert on Participant
For each row
DECLARE
	VdateSeminaire date;
BEGIN
	select S.dateSeminaire into VdateSeminaire
	from Participant P join Seminaire S on (P.idSeminaire = S.idSeminaire)
	where P.idSeminaire = :NEW.idSeminaire;

	if (sysdate > VdateSeminaire - 7) then
		raise_application_error(-20100, 'Le nombre de participants pour un seminaire est fixe une semaine avant la date de debut de ce dernier');
	end if;

END;
/