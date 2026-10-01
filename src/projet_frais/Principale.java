/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet_frais;

import java.sql.*;

/**
 *
 * @author tboyer
 */
public class Principale {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        AccesBDD moi = new AccesBDD();
        
        if (moi.getConnection() != null){
            System.out.println("Liste des Clients : ");
            ResultSet result = moi.getlesVisiteurs();
            if (!result.next()){
                System.out.println("Aucun livre cheffe");
            }
            else{
                while (result.next()){
                    String nom = result.getString(2);
                    String prenom = result.getString(3);
                    String login = result.getString(4);
                    System.out.println(nom + " - " + prenom + " - " + login);
                }
            }
        } 
    }
    
}
    

