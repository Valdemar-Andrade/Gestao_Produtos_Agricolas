package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T, K> {

    public abstract List<T> listar () throws SQLException;

    public abstract T buscar ( K id ) throws SQLException;

    public abstract String[] obterCampos () throws SQLException;

    public abstract String[] mapearCamposTabela ();

    public abstract T converterParaEntidade ( ResultSet resultado ) throws SQLException;

    public abstract void adicionar ( T entidade ) throws SQLException;

    public abstract void remover ( K id ) throws SQLException;

    public abstract void actualizar ( K id, T entidade ) throws SQLException;
}
