package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import billetera.Controladores.ControladorTransacciones;

import java.awt.*;
import java.sql.SQLException;

public class VistaTransacciones extends JFrame {
    private JTable tablaTransacciones;
    private JLabel lblTitulo;
    private ControladorTransacciones miControlador;

    public VistaTransacciones(ControladorTransacciones miControlador) throws SQLException {
        super("Transacciones");
        this.miControlador = miControlador;

        // Configuración de la ventana
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout()); // Layout principal para organizar componentes

        // Crear el título
        lblTitulo = new JLabel("Transacciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio alrededor del título

        // Crear la tabla
        String[] columnas = {"Usuario", "Fecha", "Resumen"}; // Encabezados de las columnas
        Object[][] datos = miControlador.obtenerTransacciones(); //deben tener la estructura{"usuario1", "2024-12-01", "Compra de activos"},


        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas);
        tablaTransacciones = new JTable(modeloTabla);
        tablaTransacciones.setFillsViewportHeight(true);

        // Agregar la tabla dentro de un JScrollPane para permitir scroll
        JScrollPane scrollPane = new JScrollPane(tablaTransacciones);

        // Agregar componentes al JFrame
        add(lblTitulo, BorderLayout.NORTH); // Título en la parte superior
        add(scrollPane, BorderLayout.CENTER); // Tabla al centro
    }

}