package Prueba.Servicios;

import Prueba.FactoryDAO;
import Prueba.Interfaz;
import Prueba.Activos.Activo;
import Prueba.Activos.ActivoCriptoDAOjdbc;
import Prueba.Transaccion.Transaccion;
import Prueba.Transaccion.TransaccionDAOjdbc;

import java.sql.*;
import java.time.LocalDateTime;

public class ServicioSwap 
{
    /*Simular un SWAP. Es decir: al seleccionar esta opción se ingresará una criptomoneda a convertir, una cantidad y la criptomoneda esperada. A modo de ejemplo, si se ingresa: BTC, 5, ETH y el sistema calcula el equivalente en ETH de 5 BTC. 
Tanto la criptomoneda a convertir como la criptomoneda esperada deben encontrarse entre Mis Activos.
Si el usuario confirma la operación se realiza la actualización de los activos.
Se guarda una descripción de la transacción en la base de datos.
Se espera que verifique el Stock disponible.
 */
    public void swap() throws SQLException
    {
        
        
        
        //mostrar activos
        ServicioActivos servicioActivos = new ServicioActivos();

        System.out.println("Activos Cripto del usuario: ");
        servicioActivos.mostrarActivosCripto();
        // Solicitar al usuario ingresar los datos del swap
        System.out.print("Ingrese la criptomoneda a convertir (ej. BTC): ");
        String nomenclaturaCripto = Interfaz.pedirString().toUpperCase();
        System.out.print("Ingrese la cantidad de " + nomenclaturaCripto + " a convertir: ");
        float cantidadCripto = Interfaz.pedirFloat();
        System.out.print("Ingrese la criptomoneda esperada (ej. ETH): ");
        String nomenclaturaEsperada = Interfaz.pedirString().toUpperCase();

        // Verificar si la criptomoneda a convertir y la criptomoneda esperada se encuentran entre los activos
        ActivoCriptoDAOjdbc activoDAO = FactoryDAO.getActivoCriptoDAO();
        Activo activoConvertir = activoDAO.obtenerActivoPorNomenclatura(nomenclaturaCripto);
        Activo activoEsperado = activoDAO.obtenerActivoPorNomenclatura(nomenclaturaEsperada);

        
        // Verificar si la criptomoneda a convertir y la criptomoneda esperada se encuentran entre los activos
        if (activoConvertir == null || activoEsperado == null) {
            System.out.println("Error: las criptomonedas deben encontrarse entre sus activos.");
            return;
        }
        // Verificar si hay stock disponible de la criptomoneda a convertir
        if (activoConvertir.getCantidad() < cantidadCripto) {
            System.out.println("Error: no hay suficiente cantidad de " + nomenclaturaCripto + ".");
            return;
        }

        // Verificar si hay stock disponible de la criptomoneda a esperar
        if (activoEsperado.getCantidad() < 0) {
            System.out.println("Error: no hay suficiente cantidad de " + nomenclaturaEsperada + ".");
            return;
        }
        
        // Calcular la cantidad de la criptomoneda esperada
        float cantidadEsperada = cantidadCripto * activoConvertir.getMoneda().getValorDolar() / activoEsperado.getMoneda().getValorDolar();
        
        // Confirmar si el usuario quiere realizar el swap
        System.out.println("¿Desea realizar el swap de " + cantidadCripto + " " + nomenclaturaCripto + " a " + cantidadEsperada + " " + nomenclaturaEsperada + "? (S/N): ");
        String confirmacion = Interfaz.pedirString();
        if (confirmacion.equalsIgnoreCase("S")) {
            // Verificar valores antes de actualizar
        if (cantidadCripto <= 0 || cantidadEsperada <= 0) {
            System.out.println("Error: las cantidades deben ser mayores a cero.");
            return;
        }

            // Actualizar los activos en la base de datos
            activoDAO.actualizarActivo(nomenclaturaCripto, activoConvertir.getCantidad() - cantidadCripto);
            activoDAO.actualizarActivo(nomenclaturaEsperada, activoEsperado.getCantidad() + cantidadEsperada);
            // Guardar la transacción en la base de datos
            TransaccionDAOjdbc transaccionDAO = FactoryDAO.getTransaccionDAO();
            String descripcion = "Swap de " + cantidadCripto + " " + nomenclaturaCripto + " a " + cantidadEsperada + " " + nomenclaturaEsperada;
            Transaccion transaccion = new Transaccion(descripcion, LocalDateTime.now(),"Swap");
            transaccionDAO.crearTransaccion(transaccion);
            System.out.println("Swap realizado exitosamente.");
        }

    }
}