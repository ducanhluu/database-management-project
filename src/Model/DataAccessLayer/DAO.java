/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DataAccessLayer;

import BackEnd.RequeteOrganisateur;
import BackEnd.RequeteExpert;
import BackEnd.RequeteOrganisateur;
import Model.Business.Artiste;
import Model.Business.Expert;
import Model.Business.Numero;
import Model.Business.Spectacle;
import Model.Business.User;
import java.util.Collection;

/**
 *
 * @author luud
 */
public class DAO {
    
    // Des méthodes pour manipuler la donnée
    public void ajouteCommentairePourNumero(int codeNum, String com) {
        RequeteExpert.updateComment(codeNum, com);
    }
    
    
    public void ajouteNotePourNumero(int codeNum, int note) {
        RequeteExpert.updateNote(codeNum, note);
    }
    
    public Collection<Numero> getNumerosPourEvaluer() {
        Collection<Numero> rets= RequeteExpert.getNumeros().getNumeros();
        return rets;
    }
    
    
    public void ajouteExpert(Expert exp) {
        RequeteOrganisateur req = new RequeteOrganisateur();
        
        req.addExpert(exp);
    }
    
    public void ajouteArtiste(Artiste artiste) {
        
    }
    
    public void ajouteSpectacle(Spectacle spectacle) {
        RequeteOrganisateur req = new RequeteOrganisateur();
        
        req.addSpectacle(spectacle);
    }  
    
    public void ajouteNumero(Numero numero) {
        RequeteOrganisateur req = new RequeteOrganisateur();
        
        req.addNumero(numero);
    }
    
    public Collection<Expert> getAllExperts() {
        // TODO
        return null;
    }
    
    public User getUserByUserNameAndPassword(String username, String password) {
        System.out.println("Not yet Implemented!\n");
        return new User(username, password);
    }

    public Collection<Numero> getProgrammeSpectacle(int codeSpec) {
        Collection<Numero> rets;
        rets = RequeteOrganisateur.getNumeros(codeSpec).getNumeros();
        return rets;
    }

    public Collection<Spectacle> getAllSpectacles() {
        //TODO:
        System.out.println("Not yet Implemented!\n");
        return null;
    }

}
    