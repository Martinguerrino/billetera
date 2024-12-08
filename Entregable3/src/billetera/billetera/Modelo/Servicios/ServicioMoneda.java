package billetera.Modelo.Servicios;

import billetera.Auxiliar.Moneda;
import billetera.Modelo.DAO.MonedaDAOjdbc;
import java.sql.SQLException;
import java.util.List;

public class ServicioMoneda 
{
    public void crearMoneda(Moneda moneda) throws SQLException
    {
        //Crea una moneda
        MonedaDAOjdbc monedaDAO = new MonedaDAOjdbc();
        monedaDAO.crearMoneda(moneda);

    }
    public List<Moneda> listarMonedas() throws SQLException
    {
        //Lista las monedas
        MonedaDAOjdbc monedaDAO = new MonedaDAOjdbc();
        return monedaDAO.listarMonedas();

    }
    
    public void existeNomenclatura()
    {
        //Verifica si existe una nomenclatura
    }
    public void actualizarStock(String nomenclatura, float stock) throws SQLException
    {
        //Actualiza el stock de una moneda
        MonedaDAOjdbc monedaDAO = new MonedaDAOjdbc();
        monedaDAO.actualizarStock(nomenclatura, stock);
    }
    public Moneda buscarMonedaPorId(int id) throws SQLException
    {
        //Busca una moneda por id
        MonedaDAOjdbc monedaDAO = new MonedaDAOjdbc();
        return monedaDAO.buscarMonedaPorId(id);

    }
    public Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException
    {
        //Busca una moneda por nomenclatura
        MonedaDAOjdbc monedaDAO = new MonedaDAOjdbc();
        return monedaDAO.buscarMonedaPorNomenclatura(nomenclatura);

    }
}
