/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Model.Business.Factory;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author milang
 */
public class RequeteAuthentification extends Requete{
    
    /**
     * Change le mot de passe du user courant avec le nouveau et l'ancien password
     * @param newPwd
     * @param oldPwd
     * @return 
     */
    public static boolean setPassword(String newPwd, String oldPwd){
        String test = "SELECT * FROM Login WHERE codeArtiste = " + Factory.getUser().getUserId();
        //
        try {
            ResultSet b = Getter.request(test);
            if (b.next()) {
                String pwd = b.getString("motDePasse");
                if (pwd.equals(oldPwd)){
                    Getter.request("UPDATE Login Set MotDePasse="+newPwd+" WHERE codeArtiste = " + Factory.getUser().getUserId());
                    Factory.setUser(Factory.getUser().getUserId(), newPwd);
                    return true;
                }
            }
            else{
                return false;
            }
        }
        catch(SQLException e) {
            System.out.println("SQL erreur : Aucun numéro");
        }
        return false;
    }
    
    /** 
     * créer un nouveau compte en lui donnant un login
     * @param Pwd
     * @return true si l'account a bien été crée, false sinon
     */
    public static boolean createAccount(String Pwd){
        String codeArt = Factory.createCodeArtiste();
        String test = "SELECT * FROM Artiste WHERE codeArtiste = " + codeArt;
        try {
            ResultSet b = Getter.request(test);
            if (b.next()) {
                Getter.request("INSERT INTO Login VALUES ( " + codeArt + " , " + Pwd+ " )");
                Factory.setUser(codeArt, Pwd);
                return true;
            }
        }
        catch(SQLException e){
            System.out.println("SQL erreur : Aucun numéro");
        }
        return false;
    }
    
    /**
     * Permet de se connecter à l'aide d'un login et d'un password
     * @param login
     * @param Pwd
     * @return 
     */
    public static boolean connexion(String login, String Pwd){
        String test = "SELECT * FROM Login WHERE codeArtiste = " + login;
        //
        try {
            ResultSet b = Getter.request(test);
            if (b.next()) {
                String pwd = b.getString("motDePasse");
                if (pwd.equals(Pwd)){
                    Factory.setUser(login, Pwd);
                    return true;
                }
            }
        }
        catch(SQLException e) {
            System.out.println("SQL erreur : Aucun numéro");
        }
        return false;
    }
    
    
    public static boolean deconnexion(){
        Factory.resetUser();
        return true;
    }
    
    
}

