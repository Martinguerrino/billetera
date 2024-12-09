package billetera.Modelo.DAO;
import billetera.Modelo.MyConnection;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TransaccionDAOjdbc implements TransaccionDAO 
{

    @Override
    public static void crearTransaccion(Transaccion transaccion) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = MyConnection.getCon();
        try {//insertar transaccion
            String sql = "INSERT INTO TRANSACCION (ID,RESUMEN,FECHA_HORA,ID_USUARIO) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(2, transaccion.getDescripcion());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(3, transaccion.getFecha_hora().format(formato));
            stmt.setInt(1, transaccion.getId_Usuario());
            stmt.setInt(4, transaccion.getId());

            stmt.executeUpdate();
        }
        catch (SQLException e) 
        {
            throw new SQLException("Error al crear transaccion: " + e.getMessage(), e);
        }
        
    }
    @Override
    public static List<Transaccion> listarTransacciones() throws SQLException 
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Transaccion> transacciones = new ArrayList<>();
        Transaccion transaccion= new Transaccion();
        conn = MyConnection.getCon();
        try 
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM TRANSACCION");
            while (rs.next()) 
            {
                transaccion.setId(rs.getInt("ID"));
                transaccion.setDescripcion(rs.getString("RESUMEN"));
                transaccion.setFecha_hora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
                transaccion.setId_Usuario(rs.getInt("ID_USUARIO"));
                transacciones.add(transaccion);

            }
            return transacciones;
        } 
        catch (SQLException e) 
        {
            throw new SQLException("el acceso a las transacciones fue erroneo");
        }
    }
    @Override
    public List<Transaccion> obtenerTransaccionesDeUsuario(int idUsuario) throws SQLException {
        List<Transaccion> transacciones = new ArrayList<>();
        Connection con = MyConnection.getCon();
        String sql = "SELECT t.ID, t.RESUMEN, t.FECHA_HORA, t.ID_USUARIO, u.MAIL " +
                     "FROM TRANSACCION t " +
                     "INNER JOIN USUARIO u ON t.ID_USUARIO = u.ID " +
                     "WHERE t.ID_USUARIO = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion();
                    transaccion.setId(rs.getInt("ID"));
                    transaccion.setDescripcion(rs.getString("RESUMEN"));
                    transaccion.setFecha_hora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
                    transaccion.setId_Usuario(rs.getInt("ID_USUARIO"));
                    transacciones.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener las transacciones del usuario: " + e.getMessage(), e);
        }
        return transacciones;
    }
    
}
