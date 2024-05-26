/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author valdemar
 */
public class Login {
    
    private Integer pkLogin;
    private String usuario;
    private String senha;
    private Integer fkUsuario;

    public Login () {
    }

    public Login ( Integer pkLogin, String usuario, String senha, Integer fkUsuario ) {
        this.pkLogin = pkLogin;
        this.usuario = usuario;
        this.senha = senha;
        this.fkUsuario = fkUsuario;
    }

    public Integer getPkLogin () {
        return pkLogin;
    }

    public void setPkLogin ( Integer pkLogin ) {
        this.pkLogin = pkLogin;
    }

    public String getUsuario () {
        return usuario;
    }

    public void setUsuario ( String usuario ) {
        this.usuario = usuario;
    }

    public String getSenha () {
        return senha;
    }

    public void setSenha ( String senha ) {
        this.senha = senha;
    }

    public Integer getFkUsuario () {
        return fkUsuario;
    }

    public void setFkUsuario ( Integer fkUsuario ) {
        this.fkUsuario = fkUsuario;
    }

    
    
    
    
}
