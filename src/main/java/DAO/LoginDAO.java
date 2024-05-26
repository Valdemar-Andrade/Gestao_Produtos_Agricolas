/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Login;

/**
 *
 * @author valdemar
 */
public class LoginDAO extends DAOImplementacao<Login, Integer>{

    public LoginDAO () {
        super( new Login() );
    }
    
    public boolean autenticarLogin(String senha, String usuario){
        
        return true;
        
    }
    
}
