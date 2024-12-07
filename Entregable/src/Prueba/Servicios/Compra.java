package Prueba.Servicios;

import Prueba.Activos.ActivoFiatDAOjdbc;
import Prueba.FactoryDAO;
import Prueba.Interfaz;
import Prueba.Monedas.MonedaDAOjdbc;
import Prueba.Transaccion.Transaccion;
import Prueba.Transaccion.TransaccionDAOjdbc;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

import Aux.Activo;
import Aux.Moneda;

public class Compra 
{

    private Moneda cripto;
    private Activo fiat;
    
    public  Compra(Moneda cripto, Activo fiat) 
    {
        this.cripto = cripto;
        this.fiat = fiat;
    }

	public Moneda getCripto() {
		return cripto;
	}

	public void setCripto(Moneda cripto) {
		this.cripto = cripto;
	}

	public Activo getFiat() {
		return fiat;
	}

	public void setFiat(Activo fiat) {
		this.fiat = fiat;
	}
    

   
}
