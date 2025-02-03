package Controladores;
import Auxiliar.Transaccion;
import Auxiliar.Usuario;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.TransaccionDAO;
import Vista.VistaTransacciones;
import Vista.Ventana.VentanaInicio;

import java.awt.BorderLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class ControladorTransacciones {

	private VistaTransacciones vista;
	private TransaccionDAO miTransaccionDAO;
	private Usuario miUsuario;
	private VentanaInicio ventana;
	private ControladorIndex controladorPrincipal;
	
	public ControladorTransacciones(VentanaInicio ventana,ControladorIndex controladorIndex,Usuario miUsuario) throws SQLException{
		miTransaccionDAO = FactoryDAO.getTransaccionDAO();
		this.miUsuario=miUsuario;
		this.ventana=ventana;
		controladorPrincipal=controladorIndex;
		vista = new VistaTransacciones(this);
	}
	
	public Object[][] obtenerTransacciones() throws SQLException {
		// TODO Auto-generated method stub
		List<Transaccion> transacciones= miTransaccionDAO.listarTransaccionesDeUsuario(miUsuario.getId());
		Object[][] vectorRetorno= new Object[transacciones.size()][5]; 
		int contadorsito=0;
    	for (Transaccion transaccion : transacciones) {
			vectorRetorno[contadorsito][0]=transaccion.getUsuario().getPersona().getNombre()+ "  " + transaccion.getUsuario().getPersona().getApellido();
			vectorRetorno[contadorsito][1]=transaccion.getFecha_hora();
			vectorRetorno[contadorsito][2]=transaccion.getDescripcion();
			contadorsito++;
		}
		return vectorRetorno;
	}

	public void setMiVista(VistaTransacciones nuevaVista) {
		// TODO Auto-generated method stub
		vista=nuevaVista;
	}
	public void exportarTransaccionesACSV(String filePath) throws SQLException, IOException {
        List<Transaccion> transacciones = miTransaccionDAO.listarTransaccionesDeUsuario(miUsuario.getId());
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribir la cabecera del CSV
            writer.append("Usuario,FechaHora,Resumen\n");

            // Escribir los datos de las transacciones
            for (Transaccion transaccion : transacciones) {
                writer.append(transaccion.getUsuario().getPersona().getNombre())
                      .append(',')
                      .append(transaccion.getFecha_hora().toString())
                      .append(',')
                      .append(transaccion.getDescripcion())
                      .append('\n');
            }
        }
	}
    public void iniciar() {
    	ventana.getContentPane().removeAll();
		ventana.getContentPane().add(vista, BorderLayout.CENTER);
		ventana.revalidate();
		ventana.repaint();
    }
    
    public void redirigirIndex() {
		controladorPrincipal.iniciar();
	}
}
