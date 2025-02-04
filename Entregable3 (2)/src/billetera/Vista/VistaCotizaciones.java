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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Auxiliar.ModeloCotizacionTabla;
import Auxiliar.Panel;
import Controladores.ControladorCotizaciones;

public class VistaCotizaciones extends Panel {
    ControladorCotizaciones miControlador;
    JLabel lblTitulo;
    JTable tablaCotizacion;
    JPanel panelImagen; // Panel para mostrar la imagen
    JLabel lblImagen;   // JLabel para contener la imagen

    public VistaCotizaciones(ControladorCotizaciones miControlador) throws SQLException {
        this.miControlador = miControlador;
        String[] columnas = {"Nombre", "Nomenclatura", "Icono", "Valor en dolares", "Volatilidad"}; // Encabezados de las columnas
        tablaCotizacion = new JTable();
        tablaCotizacion.setModel(new ModeloCotizacionTabla(miControlador.obtenerCotizaciones(), columnas));
        tablaCotizacion.setFillsViewportHeight(true);
        
        // Configuración de la ventana
        setSize(600, 400);
        setLayout(new BorderLayout()); // Layout principal para organizar componentes
        tablaCotizacion.setRowHeight(50);
        
        // Crear el título
        lblTitulo = new JLabel("Cotizaciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacio alrededor del título

        // Crear un panel para mostrar la imagen
        panelImagen = new JPanel(); // Crear el panel
        lblImagen = new JLabel(); // Crear el JLabel para mostrar la imagen
        panelImagen.add(lblImagen); // Añadir el JLabel al panel

        // Agregar la tabla dentro de un JScrollPane para permitir scroll
        JScrollPane scrollPane = new JScrollPane(tablaCotizacion);
        
        // Crear botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volver());
        
        // Panel inferior con el botón de volver
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnVolver);

        // Agregar componentes al JFrame
        add(lblTitulo, BorderLayout.NORTH); // Título en la parte superior
        add(scrollPane, BorderLayout.CENTER); // Tabla al centro
        add(panelImagen, BorderLayout.SOUTH); // Panel de imagen en la parte inferior
        add(panelInferior, BorderLayout.SOUTH); // Botón de volver en la parte inferior
        
        mostrarImagen("src/billetera/Controladores/btc.png");
    }

    // Método para actualizar la imagen en el panel
    public void mostrarImagen(String rutaImagen) {
        ImageIcon icono = new ImageIcon(rutaImagen);
        // Redimensionar la imagen si es necesario
        Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionar a 100x100
        lblImagen.setIcon(new ImageIcon(img)); // Establecer la imagen redimensionada en el JLabel
    }
    
    private void volver() {
        miControlador.redirigirIndex();
    }
}

