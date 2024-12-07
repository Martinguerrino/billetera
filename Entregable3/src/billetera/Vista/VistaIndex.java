package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaIndex extends JFrame {
    private JButton btnBalanceActivos;
    private JButton btnTransacciones;
    private JButton btnCompra;
    private Controlador miControlador;

    public VistaIndex(Controlador miControlador) {
    	super("Opciones");
        this.miControlador = miControlador;

        // Configuración de la ventana
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear los botones
        btnBalanceActivos = new JButton("Visualización de Balance y Mis Activos");
        btnTransacciones = new JButton("Visualización de Transacciones");
        btnCompra = new JButton("Compra");
        btnCotizaciones = new JButton("Cotizaciones");

        // Agregar ActionListeners a los botones
        btnBalanceActivos.addActionListener(new LBalanceActivos());
        btnTransacciones.addActionListener(new LTransacciones());
        btnCompra.addActionListener(new LCompra());
        btnCotizaciones.addActionListener(new LCotizaciones());

        // Layout para organizar los botones en 2 columnas y 2 filas
        setLayout(new GridLayout(2, 2, 10, 10)); // 2 filas, 2 columnas con espacio entre ellas

        // Añadir los botones a la ventana
        add(btnBalanceActivos);
        add(btnTransacciones);
        add(btnCompra);
        add(btnCotizaciones);
    }

    // Clase interna para manejar el botón "Visualización de Balance y Mis Activos"
    private class LBalanceActivos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            miControlador.redirigirBalanceActivos();
        }
    }

    // Clase interna para manejar el botón "Visualización de Transacciones"
    private class LTransacciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            miControlador.redirigirTransacciones();
        }
    }

    // Clase interna para manejar el botón "Compra"
    private class LCompra implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            miControlador.redirigirCompra();
        }
}
