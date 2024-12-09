package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import billetera.Controladores.ControladorCotizaciones;

public class VistaCotizaciones extends JFrame{
	ControladorCotizaciones miControlador;
	JLabel lblTitulo;
	JTable tablaCotizacion;
	public VistaCotizaciones(ControladorCotizaciones miControlador) throws SQLException {
		super("Cotizaciones");
        this.miControlador = miControlador;
        String[] columnas = {"Nombre", "Nomenclatura", "Icono" , "Valor en dolares", "Volatilidad"}; // Encabezados de las columnas
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCotizacion = new JTable(modeloTabla);
        tablaCotizacion.setFillsViewportHeight(true);
        actualizarCotizaciones();
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


        // Agregar la tabla dentro de un JScrollPane para permitir scroll
        JScrollPane scrollPane = new JScrollPane(tablaCotizacion);

        // Agregar componentes al JFrame
        add(lblTitulo, BorderLayout.NORTH); // Título en la parte superior
        add(scrollPane, BorderLayout.CENTER); // Tabla al centro
    }
	public void actualizarCotizaciones() throws SQLException {
		DefaultTableModel modeloTabla = (DefaultTableModel) tablaCotizacion.getModel();
		Object[][] cotizaciones=miControlador.obtenerCotizaciones();
		modeloTabla.setRowCount(0);
		for(Object[] cotizacion : cotizaciones) {
			modeloTabla.addRow(cotizacion);
		}
	}
	
}