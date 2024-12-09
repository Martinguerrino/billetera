package billetera.Controladores;
import java.util.List;

import Auxiliar.Moneda;
import Vista.VistaCotizaciones;
import billetera.Modelo.DAO.MonedaDAOjdbc;
import billetera.Modelo.Servicios.ServicioMoneda;

public class ControladorCotizaciones {
    private VistaCotizaciones miVista;
    //private ModeloMoneda miModeloMoneda;//estoy descendiendo a la locura
	//me imagino te referis con modelo moneda al monedaDAO o al servicio
	private ServicioMoneda miModeloMoneda;

	public Object[][] obtenerCotizaciones() {
		// TODO Auto-generated method stub
    	List<Moneda> monedas = miModeloMoneda.listarMonedas();//devuelve todas las monedas papu como monedas nomas owo reutilizamos codigos de servisios wiiiiiiiiiiiiiiiiiii
    	Object[][] vectorRetorno= new Object[monedas.size()][5]; 
		int contadorsito=0;
    	for (Moneda moneda : monedas) {
			vectorRetorno[contadorsito][0]=moneda.getNombre();
			vectorRetorno[contadorsito][1]=moneda.getNomenclatura();
			vectorRetorno[contadorsito][2]=moneda.getNombreIncono();
			vectorRetorno[contadorsito][3]=moneda.getValorDolar();
			vectorRetorno[contadorsito][4]=moneda.getVolatilidad();
			vectorRetorno[contadorsito][5]=moneda.getStock();
			contadorsito++;
		}
    	 
	}
    /*;
    private ServicioCotizaciones servicioCotizaciones;

    public ControladorCotizaciones(Vista vista, ServicioCotizaciones servicioCotizaciones) {
        this.vista = vista;
        this.servicioCotizaciones = servicioCotizaciones;
    }

    public void mostrarCotizaciones() {
        vista.mostrarCotizaciones(servicioCotizaciones.obtenerCotizaciones());
    }

    public void manejarErrores() {
        vista.mostrarErrorCotizaciones();
        mostrarCotizaciones();
    }*/

	public void setVista(VistaCotizaciones nuevaVista) {
		// TODO Auto-generated method stub
		this.miVista=nuevaVista;
	}
    
}