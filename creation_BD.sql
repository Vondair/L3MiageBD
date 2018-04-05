-- Création de la table des entreprises
create table Entreprise (
        idEntreprise integer NOT NULL,
        nomEntreprise varchar(20),
        numeroEntreprise integer,
        rueEntreprise varchar(50),
        codePostalEntreprise varchar(5),
        villeEntreprise varchar(20),
        telephoneEntreprise varchar(10),
        mailEntreprise varchar(50),
        PRIMARY KEY (idEntreprise),
        CONSTRAINT idEntrepriseSup0Entreprise CHECK (idEntreprise > 0),
        CONSTRAINT checkCPEntreprise CHECK (codePostalEntreprise like '[0-9][0-9][0-9][0-9][0-9]'),
        CONSTRAINT checkTelEntreprise CHECK (telephoneEntreprise like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- La relation Entreprise est en 3FN car : 
--         - Les données sont atomiques d'où la 1FN.
--        - La relation est en 1FN et les attributs non-clé dépendent de toute la clé d'où la 2FN
--        - La relation est en 2FN et  les attributs non clés ne dépendent pas d'autres attributs non clé d'où la 3FN

-- Création de la table des séminaires
create table Seminaire (
        idSeminaire integer NOT NULL,
        idEntreprise integer NOT NULL,
        theme varchar(30) NOT NULL,
        dateSeminaire date NOT NULL,
        nbMax integer NOT NULL,
        prixSeminaire float NOT NULL,
        typeSeminaire varchar (12) NOT NULL, 
        repas number(1) NOT NULL,
        PRIMARY KEY (idSeminaire),
        FOREIGN KEY (idEntreprise) REFERENCES Entreprise(idEntreprise),
        CONSTRAINT idSeminaireSup0Seminaire CHECK (idSeminaire > 0),
		CONSTRAINT checkrepas CHECK (repas IN (1,0)),
        CONSTRAINT idEntrepriseSup0Seminaire CHECK (idEntreprise > 0),
        CONSTRAINT checkTypeSeminaire CHECK (typeSeminaire IN ('journee', 'demi_journee')),
        CONSTRAINT prixSeminaireSup0 CHECK (prixSeminaire > 0),
        CONSTRAINT nbMaxSup0 CHECK (nbMax > 0)
);

-- La relation Seminaire est en 3FN  car : 
--         - Les données sont atomiques d'où la 1FN.
--        - La relation est en 1FN et les attributs non-clé dépendent de toute la clé d'où la 2FN
--        - La relation est en 2FN et  les attributs non clés ne dépendent pas d'autres attributs non clé d'où la 3FN

-- Création de la table des prestataires
create table Prestataire (
        idPrestataire integer NOT NULL,
        idSeminaire integer NOT NULL,
        nomPrestataire varchar(20),
        prixRepas integer NOT NULL,
        prixPause integer NOT NULL,
        prixSalle integer NOT NULL,
        PRIMARY KEY (idPrestataire),
		FOREIGN KEY (idSeminaire) REFERENCES Seminaire(idSeminaire),
        CONSTRAINT idPrestataireSup0 CHECK (idPrestataire > 0),
        CONSTRAINT idSeminaireSup0Prestataire CHECK (idSeminaire > 0),
        CONSTRAINT prixRepasSup0 CHECK (prixRepas > 0),
        CONSTRAINT prixPauseSup0 CHECK (prixPause > 0),
        CONSTRAINT prixSalleSup0 CHECK (prixSalle > 0)
);

-- La relation Prestataire est en 3FN  car : 
--         - Les données sont atomiques d'où la 1FN.
--        - La relation est en 1FN et les attributs non-clé dépendent de toute la clé d'où la 2FN
--        - La relation est en 2FN et  les attributs non clés ne dépendent pas d'autres attributs non clé d'où la 3FN

-- Création de la table des activités
create table Activite (
        idActivite integer NOT NULL,
        idSeminaire integer NOT NULL,
        nomActivite varchar(30) NOT NULL,
        PRIMARY KEY (idActivite),
        FOREIGN KEY (idSeminaire) REFERENCES Seminaire(idSeminaire),
        CONSTRAINT idActiviteSup0Activite CHECK (idActivite > 0),
        CONSTRAINT idSeminaireSup0Activite CHECK (idSeminaire > 0)
);

-- La relation Activite est en 3FN  car : 
--         - Les données sont atomiques d'où la 1FN.
--        - La relation est en 1FN et les attributs non-clé dépendent de toute la clé d'où la 2FN
--        - La relation est en 2FN et  les attributs non clés ne dépendent pas d'autres attributs non clé d'où la 3FN

-- Création de la table des participants
create table Participant ( 
        idParticipant integer NOT NULL,
        idSeminaire integer NOT NULL,
        dateInscription date NOT NULL,
        nomParticipant varchar(20) NOT NULL,
        prenomParticipant varchar(20) NOT NULL,
        mailParticipant varchar(50) NOT NULL,
        numeroParticipant integer,
        rueParticipant varchar(50),
        codePostalParticipant char(5),
        villeParticipant varchar(20),
        telephoneParticipant char(10),
        estEnAttente number(1) NOT NULL,
        PRIMARY KEY (idParticipant),
        FOREIGN KEY(idSeminaire) REFERENCES Seminaire(idSeminaire),
		CONSTRAINT checkestEnAttente CHECK (estEnAttente IN (1,0)),        
		CONSTRAINT idParticipantSup0 CHECK (idParticipant > 0),
        CONSTRAINT idSeminaireSup0Participant CHECK (idSeminaire > 0),
        CONSTRAINT checkCPParticipant CHECK (codePostalParticipant like '[0-9][0-9][0-9][0-9][0-9]'),
        CONSTRAINT checkTelParticipant CHECK (telephoneParticipant like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- La relation Participant est en 3FN  car : 
--         - Les données sont atomiques d'où la 1FN.
--        - La relation est en 1FN et les attributs non-clé dépendent de toute la clé d'où la 2FN
--        - La relation est en 2FN et  les attributs non clés ne dépendent pas d'autres attributs non clé d'où la 3FN

-- Création de la table des conférenciers
create table Conferencier (
        idConferencier integer NOT NULL,
        idActivite integer NOT NULL,
        nomConferencier varchar(20) NOT NULL,
        prenomConferencier varchar(20) NOT NULL,
        mailConferencier varchar(50) NOT NULL,
        numeroConferencier integer,
        rueConferencier varchar(50),
        codePostalConferencier char(5),
        villeConferencier varchar(20),
        telephoneConferencier char(10),
        titreConference varchar(30) NOT NULL,
        dateRemiseTransparents date NOT NULL,
        PRIMARY KEY (idConferencier),
        FOREIGN KEY (idActivite) REFERENCES Activite(idActivite),
        CONSTRAINT idConferencierSup0 CHECK (idConferencier > 0),
        CONSTRAINT idActiviteSup0Conferencier CHECK (idActivite > 0),
        CONSTRAINT checkCPConferencier CHECK (codePostalConferencier like '[0-9][0-9][0-9][0-9][0-9]'),
        CONSTRAINT checkTelConferencier CHECK (telephoneConferencier like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- La relation Conferencier est en 3FN  car : 
--         - Les données sont atomiques d'où la 1FN.
--        - La relation est en 1FN et les attributs non-clé dépendent de toute la clé d'où la 2FN
--        - La relation est en 2FN et  les attributs non clés ne dépendent pas d'autres attributs non clé d'où la 3FN

-- Création de la table des animateurs
create table Animateur (
        idAnimateur integer NOT NULL,
        idSeminaire integer NOT NULL,
        nomAnimateur varchar(20) NOT NULL,
        prenomAnimateur varchar(20) NOT NULL,
        mailAnimateur varchar(50) NOT NULL,
        numeroAnimateur integer,
        rueAnimateur varchar(50),
        codePostalAnimateur char(5),
        villeAnimateur varchar(20),
        telephoneAnimateur char(10),
        PRIMARY KEY (idAnimateur),
        FOREIGN KEY(idSeminaire) REFERENCES Seminaire(idSeminaire),
        CONSTRAINT idAnimateurSup0 CHECK (idAnimateur > 0),
        CONSTRAINT idSeminaireSup0Animateur CHECK (idSeminaire > 0),
        CONSTRAINT checkCPAnimateur CHECK (codePostalAnimateur like '[0-9][0-9][0-9][0-9][0-9]'),
        CONSTRAINT checkTelAnimateur CHECK (telephoneAnimateur like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- La relation Animateur est en 3FN  car : 
--         - Les données sont atomiques d'où la 1FN.
--        - La relation est en 1FN et les attributs non-clé dépendent de toute la clé d'où la 2FN
--        - La relation est en 2FN et  les attributs non clés ne dépendent pas d'autres attributs non clé d'où la 3FN



