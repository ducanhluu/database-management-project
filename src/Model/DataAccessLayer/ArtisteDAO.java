/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DataAccessLayer;

import Model.Artiste;

/**
 *
 * @author nomezing
 */
public interface ArtisteDAO {
    public boolean update(Artiste artiste);
    public boolean insert(Artiste artiste);
    public boolean delete(Artiste artiste);
}
