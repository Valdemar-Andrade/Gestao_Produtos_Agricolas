/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Usuario;

/**
 *
 * @author valdemar
 */
public class UsuarioDAO extends DAOImplementacao<Usuario, Integer> {

    public UsuarioDAO () {
        super( new Usuario() );
    }

}
