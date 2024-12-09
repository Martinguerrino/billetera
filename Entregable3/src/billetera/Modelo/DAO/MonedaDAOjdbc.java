package Modelo.DAO;
import Auxiliar.Moneda;
import Modelo.MyConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MonedaDAOjdbc implements MonedaDAO 
{
    //TODO
    //Implementar los metodos de la interfaz MonedaDAO
    //crearMoneda
    //listarMonedas
    //buscarMonedaPorNomenclatura
    //existeNomenclatura
    //actualizarStock
    @Override
    public void crearMoneda(Moneda moneda) throws SQLException
    {
        Connection con = null;
        con = MyConnection.getCon();
        String sql = "INSERT INTO MONEDA (TIPO,NOMBRE,NOMENCLATURA,VALOR_DOLAR,VOLATILIDAD,STOCK,NOMBRE_ICONO) VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setString(1, moneda.getTipo());
            ps.setString(2, moneda.getNombre());
            ps.setString(3, moneda.getNomenclatura());
            ps.setFloat(4, moneda.getValorDolar());
            ps.setFloat(5, moneda.getVolatilidad());
            ps.setFloat(6, moneda.getStock());
            ps.setString(7, moneda.getNombreIcono());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public List<Moneda> listarMonedas() throws SQLException
    {
        Connection con = null;
        con = MyConnection.getCon();
        List<Moneda> monedas = new ArrayList<>();
        String sql = "SELECT * FROM MONEDA";
        try(Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);)
        {
            while(rs.next())
            {
                Moneda moneda = new Moneda(rs.getInt("ID"),rs.getString("TIPO"), rs.getString("NOMBRE"), rs.getString("NOMENCLATURA"), rs.getFloat("VALOR_DOLAR"), rs.getFloat("VOLATILIDAD"), rs.getString("NOMBRE_ICONO"), rs.getFloat("STOCK"));
                monedas.add(moneda);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return monedas;
    }
    public Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException
    {
        Connection con = null;
        con = MyConnection.getCon();
        Moneda moneda = null;
        String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setString(1, nomenclatura);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                moneda = new Moneda(rs.getInt("ID"),rs.getString("TIPO"), rs.getString("NOMBRE"), rs.getString("NOMENCLATURA"), rs.getFloat("VALOR_DOLAR"), rs.getFloat("VOLATILIDAD"), rs.getString("NOMBRE_ICONO"), rs.getFloat("STOCK"));
                
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return moneda;
    }

    public boolean existeNomenclatura(String nomenclatura) throws SQLException
    {
        Connection con = null;
        con = MyConnection.getCon();
        String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setString(1, nomenclatura);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void actualizarStock(String nomenclatura, float stock) throws SQLException
    {
        Connection con = null;
        con = MyConnection.getCon();
        String sql = "UPDATE MONEDA SET STOCK = ? WHERE NOMENCLATURA = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setFloat(1, stock);
            ps.setString(2, nomenclatura);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Moneda buscarMonedaPorId(int id) throws SQLException
    {
        Connection con = null;
        con = MyConnection.getCon();
        Moneda moneda = null;
        String sql = "SELECT * FROM MONEDA WHERE ID = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                moneda = new Moneda(rs.getInt("ID"),rs.getString("TIPO"), rs.getString("NOMBRE"), rs.getString("NOMENCLATURA"), rs.getFloat("VALOR_DOLAR"), rs.getFloat("VOLATILIDAD"), rs.getString("NOMBRE_ICONO"), rs.getFloat("STOCK"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return moneda;
    }
    public void actualizarValorDolar(String nomenclatura, float valorDolar) throws SQLException
    {
        Connection con = null;
        con = MyConnection.getCon();
        
        String sql = "UPDATE MONEDA SET VALOR_DOLAR = ? WHERE NOMENCLATURA = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setFloat(1, valorDolar);
            ps.setString(2, nomenclatura);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public List<Moneda> listarMonedasOrdenado()
    {
        Connection con = null;
        con = MyConnection.getCon();
        List<Moneda> monedas = new ArrayList<>();
        String sql = "SELECT * FROM MONEDA ORDER BY NOMBRE";
        try(Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql);)
        {
            while(rs.next())
            {
                Moneda moneda = new Moneda(rs.getInt("ID"),rs.getString("TIPO"), rs.getString("NOMBRE"), rs.getString("NOMENCLATURA"), rs.getFloat("VALOR_DOLAR"), rs.getFloat("VOLATILIDAD"), rs.getString("NOMBRE_ICONO"), rs.getFloat("STOCK"));
                monedas.add(moneda);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return monedas;
    }
}

