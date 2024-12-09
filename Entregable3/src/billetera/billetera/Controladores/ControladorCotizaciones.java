package billetera.Controladores;
import Vista.VistaCotizaciones;
import billetera.Auxiliar.Moneda;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.FactoryDAO;
import billetera.Modelo.DAO.MonedaDAO;
import java.sql.SQLException;
import java.util.List;

public class ControladorCotizaciones {
	private VistaCotizaciones miVista;
	Usuario miUsuario;
	//private ModeloMoneda miModeloMoneda;//estoy descendiendo a la locura
	//me imagino te referis con modelo moneda al monedaDAO o al servicio
	private MonedaDAO miModeloMoneda;
	
	public ControladorCotizaciones(Usuario miUsuario){
		miModeloMoneda=FactoryDAO.getMonedaDAO();
		this.miUsuario=miUsuario;
	}

	public Object[][] obtenerCotizaciones() throws SQLException 
	{
		// TODO Auto-generated method stub
    	List<Moneda> monedas = miModeloMoneda.listarMonedas();//devuelve todas las monedas papu como monedas nomas owo reutilizamos codigos de servisios wiiiiiiiiiiiiiiiiiii
    	Object[][] vectorRetorno= new Object[monedas.size()][6]; 
		int contadorsito=0;
    	for (Moneda moneda : monedas) {
			vectorRetorno[contadorsito][0]=moneda.getNombre();
			vectorRetorno[contadorsito][1]=moneda.getNomenclatura();
			vectorRetorno[contadorsito][2]=moneda.getNombreIcono();
			vectorRetorno[contadorsito][3]=moneda.getValorDolar();
			vectorRetorno[contadorsito][4]=moneda.getVolatilidad();
			vectorRetorno[contadorsito][5]=moneda.getStock();
			contadorsito++;
		}
    	 return vectorRetorno;
	}
    

	public void setVista(VistaCotizaciones nuevaVista) {
		// TODO Auto-generated method stub
		this.miVista=nuevaVista;
	}

    public void iniciar() {
    	miVista.setVisible(true);
    }
    
}
