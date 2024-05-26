package config;

import java.sql.Connection;

public interface IGerenciadorConexaoDB {

    void abrirConexao ();

    Connection obterConexao ();

    void fecharConexao ();
}
