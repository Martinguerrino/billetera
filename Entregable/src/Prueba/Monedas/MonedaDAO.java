package Prueba.Monedas;
import java.sql.SQLException;
import java.util.List;

public interface MonedaDAO 
{
    void crearMoneda(Moneda moneda) throws SQLException;
    List<Moneda> listarMonedas() throws SQLException;
    void generarStockAleatorio() throws SQLException;
    Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException;
    boolean existeNomenclatura(String nomenclatura) throws SQLException;
    void actualizarStock(Moneda moneda,float stock) throws SQLException;
}
