package Vista;
import javax.swing.*;

import billetera.Controladores.ControladorIndex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaIndex extends JFrame {
    private JButton btnBalanceActivos;
    private JButton btnTransacciones;
    private JButton btnCompra;
    private JButton btnCotizaciones;
    private JButton btnLogOut;

    private ControladorIndex miControlador;

    public VistaIndex(ControladorIndex miControlador) {
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
        btnLogOut.addActionListener(new LLogOut());
        // Layout para organizar los botones en 2 columnas y 2 filas
        setLayout(new GridLayout(2, 2, 10, 10)); // 2 filas, 2 columnas con espacio entre ellas

        // Añadir los botones a la ventana
        add(btnBalanceActivos);
        add(btnTransacciones);
        add(btnCompra);
        add(btnCotizaciones);
        add(btnLogOut);
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
    
    private class LCotizaciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            miControlador.redirigirCotizaciones();
        }
    }
    
    private class LLogOut implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		miControlador.LogOut();
    	}
    }
}
