package Controladores;

import Auxiliar.Activo;
import Auxiliar.GeneradorMonedas;
import Auxiliar.Moneda;
import Auxiliar.Transaccion;
import Auxiliar.Usuario;
import Excepciones.NoHayMonedasCargadasException;
import Modelo.DAO.ActivoDAO;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.MonedaDAO;
import Vista.VistaBalanceYMisActivos;
import Vista.Ventana.VentanaInicio;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;


public class ControladorBalanceYMisActivos {

	private Usuario miUsuario;
	private VistaBalanceYMisActivos vista;
	private VentanaInicio ventanaInicio;
	private ControladorIndex controladorPrincipal;
	private ActivoDAO miActivoCriptoDAO ;
	private ActivoDAO miActivoFiatDAO;
	private MonedaDAO miMonedaDAO;
    private static final Random RANDOM = new Random();
	
	
	public ControladorBalanceYMisActivos(VentanaInicio ventana, ControladorIndex controladorIndex,Usuario miUsuario) throws SQLException
	{
		miActivoCriptoDAO=FactoryDAO.getActivoCriptoDAO();
		miMonedaDAO=FactoryDAO.getMonedaDAO();
		miActivoFiatDAO=FactoryDAO.getActivoFiatDAO();
		this.miUsuario=miUsuario;
		this.ventanaInicio=ventana;
		this.controladorPrincipal=controladorIndex;
		this.vista=new VistaBalanceYMisActivos(this);
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
			ImageIcon icono = new ImageIcon("src/billetera/etc/" + activo.getMoneda().getNombreIcono());
			ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // Ajusta el tamaño a 30x30

			returnArray[c][3]=iconoEscalado;
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
			ImageIcon icono = new ImageIcon("src/billetera/etc/" + activo.getMoneda().getNombreIcono());
			ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // Ajusta el tamaño a 30x30

			returnArray[c][3]=iconoEscalado;
			returnArray[c][4]=activo.getMoneda().getValorDolar();
			c++;
		}
		System.out.println(returnArray);
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
		vista=nuevaVista;
		
	}

    public void iniciar() {
    	ventanaInicio.getContentPane().removeAll();
    	ventanaInicio.getContentPane().add(vista, BorderLayout.CENTER);
    	ventanaInicio.revalidate();
    	ventanaInicio.repaint();
    }

	public void generarDatosDePrueba() throws SQLException, NoHayMonedasCargadasException {
		// TODO Auto-generated method stub
		List<Moneda> fiats= miMonedaDAO.listarMonedasFiat();
		//cambiar por enum
		if(fiats.size()==0) {
			throw new NoHayMonedasCargadasException();
		}
		for (Moneda moneda : fiats) {
			Activo cargarActivo= new Activo(miUsuario,moneda,1.0f + RANDOM.nextFloat() * 50000);
			miActivoFiatDAO.cargarActivo(cargarActivo);			
		}
		
	}
	
	public void exportarTransaccionesACSV(String filePath, Object[][] activosCripto, Object[][] activosFiat) throws SQLException, IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribir la cabecera del CSV
            writer.append("Nombre,Nomenclatura,cantidad,Valor en dolar\n");

            // Escribir los datos de las transacciones
            for (int i = 0; i < activosCripto.length; i++) {
                writer.append(String.valueOf(activosCripto[i][0])) // Nombre
                      .append(',')
                      .append(String.valueOf(activosCripto[i][1])) // Nomenclatura
                      .append(',')
                      .append(String.valueOf(activosCripto[i][2])) // Cantidad
                      .append(',')
                      .append(String.valueOf(activosCripto[i][4])) // Valor en dólar
                      .append('\n');
            }

            // Escribir los datos de las transacciones (Fiat)
            for (int i = 0; i < activosFiat.length; i++) {
                writer.append(String.valueOf(activosFiat[i][0])) // Nombre
                      .append(',')
                      .append(String.valueOf(activosFiat[i][1])) // Nomenclatura
                      .append(',')
                      .append(String.valueOf(activosFiat[i][2])) // Cantidad
                      .append(',')
                      .append(String.valueOf(activosFiat[i][4])) // Valor en dólar
                      .append('\n');
            }
        }
	}

	public void redirigirIndex() {
		// TODO Auto-generated method stub
		controladorPrincipal.iniciar();
	}
	
}

