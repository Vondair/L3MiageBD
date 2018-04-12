package Connexionsgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Connexionsgbd {
    private static final String configurationFile = "BD.properties";
   
    public static void main( String args[] ) {
    
        try {
            String jdbcDriver, dbUrl, username, password;

            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            jdbcDriver = dap.getJdbcDriver();
            System.out.println(jdbcDriver);
            dbUrl = dap.getDatabaseUrl();
            username = dap.getUsername();
            password = dap.getPassword();

            // Load the database driver
            Class.forName(jdbcDriver) ;


            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            
            // Scanner pour récupérer reponses
   		 	Scanner sc = new Scanner (System.in);
   		 	
   		 	int reponse, checkS ;
    		System.out.println("Bienvenue dans votre gestionnaire de Séminaire");

            
            // Test confirmation seminaire
    		System.out.println("Boulez vous v�rifier les s�minaire ? (oui : 1, non : 0");
    		checkS = Integer.parseInt(sc.nextLine());
    		if(checkS ==1)
    			ConfirmationSeminaire.CheckSeminaire(conn);

            
            /* Table */
    		System.out.println("Vous pourrez ajouter des séminaires, des animateurs, des prestataires, des entreprises, des conferenciers, des activités, des participants");

    		
    		// Ajout séminaire
    		System.out.print("Voulez vous ajoutez un séminaire ? (oui : 1, non : 0) ");
    		reponse = Integer.parseInt(sc.nextLine());
    		
    		while((reponse != 0) && (reponse != 1)){
    	            System.out.println("Veuillez rentrer 1 pour oui, 0 pour non ");
    	            reponse = Integer.parseInt(sc.nextLine());
    	    }
    		while (reponse ==1){
    			CreationSeminaireComplet.InfoSeminaire(conn);
    			
    			System.out.print("Voulez vous ajoutez un autre séminaire ? (oui : 1, non :0) ");
        		reponse = Integer.parseInt(sc.nextLine());
        		
        		while((reponse != 0) && (reponse != 1)){
        	            System.out.println("Veuillez rentrer 1 pour oui, 0 pour non ");
        	            reponse = Integer.parseInt(sc.nextLine());
        	    }
    		}
            
    		
    		System.out.print("Aurevoir");
    		
    		
            // Print information about connection warnings
            SQLWarningsExceptions.printWarnings(conn);
            conn.close() ;
            sc.close();
        }
        catch( SQLException se ) {

        // Print information about SQL exceptions
        SQLWarningsExceptions.printExceptions(se);

        return;
        
        } catch( Exception e ) {
        System.err.println( "Exception: " + e.getMessage()) ;
        e.printStackTrace();
        return;
        }
   }
}