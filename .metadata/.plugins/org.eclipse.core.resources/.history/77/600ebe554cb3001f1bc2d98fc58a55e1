package billetera.Modelo.Servicios;

import java.time.LocalDateTime;
import java.util.Calendar;

import billetera.Modelo.DAO.Activo;
import billetera.Modelo.DAO.Transaccion;
import billetera.Modelo.DAO.TransaccionDAOjdbc;

public class ServicioCompra 
{
    public void comprar()
    {
        //mostrar monedas
        System.err.println("Monedas del broker: ");
        ServicioMoneda servicioMoneda = new ServicioMoneda();
        servicioMoneda.imprimirMonedasOrdenado();
        // Solicitar al usuario ingresar los datos de la compra
        System.out.print("Ingrese la criptomoneda a comprar (ej. BTC): ");
        String nomenclaturaCripto = Interfaz.pedirString().toUpperCase();
        //mostrar activos fiat
        System.err.println("Activos fiat del usuario: ");
        ServicioActivos servicioActivos = new ServicioActivos();
        servicioActivos.mostrarActivosFiat();
        System.out.print("Ingrese la moneda FIAT con la que va a pagar (ej. ARS): ");
        String nomenclaturaFiat = Interfaz.pedirString().toUpperCase();

        System.out.print("Ingrese la cantidad disponible en " + nomenclaturaFiat + ": ");
        float cantidadFiat = Interfaz.pedirFloat();
        
        
        MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
        Moneda criptoMoneda = monedaDAO.buscarMonedaPorNomenclatura(nomenclaturaCripto);
        if(criptoMoneda == null) {
        	System.out.println("Error: no hay dicha cirptomoneda");
        	return;
        }else if (criptoMoneda.getStock() <= 0) {
            System.out.println("Error: no hay stock disponible de " + criptoMoneda.getNomenclatura() + ".");
            return ;
        }
        Activo fiat = new Activo(cantidadFiat, monedaDAO.buscarMonedaPorNomenclatura(nomenclaturaFiat));
        Compra compra = new Compra(criptoMoneda,fiat);
        //Si la criptomoneda a comprar no es aún un activo, se crea el nuevo activo.
        ActivoCriptoDAOjdbc activoDAO = FactoryDAO.getActivoCriptoDAO();
        
        if(activoDAO.obtenerActivoPorNomenclatura(nomenclaturaCripto)==null)
        {
            Activo nuevoActivo = new Activo((float)0, criptoMoneda);
            activoDAO.cargarActivo(nuevoActivo);
        }
        ///////aqui
        // Calcular equivalencia en FIAT
    	Moneda aux = fiat.getMoneda();
    	if(aux==null) {
    		System.out.println("Error: no existe dicha fiat");
    		return;
    	}
        float cantidadEquivalenteCripto = fiat.getCantidad() * (aux.getValorDolar() / compra.getCripto().getValorDolar());
        
        // Verificar si es suficiente la cantidad en FIAT para realizar la compra
        if (cantidadEquivalenteCripto > compra.getCripto().getStock()) {
            System.out.println("Error: fondos insuficientes en " + fiat.getMoneda().getNomenclatura() + " para comprar la cantidad deseada de " + compra.getCripto().getNomenclatura() + ".");
            return;
        }

        // Confirmación de la operación
        System.out.println("Se obtendrán " + cantidadEquivalenteCripto + " " + compra.getCripto().getNomenclatura() + " por " 
        + fiat.getCantidad() + " " + fiat.getMoneda().getNomenclatura() + ".");
        
        System.out.print("¿Desea confirmar la operación? (S/N): ");
        
        
        String confirmacion = Interfaz.pedirString();
        
        if (!confirmacion.equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada.");
            return;
        }


        // Actualizar cantidad de criptomoneda
        monedaDAO.actualizarStock(compra.getCripto(), compra.getCripto().getStock() - cantidadEquivalenteCripto);
        

        // Actualizar cantidad de moneda FIAT
        ActivoFiatDAOjdbc fiatDAO = FactoryDAO.getActivoFiatDAO();
        
        
        fiatDAO.actualizarActivo(fiat.getMoneda().getNomenclatura(), fiatDAO.obtenerCantidad(fiat.getMoneda().getNomenclatura()) - fiat.getCantidad());
        

        // Registrar la transacción
        String descripcion = "Transaccion de " + cantidadEquivalenteCripto + " " + compra.getCripto().getNomenclatura() + " por " + fiat.getCantidad() + " " + fiat.getMoneda().getNomenclatura();
        Calendar calendar = Calendar.getInstance();
        Transaccion transaccion = new Transaccion(descripcion, LocalDateTime.now(), "Compra");
        TransaccionDAOjdbc transaccionDAO = FactoryDAO.getTransaccionDAO();
        transaccionDAO.crearTransaccion(transaccion);

        System.out.println("Operación realizada con éxito.");
        return;
    }
}
