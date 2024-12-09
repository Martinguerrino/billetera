package billetera.Modelo.DAO;

import billetera.Auxiliar.Persona;
import java.sql.SQLException;

public interface  PersonaDAO 
{
    int cargarPersona(Persona persona) throws SQLException;
    void actualizarPersona(Persona persona);
    int obtenerIdPersona(String nombre, String apellido);
    
    
}
