package billetera.Controladores;

import Vista.VistaCompra;
import billetera.Auxiliar.Activo;
import billetera.Auxiliar.Moneda;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.ActivoDAO;
import billetera.Modelo.DAO.FactoryDAO;
import billetera.Modelo.DAO.MonedaDAO;
import java.sql.SQLException;
import java.util.List;

public class ControladorCompra {
    private VistaCompra miVista;
    private Usuario miUsuario;
    private MonedaDAO miMonedaDAO;
    private ActivoDAO miActivoCriptoDAO;
    private ActivoDAO miActivoFiatDAO;
    
    
    public ControladorCompra(Usuario miUsuario){
    	miMonedaDAO=FactoryDAO.getMonedaDAO();
    	miActivoCriptoDAO=FactoryDAO.getActivoCriptoDAO();
    	miActivoFiatDAO=FactoryDAO.getActivoFiatDAO();
    	this.miUsuario=miUsuario;
    }
    
    public boolean comprarCripto(Moneda criptoSeleccionada,Activo resolverFiat, double cantidadFiat) throws SQLException 
    {	
        if(resolverFiat.getMoneda().getValorDolar()*cantidadFiat>resolverFiat.getCantidad()) {
            //no tiene saldo suficiente
            return false;
        }
        if(resolverFiat.getMoneda().getValorDolar()*cantidadFiat<0) {
            //no se puede comprar una cantidad negativa
            return false;
        }
        
        if(criptoSeleccionada==null)
        {
            //no existe la moneda
            return false;
        }
        float cant_compra = (float) (resolverFiat.getMoneda().getValorDolar()*cantidadFiat/criptoSeleccionada.getValorDolar());
        if(criptoSeleccionada.getStock()<=cant_compra)
        {
            //no hay stock
            return false;
        }
        
        resolverFiat.setCantidad((float) (resolverFiat.getCantidad()-cantidadFiat));
        //ahora el fiat tiene la cantidad requerida y la moneda seleccionada tambiem
        List<Activo> misActivosCripto= miActivoCriptoDAO.listarActivos(miUsuario.getId());
        miActivoCriptoDAO.actualizarActivo(miUsuario.getId(), resolverFiat.getId(), resolverFiat.getCantidad());
        miMonedaDAO.actualizarStock(criptoSeleccionada.getNomenclatura(), criptoSeleccionada.getStock()-cant_compra);
        miMonedaDAO.actualizarStock(resolverFiat.getMoneda().getNomenclatura(), (float) (resolverFiat.getMoneda().getStock()+cantidadFiat));
        for (Activo activo : misActivosCripto) {
			if(activo.getMoneda().getNombre().equals(criptoSeleccionada.getNombre())) {
				miActivoCriptoDAO.actualizarActivo(miUsuario.getId(), criptoSeleccionada.getId(), activo.getCantidad()+cant_compra);
				return true;
				
			}
		}
        Activo nuevoActivo= new Activo(miUsuario,criptoSeleccionada, cant_compra);
        miActivoCriptoDAO.cargarActivo(nuevoActivo);
        return true;
        
    }

    public Moneda[] obtenerCriptos() throws SQLException {
    	List<Moneda> monedas= miMonedaDAO.listarMonedas();//JKASJDKAS RE XD ESTE METODO    
        @SuppressWarnings("CollectionsToArray")
    	Moneda[] array = monedas.toArray(new Moneda[0]);//parseo crazy son las 3 am me quiero dormir
    	return array;//xd
    }
    public Activo[] obtenerActivosFiats() throws SQLException {
    	List<Activo> activos= miActivoFiatDAO.listarActivos(miUsuario.getId());//JKASJDKAS RE XD ESTE METODO  
        Activo[] Array_activos = activos.toArray(new Activo[0]);
        return Array_activos;//xd
    	
    }
	public void setVista(VistaCompra nuevaVista) {
		// TODO Auto-generated method stub
		miVista=nuevaVista;
	}

    public void iniciar() {
    	miVista.setVisible(true);
    }
}