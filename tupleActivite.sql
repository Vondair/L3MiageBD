-- ACTIVITE
--000 idseminaire =0
--022  seminaire 006 n’existe pas
--023  nomactivite trop long

INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(100, 001, 'chat');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(001, 001, 'numérique et lecture');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(002, 001, 'numerique et sport');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(003, 001, 'lEcole du numerique');  
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(004, 002, 'digitalisation et futur');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(005, 002, 'Science de demain');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(006, 002, 'Le digital:un univers');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(013, 003, 'reseaux et sante');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(014, 003, 'stratégies et web');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(015, 003, 'medias sociaux');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(007, 005, 'datalake et Big Data');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(008, 005, 'le Big Data');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(009, 005, 'talents du Big Data');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(010, 005, 'Big Data et lEnergie');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(011, 005, 'enjeux du Big Data');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(012, 005, 'Big Data et Science');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(016, 004, 'la Securité');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(017, 004, 'la cryptoanalyse.');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(018, 004, 'La cybersecurite');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(019, 004, 'algorithmes quantique');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(020, 004, 'cryptographie quantique?');
INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
 VALUES
(021, 004, 'Cryptomachine à chiffrer?');

-- idseminaire 6 inexistant
--INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
-- VALUES
--(022, 006, 'chien');

-- nom trop long
--INSERT INTO Activite (idActivite,idSeminaire,nomActivite)
-- VALUES
--(023, 006, 'c lhistoire de tux un pingouin qui ..');
