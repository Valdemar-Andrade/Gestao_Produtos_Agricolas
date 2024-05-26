/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Produto;

/**
 *
 * @author valdemar
 */
public class ProdutoDAO extends DAOImplementacao<Produto, Integer>{

    public ProdutoDAO () {
        super( new Produto() );
    }
    
}
