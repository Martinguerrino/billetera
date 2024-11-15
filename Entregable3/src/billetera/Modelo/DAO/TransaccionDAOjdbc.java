package billetera.Modelo.DAO;
import billetera.Modelo.MyConnection;
import java.sql.*;
import java.time.format.DateTimeFormatter;


public class TransaccionDAOjdbc 
{

    
    public void crearTransaccion(Transaccion transaccion) throws SQLException
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
}
