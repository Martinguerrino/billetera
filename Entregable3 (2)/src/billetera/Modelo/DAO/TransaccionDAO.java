package Modelo.DAO;
import java.sql.*;
import java.util.List;

import Auxiliar.Transaccion;

public interface  TransaccionDAO 
{
     void crearTransaccion(Transaccion transaccion) throws SQLException ;
    List<Transaccion> listarTransacciones() throws SQLException;
    //void ObtenerTransaccionesDeUsuario(int id_usuario) throws SQLException;

}
