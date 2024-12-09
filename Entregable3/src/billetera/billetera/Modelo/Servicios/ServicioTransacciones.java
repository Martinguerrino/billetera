package billetera.Modelo.Servicios;

import billetera.Modelo.DAO.Transaccion;
import billetera.Modelo.DAO.TransaccionDAOjdbc;
import java.sql.SQLException;
import java.util.List;

public class ServicioTransacciones 
{
    public void crearTransaccion(Transaccion transaccion) throws SQLException
    {
        
        try{
            TransaccionDAOjdbc.crearTransaccion(transaccion);
            }
        catch (SQLException e){
            System.out.println("Error al crear la transaccion");
    }
    }
    public List<Transaccion> listarTransacciones() throws SQLException
    {
        try 
        {
            List<Transaccion> transacciones = TransaccionDAOjdbc.listarTransacciones();
            return transacciones;
        } 
        catch (SQLException e) 
        {
            System.out.println("Error al listar las transacciones");
            return null;
        }
        
        
    }
    public void imprimirTransacciones()
    {
        try 
        {
            List<Transaccion> transacciones = listarTransacciones();
            for (Transaccion transaccion : transacciones) 
            {
                System.out.println(transaccion);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error al imprimir las transacciones");
        }
    }
    
}
