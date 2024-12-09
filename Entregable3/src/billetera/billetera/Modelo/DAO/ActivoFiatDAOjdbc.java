package billetera.Modelo.DAO;

import billetera.Auxiliar.Activo;
import billetera.Modelo.MyConnection;
import billetera.billetera.Auxiliar.Activo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivoFiatDAOjdbc implements ActivoDAO
{
    public void cargarActivo(Activo activo) 
    {
        Connection conn = null;
        conn = MyConnection.getCon();
        String sql = "INSERT INTO ACTIVO_FIAT (id_usuario, id_moneda, cantidad) VALUES (?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, activo.getId_usuario());
            ps.setInt(2, activo.getId_moneda());
            ps.setFloat(3, activo.getCantidad());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void actualizarActivo(int id_usuario, int id_moneda, float cantidad) 
    {
        Connection conn = null;
        conn = MyConnection.getCon();
        String sql = "UPDATE ACTIVO_FIAT SET cantidad = ? WHERE id_usuario = ? AND id_moneda = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setFloat(1, cantidad);
            ps.setInt(2, id_usuario);
            ps.setInt(3, id_moneda);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public List<Activo> listarActivos(int id_usuario) 
    {
        Connection conn = null;
        conn = MyConnection.getCon();
        List<Activo> activos = new ArrayList<>();
        String sql = "SELECT * FROM ACTIVO_FIAT WHERE id_usuario = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Activo activo = new Activo();
                activo.setId(rs.getInt("id"));
                activo.setId_usuario(rs.getInt("id_usuario"));
                activo.setId_moneda(rs.getInt("id_moneda"));
                activo.setCantidad(rs.getFloat("cantidad"));
                activos.add(activo);
            }
            return activos;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
                return null;
    }
    public Activo obtenerActivo(String nomenclatura)
    {
        Connection con = null;
        con = MyConnection.getCon();
        Activo activo = null;
        String sql = "SELECT * FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setString(1, nomenclatura);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                activo = new Activo(rs.getInt("ID"), rs.getInt("ID_USUARIO"), rs.getInt("ID_MONEDA"), rs.getFloat("CANTIDAD"));
                
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
    }
    return activo;
    }
}
}