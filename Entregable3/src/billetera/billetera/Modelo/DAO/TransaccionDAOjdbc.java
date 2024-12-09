package billetera.Modelo.DAO;
import billetera.Auxiliar.Persona;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.MyConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TransaccionDAOjdbc implements TransaccionDAO 
{

	@Override
    public  void crearTransaccion(Transaccion transaccion) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = MyConnection.getCon();
        try {//insertar transaccion
            String sql = "INSERT INTO TRANSACCION (RESUMEN,FECHA_HORA,ID_USUARIO) VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, transaccion.getDescripcion());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(2, transaccion.getFecha_hora().format(formato));
            stmt.setInt(3, transaccion.getUsuario().getId());
            

            stmt.executeUpdate();
        }
        catch (SQLException e) 
        {
            throw new SQLException("Error al crear transaccion: " + e.getMessage(), e);
        }
        
    }

    @Override
    public List<Transaccion> listarTransacciones() throws SQLException {
        List<Transaccion> transacciones = new ArrayList<>();
        Connection con = MyConnection.getCon();
        String sql = "SELECT t.ID, t.RESUMEN, t.FECHA_HORA, " +
                     "u.ID AS USUARIO_ID, u.ID_PERSONA, u.PASSWORD, u.ACEPTA_TERMINOS, u.MAIL, " +
                     "p.NOMBRES, p.APELLIDOS " +
                     "FROM TRANSACCION t " +
                     "INNER JOIN USUARIO u ON t.ID_USUARIO = u.ID " +
                     "INNER JOIN PERSONA p ON u.ID_PERSONA = p.ID";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int transaccionId = rs.getInt("ID");
                String resumen = rs.getString("RESUMEN");
                LocalDateTime fechaHora = rs.getTimestamp("FECHA_HORA").toLocalDateTime();

                int usuarioId = rs.getInt("USUARIO_ID");
                int idPersona = rs.getInt("ID_PERSONA");
                String password = rs.getString("PASSWORD");
                boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
                String mail = rs.getString("MAIL");
                String nombres = rs.getString("NOMBRES");
                String apellidos = rs.getString("APELLIDOS");

                Persona persona = new Persona(idPersona, nombres, apellidos);
                Usuario usuario = new Usuario(usuarioId, persona, password, aceptaTerminos, mail);

                Transaccion transaccion = new Transaccion(transaccionId, resumen, fechaHora, usuario);
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al listar las transacciones: " + e.getMessage(), e);
        }
        return transacciones;
    }
    
    /* 
    public Transaccion obtenerTransaccionesDeUsuario(int id) throws SQLException {
        Transaccion transaccion = null;
        Connection con = MyConnection.getCon();
        String sql = "SELECT t.ID, t.RESUMEN, t.FECHA_HORA, " +
                     "u.ID AS USUARIO_ID, u.ID_PERSONA, u.PASSWORD, u.ACEPTA_TERMINOS, u.MAIL, " +
                     "p.NOMBRES, p.APELLIDOS " +
                     "FROM TRANSACCION t " +
                     "INNER JOIN USUARIO u ON t.ID_USUARIO = u.ID " +
                     "INNER JOIN PERSONA p ON u.ID_PERSONA = p.ID " +
                     "WHERE t.ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int transaccionId = rs.getInt("ID");
                    String resumen = rs.getString("RESUMEN");
                    LocalDateTime fechaHora = rs.getTimestamp("FECHA_HORA").toLocalDateTime();

                    int usuarioId = rs.getInt("USUARIO_ID");
                    int idPersona = rs.getInt("ID_PERSONA");
                    String password = rs.getString("PASSWORD");
                    boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
                    String mail = rs.getString("MAIL");
                    String nombres = rs.getString("NOMBRES");
                    String apellidos = rs.getString("APELLIDOS");

                    Persona persona = new Persona(idPersona, nombres, apellidos);
                    Usuario usuario = new Usuario(usuarioId, persona, password, aceptaTerminos, mail);

                    transaccion = new Transaccion(transaccionId, resumen, fechaHora, usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener la transaccion: " + e.getMessage(), e);
        }
        return transaccion;
    }
        */
    
}
