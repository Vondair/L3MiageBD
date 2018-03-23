-- Création de la table des entreprises
create table Entreprise 

-- Création de la table des séminaires
create table Seminaire (
	idSeminaire integer NOT NULL,
	idEntreprise integer NOT NULL,
	theme varchar(30) NOT NULL,
	dateSeminaire date NOT NULL,
	nbMax integer NOT NULL,
	prixSeminaire double NOT NULL,
	typeSeminaire varchar NOT NULL, 
	repas boolean NOT NULL,
	FOREIGN KEY idEntreprise REFERENCES Entreprise(idEntreprise),
	PRIMARY KEY (idSeminaire, idEntreprise),
	CONSTRAINT idSeminaireSup0Seminaire CHECK (idSeminaire > 0),
	CONSTRAINT idEntrepriseSup0Seminaire CHECK (idEntreprise > 0),
	CONSTRAINT checkTypeSeminaire CHECK (typeSeminaire BETWEEN 'journee' AND 'demi_journee')
);

-- Création de la table des activités
create table Activite (
	idActivite integer NOT NULL,
	idSeminaire integer NOT NULL,
	nomActivite varchar(30) NOT NULL,
	FOREIGN KEY idSeminaire REFERENCES Seminaire(idSeminaire),
	PRIMARY KEY (idActivite, idSeminaire),
	CONSTRAINT idActiviteSup0Activite CHECK (idActivite > 0),
	CONSTRAINT idSeminaireSup0Activite CHECK (idSeminaire > 0)
);

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
	estEnAttente boolean NOT NULL,
	FOREIGN KEY(idSeminaire) REFERENCES Seminaire(idSeminaire),
	PRIMARY KEY (idParticipant, idSeminaire),
	CONSTRAINT idParticipantSup0 CHECK (idParticipant > 0),
	CONSTRAINT idSeminaireSup0Participant CHECK (idSeminaire > 0),
	CONSTRAINT checkCPParticipant CHECK (codePostalParticipant like '[0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT checkTelParticipant CHECK (telephoneParticipant like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- Création de la table des conférenciers
create table Conferencier (
	idConferencier integer NOT NULL,
	idActivite integer NOT NULL,
	nomConferencier varchar(20) NOT NULL,
	prenomConferencier varchar(20) NOT NULL,
	mailConferencier varchar(50) NOT NULL,
	numeroConferencier varchar(10),
	rueConferencier varchar(50),
	codePostalConferencier char(5),
	villeConferencier varchar(20),
	telephoneConferencier char(10),
	titreConference varchar(30) NOT NULL,
	dateRemiseTransparents date NOT NULL,
	FOREIGN KEY idActivite REFERENCES Activite(idActivite),
	PRIMARY KEY (idConferencier, idActivite),
	CONSTRAINT idConferencierSup0 CHECK (idConferencier > 0),
	CONSTRAINT idActiviteSup0Conferencier CHECK (idActivite > 0),
	CONSTRAINT checkCPConferencier CHECK (codePostalConferencier like '[0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT checkTelConferencier CHECK (telephoneConferencier like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- Création de la table des animateurs
create table Animateur (
	idAnimateur integer NOT NULL,
	idSeminaire integer NOT NULL,
	nomAnimateur varchar(20) NOT NULL,
	prenomAnimateur varchar(20) NOT NULL,
	mailAnimateur varchar(50) NOT NULL,
	numeroAnimateur varchar(10),
	rueAnimateur varchar(50),
	codePostalAnimateur char(5),
	villeAnimateur varchar(20),
	telephoneAnimateur char(10),
	FOREIGN KEY(idSeminaire) REFERENCES Seminaire(idSeminaire),
	PRIMARY KEY (idAnimateur, idSeminaire),
	CONSTRAINT idAnimateurSup0 CHECK (idAnimateur > 0),
	CONSTRAINT idSeminaireSup0Animateur CHECK (idSeminaire > 0),
	CONSTRAINT checkCPAnimateur CHECK (codePostalAnimateur like '[0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT checkTelAnimateur CHECK (telephoneAnimateur like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);