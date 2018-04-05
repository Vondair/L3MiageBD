package Connexionsgbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;




public class CreationSeminaireComplet {
	
	 /**
	    * CrÃ©ation de sÃ©minaire
	    *
	    * @param conn connexion Ã  la base de donnÃ©es
	    * 		idSeminaire
	    * 		idEntreprise
	    * 		nbMax
	    * 		repas
	    * 		prixSeminaire
	    * 		theme
	    * 		typeSeminaire
	    * 		dateSeminaire
	    * @throws SQLException 
	    * @throws Exception 
	    */
	
	public static void CreationSeminaire(Connection conn, int idSeminaire, int idEntreprise, int nbMax, int repas, int prixSeminaire, String theme, String typeSeminaire, String dateSeminaire ) throws SQLException {

		try {
			 // Get a statement from the connection
	        Statement stmt = conn.createStatement() ;
			
			//commande
            String s = "INSERT INTO Seminaire VALUES (" + idSeminaire + "," + idEntreprise + "," + "'" + theme + "'" + "," + "to_date('" + dateSeminaire + "' ,'yyyy-MM-dd')" + "," + nbMax + "," + prixSeminaire + "," + "'" + typeSeminaire + "'" + "," + repas + ")";
            // execution de la commande + récupératino du résultat
            int res = stmt.executeUpdate(s);
            
            
            // commentaire si tout c'est bien passé
            System.out.println(s);
            System.out.println("Insertion du séminaire, " + res + "ligne rajoutée") ;
            
            // Close the statement 
            stmt.close() ;

        }
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur de Creation séminaire!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}

	
	
	 /**
	    * CrÃ©ation d'un animateur
	    *
	    * @param conn connexion Ã  la base de donnÃ©es
	    * 		idAnimateur
	    * 		idSeminaire
	    * 		numeroAnimateur
	    * 		nomAnimateur
	    * 		prenomAnimateur
	    * 		mailAnimateur
	    * 		rueAnimateur
	    * 		codePostalAnimateur
	    * 		villeAnimateur
	    * 		telephoneAnimateur
	    * @throws SQLException 
	    * @throws Exception 
	    */


	
	public static void CreationAnimateur(Connection conn, int idAnimateur, int idSeminaire, int numeroAnimateur, String nomAnimateur, String prenomAnimateur, String mailAnimateur, String rueAnimateur , int codePostalAnimateur, String villeAnimateur, int telephoneAnimateur) throws SQLException {
		
		
		try {
			 // Get a statement from the connection
	        Statement stmt = conn.createStatement() ;
			
			//commande
	         String s = "INSERT INTO Animateur VALUES (" + idAnimateur + "," + idSeminaire + "," + "'" + nomAnimateur + "'" + "," + "'" + prenomAnimateur + "'" + "," + "'" + mailAnimateur + "'" + "," + numeroAnimateur + "," + "'" + rueAnimateur + "'" + "," + codePostalAnimateur + "," + "'" + villeAnimateur + "'" + "," + telephoneAnimateur + ")";
	         // execution de la commande + récupératino du résultat
	         int res = stmt.executeUpdate(s);
	         
	         
	         // commentaire si tout c'est bien passé
	         System.out.println(s);
	         System.out.println("Insertion de l'animateur, " + res + "ligne rajoutée") ;
	         
	         // Close the statement 
	         stmt.close() ;
	
	     }
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur de Creation Animateur!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}

	
	
