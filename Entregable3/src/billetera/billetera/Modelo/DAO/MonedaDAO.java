package billetera.Modelo.DAO;

import java.sql.*;
import java.util.List;

import billetera.billetera.Auxiliar.Moneda;

public interface MonedaDAO 
{
    void crearMoneda(Moneda moneda) throws SQLException;
    List<Moneda> listarMonedas() throws SQLException;
    Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException;
    boolean existeNomenclatura(String nomenclatura) throws SQLException;
    void actualizarStock(Moneda moneda,float stock) throws SQLException;
    Moneda buscarMonedaPorId(int id) throws SQLException;
}
