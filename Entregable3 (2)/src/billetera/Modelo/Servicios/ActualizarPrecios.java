package Modelo.Servicios;

import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;

import Auxiliar.Moneda;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.MonedaDAO;

public class ActualizarPrecios 
{
    private MonedaDAO monedaDAO;
    private List<Moneda> monedas;

    public ActualizarPrecios(List<Moneda> monedas) {
        this.monedaDAO = FactoryDAO.getMonedaDAO();
        this.monedas = monedas;
    }
    
    public void Actualizacion()
    {
        MonedaDAO monedaDAO = FactoryDAO.getMonedaDAO();
        for (Moneda moneda : monedas)
        {
            try {
                monedaDAO.actualizarValorDolar(moneda.getNomenclatura(), moneda.getValorDolar());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}