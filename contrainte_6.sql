INSERT INTO Seminaire (idSeminaire,idEntreprise,theme,dateSeminaire,nbMax,prixSeminaire,typeSeminaire,repas)
 VALUES
(010, 003,  'les oranges 2', TO_DATE('2018-08-20 08:00:00', 'YYYY-MM-DD hh:mi:ss'),10,30,0 ,1);


select identreprise, idseminaire, dateseminaire from seminaire order by dateseminaire;