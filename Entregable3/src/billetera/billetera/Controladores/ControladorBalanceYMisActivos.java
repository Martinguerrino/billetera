package billetera.Controladores;

import java.util.List;

import billetera.Auxiliar.Activo;
import Vista.VistaBalanceYMisActivos;
import billetera.Auxiliar.Activo;
import billetera.Auxiliar.Moneda;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.ActivoCriptoDAOjdbc;
import billetera.Modelo.DAO.ActivoDAO;
import billetera.Modelo.DAO.ActivoFiatDAOjdbc;
import billetera.Modelo.DAO.MonedaDAO;
import billetera.Modelo.DAO.MonedaDAOjdbc;
import java.sql.SQLException;


public class ControladorBalanceYMisActivos {

	private Usuario miUsuario;
	private VistaBalanceYMisActivos miVistaBalanceYMisActivos;
	private ActivoDAO miActivoCriptoDAO;
	private ActivoDAO miActivoFiatDAO;
	private MonedaDAO miActivoMoneda;
	

	
	ControladorBalanceYMisActivos()
	{
		
	}
	

	
	public ControladorBalanceYMisActivos(Usuario miUsuario, VistaBalanceYMisActivos miVistaBalanceYMisActivos) {
		this.miUsuario = miUsuario;
		this.miVistaBalanceYMisActivos = miVistaBalanceYMisActivos;
	}



	public Object[][] obtenerActivosCripto() throws SQLException {
		// TODO Auto-generated method stub
		ActivoCriptoDAOjdbc activoDAO = new ActivoCriptoDAOjdbc();
		MonedaDAOjdbc monedaDAO = new MonedaDAOjdbc();
		//quien llama a este controlador debe pasar el id del usuario
		//se me ocurre que al controlador se le pase el usuario ya que que necesita cosas del usuario que busca sus cosas
		List<Activo> misCripto= activoDAO.listarActivos(miUsuario.getId());
		//ESTO ESTA COMENTADO PORQUE LO CAMBIE PERO NOSE QUE QUERES DECIR CON MIMODELO 
		//List<Activo> misCripto= miModelo.getActivosCripto();
		int c=0;
		Object[][] returnArray= new Object[misCripto.size()][4];
		for (Activo activo : misCripto) {
			//CHE AMIGO QUE PINGO ES ESTO DE retrunArray[c][0] Y ESO
			//voy a escribir en comentarios otra forma de esto por como quedo el modelo
			/* 
			Moneda moneda = monedaDAO.getMoneda(activo.getId_moneda());
			returnArray[c][0]=moneda.getNombre();
			returnArray[c][1]=moneda.getNomenclatura();
			returnArray[c][2]=activo.getCantidad();
			returnArray[c][3]=moneda.getNombreIcono();
			returnArray[c][4]=moneda.getValorDolar();
			c++;
			*/


			
			returnArray[c][0]=activo.getMoneda().getNombre();
			returnArray[c][1]=activo.getMoneda().getNomenclatura();
			returnArray[c][2]=activo.getMoneda().getValorDolar();
			returnArray[c][3]=activo.getMoneda().getNombreIcono();
			c++;
		}
		
		return returnArray;
	}

	public Object[][] ObtenerActivosFiat() throws SQLException {
		// TODO Auto-generated method stub
		ActivoFiatDAOjdbc activoDAO = new ActivoFiatDAOjdbc();
		MonedaDAOjdbc monedaDAO = new MonedaDAOjdbc();
		//quien llama a este controlador debe pasar el id del usuario
		//se me ocurre que al controlador se le pase el usuario ya que que necesita cosas del usuario que busca sus cosas
		List<Activo> misFiat= activoDAO.listarActivos(miUsuario.getId());
		//List<Activo> misFiat= miModelo.getActivosFiat();
		int c=0;
		Object[][] returnArray= new Object[misFiat.size()][4];
		for (Activo activo : misFiat) {
			//voy a escribir en comentarios otra forma de esto por como quedo el modelo
			/* 
			Moneda moneda = monedaDAO.getMoneda(activo.getId_moneda());
			returnArray[c][0]=moneda.getNombre();
			returnArray[c][1]=moneda.getNomenclatura();
			returnArray[c][2]=activo.getCantidad();
			returnArray[c][3]=moneda.getNombreIcono();
			returnArray[c][4]=moneda.getValorDolar();
			c++;
			*/
			returnArray[c][0]=activo.getMoneda().getNombre();
			returnArray[c][1]=activo.getMoneda().getNomenclatura();
			returnArray[c][2]=activo.getMoneda().getValorDolar();
			returnArray[c][3]=activo.getMoneda().getNombreIcono();
			c++;
		}
		
		return returnArray;
	}
	public float ObtenerSaldo() throws SQLException {
		// TODO Auto-generated method stub
		float saldo=0;
		List<Activo> misFiat= miActivoFiatDAO.listarActivos(miUsuario.getId());
		List<Activo> misCripto= miActivoCriptoDAO.listarActivos(miUsuario.getId());
		float valor_dolar;
		for (Activo activo : misFiat) {
			saldo+=activo.getCantidad()*activo.getMoneda().getValorDolar();;
		}
		for (Activo activo : misCripto) {
			saldo+=activo.getCantidad()*activo.getMoneda().getValorDolar();;
		}
		return saldo;
	}

	public void setVista(VistaBalanceYMisActivos nuevaVista) {
		// TODO Auto-generated method stub
		miVistaBalanceYMisActivos=nuevaVista;
		
	}

}