package billetera.Modelo.Servicios;

import billetera.Auxiliar.Activo;
import billetera.Modelo.DAO.ActivoDAOjdbc;
import billetera.Modelo.DAO.ActivoCriptoDAOjdbc;

import java.util.List;

public class ServicioActivosCripto 
{
    public  List<Activo> obtenerActivos(int id_usuario)
    {
        ActivoCriptoDAOjdbc activoDAO = new ActivoCriptoDAOjdbc();
        
        return activoDAO.listarActivos(id_usuario);
    }
    public void agregarActivo(Activo activo)
    {
        //Agrega un activo a la billetera
        ActivoCriptoDAOjdbc activoDAO = new ActivoCriptoDAOjdbc();
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
        ActivoCriptoDAOjdbc activoDAO = new ActivoCriptoDAOjdbc();
        activoDAO.actualizarActivo(id_usuario, id_moneda, cantidad);
    }

}
