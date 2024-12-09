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
    
    public int comprarCripto(Moneda criptoSeleccionada,Activo resolverFiat, double cantidadFiat) throws SQLException 
    {	
        if(resolverFiat.getMoneda().getValorDolar()*cantidadFiat>resolverFiat.getCantidad()*resolverFiat.getMoneda().getValorDolar()) {
            //no tiene saldo suficiente
            return 1;
        }

        
        if(criptoSeleccionada==null)
        {
            //no existe la moneda
            return 2;
        }
        float cant_compra = (float) (resolverFiat.getMoneda().getValorDolar()*cantidadFiat/criptoSeleccionada.getValorDolar());
        if(criptoSeleccionada.getStock()<=cant_compra)
        {
            //no hay stock
            return 3;
        }
        //ahora el fiat tiene la cantidad requerida y la moneda seleccionada tambiem
        List<Activo> misActivosCripto= miActivoCriptoDAO.listarActivos(miUsuario.getId());
        miActivoFiatDAO.actualizarActivo(miUsuario.getId(), resolverFiat.getMoneda().getId(), (float) (resolverFiat.getCantidad()-cantidadFiat));
        miMonedaDAO.actualizarStock(criptoSeleccionada.getNomenclatura(), criptoSeleccionada.getStock()-cant_compra);
        miMonedaDAO.actualizarStock(resolverFiat.getMoneda().getNomenclatura(), (float) (resolverFiat.getMoneda().getStock()+cantidadFiat));
        for (Activo activo : misActivosCripto) {
			if(activo.getMoneda().getNombre().equals(criptoSeleccionada.getNombre())) {
				miActivoCriptoDAO.actualizarActivo(miUsuario.getId(), criptoSeleccionada.getId(), activo.getCantidad()+cant_compra);
				System.out.println("comprado");
				return 0;
				
			}
		}
        Activo nuevoActivo= new Activo(miUsuario,criptoSeleccionada, cant_compra);
        miActivoCriptoDAO.cargarActivo(nuevoActivo);
        return 0;
        
    }

    public Moneda[] obtenerCriptos() throws SQLException {
    	List<Moneda> monedas= miMonedaDAO.listarMonedas();//JKASJDKAS RE XD ESTE METODO    
    	for (Moneda moneda : monedas) {
			if(moneda.getTipo().equals("F")) {
				monedas.remove(moneda);
			}
		}
    	System.out.println(monedas);
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