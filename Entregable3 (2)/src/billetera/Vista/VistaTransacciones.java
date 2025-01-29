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

public class VistaTransacciones extends Panel {
    private JTable tablaTransacciones;
    private JLabel lblTitulo;
    private JButton btnGenerarCSV; // Nuevo botón
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

        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas);
        tablaTransacciones = new JTable(modeloTabla);
        tablaTransacciones.setFillsViewportHeight(true);

        // Agregar la tabla dentro de un JScrollPane para permitir scroll
        JScrollPane scrollPane = new JScrollPane(tablaTransacciones);

        // Crear el botón "Generar CSV"
        btnGenerarCSV = new JButton("Generar CSV");
        btnGenerarCSV.addActionListener(new GenerarCSVActionListener(miControlador)); // Añadir el ActionListener al botón

        // Panel inferior para el botón
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInferior.add(btnGenerarCSV);

        // Agregar componentes al JFrame
        add(lblTitulo, BorderLayout.NORTH); // Título en la parte superior
        add(scrollPane, BorderLayout.CENTER); // Tabla al centro
        add(panelInferior, BorderLayout.SOUTH); // Botón en la parte inferior
    }
}

// Clase aparte para manejar el evento del botón
class GenerarCSVActionListener implements ActionListener {
    private ControladorTransacciones miControlador;

    public GenerarCSVActionListener(ControladorTransacciones miControlador) {
        this.miControlador = miControlador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Crear un JFileChooser para seleccionar un directorio
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setDialogTitle("Seleccionar directorio para guardar el archivo CSV");
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int userSelection = directoryChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = directoryChooser.getSelectedFile();
            String directoryPath = selectedDirectory.getAbsolutePath();
            String filePath = directoryPath + File.separator + "transacciones.csv"; // Nombre del archivo por defecto

            try {
                // Llamar al método del controlador para exportar las transacciones
                miControlador.exportarTransaccionesACSV(filePath);
                JOptionPane.showMessageDialog(null, "Archivo CSV guardado en: " + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al generar el archivo CSV: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
}}
