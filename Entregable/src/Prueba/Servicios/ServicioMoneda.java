package Prueba.Servicios;
import Prueba.FactoryDAO;
import Prueba.Interfaz;
import Prueba.Monedas.ComparadorPorNomenclatura;
import Prueba.Monedas.ComparadorPorStockDescendente;
import Prueba.Monedas.MonedaDAOjdbc;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import Aux.Moneda;

public class ServicioMoneda {
    @SuppressWarnings("ConvertToTryWithResources")
    public void crearMonedaDesdeUsuario() {

        // Solicitar el tipo de moneda y validarlo
        String tipo = "";
        while (!tipo.equalsIgnoreCase("Cripto") && !tipo.equalsIgnoreCase("FIAT")) {
            System.out.println("Ingrese el tipo de moneda (Cripto o FIAT): ");
            tipo = Interfaz.pedirString();
            if (!tipo.equalsIgnoreCase("Cripto") && !tipo.equalsIgnoreCase("FIAT")) {
                System.out.println("Error: el tipo debe ser 'Cripto' o 'FIAT'.");
            }
        }

        // Solicitar el resto de los datos
        System.out.println("Ingrese el nombre de la moneda: ");
        String nombre = Interfaz.pedirString();

        System.out.println("Ingrese la nomenclatura de la moneda (ej. BTC, ARS): ");
        String nomenclatura = Interfaz.pedirString();

        System.out.println("Ingrese el valor en dólares de la moneda: ");
        float valorDolar = Interfaz.pedirFloat();

        System.out.println("Ingrese el stock disponible: ");
        float stock = Interfaz.pedirFloat();

        // Confirmar si el usuario quiere guardar los datos
        System.out.println("¿Desea guardar la moneda en la base de datos? (S/N): ");
        
        String confirmacion = Interfaz.pedirString();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            // Crear la moneda y guardarla en la base de datos
            Moneda nuevaMoneda = new Moneda(tipo, nombre, nomenclatura, valorDolar, 0, stock);
            try {
                MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
                monedaDAO.crearMoneda(nuevaMoneda);
                System.out.println("Moneda guardada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al guardar la moneda: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private List<Moneda> devolverMonedaPorStock(int opcion, List<Moneda> monedas){
 
    	switch (opcion) {
    	case 1:
    		Collections.sort(monedas, new ComparadorPorNomenclatura());  // Orden por nomenclatura usando Comparator
    		break;
    	case 2:
    		Collections.sort(monedas, new ComparadorPorStockDescendente());  // Orden por stock descendente
    		break;
    	default:
    		System.out.println("Opción no válida.");
    		return null;
    	}
    	return monedas;
    }
    private List<Moneda> devolverMonedaPorMoneda(int opcion, List<Moneda> monedas){
    	switch (opcion) {
    	case 1:
    		Collections.sort(monedas);  // Orden por valorEnDolar usando compareTo
    		break;
    	case 2:
    		Collections.sort(monedas, new ComparadorPorNomenclatura());  // Orden por nomenclatura usando Comparator
    		break;
    	default:
    		System.out.println("Opción no válida.");
    		return null;
    	}
    	return monedas;
    }
    
    public List<Moneda> monedasOrdenadas(int opcion, int id) { //id 1=monedas, id 2=stock
        try {
            MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
            List<Moneda> monedas = monedaDAO.listarMonedas();  // Obtener todas las monedas

            switch(id) {
            	case 1:
            		monedas=devolverMonedaPorMoneda(opcion, monedas);
            		break;
            	case 2:
            		monedas=devolverMonedaPorStock(opcion, monedas);
            		
            		break;
            }
            return monedas;
        } catch (SQLException e) {
            System.out.println("Error al obtener las monedas: " + e.getMessage());
        }
        return null;
    }
    public void GenerarStock() throws SQLException
    {
        MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
        monedaDAO.generarStockAleatorio();
    }

    public void imprimirMonedasOrdenado() {
        System.out.println("¿Cómo desea ordenar las monedas?");
        System.out.println("1: Por valor en dólares");
        System.out.println("2: Por nomenclatura");
        System.out.print("Seleccione una opción: ");
        
        int opcion = Interfaz.pedirInt();

        List<Moneda> monedasOrdenadas = monedasOrdenadas(opcion,1);
        System.out.println("\n--- Monedas Ordenadas ---");
        for (Moneda moneda : monedasOrdenadas) {
            System.out.println(moneda);  // Muestra detalles de cada moneda
        }
    }
    public void imprimirStockOrdenado() {
        System.out.println("¿Cómo desea ordenar el stock?");
        System.out.println("1: Por nomenclatura");
        System.out.println("2: Por stock descendente");
        System.out.print("Seleccione una opción: ");
        
        int opcion = Interfaz.pedirInt();

        List<Moneda> stockOrdenado = monedasOrdenadas(opcion,2);
        System.out.println("\n--- Stock Ordenado ---");
        for (Moneda moneda : stockOrdenado) {
            System.out.printf("Nomenclatura: %s, Stock: %.2f%n", moneda.getNomenclatura(), moneda.getStock());
        }
    }
}