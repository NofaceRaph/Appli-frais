package projet_frais;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AccesBDD {
    
    private Connection connexion;
    
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
   
   public int ajoutVisiteur(String id, String nom,String prenom,String login,String mdp,String adresse,String cp,String ville,Date dateEmbauche){
       String sql="INSERT INTO visiteur (id, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
       int rowsInserted = 0;
       try {
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, nom);
            statement.setString(3, prenom);
            statement.setString(4, login);
            statement.setString(5, mdp);
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
   
   public int suprimmerVisiteur(String id){
        String sql="DELETE FROM visiteur where id = ?";
        int rowsSuppr = 0;
        try {
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, id);
            rowsSuppr = statement.executeUpdate();
            return rowsSuppr;
        } catch (SQLException ex) {
            Logger.getLogger(AccesBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsSuppr;
   }
   
   public int modifierVisiteur(String id, String nom,String prenom,String login,String mdp,String adresse,String cp,String ville,Date dateEmbauche){
       String sql="UPDATE visiteur SET nom = ? , prenom = ? , login = ? , mdp = ? , adresse = ? , cp = ? , ville = ? , dateEmbauche = ? WHERE id = ?";
       int rowsModi = 0;
       try {
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, login);
            statement.setString(4, mdp);
            statement.setString(5, adresse);
            statement.setString(6, cp);
            statement.setString(7, ville);
            statement.setDate(8, dateEmbauche);
            statement.setString(9, id);
            rowsModi = statement.executeUpdate();
            return rowsModi;
        } catch (SQLException ex) {
            Logger.getLogger(AccesBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsModi;
   }
    
}
