package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Auxiliar.ModeloCotizacionTabla;
import Controladores.ControladorCotizaciones;


public class VistaCotizaciones extends JFrame {
    ControladorCotizaciones miControlador;
    JLabel lblTitulo;
    JTable tablaCotizacion;
    JPanel panelImagen; // Panel para mostrar la imagen
    JLabel lblImagen;   // JLabel para contener la imagen

    
    
    public VistaCotizaciones(ControladorCotizaciones miControlador) throws SQLException {
        super("Cotizaciones");
        this.miControlador = miControlador;
        String[] columnas = {"Nombre", "Nomenclatura", "Icono", "Valor en dolares", "Volatilidad"}; // Encabezados de las columnas
        tablaCotizacion = new JTable();
        tablaCotizacion.setModel(new ModeloCotizacionTabla(miControlador.obtenerCotizaciones(),columnas));
        tablaCotizacion.setFillsViewportHeight(true);
       
        // Configuración de la ventana
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
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

        // Agregar componentes al JFrame
        add(lblTitulo, BorderLayout.NORTH); // Título en la parte superior
        add(scrollPane, BorderLayout.CENTER); // Tabla al centro
        add(panelImagen, BorderLayout.SOUTH); // Panel de imagen en la parte inferior
        mostrarImagen("src/billetera/Controladores/btc.png");
    }


    // Método para actualizar la imagen en el panel
    public void mostrarImagen(String rutaImagen) {
        ImageIcon icono = new ImageIcon(rutaImagen);
        // Redimensionar la imagen si es necesario
        System.out.println("!!!!!!!!!!");
        
        Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensionar a 100x100
        lblImagen.setIcon(new ImageIcon(img)); // Establecer la imagen redimensionada en el JLabel
    }
}
