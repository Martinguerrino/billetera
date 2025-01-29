package Controladores;

import Vista.VistaCompra;
import Vista.VistaCotizaciones;
import Vista.VistaIndex;
import Vista.VistaTransacciones;
import Auxiliar.Usuario;

import java.sql.SQLException;

import Vista.VistaBalanceYMisActivos;
public class ControladorIndex {
	private Usuario miUsuario;
	private VistaIndex vista;
	
	public void LogOut() {
		// TODO Auto-generated method stub
		vista.dispose();

	}
	public ControladorIndex(Usuario miUsuario){
		this.miUsuario=miUsuario;
	}
	

	public void redirigirCotizaciones() throws SQLException {
		// TODO Auto-generated method stub
		ControladorCotizaciones nuevoControlador = new ControladorCotizaciones(miUsuario);
		VistaCotizaciones nuevaVista = new VistaCotizaciones(nuevoControlador);
		nuevoControlador.setVista(nuevaVista);
		nuevoControlador.iniciar();
	}
	  public void redirigirBalanceActivos() throws SQLException {
	        // Redirigir a la vista de balance y activos
	        ControladorBalanceYMisActivos nuevoControlador = new ControladorBalanceYMisActivos(miUsuario);
	        VistaBalanceYMisActivos nuevaVista = new VistaBalanceYMisActivos(nuevoControlador);
	        nuevoControlador.setVista(nuevaVista);
			nuevoControlador.iniciar();
	    }

	    public void redirigirCompra() throws SQLException {
	        // Redirigir a la vista de compra
	        ControladorCompra nuevoControlador = new ControladorCompra(miUsuario);
	        VistaCompra nuevaVista = new VistaCompra(nuevoControlador);
	        nuevoControlador.setVista(nuevaVista);
			nuevoControlador.iniciar();
	    }

	    public void redirigirTransacciones() throws SQLException {
	        // Redirigir a la vista de transacciones
	        ControladorTransacciones nuevoControlador = new ControladorTransacciones(miUsuario);
	        VistaTransacciones nuevaVista = new VistaTransacciones(nuevoControlador);
	        nuevoControlador.setVista(nuevaVista);
			nuevoControlador.iniciar();
	    }

		public void setVista(VistaIndex nuevaVista) {
			// TODO Auto-generated method stub
			vista=nuevaVista;
			
		}

	    public void iniciar() {
	    	vista.setVisible(true);
	    }

}
