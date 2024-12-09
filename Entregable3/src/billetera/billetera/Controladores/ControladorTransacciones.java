package billetera.Controladores;
import java.sql.SQLException;
import java.util.List;

import Vista.VistaTransacciones;
import billetera.Modelo.DAO.Transaccion;
import billetera.Modelo.DAO.TransaccionDAO;
public class ControladorTransacciones {

	VistaTransacciones miVista;
	TransaccionDAO miTransaccionDAO;
	
	public Object[][] obtenerTransacciones() throws SQLException {
		// TODO Auto-generated method stub
		List<Transaccion> transacciones= miTransaccionDAO.listarTransacciones();
		Object[][] vectorRetorno= new Object[transacciones.size()][5]; 
		int contadorsito=0;
    	for (Transaccion transaccion : transacciones) {
			vectorRetorno[contadorsito][0]=transaccion.getUsuario().getNombre();
			vectorRetorno[contadorsito][1]=transaccion.getFecha_hora();
			vectorRetorno[contadorsito][2]=transaccion.getResumen();
			contadorsito++;
		}
		return vectorRetorno;
	}

}
