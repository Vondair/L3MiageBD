-- SEMINAIRE

-- demi-journee = 0 et journee = 1

INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(001, 001, 'le numerique', TO_DATE('2018-08-01 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30 ,0,0);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(002, 001, 'le digitale', TO_DATE('2018-08-05 08:00:00', 'YYYY-MM-DD hh:mi:ss'), 10,30, 0,0);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(003, 001,  'les reseaux sociaux', TO_DATE('2018-08-20 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30,0 ,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(014, 001,  'les oranges', TO_DATE('2018-08-20 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30,0 ,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(004, 001, 'la cryptologie', TO_DATE('2018-08-11 08:00:00', 'YYYY-MM-DD hh:mi:ss'), 10,50,1,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(005, 001,'le big data', TO_DATE('2018-12-01 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10, 50, 1 ,1);


-- date qui ne convient pas 
--INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 --VALUES
--(006, 001,  'les patates', TO_DATE('2018-08-46 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30,0 ,1);


INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(007, 001, 'le numerique 2', TO_DATE('2018-08-01 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30 ,0,0);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(008, 002, 'le digitale 2', TO_DATE('2018-08-05 08:00:00', 'YYYY-MM-DD hh:mi:ss'), 10,30, 0,0);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(009, 003,  'les reseaux sociaux 2', TO_DATE('2018-08-20 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30,0 ,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(011, 002, 'la cryptologie 2', TO_DATE('2018-08-11 08:00:00', 'YYYY-MM-DD hh:mi:ss'), 10,50,1,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(012, 005,'le big data 2', TO_DATE('2018-12-01 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10, 50, 1 ,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(0013, 003,  'les patates 2', TO_DATE('2018-08-26 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30,0 ,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(0015, 003,  'orages', TO_DATE('2018-04-15 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30,0 ,1);
INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(016, 003,  'orages', TO_DATE('2018-09-15 08:00:00', 'YYYY-MM-DD hh:mi:ss'),1,30,0 ,1);
