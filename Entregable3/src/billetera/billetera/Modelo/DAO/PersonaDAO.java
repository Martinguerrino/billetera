package billetera.Modelo.DAO;

import billetera.Auxiliar.Persona;

public interface  PersonaDAO 
{
    void cargarPersona(Persona persona);
    void actualizarPersona(Persona persona);
    int obtenerIdPersona(String nombre, String apellido);
    
    
}
