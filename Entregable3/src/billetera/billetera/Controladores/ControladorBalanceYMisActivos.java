package billetera.Controladores;

import Vista.VistaBalanceYMisActivos;
import billetera.Auxiliar.Activo;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.ActivoCriptoDAOjdbc;
import billetera.Modelo.DAO.ActivoDAO;
import billetera.Modelo.DAO.ActivoFiatDAOjdbc;
import billetera.Modelo.DAO.FactoryDAO;
import billetera.Modelo.DAO.MonedaDAO;
import billetera.Modelo.DAO.MonedaDAOjdbc;
import java.sql.SQLException;
import java.util.List;


public class ControladorBalanceYMisActivos {

	private Usuario miUsuario;
	private VistaBalanceYMisActivos miVista;
	private ActivoDAO miActivoCriptoDAO ;
	private ActivoDAO miActivoFiatDAO;
	private MonedaDAO miMonedaDAO;
	

	
	public ControladorBalanceYMisActivos(Usuario miUsuario)
	{
		miActivoCriptoDAO=FactoryDAO.getActivoCriptoDAO();
		miMonedaDAO=FactoryDAO.getMonedaDAO();
		miActivoFiatDAO=FactoryDAO.getActivoFiatDAO();
		this.miUsuario=miUsuario;
	}
	



	public Object[][] obtenerActivosCripto() throws SQLException {
		
		
		//quien llama a este controlador debe pasar el id del usuario
		//se me ocurre que al controlador se le pase el usuario ya que que necesita cosas del usuario que busca sus cosas
		List<Activo> misCripto= miActivoCriptoDAO.listarActivos(miUsuario.getId());
		//ESTO ESTA COMENTADO PORQUE LO CAMBIE PERO NOSE QUE QUERES DECIR CON MIMODELO 
		//List<Activo> misCripto= miModelo.getActivosCripto();
		int c=0;
		Object[][] returnArray= new Object[misCripto.size()][5];
		for (Activo activo : misCripto) {
			//CHE AMIGO QUE PINGO ES ESTO DE retrunArray[c][0] Y ESO
			//voy a escribir en comentarios otra forma de esto por como quedo el modelo
			
			
			returnArray[c][0]=activo.getMoneda().getNombre();
			returnArray[c][1]=activo.getMoneda().getNomenclatura();
			returnArray[c][2]=activo.getCantidad();
			returnArray[c][3]=activo.getMoneda().getNombreIcono();
			returnArray[c][4]=activo.getMoneda().getValorDolar();
			c++;
		}
		
		return returnArray;
	}

	public Object[][] ObtenerActivosFiat() throws SQLException {
		// TODO Auto-generated method stub
		
		//quien llama a este controlador debe pasar el id del usuario
		//se me ocurre que al controlador se le pase el usuario ya que que necesita cosas del usuario que busca sus cosas
		List<Activo> misFiat= miActivoFiatDAO.listarActivos(miUsuario.getId());
		//List<Activo> misFiat= miModelo.getActivosFiat();
		int c=0;
		Object[][] returnArray= new Object[misFiat.size()][5];
		for (Activo activo : misFiat) 
		{
			returnArray[c][0]=activo.getMoneda().getNombre();
			returnArray[c][1]=activo.getMoneda().getNomenclatura();
			returnArray[c][2]=activo.getCantidad();
			returnArray[c][3]=activo.getMoneda().getNombreIcono();
			returnArray[c][4]=activo.getMoneda().getValorDolar();
			c++;
		}
		
		return returnArray;
	}
	public float ObtenerSaldo() throws SQLException {
		float saldo=0;
		List<Activo> misFiat= miActivoFiatDAO.listarActivos(miUsuario.getId());
		List<Activo> misCripto= miActivoCriptoDAO.listarActivos(miUsuario.getId());
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
		miVista=nuevaVista;
		
	}

    public void iniciar() {
    	miVista.setVisible(true);
    }

}
