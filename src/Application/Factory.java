/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Model.Business.User;
import Model.DataAccessLayer.DAO;

/**
 *
 * @author luud
 */
public class Factory {
    private static DAO dao = null;
    private static User user;
    
    
    public static synchronized DAO getDAO() {
        return dao == null ? dao = new DAO() : dao;
    }
    
    public static User getUser() {
        return user;
    }
    
    public static void setUser(String username, String password) {
        user = getDAO().getUserByUserNameAndPassword(username, password);
    }
}
