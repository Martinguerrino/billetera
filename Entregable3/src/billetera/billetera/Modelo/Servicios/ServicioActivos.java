package billetera.Modelo.Servicios;

import billetera.Auxiliar.Activo;
import billetera.Modelo.DAO.ActivoDAOjdbc;
import java.util.List;

public class ServicioActivos 
{
    public  List<Activo> obtenerActivos(int id_usuario)
    {
        ActivoDAOjdbc activoDAO = new ActivoDAOjdbc();
        
        return activoDAO.listarActivos(id_usuario);
    }
    public void agregarActivo(Activo activo)
    {
        //Agrega un activo a la billetera
        ActivoDAOjdbc activoDAO = new ActivoDAOjdbc();
        activoDAO.cargarActivo(activo);


    }
    public void eliminarActivo()
    {
        //Elimina un activo de la billetera
        //por ahora no se implementa ya que no se pide en el entregable
    }
    public void modificarActivo()
    {
        //Modifica un activo de la billetera
        //que hago
        

    }

}
