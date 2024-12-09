package Prueba.Servicios;
import Prueba.Activos.Activo;
import Prueba.Activos.ActivoCriptoDAOjdbc;
import Prueba.Activos.ActivoFiatDAOjdbc;
import Prueba.Activos.ComparadorActivosPorNomenclatura;
import Prueba.FactoryDAO;
import Prueba.Interfaz;
import Prueba.Monedas.Moneda;
import Prueba.Monedas.MonedaDAOjdbc;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ServicioActivos {
    /**
     * Solicita al usuario ingresar la cantidad y la nomenclatura de un activo y lo guarda en la base de datos.
     */
    public void ingresarActivo() {
        try {
            // Solicitar al usuario ingresar la nomenclatura de la moneda
            System.out.print("Ingrese la nomenclatura de la moneda: ");
            String nomenclatura = Interfaz.pedirString().toUpperCase();
            MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
            // Verificar si la nomenclatura existe en la base de datos
            if (!monedaDAO.existeNomenclatura(nomenclatura)) {
                System.out.println("La nomenclatura ingresada no existe en las monedas registradas.");
                return;
            }
            Moneda moneda = monedaDAO.buscarMonedaPorNomenclatura(nomenclatura);
            
            // Solicitar al usuario ingresar la cantidad
            System.out.print("Ingrese la cantidad de la moneda: ");
            float cantidad = Interfaz.pedirFloat();
            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a 0.");
                return;
            }
            

            System.out.print("Ingrese 1 si quiere confirmar o 0 si quiere cancelar: ");
            int confirmacion = Interfaz.pedirInt();
            if(confirmacion==0)
            {
                return;
            }
            // Crear el objeto Activo
            Activo activo = new Activo(cantidad,moneda);
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
    public void mostrarActivosCripto() throws SQLException {
        // Obtener la lista de activos desde el DAO
        ActivoCriptoDAOjdbc activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
        List<Activo> activos = activoCriptoDAO.listarActivos();

        // Solicitar al usuario el tipo de ordenamiento
        System.out.println("Seleccione el tipo de ordenamiento:");
        System.out.println("1. Sin ordenamiento");
        System.out.println("2. Ordenado por cantidad (descendente)");
        System.out.println("3. Ordenado por nomenclatura");
        @SuppressWarnings("resource")
        int opcion = Interfaz.pedirInt();
        
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
                               " | Cantidad: " + activo.getCantidad() );
        }
    }

    public void mostrarActivosFiat() throws SQLException {
        // Obtener la lista de activos desde el DAO
        
        ActivoFiatDAOjdbc activoFiatDAO = FactoryDAO.getActivoFiatDAO();
        List<Activo> activos = activoFiatDAO.listarActivos();

        // Solicitar al usuario el tipo de ordenamiento
        System.out.println("Seleccione el tipo de ordenamiento:");
        System.out.println("1. Sin ordenamiento");
        System.out.println("2. Ordenado por cantidad (descendente)");
        System.out.println("3. Ordenado por nomenclatura");
        @SuppressWarnings("resource")
        int opcion = Interfaz.pedirInt();
        
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
    

    
}