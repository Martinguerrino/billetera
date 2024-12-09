package billetera.Modelo.DAO;
import java.sql.*;
import java.util.List;

public interface  TransaccionDAO 
{
	public void crearTransaccion(Transaccion transaccion) throws SQLException ;

    public List<Transaccion> listarTransacciones() throws SQLException;
    public List<Transaccion> ObtenerTransaccionesDeUsuario(int id_usuario) throws SQLException;

}
