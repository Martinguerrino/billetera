package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Auxiliar.Panel;
import Controladores.ControladorTransacciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;

public class VistaTransacciones extends Panel {
    private JTable tablaTransacciones;
    private JLabel lblTitulo;
    private ControladorTransacciones miControlador;

    public VistaTransacciones(ControladorTransacciones miControlador) throws SQLException {
        this.miControlador = miControlador;

        // Configuración de la ventana
        
        setSize(600, 400);
        setLayout(new BorderLayout()); // Layout principal para organizar componentes

        // Crear el título
        lblTitulo = new JLabel("Transacciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio alrededor del título

        // Crear la tabla
        String[] columnas = {"Usuario", "Fecha", "Resumen"}; // Encabezados de las columnas
        Object[][] datos = miControlador.obtenerTransacciones(); //deben tener la estructura{"usuario1", "2024-12-01", "Compra de activos"}
        System.out.println(datos.length);
        for (Object[] dato : datos) {
        	System.out.println("dato:");
			System.out.println(Arrays.toString(dato));
		}
        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas);
        tablaTransacciones = new JTable(modeloTabla);
        tablaTransacciones.setFillsViewportHeight(true);

        // Agregar la tabla dentro de un JScrollPane para permitir scroll
        JScrollPane scrollPane = new JScrollPane(tablaTransacciones);

        // Panel inferior para los botones
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volver());
        panelInferior.add(btnVolver);

        // Agregar componentes al JFrame
        add(lblTitulo, BorderLayout.NORTH); // Título en la parte superior
        add(scrollPane, BorderLayout.CENTER); // Tabla al centro
        add(panelInferior, BorderLayout.SOUTH); // Botón en la parte inferior
    }

    private void volver() {
        miControlador.redirigirIndex();
    }
}
