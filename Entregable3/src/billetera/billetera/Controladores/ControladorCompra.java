package billetera.Controladores;

import billetera.Modelo.ServicioCompra;

public class ControladorCompra {
    private Vista vista;
    private ServicioCompra servicioCompra;

    public ControladorCompra(Vista vista, ServicioCompra servicioCompra) {
        this.vista = vista;
        this.servicioCompra = servicioCompra;
    }

    public void controladorCompra() {
        vista.mostrarCompra();
        // Lógica para manejar la compra
        while (!servicioCompra.comprar(monedaFiat, cantidad, moneda)) {
            vista.mostrarErrorCompra();
            vista.mostrarCompra();
        }
    }
}
