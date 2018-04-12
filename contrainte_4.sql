-- nbmax de participants

INSERT INTO Participant (idParticipant,idSeminaire, dateInscription, nomParticipant, prenomParticipant,mailParticipant, numeroParticipant, rueParticipant, codePostalParticipant, villeParticipant,telephoneParticipant,estEnAttente)
 VALUES
(062, 016, to_date('2018-07-20 10:00:00','yyyy-mm-dd hh:mi:ss'), 'nom60','prenom60' , 'prenom60nom60@participant.com',  60, 'rue des participants', 38000, 'Grenoble', '2222222060',0);
