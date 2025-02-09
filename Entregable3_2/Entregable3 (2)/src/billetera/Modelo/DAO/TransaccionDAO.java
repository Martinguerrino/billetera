package Modelo.DAO;
import Auxiliar.Transaccion;
import java.sql.*;
import java.util.List;

public interface  TransaccionDAO 
{
    void crearTransaccion(Transaccion transaccion) throws SQLException ;
    List<Transaccion> listarTransacciones() throws SQLException;
    List<Transaccion> listarTransaccionesDeUsuario(int id_usuario) throws SQLException;

}
