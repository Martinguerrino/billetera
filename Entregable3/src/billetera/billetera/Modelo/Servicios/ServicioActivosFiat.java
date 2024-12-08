package billetera.Modelo.Servicios;

import billetera.Auxiliar.Activo;
import billetera.Modelo.DAO.ActivoDAOjdbc;
import billetera.Modelo.DAO.ActivoFiatDAOjdbc;

import java.util.List;

public class ServicioActivosFiat 
{
    public  List<Activo> obtenerActivos(int id_usuario)
    {
        ActivoFiatDAOjdbc activoDAO = new ActivoFiatDAOjdbc();
        
        return activoDAO.listarActivos(id_usuario);
    }
    public void agregarActivo(Activo activo)
    {
        //Agrega un activo a la billetera
        ActivoFiatDAOjdbc activoDAO = new ActivoFiatDAOjdbc();
        activoDAO.cargarActivo(activo);


    }
    public void eliminarActivo()
    {
        //Elimina un activo de la billetera
        //por ahora no se implementa ya que no se pide en el entregable
    }
    public void modificarActivo(int id_usuario,int id_moneda, float cantidad)
    {
        //Modifica un activo de la billetera
        //que hago
        ActivoFiatDAOjdbc activoDAO = new ActivoFiatDAOjdbc();
        activoDAO.actualizarActivo(id_usuario, id_moneda, cantidad);
    }

}
