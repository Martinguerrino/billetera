package billetera.Modelo.DAO;
import java.sql.*;

public interface  TransaccionDAO 
{
    static void crearTransaccion(Transaccion transaccion) throws SQLException 
    {
        
        // TODO Auto-generated method stub
    }
    static void listarTransacciones() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTransacciones'");
    }
    static void ObtenerTransaccionesDeUsuario(int id_usuario) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTransaccionesPorUsuario'");
    }

}
