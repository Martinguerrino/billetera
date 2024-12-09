package Auxiliar;

import Modelo.DAO.MonedaDAO;
import Modelo.DAO.MonedaDAOjdbc;
import java.sql.SQLException;
import java.util.Random;

public class GeneradorMonedas {

    private static final String[] NOMBRES_FIAT = {"Dólar", "Euro",  "Peso"};
    private static final String[] NOMENCLATURAS_FIAT = {"USD", "EUR", "ARS"};
    private static final String[] NOMBRES_CRIPTO = {"Bitcoin", "Ethereum", "Dogecoin","Ripple"};
    private static final String[] NOMENCLATURAS_CRIPTO = {"BTC", "ETH", "DOGE",  "XRP"};

    private static final Random RANDOM = new Random();
    private MonedaDAO monedaDAO;

    public GeneradorMonedas() {
        this.monedaDAO = new MonedaDAOjdbc();
    }

    public Moneda generarMonedaFiat() {
        int index = RANDOM.nextInt(NOMBRES_FIAT.length);
        String nombre = NOMBRES_FIAT[index];
        String nomenclatura = NOMENCLATURAS_FIAT[index];
        float valorDolar = 1.0f + RANDOM.nextFloat() * 100; // Valor aleatorio entre 1 y 100
        float volatilidad = RANDOM.nextFloat(); // Volatilidad aleatoria
        String nombreIcono = nomenclatura.toLowerCase() + ".png"; // Nombre del icono basado en el nombre
        float stock = RANDOM.nextFloat() * 1000; // Stock aleatorio entre 0 y 1000
        return new Moneda("Fiat", nombre, nomenclatura, valorDolar, volatilidad, nombreIcono, stock);
    }

    public Moneda generarMonedaCripto() {
        int index = RANDOM.nextInt(NOMBRES_CRIPTO.length);
        String nombre = NOMBRES_CRIPTO[index];
        String nomenclatura = NOMENCLATURAS_CRIPTO[index];
        float valorDolar = 1.0f + RANDOM.nextFloat() * 50000; // Valor aleatorio entre 1 y 50000
        float volatilidad = RANDOM.nextFloat(); // Volatilidad aleatoria
        String nombreIcono = nomenclatura.toLowerCase() + ".png"; // Nombre del icono basado en el nombre
        float stock = RANDOM.nextFloat() * 1000; // Stock aleatorio entre 0 y 1000
        return new Moneda("Cripto", nombre, nomenclatura, valorDolar, volatilidad, nombreIcono, stock);
    }

    public void crearMonedas() throws SQLException {
        Moneda monedaFiat = generarMonedaFiat();
        Moneda monedaCripto = generarMonedaCripto();

        monedaDAO.crearMoneda(monedaFiat);
        monedaDAO.crearMoneda(monedaCripto);

        System.out.println("Moneda Fiat Generada: " + monedaFiat.getNombre() + " (" + monedaFiat.getNomenclatura() + ") - Valor en USD: " + monedaFiat.getValorDolar());
        System.out.println("Moneda Cripto Generada: " + monedaCripto.getNombre() + " (" + monedaCripto.getNomenclatura() + ") - Valor en USD: " + monedaCripto.getValorDolar());
    }

    public static void iniciar() {
        GeneradorMonedas generador = new GeneradorMonedas();
        try {
            generador.crearMonedas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}