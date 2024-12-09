package billetera.Controladores;

import java.util.List;

import billetera.Auxiliar.Activo;
import billetera.Auxiliar.Moneda;
import Vista.VistaCompra;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.ActivoDAO;
import billetera.Modelo.DAO.MonedaDAO;
import billetera.Modelo.Servicios.ServicioMoneda;
import java.sql.SQLException;

public class ControladorCompra {
    private VistaCompra miVista;
    private Usuario miUsuario;
    private MonedaDAO miMonedaDAO;
    private ActivoDAO miActivoCriptoDAO;
    private ActivoDAO miActivoFiatDAO;
    /*private ServicioCompra servicioCompra;

    public ControladorCompra(Vista vista, ServicioCompra servicioCompra) {
        this.vista = vista;
        this.servicioCompra = servicioCompra;
    }

    public void controladorCompra() {
        vista.mostrarCompra();
        // Lógica para manejar la compra
        while (!servicioCompra.comprar(monedaFiat, cantidad, moneda)) {
            vista.mostrarErrorCompra();
            vista.mostrarCompra();
        }
    }*/
    //cambie el codiog para que la cripto seleccionada sea la nomenclatura
    //esto deberia de hacer la compra de la cripto?
    /*public boolean comprarCripto(String criptoSeleccionada, double cantidad) {
    	if(!miModelo.getCripto(criptoSeleccionada)) {
    		return false;
    	}
    	return true;
    }*/
    public boolean comprarCripto(String criptoSeleccionada,Activo resolverFiat, double cantidadFiat) throws SQLException 
    {	
        if(resolverFiat.getMoneda().getValorDolar()*cantidadFiat>resolverFiat.getCantidad()) {
            //no tiene saldo suficiente
            return false;
        }
        if(resolverFiat.getMoneda().getValorDolar()*cantidadFiat<0) {
            //no se puede comprar una cantidad negativa
            return false;
        }
        Moneda monedaSeleccionada= miMonedaDAO.buscarMonedaPorNomenclatura(criptoSeleccionada);
        if(monedaSeleccionada==null)
        {
            //no existe la moneda
            return false;
        }
        float cant_compra = (float) (resolverFiat.getMoneda().getValorDolar()*cantidadFiat/miMonedaDAO.buscarMonedaPorNomenclatura(criptoSeleccionada).getValorDolar());
        if(monedaSeleccionada.getStock()<=cant_compra)
        {
            //no hay stock
            return false;
        }
        
        resolverFiat.setCantidad((float) (resolverFiat.getCantidad()-cantidadFiat));
        //ahora el fiat tiene la cantidad requerida y la moneda seleccionada tambiem
        List<Activo> misActivosCripto= miActivoCriptoDAO.listarActivos(miUsuario.getId());
        List<Activo> misActivosFiat= miActivoFiatDAO.listarActivos(miUsuario.getId());
        miActivoCriptoDAO.actualizarActivo(miUsuario.getId(), resolverFiat.getId(), resolverFiat.getCantidad());
        miMonedaDAO.actualizarStock(monedaSeleccionada.getNomenclatura(), monedaSeleccionada.getStock()-cant_compra);
        miMonedaDAO.actualizarStock(resolverFiat.getMoneda().getNomenclatura(), (float) (resolverFiat.getMoneda().getStock()+cantidadFiat));
        for (Activo activo : misActivosCripto) {
			if(activo.getMoneda().getNombre()==monedaSeleccionada.getNombre()) {
				miActivoCriptoDAO.actualizarActivo(miUsuario.getId(), monedaSeleccionada.getId(), activo.getCantidad()+cant_compra);
				return true;
				
			}
		}
        Activo nuevoActivo= new Activo(miUsuario,monedaSeleccionada, cant_compra);
        miActivoCriptoDAO.cargarActivo(nuevoActivo);
        return true;
        
    }

    public String[] obtenerCriptos() throws SQLException {
    	List<Moneda> monedas= miMonedaDAO.listarMonedas();//JKASJDKAS RE XD ESTE METODO
    	String[] array = monedas.toArray(new String[0]);//parseo crazy son las 3 am me quiero dormir
    	return array;//xd
    }
	public void setVista(VistaCompra nuevaVista) {
		// TODO Auto-generated method stub
		miVista=nuevaVista;
	}
}