	/**
	    * CrÃ©ation d'une activité
	    *
	    * @param conn connexion Ã  la base de donnÃ©es
	    * 		idActivite
	    * 		idSeminaire
	    * 		nomActivite
	    * @throws SQLException 
	    * @throws Exception 
	    */


	
	public static void CreationActivite(Connection conn, int idActivite, int idSeminaire, String nomActivite) throws SQLException {
		
		try {
			 // Get a statement from the connection
	        Statement stmt = conn.createStatement() ;
			
			//commande
		    String s = "INSERT INTO Activite VALUES (" + idActivite + "," + idSeminaire + "," + "'" + nomActivite + "'" + ")";
		    // execution de la commande + récupératino du résultat
		    int res = stmt.executeUpdate(s);
		      
		      
		    // commentaire si tout c'est bien passé
		    System.out.println(s);
		    System.out.println("Insertion de l'activité, " + res + "ligne rajoutée") ;
		      
		    // Close the statement 
		    stmt.close() ;
		
		  }
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur de Creation Activité!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}
	
	
	
	
	 /**
	    * CrÃ©ation d'un conferencier
	    *
	    * @param conn connexion Ã  la base de donnÃ©es
	    * 		idConferencier 
	    * 		idActivite 
	    * 		nomConferencier 
	    * 		prenomConferencier 
	    * 		mailConferencier 
	    * 		numeroConferencier 
	    * 		rueConferencier 
	    * 		codePostalConferencier 
	    * 		villeConferencier 
	    * 		telephoneConferencier 
	    * 		titreConference 
	    * 		dateRemiseTransparents 
	    * @throws SQLException 
	    * @throws Exception 
	    */


	
	public static void CreationConferencier(Connection conn, int idConferencier, int idActivite , int numeroConferencier, String nomConferencier, String prenomConferencier, String mailConferencier, String rueConferencier , int codePostalConferencier, String villeConferencier, int telephoneConferencier, String titreConference, String dateRemiseTransparents) throws SQLException {
		
		try {
			
			 // Get a statement from the connection
			Statement stmt = conn.createStatement() ;
			
			//commande
			String s = "INSERT INTO Conferencier VALUES (" + idConferencier + "," + idActivite + "," + "'" + nomConferencier + "'" + "," + "'" + prenomConferencier + "'" + "," + "'" + mailConferencier + "'" + "," + numeroConferencier + "," + "'" + rueConferencier + "'" + "," + codePostalConferencier + "," + "'" + villeConferencier + "'" + "," + telephoneConferencier + "," + "'" + titreConference + "'" + "," + "'" + dateRemiseTransparents + "'" + ")";
			// execution de la commande + récupératino du résultat
			int res = stmt.executeUpdate(s);
	      
	      
			// commentaire si tout c'est bien passé
			System.out.println(s);
			System.out.println("Insertion d'un conferencier, " + res + "ligne rajoutée") ;
	      
			// Close the statement 
			stmt.close() ;
	
		}
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur de Creation Conferencier!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}
	
	
	
	 /**
	    * CrÃ©ation d'un prestataire
	    *
	    * @param conn connexion Ã  la base de donnÃ©es
	    * 		idPrestataire 
	    * 		idSeminaire 
	    * 		nomPrestataire  
	    * 		prixRepas  
	    * 		prixPause  
	    * 		prixSalle 
	    * 		numeroPrestataire 
	    * 		ruePrestataire
	    * 		codePostalPrestataire
	    * 		villePrestataire
	    * 		telephonePrestataire
	    * @throws SQLException 
	    * @throws Exception 
	    */


	
	public static void CreationPrestataire(Connection conn, int idPrestataire, int idSeminaire, String nomPrestataire, int prixRepas, int prixPause, int prixSalle, String mailPrestataire , int numeroPrestataire, String ruePrestataire, int codePostalPrestataire, String villePrestataire, int telephonePrestataire) throws SQLException {
		
		
		try {
			 // Get a statement from the connection
			Statement stmt = conn.createStatement() ;
			
			
			//commande
			String s = "INSERT INTO Prestataire VALUES (" + idPrestataire + "," + idSeminaire + "," + "'" + nomPrestataire + "'" + "," + prixRepas + "," + prixPause + "," + prixSalle + "," + "'" + mailPrestataire + "'" + "," + numeroPrestataire + "," +  "'" + ruePrestataire + "'" + "," + codePostalPrestataire + "," + "'" + villePrestataire + "'" + "," + telephonePrestataire + "," + ")";
			// execution de la commande + récupératino du résultat
			int res = stmt.executeUpdate(s);
	      
	      
			// commentaire si tout c'est bien passé
			System.out.println(s);
			System.out.println("Insertion d'un prestataire, " + res + "ligne rajoutée") ;
	      
			// Close the statement 
			stmt.close() ;
	
		}
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur de Creation Prestataire!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}
	
	
	
	
	  public static void recettesSeminaire (Connection conn, int idSeminaire) throws SQLException {
	        	        
	        System.out.print("Reference du Seminaire portant l'id : " + idSeminaire);
	        
	        try {
		        // Get a statement max from the connection
				Statement stmtMax = conn.createStatement() ;
				
				 // Get a statement min from the connection
				Statement stmtMin = conn.createStatement() ;	
	        	
				// Récupération des résultats des requetes
	            ResultSet resMax = stmtMax.executeQuery("SELECT prixSeminaire*nbMax as recetteMax  FROM Seminaire WHERE idSeminaire = "+idSeminaire);
	            ResultSet resMin = stmtMin.executeQuery("SELECT prixSeminaire*(nbMax/2) as recetteMin  FROM Seminaire WHERE idSeminaire = "+idSeminaire);

	            // On récupère les résultats qui sont en 1ère position
		        while (resMax.next()) {
		        	System.out.println("Recettes maximum : " + resMax.getInt(1));
		        }
		        while (resMin.next()) {
		            System.out.println("Recettes minimum : " + resMin.getInt(1));
		        }
		        
		        // Close the statement and resultset
				stmtMax.close() ;
				stmtMin.close() ;
				resMax.close() ;
				resMin.close() ;
	            
	        }
	        
	        catch(SQLException e){
				// commentaire si erreur
		        System.out.println("Erreur recette Seminaire") ;
		        SQLWarningsExceptions.printExceptions(e) ;
		        System.exit(1) ;
			} 
	        
	 }



