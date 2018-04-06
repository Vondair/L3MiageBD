package Connexionsgbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionInscription {

	
	 /**
	    * Inscription
	    *
	    * @param conn connexion à la base de données
	    * 		idParticipant
	    * 		idSeminaire
	    * 		dateInscription
	    * 		nomParticipant
	    * 		prenomParticipant
	    * 		mailParticipant
	    * 		numeroParticipant
	    * 		rueParticipant
	    * 		codePostalParticipant
	    * 		villeParticipant 
	    * 		telephoneParticipant
	    * 		estEnAttente
	    * @throws SQLException 
	    * @throws Exception 
	    */
	
	
	
	public static void Inscription(Connection conn, int idParticipant, int idSeminaire, String dateInscription, String nomParticipant, String prenomParticipant, String mailParticipant, int numeroParticipant, String rueParticipant, int codePostalParticipant, String villeParticipant, int telephoneParticipant ) throws SQLException {
			int estEnAttente ;
		
		try {
			
			/*Gestion de l'attente*/
	        // Get a statement from the connection
			Statement stmt = conn.createStatement() ;
        	
			// R�cup�ration des r�sultats des requetes
            ResultSet res = stmt.executeQuery("SELECT ...");

            
            // On r�cup�re les r�sultats qui sont en 1�re position
	        while (res.next()) {
	        	System.out.println("Nombre de place disponible : " + res.getInt(1));
	        }
 
	        // On définie si une personne qui s'inscrit est en attente ou en participant
	        if (res.getInt(1) > 0){
	        	estEnAttente = 0 ;
	        	System.out.println("La personne sera donc participant");
	        }
	        else {
	        	estEnAttente = 1 ;
	        	System.out.println("La personne sera donc en attente");
	        }
	        
	        
	        
	        /*Ajout de la personne*/	
			//commande
	         String s = "INSERT INTO Prticipant VALUES (" + idParticipant + "," + idSeminaire + "," + "to_date('" + dateInscription + "' ,'yyyy-MM-dd hh:mm:ss')" + "," +  "'" + nomParticipant + "'" + "," +  "'" + prenomParticipant + "'" + "," +  "'" + mailParticipant + "'" + "," + numeroParticipant + "," +  "'" + rueParticipant + "'" + "," + codePostalParticipant + "," +  "'" + villeParticipant + "'" + "," + telephoneParticipant + "," + estEnAttente + ")";
	         // execution de la commande + r�cup�ratino du r�sultat
	         int res2 = stmt.executeUpdate(s);
	         
	         
	         // commentaire si tout c'est bien pass�
	         System.out.println(s);
	         System.out.println("Insertion d'un participant, " + res2 + "ligne rajout�e") ;
	         
	         
	         // Close the statement and ResultSet
	         stmt.close() ;
	         res.close() ;
	         
	
	     }
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur Insertion Participant!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}
	
	
	
	 /**
	    * Annulation
	    *
	    * @param conn connexion à la base de données
	    * 		idParticipant
	    * @throws SQLException 
	    * @throws Exception 
	    */
	
	
	
	public static void Annulation(Connection conn, int idParticipant, int idSeminaire) throws SQLException {

		try {
			
			/*Gestion de l'attente*/
	        // Get a statement from the connection
			Statement stmt = conn.createStatement() ;
        	
			// R�cup�ration des r�sultats des requetes
            ResultSet res = stmt.executeQuery("SELECT ...");

            
            // On r�cup�re les r�sultats qui sont en 1�re position
	        while (res.next()) {
	        	System.out.println("Nombre de place disponible : " + res.getInt(1));
	        }
 
	        // On définie si une personne qui s'inscrit est en attente ou en participant
	        if (res.getInt(1) == 0){
	        	System.out.println("Une personne en attente va passer en participant");
	        }
	        
			
	        /*Annulation participant*/
			//commande
	         String s = "DELETE FROM Participant WHERE idParticipant =" + idParticipant ;
	         // execution de la commande + r�cup�ratino du r�sultat
	         int res2 = stmt.executeUpdate(s);
	         
	         
	         // commentaire si tout c'est bien pass�
	         System.out.println(s);
	         System.out.println("Annulation d'un participant, " + res2 + "ligne rajout�e") ;
	         
	         
	         
	         /*Recherche d'une personne a passer en participant*/
	         // R�cup�ration des r�sultats des requetes
	         ResultSet res3 = stmt.executeQuery("SELECT P1.idParticipant FROM Participant P1 join Participant P2 on (P1.dateInscription < P2.dateInscription) WHERE idSeminaire =" + idSeminaire);

	            
	         // On r�cup�re les r�sultats qui sont en 1�re position
	         while (res3.next()) {
		       	System.out.println("L'id du participant ajouté à la liste : " + res3.getInt(1));
		     }
	 
	         //commande
	         String s3 = "UPDATE Participant SET estEnAttente = 0 WHERE idParticipant =" + idParticipant +" and idSeminaire =" + idSeminaire ;
	         // execution de la commande + r�cup�ratino du r�sultat
	         int res4 = stmt.executeUpdate(s);
 
	         // commentaire si tout c'est bien pass�
	         System.out.println(s3);
	         System.out.println("Ajout d'un participant, " + res2 + "ligne rajout�e") ;
	         
	         
	         // close statement and resultset
	         stmt.close();
	         res.close();
	         res3.close();
	
	     }
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur Annulation Participant!") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}

		
	}

	
	
}
