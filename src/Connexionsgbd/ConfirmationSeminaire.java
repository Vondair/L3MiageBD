package Connexionsgbd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;


public class ConfirmationSeminaire {

	public static void CheckSeminaire (Connection conn) {
		
		try {
			int resSem, nbCheck, nbParticipant ;
			
			/*Pour chaque Séminaire*/
	        // Get a statement from the connection
			Statement stmt = conn.createStatement() ;
			Statement stmt2 = conn.createStatement() ;
			Statement stmt3 = conn.createStatement() ;
			
			// R�cup�ration des idSeminaire
            ResultSet sem = stmt.executeQuery("SELECT idSeminaire FROM Seminaire");
            
            ResultSet res2 ;
            ResultSet res3 ;
            
            // On r�cup�re les r�sultats qui sont en 1�re position dans la table
	        while (sem.next()) {
	        	resSem = sem.getInt(1) ;
	        	System.out.println("ID du séminaire vérifié : " + resSem);
				
	        	// R�cup�ration des r�sultats des requetes
	            res2 = stmt2.executeQuery("SELECT nbMax/2 - nbParticipant as nbCheck FROM Seminaire WHERE nbParticipant = (SELECT count(idParticipant) FROM Participant WHERE estAttente = 0 and idSeminaire =" +resSem+") and idSeminaire =" +resSem);
	            
	            // On r�cup�re les r�sultats qui sont en 1�re position
		        while (res2.next()) {
		        	nbCheck = res2.getInt(1);
		        }
	            
	            // Y a t-il assez de participant ?
		        if (nbCheck > 0){
		        	System.out.println("Il n'y a pas assez de personne à votre séminaire, il va donc être annulé");
		        	AnnulationSeminaire(conn, resSem);
		        	
		        	System.out.println("Le prestataire va être prévenue de l'annulation du séminaire");
		        	// Envoi mail au prestataire ...
		        	
		        }
		        
		        else {
		        	System.out.println("Il n'y a assez de personne à votre séminaire, il va donc être confirmé");
		        	
		        	/*nombre de participant au séminaire*/
		            res3 = stmt2.executeQuery("SELECT count(idParticipant) as nbParticipant FROM Participant WHERE estAttente = 0 and idSeminaire =" +resSem);
		            
		            // On r�cup�re les r�sultats qui sont en 1�re position
			        while (res3.next()) {
			        	nbParticipant = res3.getInt(1);
			        	System.out.println("Nombre de participant : " + nbParticipant);
			        }
		        	
		        	System.out.println("Le prestataire va être prévenue de la confirmation du séminaire");
		        	// Envoi mail au prestataire ...
		        	
		        	
		        	/*Bilan budgétaire*/
		        }
	        }
	        
	        
	        // Close statement and resultSet
	        stmt.close();
	        stmt2.close();
	        stmt3.close();
	        res2.close();
	        res3.close();
	        
		}
	        
	        
	}
		
	public static void AnnulationSeminaire (Connection conn, int idSeminaire) {
		
	}
	
}
