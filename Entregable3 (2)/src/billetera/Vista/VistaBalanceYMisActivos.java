package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Auxiliar.Activo;
import Auxiliar.ModeloActivosTabla;
import Auxiliar.ModeloCotizacionTabla;
import Auxiliar.Panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import Controladores.ControladorBalanceYMisActivos;
public class VistaBalanceYMisActivos extends Panel {
    private JTable tableFiat;
    private JTable tableCripto;
    private JLabel lblSaldo; // Etiqueta para mostrar el saldo
    private ControladorBalanceYMisActivos miControlador;
    private Object[][] activoFiat;
    private Object[][] activoCripto;
    private ModeloActivosTabla modelFiat;
    private String[] columnNames = {"Nombre", "Nomenclatura", "Cantidad", "Icono", "Valor en Dólar"};
    /*
    private void actualizarTabla() throws SQLException {
    	cotizacionesActuales=miControlador.obtenerCotizaciones();
        String[] columnas = {"Nombre", "Nomenclatura", "Icono", "Valor en dolares", "Volatilidad"};
        tablaCotizacion.setModel(new ModeloCotizacionTabla(cotizacionesActuales, columnas));        
    }
    */
    
    
    public VistaBalanceYMisActivos(ControladorBalanceYMisActivos miControlador) throws SQLException {
        
        activoFiat= miControlador.ObtenerActivosFiat();
        activoCripto = miControlador.obtenerActivosCripto();
        
        this.miControlador = miControlador;

        // Configuración de la ventana
        setSize(800, 600);
        setLayout(new BorderLayout()); // Layout principal

        // Crear panel superior con título y saldo
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JPanel panelSaldo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTextoSaldo = new JLabel("Saldo:");
        lblTextoSaldo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSaldo = new JLabel(); // Saldo inicial
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSaldo.setText(String.format("$ %.2f", miControlador.ObtenerSaldo())); // Mostrar saldo inicial
        panelSaldo.add(lblTextoSaldo);
        panelSaldo.add(lblSaldo);

        JLabel lblTitulo = new JLabel("Balance y Mis Activos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        panelSuperior.add(panelSaldo, BorderLayout.WEST); // Mover el saldo a la izquierda
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        // Tablas de activos Fiat y Cripto
        JPanel panelTablas = new JPanel(new GridLayout(2, 1, 10, 10));

        tableFiat = new JTable();      
        actualizarTablaFiat();

        ModeloActivosTabla modelCripto = new ModeloActivosTabla(miControlador.obtenerActivosCripto(), columnNames);
        tableCripto = new JTable(modelCripto);
        tableCripto.setRowHeight(50);
        tableFiat.setRowHeight(50);
        panelTablas.add(new JScrollPane(tableFiat));
        panelTablas.add(new JScrollPane(tableCripto));

        // Panel inferior con botones
        JPanel panelInferior = new JPanel();
        JButton btnGenerarDatos = new JButton("Generar Datos de Prueba");
        btnGenerarDatos.addActionListener(new LGenerarAleatorio());
        
        JButton btnGenerarCSV = new JButton("Generar CSV");
        btnGenerarCSV.addActionListener(new LGenerarCSV());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volver());
        
        panelInferior.add(btnGenerarDatos);
        panelInferior.add(btnGenerarCSV);
        panelInferior.add(btnVolver);

        // Agregar componentes al JFrame
        add(panelSuperior, BorderLayout.NORTH);
        add(panelTablas, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void volver() {
    	
        miControlador.redirigirIndex();
    }

    // Listener para el botón "Generar Datos de Prueba"
    private class LGenerarAleatorio implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
            	if(activoFiat.length==0) {
            		miControlador.generarDatosDePrueba(); // Llama al método del controlador    
            		activoFiat= miControlador.ObtenerActivosFiat();
            		actualizarTablaFiat();
            		
            	}else {
            		JOptionPane.showMessageDialog(VistaBalanceYMisActivos.this, 
                            "Datos de prueba ya generados","Error" ,JOptionPane.ERROR_MESSAGE);
            	}
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(VistaBalanceYMisActivos.this, 
                        "Error al generar datos de prueba: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void actualizarTablaFiat() throws SQLException {
        tableFiat.setModel(new ModeloActivosTabla(miControlador.ObtenerActivosFiat(), columnNames));
    }
    
    
    // Listener para el botón "Generar CSV"
    private class LGenerarCSV implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser directoryChooser = new JFileChooser();
            directoryChooser.setDialogTitle("Seleccionar directorio para guardar el archivo CSV");
            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = directoryChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = directoryChooser.getSelectedFile();
                String directoryPath = selectedDirectory.getAbsolutePath();
                String filePath = directoryPath + File.separator + "transacciones.csv";

                try {
                    miControlador.exportarTransaccionesACSV(filePath, activoCripto, activoFiat);
                    JOptionPane.showMessageDialog(null, "Archivo CSV guardado en: " + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al generar el archivo CSV: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
