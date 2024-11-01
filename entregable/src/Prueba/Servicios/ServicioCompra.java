package Prueba.Servicios;

import Prueba.Activos.Activo;
import Prueba.Activos.ActivoCriptoDAOjdbc;
import Prueba.FactoryDAO;
import Prueba.Interfaz;
import Prueba.Monedas.Moneda;
import Prueba.Monedas.MonedaDAOjdbc;
import java.sql.SQLException;

public class ServicioCompra 
{
    public  void comprar() throws SQLException
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
        
        compra.comprar();
        
    }
    
}
