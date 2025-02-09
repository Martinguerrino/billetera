package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Auxiliar.Panel;
import Controladores.ControladorTransacciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;

public class VistaTransacciones extends Panel {
    private JTable tablaTransacciones;
    private JLabel lblTitulo;
    private ControladorTransacciones miControlador;

    public VistaTransacciones(ControladorTransacciones miControlador) throws SQLException {
    	super();
        this.miControlador = miControlador;
        // Crear el título
        lblTitulo = new JLabel("Transacciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio alrededor del título
        add(lblTitulo);

        // Crear la tabla
        String[] columnas = {"Usuario", "Fecha", "Resumen"}; // Encabezados de las columnas
        Object[][] datos = miControlador.obtenerTransacciones(); // Estructura de los datos {"usuario1", "2024-12-01", "Compra de activos"}
        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas);
        tablaTransacciones = new JTable(modeloTabla);
        tablaTransacciones.setFillsViewportHeight(true);

        // Agregar la tabla dentro de un JScrollPane para permitir scroll
        JScrollPane scrollPane = new JScrollPane(tablaTransacciones);
        add(scrollPane);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volver());
        add(btnVolver);

        // Listener para actualizar las posiciones cuando se cambia el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarPosiciones();
            }
        });

        actualizarPosiciones();
    }

    protected void actualizarPosiciones() {
        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int lblWidth = 400;
        int btnWidth = 200;
        int scrollWidth = 500;
        int lblHeight = 40;
        int btnHeight = 40;
        int scrollHeight = height - lblHeight - btnHeight - 50;

        // Ajustamos la posición del título
        lblTitulo.setBounds(centerX - lblWidth / 2, 10, lblWidth, lblHeight);

        // Ajustamos la tabla (con scroll)
        JScrollPane scrollPane = (JScrollPane) getComponent(1); // El segundo componente debe ser la tabla
        scrollPane.setBounds(centerX - scrollWidth / 2, lblHeight + 10, scrollWidth, scrollHeight);

        // Ajustamos el botón Volver
        JButton btnVolver = (JButton) getComponent(2); // El tercer componente es el botón
        btnVolver.setBounds(centerX - btnWidth / 2, height - 50, btnWidth, btnHeight);
    }

    private void volver() {
        miControlador.redirigirIndex();
    }
}