import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ServicioActivos {
    /**
     * Solicita al usuario ingresar la cantidad y la nomenclatura de un activo y lo guarda en la base de datos.
     */
    public void ingresarActivo() {
        Scanner scanner = new Scanner(System.in);
        //limpiar buffer scanner
        scanner.reset();
        try {
            // Solicitar al usuario ingresar la nomenclatura de la moneda
            System.out.print("Ingrese la nomenclatura de la moneda: ");
            String nomenclatura = scanner.nextLine().toUpperCase();
            MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
            // Verificar si la nomenclatura existe en la base de datos
            if (!monedaDAO.existeNomenclatura(nomenclatura)) {
                scanner.close();
                System.out.println("La nomenclatura ingresada no existe en las monedas registradas.");
                return;
            }
            Moneda moneda = monedaDAO.buscarMonedaPorNomenclatura(nomenclatura);
            
            // Solicitar al usuario ingresar la cantidad
            System.out.print("Ingrese la cantidad de la moneda: ");
            float cantidad = scanner.nextFloat();
            if (cantidad <= 0) {
                scanner.close();
                System.out.println("La cantidad debe ser mayor a 0.");
                return;
            }
            

            System.out.print("Ingrese 1 si quiere confirmar o 0 si quiere cancelar: ");
            int confirmacion = scanner.nextInt();
            if(confirmacion==0)
            {
                scanner.close();
                return;
            }
            // Crear el objeto Activo
            Activo activo = new Activo(cantidad, null, moneda);
            scanner.close();
            // Guardar el activo en la base de datos
            if(moneda.getTipo().equalsIgnoreCase("cripto"))
            {
                ActivoCriptoDAOjdbc activoDAO = FactoryDAO.getActivoCriptoDAO();
                activoDAO.cargarActivo(activo);
            }
            else if(moneda.getTipo().equalsIgnoreCase("fiat"))
            {
                ActivoFiatDAOjdbc activoDAO = FactoryDAO.getActivoFiatDAO();
                activoDAO.cargarActivo(activo);
            }
            else
            {
                System.out.println("Error: tipo de moneda no válido.");
                return;
            }
            
            System.out.println("Activo guardado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error al guardar el activo: " + e.getMessage());
        }
    }

    /**
     * Muestra en pantalla todos los activos registrados en la base de datos.
     */
    public void mostrarActivos() throws SQLException {
        // Obtener la lista de activos desde el DAO
        ActivoCriptoDAOjdbc activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
        List<Activo> activos = activoCriptoDAO.listarActivos();

        // Solicitar al usuario el tipo de ordenamiento
        System.out.println("Seleccione el tipo de ordenamiento:");
        System.out.println("1. Sin ordenamiento");
        System.out.println("2. Ordenado por cantidad (descendente)");
        System.out.println("3. Ordenado por nomenclatura");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                // Sin ordenamiento
                break; // No se realiza ninguna acción
            case 2:
                // Ordenar por cantidad de manera descendente
                Collections.sort(activos); // Usando compareTo en la clase Activo
                break;
            case 3:
                // Ordenar por nomenclatura
                Collections.sort(activos, new ComparadorActivosPorNomenclatura());
                break;
            default:
                System.out.println("Opción no válida. Se mostrará sin ordenamiento.");
                break;
        }

        // Mostrar activos
        mostrarActivosLista(activos);
    }

    private void mostrarActivosLista(List<Activo> activos) {
        System.out.println("Lista de activos:");
        for (Activo activo : activos) {
            System.out.println("Nomenclatura: " + activo.getMoneda().getNomenclatura() +
                               ", Cantidad: " + activo.getCantidad() +
                               ", Dirección: " + activo.getDireccion());
        }
    }
}