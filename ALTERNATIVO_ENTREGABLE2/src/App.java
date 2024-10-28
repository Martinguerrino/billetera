public class App {
    public static void main(String[] args) throws Exception {
        //hacer que el programa corra las funcionalidades de la aplicacion
        //que cargue las tablas de la base de datos con las funciones de la clase MyConnection
        //que permita al usuario interactuar con la aplicacion
        //que permita al usuario realizar compras de criptomonedas
        //que permita al usuario ver el estado de su cartera
        ServicioCompra servicioCompra = new ServicioCompra();
        servicioCompra.comprar();
        

    }
}
