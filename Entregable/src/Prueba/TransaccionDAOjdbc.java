package Prueba;
import java.sql.*;
public class TransaccionDAOjdbc 
{

    public void crearTransaccion(Transaccion transaccion) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = MyConnection.getCon();
        try {//insertar transaccion
            String sql = "INSERT INTO TRANSACCION (RESUMEN,FECHA_HORA) VALUES (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, transaccion.getDescripcion());
            stmt.setTimestamp(2, Timestamp.valueOf(transaccion.getFecha_hora()));
            stmt.executeUpdate();
            
        } catch (SQLException e) 
        {
            throw new SQLException("Error al crear transaccion: " + e.getMessage(), e);
        }
    }
}
