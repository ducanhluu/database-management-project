
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Model.Business.Artiste;
import Model.Business.ArtisteParticipant;
import Model.Business.ArtistePrincipal;
import Model.Business.Theme;
import Model.Business.Evaluation;
import Model.Business.Expert;
import Model.Business.Numero;
import Model.Business.Spectacle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author milang
 */
public class RequeteOrganisateur extends Requete {

    /**
     * Prendre tous les spectacles.
     * @return ResultatsSpectacles
     */
    public static ResultatsSpectacles getSpectacles() {
        ResultSet rs = Getter.request("SELECT * FROM Spectacle");
        ResultatsSpectacles spectacles = new ResultatsSpectacles();
        try {
            while(rs.next()) {
                Spectacle spec=new Spectacle(
                        rs.getInt("codeSpectacle"),
                        rs.getDate("jourSpectacle").toString(),
                        rs.getInt("heureDebut"),
                        rs.getInt("heureFin"),
                        rs.getDouble("prixSpectacle"),
                        rs.getInt("codeArtiste"),
                        Theme.valueOf(rs.getString("theme")),
                        rs.getInt("codeFestival")
                );
            }
        }
        catch (SQLException e) {
            System.out.println("Erreur SQL : Aucun spectacle");
        }
        return null;
    }

   
 
    /**
     * Prendre toutes les evaluations relatives au numero.
     * @param numero
     * @return ResultatsEvaluations
     */
    public static ResultatsEvaluations getNumeroEvaluations(Numero numero) {
        String cmd = "SELECT * FROM Evaluation WHERE codeNumero = " + numero.getID();
        ResultatsEvaluations res = new ResultatsEvaluations();
        try {
            ResultSet b = Getter.request(cmd);
            while (b.next()) {
                Evaluation eval = new Evaluation(
                    b.getInt("codeArtiste"),
                    b.getInt("codeNumero"),
                    b.getString("evaluation"),
                    b.getInt("note")
                );
                res.add(eval);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : Numéro inconnu");
        }
        return res;
    }
     
    public static int getMoyenneNumero(Numero numero){
        String cmd = "SELECT AVG(Note) FROM (evaluation INNER JOIN Numero WHERE evaluation.codeNumero = Numero.codeNumero) WHERE evaluation.codeNumero = " + numero.getID();
        try {
            ResultSet b = Getter.request(cmd);
            if (b.next()) {
                return b.getInt("AVG(Note)");
            }
        }
        catch(SQLException e) {
            System.out.println("Erreur SQL : Aucune note");
        } 
        return -1;
    }
    
    /**
     * Crée un artiste dans la BD
     * @param a
     * @return 
     */
    public static boolean addArtiste(Artiste a) {
        String req= "INSERT INTO Artiste VALUES (" +a.getID() + " , " + a.getCodeCirque() + " , " + a.getNom() + " , " + a.getPrenom() + " , " + a.getDate() + " , " + a.getAdresse() + ")";
        Getter.update(req);
        return true;
    }


    /**
     * Ajoute un expert dans la BD.
     * Ajoute son/ses thème/s d'expertise dans EstExpertEn
     * @param expert
     * @return boolean
     */
    public static boolean addExpert(Expert expert) {
        addArtiste(expert);
        String req= "INSERT INTO ArtisteExpert VALUES (" +expert.getID() + ")";
        Iterator<Theme> it = expert.getThemes().iterator();
        while (it.hasNext()) {
            Theme theme = it.next();
            String s2 = "INSERT INTO EstExpertEn VALUES (" + expert.getID() + ", " + theme + ")";
            Getter.update(s2);
        }
        Getter.update(req);
        return true;
    } 
    
    
    /**
     * Ajoute un participant dans la BD.
     * Ajoute son/ses thème/s d'expertise dans EstExpertEn
     * @param ap
     * @return boolean
     */
    public static boolean addParticipant(ArtisteParticipant ap) {
        addArtiste(ap);
        String req= "INSERT INTO ArtisteParticipant VALUES (" +ap.getID() + ")";
        Iterator<Integer> it = ap.getListeNumero().iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            String s2 = "INSERT INTO ParticipeA VALUES (" + ap.getID() + ", " + i + ")";
            Getter.update(s2);
        }
        Getter.update(req);
        return true;
    }     
    
    /**
     * crée un article principal
     * @param ap
     * @return 
     */
    public static boolean addPrincipal (ArtistePrincipal ap) {
        addParticipant(ap);
        String req= "INSERT INTO ArtistePrincipal VALUES (" +ap.getNumTel() + ")";
        return true;
    }     


    
    /**
     * Ajoute un spectacle dans la base.
     * @param spectacle
     * @return boolean
     */
    public static boolean addSpectacle(Spectacle spectacle) {
        String req = "INSERT INTO Spectacle (CodeSpectacle, JourSpectacle, HeureDebut, HeureFin, PrixSpectacle, CodeArtiste, Theme, codeFestival) VALUES (" + spectacle.getID() + " , " + spectacle.getJour() + " , " + spectacle.getDebut() + " , " + spectacle.getFin() + " , " + spectacle.getPrix()
                + " , " + spectacle.getPresentateur() + " , " + spectacle.getTheme() + " , " + spectacle.getCodeFestival() + ")";
        Getter.update(req);
        return true;
    }
     
    /**
     * Ajoute un numero à un spectacle dans la BD.
     * @param spectacle
     * @param numero
     * @param heure
     * @return boolean
     */
    public static boolean addNumeroToSpectacle(Spectacle spectacle,Numero numero, int heure) {
        String cmd = "INSERT INTO NumeroAccepte(codeNumero, codeSpectacle, HeureNumero ) VALUES ( " + numero.getID() + " , " + spectacle.getID() + " , " + heure + ")";
        Getter.update(cmd);
        return true;
    }
    
    /**
     * Affecte un numéro à un expert
     * @param exp
     * @param numero 
     */
    public static boolean associeNumeroExpert(Expert exp, Numero numero) {
        String cmd = "INSERT INTO Evaluation VALUES (" + exp.getId() + ", " + numero.getID() + ")";
        Getter.update(cmd);
        exp.ajouteNumero(numero);
        return true;
    }
    
    
    public static ResultatsExperts getExpertsAvailable(ArrayList<Expert> listeExp) {
        String cmd = "SELECT codeArtiste FROM ArtisteExpert";
        if (listeExp.size() > 0) {
            cmd += " WHERE codeArtiste!=" + listeExp.get(0);
        }
        if (listeExp.size() > 1) {
            cmd += " and codeArtiste!=" + listeExp.get(1);
        }
        ResultatsExperts res = new ResultatsExperts();
        try {
            ResultSet b = Getter.request(cmd);
            while (b.next()) {
                //Expert exp = new Expert(
              //          b.getInt("codeArtiste")      
                //);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : Aucun expert");
        }
        return res;
    }
}
