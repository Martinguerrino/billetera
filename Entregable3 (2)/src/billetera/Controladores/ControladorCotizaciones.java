package Controladores;
import Vista.VistaCotizaciones;
import Auxiliar.Moneda;
import Auxiliar.Usuario;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.MonedaDAO;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;

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
		
		List<Moneda> monedas = miModeloMoneda.listarMonedasOrdenado();//devuelve todas las monedas papu como monedas nomas owo reutilizamos codigos de servisios wiiiiiiiiiiiiiiiiiii
    	Object[][] vectorRetorno= new Object[monedas.size()][6]; 
		int contadorsito=0;
    	for (Moneda moneda : monedas) {
			vectorRetorno[contadorsito][0]=moneda.getNombre();
			vectorRetorno[contadorsito][1]=moneda.getNomenclatura();
			ImageIcon icono = new ImageIcon("src/billetera/etc/" + moneda.getNombreIcono());
			ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // Ajusta el tamaño a 30x30
			vectorRetorno[contadorsito][2] = iconoEscalado;
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
