package billetera.Modelo.DAO;

import billetera.Auxiliar.Activo;
import billetera.Auxiliar.Moneda;
import billetera.Modelo.MyConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivoCriptoDAOjdbc implements ActivoDAO
{
    public void cargarActivo(Activo activo) 
    {
        Connection conn = null;
        conn = MyConnection.getCon();
        String sql = "INSERT INTO ACTIVO_CRIPTO (id_usuario, id_moneda, cantidad) VALUES (?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, activo.getUsuario().getId());
            ps.setInt(2, activo.getMoneda().getId());
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
        String sql = "UPDATE ACTIVO_CRIPTO SET cantidad = ? WHERE id_usuario = ? AND id_moneda = ?";
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
    
    @Override
    public Activo obtenerActivoPorNomenclatura(String nomenclatura) throws SQLException {
        Connection con = MyConnection.getCon();
        Activo activo = null;
        String sql = "SELECT a.ID, a.CANTIDAD, m.ID AS MONEDA_ID, m.TIPO, m.NOMBRE, m.NOMENCLATURA, m.VALOR_DOLAR, m.VOLATILIDAD, m.STOCK, m.NOMBRE_ICONO " +
                     "FROM ACTIVO_CRIPTO a " +
                     "INNER JOIN MONEDA m ON a.ID_MONEDA = m.ID " +
                     "WHERE m.NOMENCLATURA = ? " +
                     "UNION ALL " ;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nomenclatura);
            ps.setString(2, nomenclatura);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    activo = new Activo();
                    activo.setId(rs.getInt("ID"));
                    activo.setCantidad(rs.getFloat("CANTIDAD"));
                    Moneda moneda = new Moneda();
                    moneda.setId(rs.getInt("MONEDA_ID"));
                    moneda.setTipo(rs.getString("TIPO"));
                    moneda.setNombre(rs.getString("NOMBRE"));
                    moneda.setNomenclatura(rs.getString("NOMENCLATURA"));
                    moneda.setValorDolar(rs.getFloat("VALOR_DOLAR"));
                    moneda.setVolatilidad(rs.getFloat("VOLATILIDAD"));
                    moneda.setStock(rs.getFloat("STOCK"));
                    moneda.setNombreIcono(rs.getString("NOMBRE_ICONO"));
                    activo.setMoneda(moneda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener el activo por nomenclatura: " + e.getMessage(), e);
        }
        return activo;
    }

    @Override
public List<Activo> listarActivos(int id_usuario) throws SQLException {
    Connection conn = MyConnection.getCon();
    List<Activo> activos = new ArrayList<>();
    String sql = "SELECT a.ID, a.CANTIDAD, m.ID AS MONEDA_ID, m.TIPO, m.NOMBRE, m.NOMENCLATURA, m.VALOR_DOLAR, m.VOLATILIDAD, m.STOCK, m.NOMBRE_ICONO " +
                     "FROM ACTIVO_CRIPTO a " +
                     "INNER JOIN MONEDA m ON a.ID_MONEDA = m.ID " +
                     "WHERE a.ID_USUARIO = ? " +
                     "UNION ALL " ;
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id_usuario);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Activo activo = new Activo();
                activo.setId(rs.getInt("ID"));
                activo.setCantidad(rs.getFloat("CANTIDAD"));
                Moneda moneda = new Moneda();
                moneda.setId(rs.getInt("MONEDA_ID"));
                moneda.setTipo(rs.getString("TIPO"));
                moneda.setNombre(rs.getString("NOMBRE"));
                moneda.setNomenclatura(rs.getString("NOMENCLATURA"));
                moneda.setValorDolar(rs.getFloat("VALOR_DOLAR"));
                moneda.setVolatilidad(rs.getFloat("VOLATILIDAD"));
                moneda.setStock(rs.getFloat("STOCK"));
                moneda.setNombreIcono(rs.getString("NOMBRE_ICONO"));
                activo.setMoneda(moneda);
                activos.add(activo);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al listar los activos cripto del usuario: " + e.getMessage(), e);
    }
    return activos;
}
@Override
public Activo obtenerActivoPorId(int id) throws SQLException {
    Activo activo = null;
    Connection conn = MyConnection.getCon();
    String sql = "SELECT a.ID, a.CANTIDAD, m.TIPO, m.NOMBRE, m.NOMENCLATURA, m.VALOR_DOLAR, m.VOLATILIDAD, m.STOCK, m.NOMBRE_ICONO " +
                 "FROM ACTIVO_CRIPTO a " +
                 "INNER JOIN MONEDA m ON a.ID_MONEDA = m.ID " +
                 "WHERE a.ID = ? " +
                 "UNION ALL " +
                 "SELECT a.ID, a.CANTIDAD, m.TIPO, m.NOMBRE, m.NOMENCLATURA, m.VALOR_DOLAR, m.VOLATILIDAD, m.STOCK, m.NOMBRE_ICONO " +
                 "FROM ACTIVO_FIAT a " +
                 "INNER JOIN MONEDA m ON a.ID_MONEDA = m.ID " +
                 "WHERE a.ID = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.setInt(2, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                activo = new Activo();
                activo.setId(rs.getInt("ID"));
                activo.setCantidad(rs.getFloat("CANTIDAD"));
                Moneda moneda = new Moneda();
                moneda.setTipo(rs.getString("TIPO"));
                moneda.setNombre(rs.getString("NOMBRE"));
                moneda.setNomenclatura(rs.getString("NOMENCLATURA"));
                moneda.setValorDolar(rs.getFloat("VALOR_DOLAR"));
                moneda.setVolatilidad(rs.getFloat("VOLATILIDAD"));
                moneda.setStock(rs.getFloat("STOCK"));
                moneda.setNombreIcono(rs.getString("NOMBRE_ICONO"));
                activo.setMoneda(moneda);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al obtener el activo por ID: " + e.getMessage(), e);
    }
    return activo;
}
}