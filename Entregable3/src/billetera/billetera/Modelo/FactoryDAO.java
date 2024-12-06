package billetera.Modelo;

import billetera.Modelo.DAO.*;

public class FactoryDAO 
{
    public static PersonaDAO getPersonaDAO() {
    	return new PersonaDAOjdbc();
    }
    
    public static UsuarioDAO getUsuarioDAO() {
    	return new UsuarioDAOjdbc();
    }
    
    public static ActivoDAO getActivoDAO() {
    	return new ActivoDAOjdbc();
    }
    
    public static TransaccionDAO getTransaccionDAO()
    {
        return new TransaccionDAOjdbc();
    }
}