package Controladores;

import Auxiliar.Activo;
import Auxiliar.GeneradorMonedas;
import Auxiliar.Transaccion;
import Auxiliar.Usuario;
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
import javax.swing.ImageIcon;


public class ControladorBalanceYMisActivos {

	private Usuario miUsuario;
	private VistaBalanceYMisActivos vista;
	private VentanaInicio ventanaInicio;
	private ControladorIndex controladorPrincipal;
	private ActivoDAO miActivoCriptoDAO ;
	private ActivoDAO miActivoFiatDAO;
	private MonedaDAO miMonedaDAO;
	
	
	public ControladorBalanceYMisActivos(VentanaInicio ventana, ControladorIndex controladorIndex,Usuario miUsuario)
	{
		miActivoCriptoDAO=FactoryDAO.getActivoCriptoDAO();
		miMonedaDAO=FactoryDAO.getMonedaDAO();
		miActivoFiatDAO=FactoryDAO.getActivoFiatDAO();
		this.miUsuario=miUsuario;
		this.ventanaInicio=ventana;
		this.controladorPrincipal=controladorIndex;
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

	public void generarDatosDePrueba() throws SQLException {
		// TODO Auto-generated method stub
		GeneradorMonedas generador=new GeneradorMonedas();
		generador.generarMonedas(miUsuario.getId());
	}
	
	public void redirigirIndex() {
		controladorPrincipal.iniciar();
	}
	
	public void exportarTransaccionesACSV(String filePath, Object[][] activosCripto, Object[][] activosFiat) throws SQLException, IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Escribir la cabecera del CSV
            writer.append("Usuario,FechaHora,Resumen\n");

            // Escribir los datos de las transacciones
            for (int i=0;i<activosCripto.length;i++) {
                writer.append((CharSequence) activosCripto[i][0])
                      .append(',')
                      .append((CharSequence) activosCripto[i][1])
                      .append(',')
                      .append((CharSequence) activosCripto[i][2])
                      .append((CharSequence) activosCripto[i][4])
                      .append('\n');
            }
            for (int i=0;i<activosFiat.length;i++) {
                writer.append((CharSequence) activosFiat[i][0])
                      .append(',')
                      .append((CharSequence) activosFiat[i][1])
                      .append(',')
                      .append((CharSequence) activosFiat[i][2])
                      .append((CharSequence) activosFiat[i][4])
                      .append('\n');
            }
        }
	}
	
}

