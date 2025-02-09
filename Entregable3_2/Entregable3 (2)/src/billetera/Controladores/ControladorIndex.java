package Controladores;

import Vista.VistaCompra;
import Vista.VistaCotizaciones;
import Vista.VistaIndex;
import Vista.VistaTransacciones;
import Vista.Ventana.VentanaInicio;
import Auxiliar.Usuario;

import java.awt.BorderLayout;
import java.sql.SQLException;

import Vista.VistaBalanceYMisActivos;
public class ControladorIndex {
	private Usuario miUsuario;
	private VistaIndex vista;
	private VentanaInicio ventana;
	

	public ControladorIndex(Usuario miUsuario, VentanaInicio ventana){
		this.miUsuario=miUsuario;
		this.ventana=ventana;
		this.vista=new VistaIndex(this);
	}
	

	public void redirigirCotizaciones() throws SQLException {
		// TODO Auto-generated method stub
		ControladorCotizaciones nuevoControlador = new ControladorCotizaciones(ventana, this, miUsuario);
		nuevoControlador.iniciar();
	}
	  public void redirigirBalanceActivos() throws SQLException {
	        // Redirigir a la vista de balance y activos
		    ControladorBalanceYMisActivos nuevoControlador = new ControladorBalanceYMisActivos(ventana, this,miUsuario);
			nuevoControlador.iniciar();
	    }

	    public void redirigirCompra() throws SQLException {
	        // Redirigir a la vista de compra
	    	ControladorCompra nuevoControlador = new ControladorCompra(ventana, this, miUsuario);
			nuevoControlador.iniciar();
	    }

	    public void redirigirTransacciones() throws SQLException {
	        // Redirigir a la vista de transacciones
	    	ControladorTransacciones nuevoControlador = new ControladorTransacciones(ventana, this, miUsuario);
			nuevoControlador.iniciar();
	    }

		public void setVista(VistaIndex nuevaVista) {
			// TODO Auto-generated method stub
			vista=nuevaVista;
			
		}

	    public void iniciar() {
	    	ventana.getContentPane().removeAll();
			ventana.getContentPane().add(vista, BorderLayout.CENTER);
			ventana.revalidate();
			ventana.repaint();
	    }

}
