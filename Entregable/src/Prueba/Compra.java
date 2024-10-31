package Prueba;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Compra 
{

    private Moneda cripto;
    private Activo fiat;
    
    public  Compra(Moneda cripto, Activo fiat) 
    {
        this.cripto = cripto;
        this.fiat = fiat;
    }
    
    
    public boolean ejecutarTransaccion() throws SQLException {
      
        

        // Calcular equivalencia en FIAT
    	Moneda aux = fiat.getMoneda();
    	if(aux==null) {
    		System.out.println("Error: no existe dicha fiat");
    		return false;
    	}
        float cantidadEquivalenteCripto = fiat.getCantidad() * (aux.getValorDolar() / cripto.getValorDolar());
        
        // Verificar si es suficiente la cantidad en FIAT para realizar la compra
        if (cantidadEquivalenteCripto > cripto.getStock()) {
            System.out.println("Error: fondos insuficientes en " + fiat.getMoneda().getNomenclatura() + " para comprar la cantidad deseada de " + cripto.getNomenclatura() + ".");
            return false;
        }

        // Confirmación de la operación
        System.out.println("Se obtendrán " + cantidadEquivalenteCripto + " " + cripto.getNomenclatura() + " por " 
        + fiat.getCantidad() + " " + fiat.getMoneda().getNomenclatura() + ".");
        
        System.out.print("¿Desea confirmar la operación? (S/N): ");
        
        
        String confirmacion = Interfaz.pedirString();
        
        if (!confirmacion.equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada.");
            return false;
        }


        // Actualizar cantidad de criptomoneda
        MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
        monedaDAO.actualizarStock(cripto, cripto.getStock() - cantidadEquivalenteCripto);
        

        // Actualizar cantidad de moneda FIAT
        ActivoFiatDAOjdbc fiatDAO = FactoryDAO.getActivoFiatDAO();
        
        
        fiatDAO.actualizarActivo(fiat.getMoneda().getNomenclatura(), fiatDAO.obtenerCantidad(fiat.getMoneda().getNomenclatura()) - fiat.getCantidad());
        

        // Registrar la transacción
        String descripcion = "Compra de " + cantidadEquivalenteCripto + " " + cripto.getNomenclatura() + " por " + fiat.getCantidad() + " " + fiat.getMoneda().getNomenclatura();
        Transaccion transaccion = new Transaccion(descripcion, LocalDateTime.now());
        TransaccionDAOjdbc transaccionDAO = FactoryDAO.getTransaccionDAO();
        transaccionDAO.crearTransaccion(transaccion);

        System.out.println("Operación realizada con éxito.");
        return true;
    }



   
}
