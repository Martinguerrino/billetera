package Controladores;
import Auxiliar.Transaccion;
import Auxiliar.Usuario;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.TransaccionDAO;
import Vista.VistaTransacciones;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class ControladorTransacciones {

	VistaTransacciones miVista;
	TransaccionDAO miTransaccionDAO;
	Usuario miUsuario;
	
	public ControladorTransacciones(Usuario miUsuario){
		miTransaccionDAO = FactoryDAO.getTransaccionDAO();
		this.miUsuario=miUsuario;
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
		miVista=nuevaVista;
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
    	miVista.setVisible(true);
    }
}
