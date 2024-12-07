package billetera.Controladores;

import java.util.List;

import Aux.Moneda;
import Vista.VistaCompra;

public class ControladorCompra {
    private VistaCompra miVista;
    private ModeloCripto miModelo;//pense una re buena martucho, los modelos son de las cosas de bases de datos, asi obtenemos las cosas q necesitamos de ahi y desp los metodos de controlador parsean todo lo q pidan la vista re loco mal
    /*private ServicioCompra servicioCompra;

    public ControladorCompra(Vista vista, ServicioCompra servicioCompra) {
        this.vista = vista;
        this.servicioCompra = servicioCompra;
    }

    public void controladorCompra() {
        vista.mostrarCompra();
        // Lógica para manejar la compra
        while (!servicioCompra.comprar(monedaFiat, cantidad, moneda)) {
            vista.mostrarErrorCompra();
            vista.mostrarCompra();
        }
    }*/
    public boolean comprarCripto(String criptoSeleccionada, double cantidad) {
    	if(!miModelo.getCripto(criptoSeleccionada)) {
    		return false;
    	}
    	return true;
    }
    public String[] obtenerCriptos() {
    	List<Moneda> monedas= miModelo.obtenerCriptos();//JKASJDKAS RE XD ESTE METODO
    	String[] array = monedas.toArray(new String[0]);//parseo crazy son las 3 am me quiero dormir
    	return array;//xd
    }
	public void setVista(VistaCompra nuevaVista) {
		// TODO Auto-generated method stub
		miVista=nuevaVista;
	}
}
