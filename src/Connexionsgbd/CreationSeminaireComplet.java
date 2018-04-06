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
	    * Création de séminaire
	    *
	    * @param conn connexion à la base de données
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
	
	public static void CreationSeminaire(Connection conn, int idSeminaire, int idEntreprise, int nbMax, int repas, int prixSeminaire, String theme, int typeSeminaire, String dateSeminaire ) throws SQLException {

		try {
			 // Get a statement from the connection
	        Statement stmt = conn.createStatement() ;
			
			//commande
            String s = "INSERT INTO Seminaire VALUES (" + idSeminaire + "," + idEntreprise + "," + "'" + theme + "'" + "," + "to_date('" + dateSeminaire + "' ,'yyyy-MM-dd hh:mi:ss')" + "," + nbMax + "," + prixSeminaire + "," + typeSeminaire + "," + repas + ")";
            // execution de la commande + r�cup�ratino du r�sultat
            int res = stmt.executeUpdate(s);
            
            
            // commentaire si tout c'est bien pass�
            System.out.println(s);
            System.out.println("Insertion du s�minaire, " + res + "ligne rajout�e") ;
            
            // Close the statement 
            stmt.close() ;

        }
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur de Creation s�minaire!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}

	
	
	 /**
	    * Création d'un animateur
	    *
	    * @param conn connexion à la base de données
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
	         // execution de la commande + r�cup�ratino du r�sultat
	         int res = stmt.executeUpdate(s);
	         
	         
	         // commentaire si tout c'est bien pass�
	         System.out.println(s);
	         System.out.println("Insertion de l'animateur, " + res + "ligne rajout�e") ;
	         
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
	    * Création d'une activit�
	    *
	    * @param conn connexion à la base de données
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
		    // execution de la commande + r�cup�ratino du r�sultat
		    int res = stmt.executeUpdate(s);
		      
		      
		    // commentaire si tout c'est bien pass�
		    System.out.println(s);
		    System.out.println("Insertion de l'activit�, " + res + "ligne rajout�e") ;
		      
		    // Close the statement 
		    stmt.close() ;
		
		  }
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur de Creation Activit�!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}
	
	
	
	
	 /**
	    * Création d'un conferencier
	    *
	    * @param conn connexion à la base de données
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
			String s = "INSERT INTO Conferencier VALUES (" + idConferencier + "," + idActivite + "," + "'" + nomConferencier + "'" + "," + "'" + prenomConferencier + "'" + "," + "'" + mailConferencier + "'" + "," + numeroConferencier + "," + "'" + rueConferencier + "'" + "," + codePostalConferencier + "," + "'" + villeConferencier + "'" + "," + telephoneConferencier + "," + "'" + titreConference + "'" + "," + "to_date('" + dateRemiseTransparents + "' ,'yyyy-MM-dd hh:mi:ss')" + ")";
			// execution de la commande + r�cup�ratino du r�sultat
			int res = stmt.executeUpdate(s);
	      
	      
			// commentaire si tout c'est bien pass�
			System.out.println(s);
			System.out.println("Insertion d'un conferencier, " + res + "ligne rajout�e") ;
	      
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
	    * Création d'un prestataire
	    *
	    * @param conn connexion à la base de données
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
			// execution de la commande + r�cup�ratino du r�sultat
			int res = stmt.executeUpdate(s);
	      
	      
			// commentaire si tout c'est bien pass�
			System.out.println(s);
			System.out.println("Insertion d'un prestataire, " + res + "ligne rajout�e") ;
	      
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
	        	
				// R�cup�ration des r�sultats des requetes
	            ResultSet resMax = stmtMax.executeQuery("SELECT prixSeminaire*nbMax as recetteMax  FROM Seminaire WHERE idSeminaire = "+idSeminaire);
	            ResultSet resMin = stmtMin.executeQuery("SELECT prixSeminaire*(nbMax/2) as recetteMin  FROM Seminaire WHERE idSeminaire = "+idSeminaire);

	            // On r�cup�re les r�sultats qui sont en 1�re position
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
	        	
				// R�cup�ration des d�penses des requetes
	            ResultSet resMax = stmtMax.executeQuery("SELECT (prixSalle + prixRepas * nbRepas * nbMax + prixPause * nbPause * nbMax)  as depenseMax  FROM Seminaire WHERE idSeminaire = "+idSeminaire + "and nbRepas = (SELECT repas from Seminaire WHERE idSeminaire = "+idSeminaire + ") and nbPause = (SELECT typeSeminaire+1 FROM Seminaire WHERE idSeminaire = "+idSeminaire );
	            ResultSet resMin = stmtMin.executeQuery("SELECT ...");

	            // On r�cup�re les r�sultats qui sont en 1�re position
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
	    * Demande d'information sur un séminaire
	    *
	    * @param conn connexion à la base de données
	    * @throws Exception 
	    */
	
	public static void InfoSeminaire(Connection conn) throws Exception {
	    // Scanner pour récupérer les valeurs
		 Scanner sc = new Scanner (System.in);

		/*Seminaire*/
		// Attributs de la table
		int idSeminaire, idEntreprise, nbMax, repas, prixSeminaire, typeSeminaire ;
		String theme, dateSeminaire ;

		
		// Récupération de l'id du Séminaire
		System.out.print("Veuillez rentrer l'id du séminaire : ");
	    idSeminaire = Integer.parseInt(sc.nextLine());
	    
		while(idSeminaire <= 0){
            System.out.println("Veuillez rentrer un idSeminaire valide (>0) : ");
            idSeminaire = Integer.parseInt(sc.nextLine());
		}
    
		// Récupération de l'id de l'entreprise
		System.out.print("Veuillez rentrer l'id de l'entreprise : ");
		idEntreprise = Integer.parseInt(sc.nextLine());
		
		while(idEntreprise <= 0){
            System.out.println("Veuillez rentrer un idEntreprise valide (>0) : ");
            idEntreprise = Integer.parseInt(sc.nextLine());
		}
	    
		// Récupération du nombre max de participants
		System.out.print("Veuillez rentrer le nombre max de participants : ");
	    nbMax = Integer.parseInt(sc.nextLine());
	    
		// Récupération du prix du séminaire
		System.out.print("Veuillez rentrer le prix du séminaire : ");
		prixSeminaire = Integer.parseInt(sc.nextLine());
	    
		// Récupération de theme du Séminaire
		System.out.print("Veuillez rentrer le theme du Séminaire : ");
		theme = sc.nextLine();
	    
		// Récupération du type de Seminaire (journée, demi_journée)
		System.out.print("Veuillez rentrer le typeSeminaire (journee:1 / demi_journee:0) : ");
		typeSeminaire = Integer.parseInt(sc.nextLine());
		
		while((typeSeminaire != 0) && (typeSeminaire != 1)){
	            System.out.println("Veuillez rentrer un typeSeminaire valide (journee:1 / demi_journee:0) : ");
	            typeSeminaire = Integer.parseInt(sc.nextLine());
	    }

		
		// Récupération de la date du Séminaire
		System.out.print("Veuillez rentrer la date du Séminaire au format yyyy-MM-dd hh:mi:ss: ");
		dateSeminaire = sc.nextLine();
	    
		// Tester si une date est valide autant au niveau du format qu'au niveau du calendrier
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mi:ss");
		sdf.setLenient(false);
		java.util.Date dateV = null;
		try {
            dateV = sdf.parse(dateSeminaire);
        } catch (ParseException e) {
            e.printStackTrace();
        }

		
		// Récupération d'un repas possible
		System.out.print("Veuillez indiquer s'il y a un repas ou non (oui:1, non:0) : ");
		repas = Integer.parseInt(sc.nextLine());
		
		while((repas != 0) && (repas != 1)){
            System.out.println("Veuillez rentrer une volonte de repas valide (oui:1, non:0) : ");
            repas = Integer.parseInt(sc.nextLine());
		}
		
		
		// Cr�ation du S�minaire
		CreationSeminaire(conn, idSeminaire, idEntreprise, nbMax, repas, prixSeminaire, theme, typeSeminaire, dateSeminaire);
		
		
		
		
		
		
		
		/*Animateur*/
		int idAnimateur, numeroAnimateur, codePostalAnimateur, telephoneAnimateur  ;
		String nomAnimateur, prenomAnimateur, mailAnimateur, rueAnimateur, villeAnimateur ;

		
		// Récupération de l'id de l'Animateur
		System.out.print("Veuillez rentrer l'id de l'Animateur : ");
		idAnimateur = Integer.parseInt(sc.nextLine());
	    
		while(idAnimateur <= 0){
            System.out.println("Veuillez rentrer un idAnimateur valide (>0) : ");
            idAnimateur = Integer.parseInt(sc.nextLine());
		}
    
		// Récupération du nom de l'animateur
		System.out.print("Veuillez rentrer le nom de l'animateur : ");
		nomAnimateur = sc.nextLine();
		
		// Récupération du prenom de l'animateur
		System.out.print("Veuillez rentrer le prenom de l'animateur : ");
		prenomAnimateur = sc.nextLine();
		
		// Récupération du mail de l'animateur
		System.out.print("Veuillez rentrer le mail de l'animateur : ");
		mailAnimateur = sc.nextLine();
		
		// Récupération du numero de rue de l'Animateur
		System.out.print("Veuillez rentrer le numero de rue de l'Animateur : ");
		numeroAnimateur = Integer.parseInt(sc.nextLine());
	    
		while(numeroAnimateur <= 0){
            System.out.println("Veuillez rentrer un numeroAnimateur valide (>0) : ");
            numeroAnimateur = Integer.parseInt(sc.nextLine());
		}
		
		// Récupération de la rue de l'animateur
		System.out.print("Veuillez rentrer la rue de l'animateur : ");
		rueAnimateur = sc.nextLine();
		
		// Récupération du code postal de l'animateur
		System.out.print("Veuillez rentrer le code postal de l'animateur : ");
		codePostalAnimateur = Integer.parseInt(sc.nextLine());
		
		// Récupération de la ville de l'animateur
		System.out.print("Veuillez rentrer la ville de l'animateur : ");
		villeAnimateur = sc.nextLine();
		
		// Récupération du telephone de l'animateur
		System.out.print("Veuillez rentrer le t�l�phone de l'animateur : ");
		telephoneAnimateur = Integer.parseInt(sc.nextLine());
		
		
		// Cr�ation de l'animateur
		CreationAnimateur(conn, idAnimateur, idSeminaire, numeroAnimateur, nomAnimateur, prenomAnimateur, mailAnimateur, rueAnimateur , codePostalAnimateur, villeAnimateur, telephoneAnimateur);
				
				
		
		
		
		/*Activit�s*/
		int idActivite  ;
		String nomActivite ;

		
		// Récupération de l'id de l'Activit�
		System.out.print("Veuillez rentrer l'id de l'Activit� : ");
		idActivite = Integer.parseInt(sc.nextLine());
	    
		while(idActivite <= 0){
            System.out.println("Veuillez rentrer un idActivite valide (>0) : ");
            idActivite = Integer.parseInt(sc.nextLine());
		}
		
		// Récupération du nom de l'activit�
		System.out.print("Veuillez rentrer le nom de l'activit� : ");
		nomActivite = sc.nextLine();
	
		// Cr�ation de l'activit�
		CreationActivite(conn, idActivite, idSeminaire, nomActivite);
		
		
		
		
		/*Conferenciers*/
		int nbConf, idConferencier , numeroConferencier , codePostalConferencier , telephoneConferencier   ;
		String nomConferencier , prenomConferencier , mailConferencier , rueConferencier , villeConferencier, titreConference, dateRemiseTransparents    ;
		

		// Demande du nombre de conf�rencier a creer
		System.out.print("Veuillez indiquer le nombre de conf�rencier : ");
		nbConf = Integer.parseInt(sc.nextLine());
		
		while (nbConf != 0) {
			// Récupération de l'id du Conferencier
			System.out.print("Veuillez rentrer l'id du Conferencier : ");
			idConferencier = Integer.parseInt(sc.nextLine());
		    
			while(idConferencier <= 0){
	            System.out.println("Veuillez rentrer un idConferencier valide (>0) : ");
	            idConferencier = Integer.parseInt(sc.nextLine());
			}
	    
			// Récupération du nom du Conferencier
			System.out.print("Veuillez rentrer le nom du Conferencier : ");
			nomConferencier = sc.nextLine();
			
			// Récupération du prenom du Conferencier
			System.out.print("Veuillez rentrer le prenom du Conferencier : ");
			prenomConferencier = sc.nextLine();
			
			// Récupération du mail du Conferencier
			System.out.print("Veuillez rentrer le mail du Conferencier : ");
			mailConferencier = sc.nextLine();
			
			// Récupération du numero de rue du Conferencier
			System.out.print("Veuillez rentrer le numero de rue du Conferencier : ");
			numeroConferencier = Integer.parseInt(sc.nextLine());
		    
			while(numeroConferencier <= 0){
	            System.out.println("Veuillez rentrer un numeroConferencier valide (>0) : ");
	            numeroConferencier = Integer.parseInt(sc.nextLine());
			}
			
			// Récupération de la rue du Conferencier
			System.out.print("Veuillez rentrer la rue du Conferencier : ");
			rueConferencier = sc.nextLine();
			
			// Récupération du code postal du Conferencier
			System.out.print("Veuillez rentrer le code postal du Conferencier : ");
			codePostalConferencier = Integer.parseInt(sc.nextLine());
			
			// Récupération de la ville du Conferencier
			System.out.print("Veuillez rentrer la ville du Conferencier : ");
			villeConferencier = sc.nextLine();
			
			// Récupération du telephone du Conferencier
			System.out.print("Veuillez rentrer le t�l�phone du Conferencier : ");
			telephoneConferencier = Integer.parseInt(sc.nextLine());
			
			// Récupération du titre de la conference
			System.out.print("Veuillez rentrer le titre de la conference : ");
			titreConference  = sc.nextLine();
			
			// Récupération de la date de remise des transparents
			System.out.print("Veuillez rentrer la date de remise des transparents au format yyyy-MM-dd hh:mi:ss : ");
			dateRemiseTransparents = sc.nextLine();
		    
			// Tester si une date est valide autant au niveau du format qu'au niveau du calendrier
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mi:ss");
			sdf2.setLenient(false);
			dateV = null;
			try {
	            dateV = sdf2.parse(dateRemiseTransparents);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			// Cr�ation du Conferencier
			CreationConferencier(conn, idConferencier, idActivite, numeroConferencier, nomConferencier, prenomConferencier, mailConferencier, rueConferencier , codePostalConferencier, villeConferencier, telephoneConferencier, titreConference, dateRemiseTransparents);
			
			nbConf-- ;
		}
		
		
		
		
		
		/*Prestataire*/
		int idPrestataire , prixRepas  , prixPause  , prixSalle, numeroPrestataire , codePostalPrestataire , telephonePrestataire  ;
		String nomPrestataire, mailPrestataire , ruePrestataire , villePrestataire ;
		

		// Récupération de l'id du Prestataire
		System.out.print("Veuillez rentrer l'id du Prestataire : ");
		idPrestataire = Integer.parseInt(sc.nextLine());
		    
		while(idPrestataire <= 0){
            System.out.println("Veuillez rentrer un idPrestataire valide (>0) : ");
            idPrestataire = Integer.parseInt(sc.nextLine());
		}
		
		// Récupération du prix du repas
		System.out.print("Veuillez rentrer le  prix du repas : ");
		prixRepas = Integer.parseInt(sc.nextLine());
				    
		while(prixRepas <= 0){
			System.out.println("Veuillez rentrer un prixRepas valide (>0) : ");
			prixRepas = Integer.parseInt(sc.nextLine());
		}
		
		
		// Récupération du prix de la Pause
		System.out.print("Veuillez rentrer le  prix de la Pause : ");
		prixPause = Integer.parseInt(sc.nextLine());
				    
		while(prixPause <= 0){
			System.out.println("Veuillez rentrer un prixPause valide (>0) : ");
			prixPause = Integer.parseInt(sc.nextLine());
		}
		
		
		// Récupération du prix de la salle
		System.out.print("Veuillez rentrer le  prix de la salle : ");
		prixSalle = Integer.parseInt(sc.nextLine());
				    
		while(prixSalle <= 0){
			System.out.println("Veuillez rentrer un prixSalle valide (>0) : ");
			prixSalle = Integer.parseInt(sc.nextLine());
		}
	    
		// Récupération du nom du Prestataire
		System.out.print("Veuillez rentrer le nom du Prestataire : ");
		nomPrestataire = sc.nextLine();
		
		// Récupération du mail de Prestataire
		System.out.print("Veuillez rentrer le mail de Prestataire : ");
		mailPrestataire = sc.nextLine();
		
		// Récupération du numero de rue de Prestataire
		System.out.print("Veuillez rentrer le numero de rue de Prestataire : ");
		numeroPrestataire = Integer.parseInt(sc.nextLine());
	    
		while(numeroPrestataire <= 0){
            System.out.println("Veuillez rentrer un numeroPrestataire valide (>0) : ");
            numeroPrestataire = Integer.parseInt(sc.nextLine());
		}
		
		// Récupération de la rue de Prestataire
		System.out.print("Veuillez rentrer la rue de Prestataire : ");
		ruePrestataire = sc.nextLine();
		
		// Récupération du code postal de Prestataire
		System.out.print("Veuillez rentrer le code postal de Prestataire : ");
		codePostalPrestataire = Integer.parseInt(sc.nextLine());
		
		// Récupération de la ville de Prestataire
		System.out.print("Veuillez rentrer la ville de Prestataire : ");
		villePrestataire = sc.nextLine();
		
		// Récupération du telephone de Prestataire
		System.out.print("Veuillez rentrer le t�l�phone de Prestataire : ");
		telephonePrestataire = Integer.parseInt(sc.nextLine());
		
		
		
		
		// Cr�ation du Prestataire
		CreationPrestataire(conn, idPrestataire, idSeminaire, nomPrestataire, prixRepas, prixPause, prixSalle, mailPrestataire , numeroPrestataire, ruePrestataire, codePostalPrestataire, villePrestataire, telephonePrestataire);
		
		
		
		// Total des recettes pr�vues (min, max)
		recettesSeminaire(conn, idSeminaire);
		
		// Total des depenses pr�vues (min, max)
		depensesSeminaire(conn, idSeminaire);
		
		
	}

	

}
