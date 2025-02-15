package Prueba;

import Prueba.Activos.ActivoCriptoDAOjdbc;
import Prueba.Activos.ActivoFiatDAOjdbc;
import Prueba.Monedas.MonedaDAOjdbc;
import Prueba.Transaccion.TransaccionDAOjdbc;

public class FactoryDAO 
{
    public static MonedaDAOjdbc getMonedaDAO()
    {
        return new MonedaDAOjdbc();
    }

    public static ActivoCriptoDAOjdbc getActivoCriptoDAO()
    {
        return new ActivoCriptoDAOjdbc();
    }

    public static ActivoFiatDAOjdbc getActivoFiatDAO()
    {
        return new ActivoFiatDAOjdbc();
    }
    public static TransaccionDAOjdbc getTransaccionDAO()
    {
        return new TransaccionDAOjdbc();
    }
}
