package Prueba.Transaccion;
import java.sql.*;
import java.time.format.DateTimeFormatter;

import Prueba.MyConnection;
public class TransaccionDAOjdbc 
{

    public void crearTransaccion(Transaccion transaccion) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = MyConnection.getCon();
        try {//insertar transaccion
            String sql = "INSERT INTO TRANSACCION (RESUMEN,FECHA_HORA,TIPO) VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, transaccion.getDescripcion());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            stmt.setString(2, transaccion.getFecha_hora().format(formato));
            System.out.println(transaccion.getTipo());
            stmt.setString(3, transaccion.getTipo().trim());
            stmt.executeUpdate();
            
        } catch (SQLException e) 
        {
            throw new SQLException("Error al crear transaccion: " + e.getMessage(), e);
        }
    }
}
