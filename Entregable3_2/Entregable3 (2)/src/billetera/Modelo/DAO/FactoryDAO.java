package Modelo.DAO;

public class FactoryDAO 
{
    public static PersonaDAO getPersonaDAO() {
    	return new PersonaDAOjdbc();
    }
    
    public static UsuarioDAO getUsuarioDAO() {
    	return new UsuarioDAOjdbc();
    }
    
    
    
    public static TransaccionDAO getTransaccionDAO()
    {
        return new TransaccionDAOjdbc();
    }

    public static MonedaDAO getMonedaDAO()
    {
        return new MonedaDAOjdbc();
    }
    public static ActivoDAO getActivoFiatDAO()
    {
        return new ActivoFiatDAOjdbc();
    }
    public static ActivoDAO getActivoCriptoDAO()
    {
        return new ActivoCriptoDAOjdbc();
    }
    
}