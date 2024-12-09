package billetera.Modelo.DAO;

import billetera.Auxiliar.Moneda;
import java.sql.*;
import java.util.List;



public interface MonedaDAO 
{
    void crearMoneda(Moneda moneda) throws SQLException;
    List<Moneda> listarMonedas() throws SQLException;
    Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException;
    boolean existeNomenclatura(String nomenclatura) throws SQLException;
    void actualizarStock(String nomenclatura,float stock) throws SQLException;
    Moneda buscarMonedaPorId(int id) throws SQLException;
    void actualizarValorDolar(String nomenclatura,float valorDolar) throws SQLException;
}
