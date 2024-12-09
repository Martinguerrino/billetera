package billetera.Controladores;

import Vista.VistaCompra;
import Vista.VistaCotizaciones;
import Vista.VistaIndex;
import Vista.VistaTransacciones;
import Vista.VistaBalanceYMisActivos;
public class ControladorIndex {

	private VistaIndex miVista;
	
	public void LogOut() {
		// TODO Auto-generated method stub
		miVista.dispose();

	}

	public void redirigirCotizaciones() {
		// TODO Auto-generated method stub
		ControladorCotizaciones nuevoControlador = new ControladorCotizaciones();
		VistaCotizaciones nuevaVista = new VistaCotizaciones(nuevoControlador);
		nuevoControlador.setVista(nuevaVista);
	}
	  public void redirigirBalanceActivos() {
	        // Redirigir a la vista de balance y activos
	        ControladorBalanceYMisActivos nuevoControlador = new ControladorBalanceYMisActivos();
	        VistaBalanceYMisActivos nuevaVista = new VistaBalanceYMisActivos(nuevoControlador);
	        nuevoControlador.setVista(nuevaVista);
	    }

	    public void redirigirCompra() {
	        // Redirigir a la vista de compra
	        ControladorCompra nuevoControlador = new ControladorCompra();
	        VistaCompra nuevaVista = new VistaCompra(nuevoControlador);
	        nuevoControlador.setVista(nuevaVista);
	    }

	    public void redirigirTransacciones() {
	        // Redirigir a la vista de transacciones
	        ControladorTransacciones nuevoControlador = new ControladorTransacciones();
	        VistaTransacciones nuevaVista = new VistaTransacciones(nuevoControlador);
	        nuevoControlador.setVista(nuevaVista);
	    }

}