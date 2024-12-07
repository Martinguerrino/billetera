package billetera.Controladores;

import java.util.List;

import Aux.Activo;
import Vista.VistaBalanceYMisActivos;

public class ControladorBalanceYMisActivos {

	Modelo miModelo;
	VistaBalanceYMisActivos miVistaBalanceYMisActivos;
	
	ControladorBalanceYMisActivos(){
		
	}
	
	public Object[][] obtenerActivosCripto() {
		// TODO Auto-generated method stub
		List<Activo> misCripto= miModelo.getActivosCripto();
		int c=0;
		Object[][] returnArray= new Object[misCripto.size()][4];
		for (Activo activo : misCripto) {
			returnArray[c][0]=activo.getMoneda().getNombre();
			returnArray[c][1]=activo.getMoneda().getNomenclatura();
			returnArray[c][2]=activo.getMoneda().getValorDolar();
			returnArray[c][3]=activo.getMoneda().getNombreIcono();
			c++;
		}
		
		return returnArray;
	}

	public Object[][] ObtenerActivosFiat() {
		// TODO Auto-generated method stub
		List<Activo> misFiat= miModelo.getActivosFiat();
		int c=0;
		Object[][] returnArray= new Object[misFiat.size()][4];
		for (Activo activo : misFiat) {
			returnArray[c][0]=activo.getMoneda().getNombre();
			returnArray[c][1]=activo.getMoneda().getNomenclatura();
			returnArray[c][2]=activo.getMoneda().getValorDolar();
			returnArray[c][3]=activo.getMoneda().getNombreIcono();
			c++;
		}
		
		return returnArray;
	}

	public void setVista(VistaBalanceYMisActivos nuevaVista) {
		// TODO Auto-generated method stub
		miVistaBalanceYMisActivos=nuevaVista;
		
	}

}