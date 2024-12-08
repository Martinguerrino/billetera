package billetera.Modelo.Servicios;

import billetera.Auxiliar.Usuario;
import java.sql.SQLException;

public class ServicioCompra 
{
    ServicioActivosFiat servicioActivosFiat = new ServicioActivosFiat();
    ServicioMoneda servicioMoneda = new ServicioMoneda();
    ServicioActivosCripto servicioActivosCripto = new ServicioActivosCripto();
    public void comprarMoneda(String criptoSeleccionada,float stock_restante,String nomenclaturaFiat, float fiatRestante,int usuario) throws SQLException 
    {
        //Compra una moneda
        //que hago
        
        servicioMoneda.actualizarStock(criptoSeleccionada, stock_restante);
        servicioActivosFiat.modificarActivo(usuario, servicioMoneda.,fiatRestante);

        

        


    }
    
}
