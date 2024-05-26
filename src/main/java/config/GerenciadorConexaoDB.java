package config;

import constantesBD.ConstantesBancoDados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciadorConexaoDB implements IGerenciadorConexaoDB {

    private static GerenciadorConexaoDB instance;
    private Connection conexao;

    private GerenciadorConexaoDB () {
    }

    public static GerenciadorConexaoDB getInstance () {
        if ( instance == null ) {
            instance = new GerenciadorConexaoDB();
        }

        return instance;
    }

    @Override
    public void abrirConexao () {
        
        var url = "jdbc:postgresql://" + ConstantesBancoDados.HOST + ":" + ConstantesBancoDados.PORTA + "/"
                + ConstantesBancoDados.NOME_BD;

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection( url, ConstantesBancoDados.NOME_UTILIZADOR, ConstantesBancoDados.SENHA );
        }
        catch ( SQLException ex ) {
            ex.printStackTrace();
        }
        catch ( ClassNotFoundException ex ) {
            System.out.println( "Erro Driver nao encontrado" );
        }
        
    }

    @Override
    public Connection obterConexao () {
        return conexao;
    }

    @Override
    public void fecharConexao () {
        try {
            instance = null;
            conexao.close();
        }
        catch ( SQLException ex ) {
            ex.printStackTrace();
        }
    }
}
