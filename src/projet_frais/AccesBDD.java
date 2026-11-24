package projet_frais;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.*;


public class AccesBDD {
    
    private Connection connexion;
    
    //Connexion à la BDD
    public AccesBDD(){
    Properties config = new Properties();
        try (FileInputStream fichier =
                new FileInputStream("config.properties")){
        // Lecture de la configuration
        config.load(fichier);
        
        String url = config.getProperty("db.url");
        String user = config.getProperty("db.user");
        String password = config.getProperty("db.password");
        
        // Ouverture de la connexion
        connexion = DriverManager.getConnection(
                url,
                user,
                password
        );
        
        System.out.println("Connexion réussie !");
        } catch (IOException e){
            System.out.println(
                    "Erreur de lecture du fichier de configuration : "
                    + e.getMessage()
            );
        } catch (SQLException e){
            System.out.println(
                    "Erreur SQL : " + e.getMessage()
            );
        }
   }
    
   public Connection getConnection(){
       return connexion;
   }
   
   // Sélectionne les visiteurs dans la BDD
   public ResultSet getlesVisiteurs(){
       ResultSet result = null;
       Statement statement;
       String sql = "SELECT * FROM visiteur";
        try {
            statement = connexion.createStatement();
            result = statement.executeQuery(sql);
            return result;
        }
        catch (SQLException ex) {
            Logger.getLogger(AccesBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
   }
   
   // Ajoute un visiteur dans la BDD
   public int ajoutVisiteur(String id, String nom,String prenom,String adresse,String cp,String ville,Date dateEmbauche){
       String sql="INSERT INTO visiteur (id, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
       int rowsInserted = 0;
       try {
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, nom);
            statement.setString(3, prenom);
            statement.setString(4, genererLogin(prenom,nom));
            statement.setString(5, genererMDP().toString());
            statement.setString(6, adresse);
            statement.setString(7, cp);
            statement.setString(8, ville);
            statement.setDate(9, dateEmbauche);
            rowsInserted = statement.executeUpdate();
            return rowsInserted ;
        } catch (SQLException ex) {
            Logger.getLogger(AccesBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
       return rowsInserted ;
   }
   
   // Génère un mot de passe aleatoire
   public StringBuilder genererMDP(){
       String c = "abcdefghijklmnopqrstuvwxyz0123456789";
       Random r = new Random();
       StringBuilder mdp = new StringBuilder();
       for(int i=0;i<5;i=i+1){
           int n = r.nextInt(1,37);
           mdp.append(c.substring(n-1,n));
       }
       return mdp;
   }
   // Génère un login aléatoire
   public String genererLogin(String prenom, String nom){
       String login = prenom.substring(0,1).toLowerCase()+nom.toLowerCase();
       return login;
   }
   
}