	  public static void depensesSeminaire (Connection conn, int idSeminaire) throws SQLException {
	        
	        System.out.print("Depenses du Seminaire portant l'id : " + idSeminaire);
	        
	        try {
		        // Get a statement max from the connection
				Statement stmtMax = conn.createStatement() ;
				
				 // Get a statement min from the connection
				Statement stmtMin = conn.createStatement() ;	
	        	
				// Récupération des dépenses des requetes
	            ResultSet resMax = stmtMax.executeQuery("SELECT (prixSalle + prixRepas * nbRepas * nbMax + prixPause * nbPause * nbMax)  as depenseMax  FROM Seminaire WHERE idSeminaire = "+idSeminaire + "and nbRepas = (SELECT repas from Seminaire WHERE idSeminaire = "+idSeminaire + ") and nbPause = (...");
	            ResultSet resMin = stmtMin.executeQuery("SELECT ...");

	            // On récupère les résultats qui sont en 1ère position
		        while (resMax.next()) {
		        	System.out.println("Depenses maximum : " + resMax.getInt(1));
		        }
		        while (resMin.next()) {
		            System.out.println("Depenses minimum : " + resMin.getInt(1));
		        }
		        
		        // Close the statement and resultset
				stmtMax.close() ;
				stmtMin.close() ;
				resMax.close() ;
				resMin.close() ;
	            
	        }
	        
	        catch(SQLException e){
				// commentaire si erreur
		        System.out.println("Erreur Depenses Seminaire") ;
		        SQLWarningsExceptions.printExceptions(e) ;
		        System.exit(1) ;
			}
	        
	        
	        
	 }
	
	
	
	
	 /**
	    * Demande d'information sur un sÃ©minaire
	    *
	    * @param conn connexion Ã  la base de donnÃ©es
	    * @throws Exception 
	    */
	
