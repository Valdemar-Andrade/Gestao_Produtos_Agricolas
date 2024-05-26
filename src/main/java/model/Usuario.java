/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author valdemar
 */
public class Usuario {
    
    private Integer pkUsuario;
    private String nome;
    private String email;

    public Usuario () {
    }

    public Usuario ( Integer pkUsuario, String nome, String email ) {
        this.pkUsuario = pkUsuario;
        this.nome = nome;
        this.email = email;
    }

    public Integer getPkUsuario () {
        return pkUsuario;
    }

    public void setPkUsuario ( Integer pkUsuario ) {
        this.pkUsuario = pkUsuario;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    
    
    
    
}
