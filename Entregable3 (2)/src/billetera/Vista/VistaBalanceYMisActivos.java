package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Auxiliar.ModeloActivosTabla;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Controladores.ControladorBalanceYMisActivos;

public class VistaBalanceYMisActivos extends JFrame {
    private JTable tableFiat;
    private JTable tableCripto;
    private JLabel lblSaldo; // Etiqueta para mostrar el saldo
    private ControladorBalanceYMisActivos miControlador;

    public VistaBalanceYMisActivos(ControladorBalanceYMisActivos miControlador) throws SQLException {
        super("Balance y Mis Activos");
        this.miControlador = miControlador;

        // Configuración de la ventana
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
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
        String[] columnNames = {"Nombre", "Nomenclatura", "Cantidad", "Icono", "Valor en Dólar"};

        ModeloActivosTabla modelFiat = new ModeloActivosTabla(miControlador.ObtenerActivosFiat(), columnNames);
        tableFiat = new JTable(modelFiat);

        ModeloActivosTabla modelCripto = new ModeloActivosTabla(miControlador.obtenerActivosCripto(), columnNames);
        tableCripto = new JTable(modelCripto);
        tableCripto.setRowHeight(50);
        tableFiat.setRowHeight(50);
        panelTablas.add(new JScrollPane(tableFiat));
        panelTablas.add(new JScrollPane(tableCripto));

        // Panel inferior con botón para generar datos de prueba
        JPanel panelInferior = new JPanel();
        JButton btnGenerarDatos = new JButton("Generar Datos de Prueba");
        btnGenerarDatos.addActionListener( new LGenerarAleatorio());
        panelInferior.add(btnGenerarDatos);

        // Agregar componentes al JFrame
        add(panelSuperior, BorderLayout.NORTH);
        add(panelTablas, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }


    // Listener para el botón "Generar Datos de Prueba"
    private class LGenerarAleatorio implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                miControlador.generarDatosDePrueba(); // Llama al método del controlador
                JOptionPane.showMessageDialog(VistaBalanceYMisActivos.this, 
                        "Datos de prueba generados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(VistaBalanceYMisActivos.this, 
                        "Error al generar datos de prueba: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
