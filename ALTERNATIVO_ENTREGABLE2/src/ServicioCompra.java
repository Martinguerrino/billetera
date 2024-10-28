
import java.sql.SQLException;
import java.util.Scanner;

public class ServicioCompra 
{
    public  void comprar() throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario ingresar los datos de la compra
        System.out.print("Ingrese la criptomoneda a comprar (ej. BTC): ");
        String nomenclaturaCripto = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda FIAT con la que va a pagar (ej. ARS): ");
        String nomenclaturaFiat = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad disponible en " + nomenclaturaFiat + ": ");
        float cantidadFiat = scanner.nextFloat();
        
        scanner.close();
        
        MonedaDAOjdbc monedaDAO = FactoryDAO.getMonedaDAO();
        Moneda criptoMoneda = monedaDAO.buscarMonedaPorNomenclatura(nomenclaturaCripto);
        if (criptoMoneda.getStock() <= 0) {
            System.out.println("Error: no hay stock disponible de " + criptoMoneda.getNomenclatura() + ".");
            return ;
        }
        Activo fiat = new Activo(cantidadFiat, "Dirección_placeholder", monedaDAO.buscarMonedaPorNomenclatura(nomenclaturaFiat));
        Compra compra = new Compra(criptoMoneda,fiat);
        //Si la criptomoneda a comprar no es aún un activo, se crea el nuevo activo.
        ActivoCriptoDAOjdbc activoDAO = FactoryDAO.getActivoCriptoDAO();
        
        if(activoDAO.obtenerActivoPorNomenclatura(nomenclaturaCripto)==null)
        {
            Activo nuevoActivo = new Activo((float)0, "Dirección_placeholder", criptoMoneda);
            activoDAO.cargarActivo(nuevoActivo);
        }
        
        compra.ejecutarTransaccion();
        
        
    }
}
