package Controladores;

import Vista.VistaCompra;
import Vista.Ventana.VentanaInicio;
import Auxiliar.Activo;
import Auxiliar.Moneda;
import Auxiliar.Transaccion;
import Auxiliar.Usuario;
import Excepciones.NoExiteMonedaException;
import Excepciones.NoHayStockException;
import Excepciones.SaldoInsuficienteException;
import Modelo.DAO.ActivoDAO;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.MonedaDAO;
import Modelo.DAO.TransaccionDAO;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ControladorCompra {
	private VentanaInicio ventanaInicio;
	private ControladorIndex controladorPrincipal;
    private VistaCompra vista;
    private Usuario miUsuario;
    private MonedaDAO miMonedaDAO;
    private ActivoDAO miActivoCriptoDAO;
    private ActivoDAO miActivoFiatDAO;
    private TransaccionDAO miTransaccionDAO;
    
    
    public ControladorCompra(VentanaInicio ventana ,ControladorIndex controladorIndex,Usuario miUsuario) throws SQLException{
    	this.controladorPrincipal=controladorIndex;
    	this.ventanaInicio=ventana;
    	this.miUsuario=miUsuario;
    	miMonedaDAO=FactoryDAO.getMonedaDAO();
    	miActivoCriptoDAO=FactoryDAO.getActivoCriptoDAO();
    	miActivoFiatDAO=FactoryDAO.getActivoFiatDAO();
    	this.miUsuario=miUsuario;
    	miTransaccionDAO=FactoryDAO.getTransaccionDAO();
    	this.vista= new VistaCompra(this);
    }
    public int comprarCripto(Moneda criptoSeleccionada,Activo resolverFiat, double cantidadFiat) throws SQLException, NoExiteMonedaException, SaldoInsuficienteException, NoHayStockException
    {	
        if(resolverFiat.getMoneda().getValorDolar()*cantidadFiat>resolverFiat.getCantidad()*resolverFiat.getMoneda().getValorDolar()) {
            //no tiene saldo suficiente
            throw new SaldoInsuficienteException();
           
        }

        
        if(criptoSeleccionada==null)
        {
            //no existe la moneda
            throw new NoExiteMonedaException();
        	
            
        }
        float cant_compra = (float) (resolverFiat.getMoneda().getValorDolar()*cantidadFiat/criptoSeleccionada.getValorDolar());
        if(criptoSeleccionada.getStock()<=cant_compra)
        {
            //no hay stock
            throw new NoHayStockException();
            
        }
        //ahora el fiat tiene la cantidad requerida y la moneda seleccionada tambiem
        List<Activo> misActivosCripto= miActivoCriptoDAO.listarActivos(miUsuario.getId());
        miActivoFiatDAO.actualizarActivo(miUsuario.getId(), resolverFiat.getMoneda().getId(), (float) (resolverFiat.getCantidad()-cantidadFiat));
        miMonedaDAO.actualizarStock(criptoSeleccionada.getNomenclatura(), criptoSeleccionada.getStock()-cant_compra);
        miMonedaDAO.actualizarStock(resolverFiat.getMoneda().getNomenclatura(), (float) (resolverFiat.getMoneda().getStock()+cantidadFiat));
        Transaccion transaccion = new Transaccion("Compra de "+criptoSeleccionada.getNombre()+" por "+ cantidadFiat + resolverFiat.getMoneda().getNombre(), LocalDateTime.now(), miUsuario);
        miTransaccionDAO.crearTransaccion(transaccion);
        for (Activo activo : misActivosCripto) {
			if(activo.getMoneda().getNombre().equals(criptoSeleccionada.getNombre())) {
				miActivoCriptoDAO.actualizarActivo(miUsuario.getId(), criptoSeleccionada.getId(), activo.getCantidad()+cant_compra);
				return 0;
				
			}
		}
        Activo nuevoActivo= new Activo(miUsuario,criptoSeleccionada, cant_compra);
        miActivoCriptoDAO.cargarActivo(nuevoActivo);
        return 0;
        
    }

    public Moneda[] obtenerCriptos() throws SQLException {
    	List<Moneda> monedas= miMonedaDAO.listarMonedasCripto();//JKASJDKAS RE XD ESTE METODO    
    	
        @SuppressWarnings("CollectionsToArray")
    	Moneda[] array = monedas.toArray(new Moneda[0]);//parseo crazy son las 3 am me quiero dormir
        System.out.println(array);
    	return array;//xd
    }
    public Activo[] obtenerActivosFiats() throws SQLException {
    	List<Activo> activos= miActivoFiatDAO.listarActivos(miUsuario.getId());//JKASJDKAS RE XD ESTE METODO  
        Activo[] Array_activos = activos.toArray(new Activo[0]);
        return Array_activos;//xd
    	
    }
	public void setVista(VistaCompra nuevaVista) {
		// TODO Auto-generated method stub
		vista=nuevaVista;
	}

    public void iniciar() {
    	ventanaInicio.getContentPane().removeAll();
    	ventanaInicio.getContentPane().add(vista, BorderLayout.CENTER);
    	ventanaInicio.revalidate();
    	ventanaInicio.repaint();
    }
    
    public void redirigirIndex() {
		controladorPrincipal.iniciar();
	}
}