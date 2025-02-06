package Auxiliar;

import Modelo.DAO.ActivoDAO;
import Modelo.DAO.MonedaDAO;
import Modelo.DAO.MonedaDAOjdbc;
import java.sql.SQLException;
import java.util.Random;

public class GeneradorMonedas {

    private static final String[] NOMBRES_FIAT = {"Dólar", "Euro", "Peso"};
    private static final String[] NOMENCLATURAS_FIAT = {"USD", "EUR", "ARS"};
    private static final String[] NOMBRES_CRIPTO = {"Bitcoin", "Ethereum", "Dogecoin", "Ripple"};
    private static final String[] NOMENCLATURAS_CRIPTO = {"BTC", "ETH", "DOGE", "XRP"};

    private static final Random RANDOM = new Random();
    private MonedaDAO monedaDAO;
    private ActivoDAO activoDAO;

    public GeneradorMonedas() {
        this.monedaDAO = new MonedaDAOjdbc();
    }

    public Moneda generarMonedaFiat(int index) {
        String nombre = NOMBRES_FIAT[index];
        String nomenclatura = NOMENCLATURAS_FIAT[index];
        float valorDolar = 1.0f + RANDOM.nextFloat() * 100; // Valor aleatorio entre 1 y 100
        float volatilidad = RANDOM.nextFloat(); // Volatilidad aleatoria entre 0.0 y 1.0
        String nombreIcono = nomenclatura.toLowerCase() + ".png"; // Nombre del icono basado en la nomenclatura
        float stock = RANDOM.nextFloat() * 1000; // Stock aleatorio entre 0 y 1000
        return new Moneda("Fiat", nombre, nomenclatura, valorDolar, volatilidad, nombreIcono, stock);
    }

    public Moneda generarMonedaCripto(int index) {
        String nombre = NOMBRES_CRIPTO[index];
        String nomenclatura = NOMENCLATURAS_CRIPTO[index];
        float valorDolar = 1.0f + RANDOM.nextFloat() * 50000; // Valor aleatorio entre 1 y 50000
        float volatilidad = RANDOM.nextFloat(); // Volatilidad aleatoria entre 0.0 y 1.0
        String nombreIcono = nomenclatura.toLowerCase() + ".png"; // Nombre del icono basado en la nomenclatura
        float stock = RANDOM.nextFloat() * 1000; // Stock aleatorio entre 0 y 1000
        return new Moneda("Cripto", nombre, nomenclatura, valorDolar, volatilidad, nombreIcono, stock);
    }

    public void generarMonedas(int id_usuario) throws SQLException {
        // Generar todas las monedas fiat
        for (int i = 0; i < NOMBRES_FIAT.length; i++) 
        {
            Moneda monedaFiat = generarMonedaFiat(i);
            monedaDAO.crearMoneda(monedaFiat);
        }

        // Generar todas las criptomonedas
        for (int i = 0; i < NOMBRES_CRIPTO.length; i++) {
            Moneda monedaCripto = generarMonedaCripto(i);
            monedaDAO.crearMoneda(monedaCripto);
               }
    }
    /*nose si darle al usuario monedas aleatorias y debamos crearlas en el momento 
    public void cargarMonedasUsuario(int id_usuario) throws SQLException 
    {
        Moneda monedaFiat = null;
        
        for (int i = 0; i < NOMBRES_FIAT.length; i++) {
            monedaFiat = generarMonedaFiat(i);
            monedaDAO.crearMoneda(monedaFiat);
            activoDAO.actualizarActivo(id_usuario, monedaFiat.getId(), monedaFiat.getStock()-(1.0f + RANDOM.nextFloat() * 100));
            
        }
    }*/
    
    
    

    
}