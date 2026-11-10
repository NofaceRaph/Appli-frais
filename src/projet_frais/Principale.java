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
            System.out.println("Liste des Visiteurs : ");
            ResultSet result = moi.getlesVisiteurs();
            if (!result.next()){
                System.out.println("Aucun visiteurs disponible");
            }
            else{
                while (result.next()){
                    String nom = result.getString(2);
                    String prenom = result.getString(3);
                    String login = result.getString(4);
                    System.out.println(nom + " - " + prenom + " - " + login);
                }
            }
            moi.ajoutVisiteur("f26","Guy","Maupassant","lguy","h47ut","15 avenue de la paix","29000","Quimper",Date.valueOf("2000-02-15"));
        } 
    }
        
}
    

