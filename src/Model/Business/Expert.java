/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Business;

import java.util.*;

/**
 *
 * @author nabem
 */
public class Expert extends Artiste{
    private ArrayList<Numero> listeNumeros;
    private ArrayList<Theme> themes;
    private int nbrNumeroEvaluer; //pour dire s'il peut tjrs évaluer un numéro (nbr < 15)
    
    public Expert(int code, int codeCirque, String nom, String prenom, String dateNaissance, String  adresse, ArrayList<Numero> listeNumero, ArrayList<Theme> themes) {
        super(code,codeCirque,nom,prenom,dateNaissance,adresse);
        this.themes = themes;
        this.nbrNumeroEvaluer = 0;
        this.listeNumeros = listeNumero;
    }
    
    public ArrayList<Numero> getListeNumeros() {
        return this.listeNumeros;
    }
     
    public void retireNumero(Numero numero) {
        this.listeNumeros.remove(numero);
        this.nbrNumeroEvaluer--;
    }
    
    public void ajouteNumero(Numero numero) {
        this.listeNumeros.add(numero);
        this.nbrNumeroEvaluer++;
    }
    
    public ArrayList<Theme> getThemes() {
        return this.themes;
    }
    
    public int getNbrNumeros() {
        return this.nbrNumeroEvaluer;
    }
    
    public void afficheNumeros() {
        System.out.println("Liste des numéros associés : ");
        for(Numero num : getListeNumeros()) {
            System.out.println(num.toString());
        }
    }
    
    public int getId(){
        return super.getID();
    }
    
    @Override
    public String toString() {
        return "" + "Monsieur " + this.getNom() + " " + this.getPrenom() + "est expert en : " + this.getThemes().toString();
    }
}
