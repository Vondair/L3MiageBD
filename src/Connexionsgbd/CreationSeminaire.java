package Connexionsgbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



import com.sun.javafx.scene.paint.GradientUtils.Parser;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class CreationSeminaire {
	
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
	    * @throws Exception 
	    */
	
	public static void CreationSeminaire(Connection conn, int idSeminaire, int idEntreprise, int nbMax, int repas, double prixSeminaire, String theme, String typeSeminaire, String dateSeminaire ) {
		
		 // Get a statement from the connection
        Statement stmt = conn.createStatement() ;
		
		try {
            String s = "INSERT INTO Seminaire VALUES (" + idSeminaire + "," + idEntreprise + "," + nbMax + "," + repas + "," + prixSeminaire + "," + "'" + theme + "'" + "," + "'" + typeSeminaire + "'" + "," + "to_date('" + dateSeminaire + "' ,'yyyy-MM-dd')" + ")";
            
            
            int rs = stmt.executeUpdate("Insert into Seminaire values (" + RefS + "," +"'" +Theme +"'" + "," +"to_date('" +date + "' ,'YYYY-MM-DD HH24:MI:SS')" + "," + "UPPER('" +Duree + "')" +"," + Dej +"," 
                    + Pause +"," + Prix+ "," + nb_Places + "," + RefA + ")");
            
            System.out.println(s);
            System.out.println("Insertion du séminaire numero: " + RefS + " au sujet de " + Theme);}
		
		catch(SQLException se){
	        System.out.println("Erreur Creation séminaire!");
	        SQLWarningsExceptions.printExceptions(se);
	        System.exit(1);
		}

		
	}

	 /**
	    * Demande d'information sur un séminaire
	    *
	    * @param conn connexion à la base de données
	    * @throws Exception 
	    */
	
	public static void Seminaire(Connection conn) throws Exception {
	    // Scanner pour récupérer les valeurs
		 Scanner sc = new Scanner (System.in);

		
		// Attributs de la table
		int idSeminaire, idEntreprise, nbMax, repas ;
		double prixSeminaire ;
		String theme, typeSeminaire, dateSeminaire ;

		
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
		prixSeminaire = Double.parseDouble(sc.nextLine());
	    
		// Récupération de theme du Séminaire
		System.out.print("Veuillez rentrer le theme du Séminaire : ");
		theme = sc.nextLine();
	    
		// Récupération du type de Seminaire (journée, demi_journée)
		System.out.print("Veuillez rentrer le typeSeminaire (journee / demi_journee) : ");
		typeSeminaire = sc.nextLine();
		
		while(!"journee".equalsIgnoreCase(typeSeminaire) && !"demi_journee".equalsIgnoreCase(typeSeminaire)){
	            System.out.println("Veuillez rentrer un typeSeminaire valide (journee / demi_journee) : ");
	            typeSeminaire = sc.nextLine();
	    }

		
		// Récupération de la date du Séminaire
		System.out.print("Veuillez rentrer la date du Séminaire au format yyyy-MM-dd : ");
		dateSeminaire = sc.nextLine();
	    
		// Tester si une date est valide autant au niveau du format qu'au niveau du calendrier
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		java.util.Date date = null;
		try {
            date = sdf.parse(dateSeminaire);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

		
		// Récupération d'un repas possible
		System.out.print("Veuillez indiquer s'il y a un repas ou non (oui:1, non:0) : ");
		repas = Integer.parseInt(sc.nextLine());
		
		while((repas != 0) && (repas != 1)){
            System.out.println("Veuillez rentrer une volonte de repas valide (oui:1, non:0) : ");
            repas = Integer.parseInt(sc.nextLine());
		}
	}


}
