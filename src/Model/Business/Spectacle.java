/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Business;

import Application.Factory;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author nabem
 */
public class Spectacle {
    private int codeSpectacle;
    private String jourSpectacle;
    private int heureDebut;
    private int heureFin;
    private double prixSpectacle;
    private Collection<Numero> programme;
    
    
    public Spectacle(int code, String jour, int debut, int fin, double prix) {
        this.codeSpectacle = code;
        this.jourSpectacle = jour;
        this.heureDebut = debut;
        this.heureFin = fin;
        this.prixSpectacle = prix;
        this.programme = Factory.getDAO().getProgrammeSpectacle(this.codeSpectacle);
    }
    
    public int getID() {
        return this.codeSpectacle;
    }
    public int getDebut(){
        return this.heureDebut;
    }
    
    public int getFin(){
        return this.heureFin;
    }
    
    @Override
    public String toString() {
        String affiche = "";
        int duree = heureFin - heureDebut;
        System.out.println("Code du spectacle : " + codeSpectacle + " " + "Jour : " + jourSpectacle + " " + "Dure : " + " " + duree + " " + "Coute : " + prixSpectacle) ;
        
        for(Numero numero : this.programme) {
            affiche += numero.toString();
        }
        return affiche;
    }
}
