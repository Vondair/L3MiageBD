package Connexionsgbd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;


public class ConfirmationSeminaire {

	public static void CheckSeminaire (Connection conn) {
		
		try {
			int resSem, nbCheck =0, nbParticipant =0 ;
			
			/*Pour chaque Séminaire*/
	        // Get a statement from the connection
			Statement stmt = conn.createStatement() ;
			Statement stmt2 = conn.createStatement() ;
			Statement stmt3 = conn.createStatement() ;
			
			// R�cup�ration des idSeminaire
            ResultSet sem = stmt.executeQuery("SELECT idSeminaire FROM Seminaire");
            
            ResultSet res2 = null;
            ResultSet res3 = null;
            
            // On r�cup�re les r�sultats qui sont en 1�re position dans la table
	        while (sem.next()) {
	        	resSem = sem.getInt(1) ;
	        	System.out.println("ID du séminaire vérifié : " + resSem);
				
	        	// R�cup�ration des r�sultats des requetes
	            res2 = stmt2.executeQuery("SELECT nbMax/2 - nbParticipant as nbCheck FROM Seminaire natural join (SELECT idSeminaire, count(idParticipant) as nbParticipant FROM Participant WHERE estEnAttente = 0 GROUP BY idSeminaire) WHERE idSeminaire =" + resSem);
	            
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
		        	System.out.println("Il y a assez de personne à votre séminaire, il va donc être confirmé");
		        	
		        	/*nombre de participant au séminaire*/
		            res3 = stmt2.executeQuery("SELECT count(idParticipant) as nbParticipant FROM Participant WHERE estEnAttente = 0 and idSeminaire =" +resSem);
		            
		            // On r�cup�re les r�sultats qui sont en 1�re position
			        while (res3.next()) {
			        	nbParticipant = res3.getInt(1);
			        	System.out.println("Nombre de participant : " + nbParticipant);
			        }
		        	
		        	System.out.println("Le prestataire va être prévenue de la confirmation du séminaire");
		        	// Envoi mail au prestataire ...
		        	
		        	
		        	/*Bilan budgétaire*/
		        	bilanSeminaire(conn, resSem, nbParticipant);
		        }
	        }
		        
		        // Close statement and resultSet
		        stmt.close();
		        stmt2.close();
		        stmt3.close();
		        res2.close();
		        res3.close();
		}
	        
	    catch(SQLException e){
	    	// commentaire si erreur
		    System.out.println("Erreur recette Seminaire") ;
		    SQLWarningsExceptions.printExceptions(e) ;
		    System.exit(1) ;
		} 
     
	}
		
	
	
	
	public static void AnnulationSeminaire (Connection conn, int idSeminaire) throws SQLException {
		
		try {
			//Appel procedure suppression de séminaire
			String sql = "{call SupprSeminaire[("+idSeminaire+")]}";
	
			CallableStatement statement = conn.prepareCall(sql);
		}
		
		catch(SQLException e){
			// commentaire si erreur
	        System.out.println("Erreur procedure supprimer Seminaire") ;
	        SQLWarningsExceptions.printExceptions(e) ;
	        System.exit(1) ;
		}
		
	}
	
	
	
	 public static void bilanSeminaire(Connection conn, int idSeminaire, int nbParticipant) throws SQLException {
	        
	        
	        try {
		        // Get a statement max from the connection
				Statement stmtrecette = conn.createStatement() ;
				
				 // Get a statement min from the connection
				Statement stmtdepense = conn.createStatement() ;	
	        	
				// R�cup�ration des r�sultats des requetes
	            ResultSet resrecette = stmtrecette.executeQuery("SELECT prixSeminaire*" + nbParticipant+" as recette  FROM Seminaire WHERE idSeminaire = "+idSeminaire );
	            ResultSet resdepense = stmtdepense.executeQuery("SELECT (prixSalle + prixRepas * repas * " + nbParticipant+" + prixPause * typeSeminaire * " + nbParticipant+")  as depense FROM Seminaire natural join Prestataire WHERE idSeminaire = "+idSeminaire );

	            // On r�cup�re les r�sultats qui sont en 1�re position
	            int recette = 0;
	            int depense = 0;
		        while (resrecette.next()) {
		        	recette = resrecette.getInt(1);
		        	System.out.println("Recettes : " + recette );
		        }
		        while (resdepense.next()) {
		        	depense = resdepense.getInt(1);
		            System.out.println("Depense : " + depense);
		        }
		        
		        int bilan = recette - depense;
		        System.out.println("Bilan : " + bilan);
		        
		        
		        // Close the statement and resultset
		        stmtrecette.close() ;
		        stmtdepense.close() ;
		        resrecette.close() ;
		        resdepense.close() ;
	            
	        }
	        
	        catch(SQLException e){
				// commentaire si erreur
		        System.out.println("Erreur bilan Seminaire") ;
		        SQLWarningsExceptions.printExceptions(e) ;
		        System.exit(1) ;
			} 
	        
	 }
}
