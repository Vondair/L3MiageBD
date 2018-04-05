package Connexionsgbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;
import java.util.Scanner;
/*
* To change this license header, choose License Headers in
Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


public class requetesbd {

    /**
    * Afficher toutes les informations sur tous les
    spectacles.
    *
    * @param conn connexion � la base de donn�es
    * @throws SQLException en cas d'erreur d'acc�s � la
    base de donn�es
    */
    
    public static void nbemp(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement() ;

       // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM EMP") ;
        while( rs.next() ) {
            System.out.println("Nombre d'employes : " + rs.getInt(1)) ;
        }

            // Close the result set, statement and the connection
            rs.close() ;
            stmt.close() ;
    }

    
    public static void employes(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement() ;
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM emp") ;
        while( rs.next() ) {
            System.out.print("empno : " + rs.getInt(1)+" ") ;
            System.out.print("nom : " + rs.getString(2)+" ") ;
            System.out.println("job : " + rs.getString(3)) ;
        }
        
        // Close the result set, statement and the connection
        rs.close() ;
        stmt.close() ;
    }
    
    
    public static void requete1(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement() ;
               
        ResultSet rs = stmt.executeQuery("SELECT empno, sal, nvl(comm, 0), hiredate from emp") ;
        while( rs.next() ) {
            System.out.print("employé n° : " + rs.getString(1)) ;
            System.out.print(" salaire : " + rs.getInt(2)) ;
            System.out.print(" commision : " + rs.getInt(3)) ;
            System.out.println(" date embauche : " + rs.getDate(4)) ;
        }

            // Close the result set, statement and the connection
            rs.close() ;
            stmt.close() ;
    }
    
     public static void requete2(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement() ;
               
        ResultSet rs = stmt.executeQuery("select EMP.ename, nvl(MAN.ename, 'bigboss') from emp EMP left outer join emp MAN on (EMP.mgr = MAN.empno)") ;
        while( rs.next() ) {
            System.out.print("employé n° : " + rs.getString(1)) ;
            System.out.println(" manager : " + rs.getString(2)) ;
        }

            // Close the result set, statement and the connection
            rs.close() ;
            stmt.close() ;
    }
     
     
    public static void requete3(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement() ;
        
        ResultSet rs = stmt.executeQuery("select deptno, sum(sal+nvl(comm,0)) from emp group by deptno order by deptno ") ;
        while( rs.next() ) {
            System.out.print("departement n° : " + rs.getInt(1)) ;
            System.out.println(" somme (salaire + comm) : " + rs.getInt(2)) ;
        }

            // Close the result set, statement and the connection
            rs.close() ;
            stmt.close() ;
    }
    
    
    public static void requete4(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement() ;
        
        ResultSet rs = stmt.executeQuery("select * from lesSpectacles2") ;
        while( rs.next() ) {
            System.out.println("num : " + rs.getInt(1)) ;
            System.out.println("nom : " + rs.getString(2)) ;
        }

            // Close the result set, statement and the connection
            rs.close() ;
            stmt.close() ;
    }
    
    
    public static void requete5(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement() ;
        
        // Demande du numéro
        Scanner num = new Scanner(System.in);
        System.out.print("Entrer un numéro de spectacle");
        num.nextLine();
               
        ResultSet rs = stmt.executeQuery("select nvl(noms, 'inconnu') from lesSpectacles2 where nums = " + num) ;
        while( rs.next() ) {
            System.out.println("nom : " + rs.getString(1)) ;
        }

            // Close the result set, statement and the connection
            rs.close() ;
            stmt.close() ;
    }
        
        
        
}