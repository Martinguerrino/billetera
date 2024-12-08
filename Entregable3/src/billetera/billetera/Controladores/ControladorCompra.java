package billetera.Controladores;

import java.util.List;

import Aux.Moneda;
import Vista.VistaCompra;
import billetera.Modelo.Servicios.ServicioMoneda;
import java.sql.SQLException;

public class ControladorCompra {
    private VistaCompra miVista;
    private Usuario miUsuario;
    private ServicioMoneda miModeloMoneda;//pense una re buena martucho, los modelos son de las cosas de bases de datos, asi obtenemos las cosas q necesitamos de ahi y desp los metodos de controlador parsean todo lo q pidan la vista re loco mal. Martin:muy buena idea bro
    private ServicioActivoFiat miModeloActivoFiat;
    private ServicioCompra miModeloCompra;
    private ServicioActivoCripto miModeloActivoCripto;
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
    public void comprarCripto(String criptoSeleccionada,billetera.Auxiliar.Activo fiat, double cantidadFiat) throws SQLException 
    {
        if(miModeloMoneda.buscarMonedaPorId(fiat.getId_moneda()).getValorDolar()*cantidadFiat>fiat.getCantidad()) {
            //no tiene saldo suficiente
            return;
        }
        if(fiat.getValorDolar()*cantidadFiat<) {
            //no se puede comprar una cantidad negativa
            return;
        }
        if(miModeloMoneda.buscarMonedaPorNomenclatura(criptoSeleccionada)==null)
        {
            //no existe la moneda
            return;
        }
        float cant_compra = (float) (fiat.getValorDolar()*cantidadFiat/miModeloMoneda.buscarMonedaPorNomenclatura(criptoSeleccionada).getValorDolar());
        if(miModeloMoneda.buscarMonedaPorNomenclatura(criptoSeleccionada).getStock()<=cant_compra)
        {
            //no hay stock
            return;
        }
        Moneda monedaFiat = miModeloMoneda.buscarMonedaPorId(fiat.getId_moneda());
        float stock_restante = miModeloMoneda.buscarMonedaPorNomenclatura(criptoSeleccionada).getStock()-cant_compra;
    	float fiatRestante = (float )(fiat.getCantidad()-cantidadFiat);
        miModeloCompra.comprar(criptoSeleccionada,stock_restante,monedaFiat, fiatRestante, miUsuario.getId());
    }

    public String[] obtenerCriptos() {
    	List<Moneda> monedas= miModeloMoneda.listarMonedas();//JKASJDKAS RE XD ESTE METODO
    	String[] array = monedas.toArray(new String[0]);//parseo crazy son las 3 am me quiero dormir
    	return array;//xd
    }
	public void setVista(VistaCompra nuevaVista) {
		// TODO Auto-generated method stub
		miVista=nuevaVista;
	}
}
