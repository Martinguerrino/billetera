package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Auxiliar.ModeloCotizacionTabla;
import Auxiliar.Panel;
import Controladores.ControladorCotizaciones;
public class VistaCotizaciones extends Panel {
	Object[][] cotizacionesActuales;
    ControladorCotizaciones miControlador;
    JLabel lblTitulo;
    JTable tablaCotizacion;
    JPanel panelImagen;
    JLabel lblImagen;
    JButton btnGenerarMonedas;
    JButton btnVolver;

    public VistaCotizaciones(ControladorCotizaciones miControlador) throws SQLException {
        this.miControlador = miControlador;
        tablaCotizacion = new JTable();
        tablaCotizacion.setFillsViewportHeight(true);
        this.actualizarTabla();
        setSize(600, 400);
        setLayout(new BorderLayout());
        tablaCotizacion.setRowHeight(50);

        lblTitulo = new JLabel("Cotizaciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelImagen = new JPanel();
        lblImagen = new JLabel();
        panelImagen.add(lblImagen);

        JScrollPane scrollPane = new JScrollPane(tablaCotizacion);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volver());

        btnGenerarMonedas = new JButton("Generar Monedas");
        btnGenerarMonedas.addActionListener(e -> {
			try {
				generarMonedas();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnGenerarMonedas);
        panelInferior.add(btnVolver);

        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelImagen, BorderLayout.SOUTH);
        add(panelInferior, BorderLayout.SOUTH);

    }


    private void actualizarTabla() throws SQLException {
    	cotizacionesActuales=miControlador.obtenerCotizaciones();
        String[] columnas = {"Nombre", "Nomenclatura", "Icono", "Valor en dolares", "Volatilidad"};
        tablaCotizacion.setModel(new ModeloCotizacionTabla(cotizacionesActuales, columnas));        
    }
    
    private void volver() {
        miControlador.redirigirIndex();
    }

    private void generarMonedas() throws SQLException {
    	if(cotizacionesActuales.length==0) {
    		miControlador.generarDatosDePrueba();
    		this.actualizarTabla();
    	}
    	else {
    		JOptionPane.showMessageDialog(this, "Ya estan generadas las monedas", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
}
