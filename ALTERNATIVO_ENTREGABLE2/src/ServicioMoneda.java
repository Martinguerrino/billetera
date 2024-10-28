import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ServicioMoneda {
    
    

    @SuppressWarnings("ConvertToTryWithResources")
    public void crearMonedaDesdeUsuario() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el tipo de moneda y validarlo
        String tipo = "";
        while (!tipo.equalsIgnoreCase("Cripto") && !tipo.equalsIgnoreCase("FIAT")) {
            System.out.println("Ingrese el tipo de moneda (Cripto o FIAT): ");
            tipo = scanner.nextLine();
            if (!tipo.equalsIgnoreCase("Cripto") && !tipo.equalsIgnoreCase("FIAT")) {
                System.out.println("Error: el tipo debe ser 'Cripto' o 'FIAT'.");
            }
        }

        // Solicitar el resto de los datos
        System.out.println("Ingrese el nombre de la moneda: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la nomenclatura de la moneda (ej. BTC, ARS): ");
        String nomenclatura = scanner.nextLine();

        System.out.println("Ingrese el valor en dólares de la moneda: ");
        float valorDolar = scanner.nextFloat();

        System.out.println("Ingrese el stock disponible: ");
        float stock = scanner.nextFloat();

        scanner.nextLine();  // Limpiar el buffer de entrada
        // Confirmar si el usuario quiere guardar los datos
        System.out.println("¿Desea guardar la moneda en la base de datos? (S/N): ");
        
        String confirmacion = scanner.nextLine();
        scanner.close();

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
        scanner.close();
    }

    public List<Moneda> monedasOrdenadas(int opcion) {
        try {
            MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
            List<Moneda> monedas = monedaDAO.listarMonedas();  // Obtener todas las monedas

            switch (opcion) {
                case 1:
                    Collections.sort(monedas);  // Orden por valorEnDolar usando compareTo
                    break;
                case 2:
                    Collections.sort(monedas, new ComparadorPorNomenclatura());  // Orden por nomenclatura usando Comparator
                    break;
                case 3:
                    Collections.sort(monedas, new ComparadorPorStockDescendente());  // Orden por stock descendente
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return null;
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
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cómo desea ordenar las monedas?");
        System.out.println("1: Por valor en dólares");
        System.out.println("2: Por nomenclatura");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.close();

        List<Moneda> monedasOrdenadas = monedasOrdenadas(opcion);
        System.out.println("\n--- Monedas Ordenadas ---");
        for (Moneda moneda : monedasOrdenadas) {
            System.out.println(moneda);  // Muestra detalles de cada moneda
        }
    }
    public void imprimirStockOrdenado() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cómo desea ordenar el stock?");
        System.out.println("2: Por nomenclatura");
        System.out.println("3: Por stock descendente");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.close();

        List<Moneda> stockOrdenado = monedasOrdenadas(opcion);
        System.out.println("\n--- Stock Ordenado ---");
        for (Moneda moneda : stockOrdenado) {
            System.out.printf("Nomenclatura: %s, Stock: %.2f%n", moneda.getNomenclatura(), moneda.getStock());
        }
    }
}