	public static void InfoSeminaire(Connection conn) throws Exception {
	    // Scanner pour rÃ©cupÃ©rer les valeurs
		 Scanner sc = new Scanner (System.in);

		/*Seminaire*/
		// Attributs de la table
		int idSeminaire, idEntreprise, nbMax, repas, prixSeminaire ;
		String theme, typeSeminaire, dateSeminaire ;

		
		// RÃ©cupÃ©ration de l'id du SÃ©minaire
		System.out.print("Veuillez rentrer l'id du sÃ©minaire : ");
	    idSeminaire = Integer.parseInt(sc.nextLine());
	    
		while(idSeminaire <= 0){
            System.out.println("Veuillez rentrer un idSeminaire valide (>0) : ");
            idSeminaire = Integer.parseInt(sc.nextLine());
		}
    
		// RÃ©cupÃ©ration de l'id de l'entreprise
		System.out.print("Veuillez rentrer l'id de l'entreprise : ");
		idEntreprise = Integer.parseInt(sc.nextLine());
		
		while(idEntreprise <= 0){
            System.out.println("Veuillez rentrer un idEntreprise valide (>0) : ");
            idEntreprise = Integer.parseInt(sc.nextLine());
		}
	    
		// RÃ©cupÃ©ration du nombre max de participants
		System.out.print("Veuillez rentrer le nombre max de participants : ");
	    nbMax = Integer.parseInt(sc.nextLine());
	    
		// RÃ©cupÃ©ration du prix du sÃ©minaire
		System.out.print("Veuillez rentrer le prix du sÃ©minaire : ");
		prixSeminaire = Integer.parseInt(sc.nextLine());
	    
		// RÃ©cupÃ©ration de theme du SÃ©minaire
		System.out.print("Veuillez rentrer le theme du SÃ©minaire : ");
		theme = sc.nextLine();
	    
		// RÃ©cupÃ©ration du type de Seminaire (journÃ©e, demi_journÃ©e)
		System.out.print("Veuillez rentrer le typeSeminaire (journee / demi_journee) : ");
		typeSeminaire = sc.nextLine();
		
		while(!"journee".equalsIgnoreCase(typeSeminaire) && !"demi_journee".equalsIgnoreCase(typeSeminaire)){
	            System.out.println("Veuillez rentrer un typeSeminaire valide (journee / demi_journee) : ");
	            typeSeminaire = sc.nextLine();
	    }

		
		// RÃ©cupÃ©ration de la date du SÃ©minaire
		System.out.print("Veuillez rentrer la date du SÃ©minaire au format yyyy-MM-dd : ");
		dateSeminaire = sc.nextLine();
	    
		// Tester si une date est valide autant au niveau du format qu'au niveau du calendrier
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		java.util.Date dateV = null;
		try {
            dateV = sdf.parse(dateSeminaire);
        } catch (ParseException e) {
            e.printStackTrace();
        }

		
		// RÃ©cupÃ©ration d'un repas possible
		System.out.print("Veuillez indiquer s'il y a un repas ou non (oui:1, non:0) : ");
		repas = Integer.parseInt(sc.nextLine());
		
		while((repas != 0) && (repas != 1)){
            System.out.println("Veuillez rentrer une volonte de repas valide (oui:1, non:0) : ");
            repas = Integer.parseInt(sc.nextLine());
		}
		
		
		// Création du Séminaire
		CreationSeminaire(conn, idSeminaire, idEntreprise, nbMax, repas, prixSeminaire, theme, typeSeminaire, dateSeminaire);
		
		
		
		
		
		
		
		/*Animateur*/
		int idAnimateur, numeroAnimateur, codePostalAnimateur, telephoneAnimateur  ;
		String nomAnimateur, prenomAnimateur, mailAnimateur, rueAnimateur, villeAnimateur ;

		
		// RÃ©cupÃ©ration de l'id de l'Animateur
		System.out.print("Veuillez rentrer l'id de l'Animateur : ");
		idAnimateur = Integer.parseInt(sc.nextLine());
	    
		while(idAnimateur <= 0){
            System.out.println("Veuillez rentrer un idAnimateur valide (>0) : ");
            idAnimateur = Integer.parseInt(sc.nextLine());
		}
    
		// RÃ©cupÃ©ration du nom de l'animateur
		System.out.print("Veuillez rentrer le nom de l'animateur : ");
		nomAnimateur = sc.nextLine();
		
		// RÃ©cupÃ©ration du prenom de l'animateur
		System.out.print("Veuillez rentrer le prenom de l'animateur : ");
		prenomAnimateur = sc.nextLine();
		
		// RÃ©cupÃ©ration du mail de l'animateur
		System.out.print("Veuillez rentrer le mail de l'animateur : ");
		mailAnimateur = sc.nextLine();
		
		// RÃ©cupÃ©ration du numero de rue de l'Animateur
		System.out.print("Veuillez rentrer le numero de rue de l'Animateur : ");
		numeroAnimateur = Integer.parseInt(sc.nextLine());
	    
		while(numeroAnimateur <= 0){
            System.out.println("Veuillez rentrer un numeroAnimateur valide (>0) : ");
            numeroAnimateur = Integer.parseInt(sc.nextLine());
		}
		
		// RÃ©cupÃ©ration de la rue de l'animateur
		System.out.print("Veuillez rentrer la rue de l'animateur : ");
		rueAnimateur = sc.nextLine();
		
		// RÃ©cupÃ©ration du code postal de l'animateur
		System.out.print("Veuillez rentrer le code postal de l'animateur : ");
		codePostalAnimateur = Integer.parseInt(sc.nextLine());
		
		// RÃ©cupÃ©ration de la ville de l'animateur
		System.out.print("Veuillez rentrer la ville de l'animateur : ");
		villeAnimateur = sc.nextLine();
		
		// RÃ©cupÃ©ration du telephone de l'animateur
		System.out.print("Veuillez rentrer le téléphone de l'animateur : ");
		telephoneAnimateur = Integer.parseInt(sc.nextLine());
		
		
		// Création de l'animateur
		CreationAnimateur(conn, idAnimateur, idSeminaire, numeroAnimateur, nomAnimateur, prenomAnimateur, mailAnimateur, rueAnimateur , codePostalAnimateur, villeAnimateur, telephoneAnimateur);
				
				
		
		
		
		/*Activités*/
		int idActivite  ;
		String nomActivite ;

		
		// RÃ©cupÃ©ration de l'id de l'Activité
		System.out.print("Veuillez rentrer l'id de l'Activité : ");
		idActivite = Integer.parseInt(sc.nextLine());
	    
		while(idActivite <= 0){
            System.out.println("Veuillez rentrer un idActivite valide (>0) : ");
            idActivite = Integer.parseInt(sc.nextLine());
		}
		
		// RÃ©cupÃ©ration du nom de l'activité
		System.out.print("Veuillez rentrer le nom de l'activité : ");
		nomActivite = sc.nextLine();
	
		// Création de l'activité
		CreationActivite(conn, idActivite, idSeminaire, nomActivite);
		
		
		
		
		/*Conferenciers*/
		int nbConf, idConferencier , numeroConferencier , codePostalConferencier , telephoneConferencier   ;
		String nomConferencier , prenomConferencier , mailConferencier , rueConferencier , villeConferencier, titreConference, dateRemiseTransparents    ;
		

		// Demande du nombre de conférencier a creer
		System.out.print("Veuillez indiquer le nombre de conférencier : ");
		nbConf = Integer.parseInt(sc.nextLine());
		
		while (nbConf != 0) {
			// RÃ©cupÃ©ration de l'id du Conferencier
			System.out.print("Veuillez rentrer l'id du Conferencier : ");
			idConferencier = Integer.parseInt(sc.nextLine());
		    
			while(idConferencier <= 0){
	            System.out.println("Veuillez rentrer un idConferencier valide (>0) : ");
	            idConferencier = Integer.parseInt(sc.nextLine());
			}
	    
			// RÃ©cupÃ©ration du nom du Conferencier
			System.out.print("Veuillez rentrer le nom du Conferencier : ");
			nomConferencier = sc.nextLine();
			
			// RÃ©cupÃ©ration du prenom du Conferencier
			System.out.print("Veuillez rentrer le prenom du Conferencier : ");
			prenomConferencier = sc.nextLine();
			
			// RÃ©cupÃ©ration du mail du Conferencier
			System.out.print("Veuillez rentrer le mail du Conferencier : ");
			mailConferencier = sc.nextLine();
			
			// RÃ©cupÃ©ration du numero de rue du Conferencier
			System.out.print("Veuillez rentrer le numero de rue du Conferencier : ");
			numeroConferencier = Integer.parseInt(sc.nextLine());
		    
			while(numeroConferencier <= 0){
	            System.out.println("Veuillez rentrer un numeroConferencier valide (>0) : ");
	            numeroConferencier = Integer.parseInt(sc.nextLine());
			}
			
			// RÃ©cupÃ©ration de la rue du Conferencier
			System.out.print("Veuillez rentrer la rue du Conferencier : ");
			rueConferencier = sc.nextLine();
			
			// RÃ©cupÃ©ration du code postal du Conferencier
			System.out.print("Veuillez rentrer le code postal du Conferencier : ");
			codePostalConferencier = Integer.parseInt(sc.nextLine());
			
			// RÃ©cupÃ©ration de la ville du Conferencier
			System.out.print("Veuillez rentrer la ville du Conferencier : ");
			villeConferencier = sc.nextLine();
			
			// RÃ©cupÃ©ration du telephone du Conferencier
			System.out.print("Veuillez rentrer le téléphone du Conferencier : ");
			telephoneConferencier = Integer.parseInt(sc.nextLine());
			
			// RÃ©cupÃ©ration du titre de la conference
			System.out.print("Veuillez rentrer le titre de la conference : ");
			titreConference  = sc.nextLine();
			
			// RÃ©cupÃ©ration de la date de remise des transparents
			System.out.print("Veuillez rentrer la date de remise des transparents au format yyyy-MM-dd : ");
			dateRemiseTransparents = sc.nextLine();
		    
			// Tester si une date est valide autant au niveau du format qu'au niveau du calendrier
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			sdf2.setLenient(false);
			dateV = null;
			try {
	            dateV = sdf2.parse(dateRemiseTransparents);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			// Création du Conferencier
			CreationConferencier(conn, idConferencier, idActivite, numeroConferencier, nomConferencier, prenomConferencier, mailConferencier, rueConferencier , codePostalConferencier, villeConferencier, telephoneConferencier, titreConference, dateRemiseTransparents);
			
			nbConf-- ;
		}
		
		
		
		
		
		/*Prestataire*/
		int idPrestataire , prixRepas  , prixPause  , prixSalle, numeroPrestataire , codePostalPrestataire , telephonePrestataire  ;
		String nomPrestataire, mailPrestataire , ruePrestataire , villePrestataire ;
		

		// RÃ©cupÃ©ration de l'id du Prestataire
		System.out.print("Veuillez rentrer l'id du Prestataire : ");
		idPrestataire = Integer.parseInt(sc.nextLine());
		    
		while(idPrestataire <= 0){
            System.out.println("Veuillez rentrer un idPrestataire valide (>0) : ");
            idPrestataire = Integer.parseInt(sc.nextLine());
		}
		
		// RÃ©cupÃ©ration du prix du repas
		System.out.print("Veuillez rentrer le  prix du repas : ");
		prixRepas = Integer.parseInt(sc.nextLine());
				    
		while(prixRepas <= 0){
			System.out.println("Veuillez rentrer un prixRepas valide (>0) : ");
			prixRepas = Integer.parseInt(sc.nextLine());
		}
		
		
		// RÃ©cupÃ©ration du prix de la Pause
		System.out.print("Veuillez rentrer le  prix de la Pause : ");
		prixPause = Integer.parseInt(sc.nextLine());
				    
		while(prixPause <= 0){
			System.out.println("Veuillez rentrer un prixPause valide (>0) : ");
			prixPause = Integer.parseInt(sc.nextLine());
		}
		
		
		// RÃ©cupÃ©ration du prix de la salle
		System.out.print("Veuillez rentrer le  prix de la salle : ");
		prixSalle = Integer.parseInt(sc.nextLine());
				    
		while(prixSalle <= 0){
			System.out.println("Veuillez rentrer un prixSalle valide (>0) : ");
			prixSalle = Integer.parseInt(sc.nextLine());
		}
	    
		// RÃ©cupÃ©ration du nom du Prestataire
		System.out.print("Veuillez rentrer le nom du Prestataire : ");
		nomPrestataire = sc.nextLine();
		
		// RÃ©cupÃ©ration du mail de Prestataire
		System.out.print("Veuillez rentrer le mail de Prestataire : ");
		mailPrestataire = sc.nextLine();
		
		// RÃ©cupÃ©ration du numero de rue de Prestataire
		System.out.print("Veuillez rentrer le numero de rue de Prestataire : ");
		numeroPrestataire = Integer.parseInt(sc.nextLine());
	    
		while(numeroPrestataire <= 0){
            System.out.println("Veuillez rentrer un numeroPrestataire valide (>0) : ");
            numeroPrestataire = Integer.parseInt(sc.nextLine());
		}
		
		// RÃ©cupÃ©ration de la rue de Prestataire
		System.out.print("Veuillez rentrer la rue de Prestataire : ");
		ruePrestataire = sc.nextLine();
		
		// RÃ©cupÃ©ration du code postal de Prestataire
		System.out.print("Veuillez rentrer le code postal de Prestataire : ");
		codePostalPrestataire = Integer.parseInt(sc.nextLine());
		
		// RÃ©cupÃ©ration de la ville de Prestataire
		System.out.print("Veuillez rentrer la ville de Prestataire : ");
		villePrestataire = sc.nextLine();
		
		// RÃ©cupÃ©ration du telephone de Prestataire
		System.out.print("Veuillez rentrer le téléphone de Prestataire : ");
		telephonePrestataire = Integer.parseInt(sc.nextLine());
		
		
		
		
		// Création du Prestataire
		CreationPrestataire(conn, idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire , numeroPrestataire, ruePrestataire, codePostalPrestataire, villePrestataire, telephonePrestataire);
		
		
		
		// Total des recettes prévues (min, max)
		recettesSeminaire(conn, idSeminaire);
		
		// Total des depenses prévues (min, max)
		depensesSeminaire(conn, idSeminaire);
		
		
	}

	

}
