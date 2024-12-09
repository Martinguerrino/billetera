package billetera.Controladores;
import java.sql.SQLException;
import java.util.List;

import Vista.VistaTransacciones;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.FactoryDAO;
import billetera.Modelo.DAO.Transaccion;
import billetera.Modelo.DAO.TransaccionDAO;
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
		List<Transaccion> transacciones= miTransaccionDAO.listarTransacciones();
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


    public void iniciar() {
    	miVista.setVisible(true);
    }
}
