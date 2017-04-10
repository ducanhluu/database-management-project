/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DataTransfertObject.SpectacleDAO;
import Model.DataTransfertObject.SpectacleDOASQL;
import Model.Spectacle;
import java.util.List;

/**
 *
 * @author nomezing
 */
public class GesProgrammeController extends MainController {

    private SpectacleDAO spectacleDAO = new SpectacleDOASQL();
    
    
    public List<Spectacle> getAllSpectacles() throws Exception {
        return spectacleDAO.getAllSpectacle();
    }
    
}