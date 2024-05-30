package DAO;

import assets.TradutorCase;
import config.GerenciadorConexaoDB;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DAOImplementacao<T extends Object, K> extends AbstractDAO<T, K> {

    private GerenciadorConexaoDB conexaoBD;
    private final Connection conexao;
    private final T entidade;

    public DAOImplementacao ( T entidade ) {
        conexaoBD = GerenciadorConexaoDB.getInstance();
        conexaoBD.abrirConexao();
        conexao = conexaoBD.obterConexao();
        this.entidade = entidade;
    }

    public String getNomeEntidade () {

        String nomeEntidade = this.entidade.getClass().getSimpleName();
        //String ultimaLetra = "";
        /*
        if ( !nomeEntidade.isEmpty() ) {
            ultimaLetra = nomeEntidade.substring( nomeEntidade.length() - 1 );
        }

        if ( ultimaLetra.equals( "o" ) ) {
            nomeEntidade = removerUltimasDuasLetras( nomeEntidade );
            return nomeEntidade + "oes";
        }
         */
        return nomeEntidade;
    }

    public String removerUltimasDuasLetras ( String palavra ) {
        if ( palavra.length() >= 2 ) {
            return palavra.substring( 0, palavra.length() - 2 );
        }
        else {
            return palavra;
        }

    }

    @Override
    public List<T> listar () throws SQLException {

        System.out.println( "==================" + this.entidade.getClass().getSimpleName() + "====================" );

        PreparedStatement sentenca = this.conexao.prepareStatement( "SELECT * FROM " + this.getNomeEntidade() + ";" );

        ResultSet resultado = sentenca.executeQuery();
        List<T> listaRetorno = new ArrayList<>();

        while ( resultado.next() ) {

            T item = this.converterParaEntidade( resultado );
            listaRetorno.add( item );

        }

        System.out.println( "===============================================" );

        return listaRetorno;
    }

    @Override
    public T buscar ( K id ) throws SQLException {
        return null;
    }

    @Override
    public String[] obterCampos () {
        var campos = entidade.getClass().getDeclaredFields();
        String strCampos = "";

        for ( var campo : campos ) {
            strCampos += campo.getName() + " ";
        }

        strCampos = strCampos.strip();
        strCampos = strCampos.replace( " ", ", " );

        return strCampos.split( ", " );
    }

    @Override
    public String[] mapearCamposTabela () {
        
        String[] campos = obterCampos();
        TradutorCase tradutor = new TradutorCase();
        String[] camposMapeados = new String[campos.length];
        
        for ( int i = 0; i < campos.length; i++ ) {
            camposMapeados[i] = tradutor.tradutorSneakCase( campos[i] );
        }
        
        return camposMapeados;
    }

    @Override
    public T converterParaEntidade ( ResultSet resultado ) throws SQLException {

        String[] camposTabela = this.mapearCamposTabela();
        String[] camposEntidade = this.obterCampos();
        String impressao = "";

        try {
            for ( int i = 0; i < camposEntidade.length; i++ ) {
                Field field = this.entidade.getClass().getDeclaredField( camposEntidade[i] );
                field.setAccessible( true );
                field.set( this.entidade, field.getType().cast( resultado.getObject( camposTabela[i] ) ) );
                impressao += resultado.getString( camposTabela[i] ) + " - ";
            }
        }
        catch ( Exception ex ) {
            throw new RuntimeException( ex );
        }

        System.out.println( impressao );

        return this.entidade;
    }

    @Override
    public void adicionar ( T entidade ) throws SQLException {

        System.err.println( this.entidade.getClass().getSimpleName() );
        String[] camposTabela = mapearCamposTabela();
        String[] camposEntidade = obterCampos();
        Field[] campos = entidade.getClass().getDeclaredFields();
        String sqlQuery = transformarQueryInsert( camposTabela );
        PreparedStatement sentenca = this.conexao.prepareStatement( sqlQuery );

        int i = 1;
        for ( Field campo : campos ) {
            campo.setAccessible( true );

            try {
                Object valor = campo.get( entidade );

                if ( valor != null ) {
                    if ( campo.getType() == String.class ) {
                        sentenca.setString( i, ( String ) valor );
                    }
                    else if ( campo.getType() == Integer.class ) {
                        sentenca.setInt( i, ( Integer ) valor );
                    }
                    else if ( campo.getType() == Double.class ) {
                        sentenca.setDouble( i, ( Double ) valor );
                    }
                    else if ( campo.getType() == Date.class ) {
                        sentenca.setDate( i, ( Date ) valor );
                    }
                    else if ( campo.getType() == Float.class ) {
                        sentenca.setFloat( i, ( Float ) valor );
                    }
                }
                else {
                    sentenca.setNull( i, Types.NULL );
                }
            }
            catch ( IllegalAccessException e ) {
                throw new RuntimeException( e );
            }
            i++;
        }

        int linhasAfectadas = sentenca.executeUpdate();
        System.out.println( "Linhas afectadas: " + linhasAfectadas );
    }

    private String transformarQueryInsert ( String[] camposTabela ) {
        String sqlQuery = "INSERT INTO " + this.getNomeEntidade() + "( ";
        for ( int i = 0; i < camposTabela.length; i++ ) {
            sqlQuery += camposTabela[i];
            if ( ( i < camposTabela.length - 1 ) ) {
                sqlQuery += ",";
            }
        }
        sqlQuery += ") values (";
        for ( int i = 0; i < camposTabela.length; i++ ) {

            if ( i == camposTabela.length - 1 ) {
                sqlQuery += "?";
            }
            else {
                sqlQuery += "?, ";
            }
        }
        sqlQuery += ");";
        return sqlQuery;
    }

    @Override
    public void remover ( K id ) throws SQLException {
        System.out.println( this.entidade.getClass().getSimpleName() );

        String campoIdMapeado = pegarIdMapeado();
        String sqlQuery = "DELETE FROM " + this.getNomeEntidade() + " WHERE " + campoIdMapeado + " = " + id + ";";
        PreparedStatement sentenca = this.conexao.prepareStatement( sqlQuery );
        int s = sentenca.executeUpdate();
        System.out.println( "fim===" + s );
    }

    private String pegarIdMapeado () {
        Field[] field = this.entidade.getClass().getDeclaredFields();
        TradutorCase tradutor = new TradutorCase();
        return tradutor.tradutorSneakCase( field[0].getName() );
    }

    @Override
    public void actualizar ( K id, T entidade ) throws SQLException {

        String idMapeado = pegarIdMapeado();
        String sqlQuery = "UPDATE " + this.getNomeEntidade() + " SET ";
        String[] camposTabela = mapearCamposTabela();
        String[] camposEntidade = obterCampos();
        Field[] fields = entidade.getClass().getDeclaredFields();
        int i = 0, j = 0;

        for ( Field field : fields ) {

            field.setAccessible( true );
            try {
                Object valor = field.get( entidade );
                if ( valor != null ) {
                    sqlQuery += camposTabela[i] + " = ?";
                    j++;
                }
                System.out.println( valor + " " + field.getName() );
            }
            catch ( IllegalAccessException e ) {
                throw new RuntimeException( e );
            }
            if ( !( i == camposEntidade.length - 1 ) ) {
                sqlQuery += ",";
            }
            i++;
        }

        if ( j == 1 ) {
            sqlQuery = sqlQuery.substring( 0, sqlQuery.length() - 1 );
        }

        sqlQuery += " WHERE " + idMapeado + " = " + id + ";";
        PreparedStatement sentenca = this.conexao.prepareStatement( sqlQuery );

        for ( int contador = 0; contador < camposEntidade.length; contador++ ) {

            fields[contador].setAccessible( true );
            try {
                Object valor = fields[contador].get( entidade );
                if ( valor != null ) {
                    if ( fields[contador].getType() == String.class ) {
                        sentenca.setString( contador + 1, ( String ) valor );
                    }
                    else if ( fields[contador].getType() == Integer.class ) {
                        sentenca.setInt( contador + 1, ( Integer ) valor );
                    }
                    else if ( fields[contador].getType() == Double.class ) {
                        sentenca.setDouble( contador + 1, ( Double ) valor );
                    }
                    else if ( fields[contador].getType() == Date.class ) {
                        sentenca.setDate( contador + 1, ( Date ) valor );
                    }
                    else if ( fields[contador].getType() == Float.class ) {
                        sentenca.setFloat( contador + 1, ( Float ) valor );
                    }
                }
                else {
                    sentenca.setNull( contador, Types.NULL );
                }
            }
            catch ( IllegalAccessException e ) {
                throw new RuntimeException( e );
            }
        }

        System.out.println( sqlQuery );
        //PreparedStatement sentenca = this.conexao.prepareStatement(sqlQuery);
        int rows = sentenca.executeUpdate();
        System.out.println( rows );
    }
}
