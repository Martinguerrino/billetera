package Vista;

import Auxiliar.ModeloCotizacionTabla;
import Auxiliar.Panel;
import Auxiliar.PanelTablas;
import Auxiliar.RoundedButton;
import Auxiliar.Tabla;
import Controladores.ControladorCotizaciones;
import etc.Colores;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.*;

public class VistaCotizaciones extends Panel {
    private Object[][] cotizacionesActuales;
    private ControladorCotizaciones miControlador;
    private JLabel lblTitulo;
    private Tabla tablaCotizacion;
    private RoundedButton btnGenerarMonedas;
    private RoundedButton btnVolver;
    private PanelTablas scrollPane;

    // Definición de colores y fuentes
    private static final Color BG_COLOR = Colores.FONDO.getColor();
    private static final Color TEXT_COLOR = Colores.TEXTO.getColor();
    private static final Color BUTTON_COLOR = Colores.AMARILLO.getColor();
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public VistaCotizaciones(ControladorCotizaciones miControlador) throws SQLException {
        super();
        this.miControlador = miControlador;
        setBackground(BG_COLOR);
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
        actualizarTabla();
    }

    private void inicializarComponentes() {
        // Configuración del título
        lblTitulo = new JLabel("Cotizaciones", SwingConstants.CENTER);
        lblTitulo.setFont(TITLE_FONT);
        lblTitulo.setForeground(TEXT_COLOR);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Configuración de la tabla
        tablaCotizacion = new Tabla(null);

        scrollPane = new PanelTablas(tablaCotizacion);
        // Configuración de botones
        btnVolver = new RoundedButton("Volver",150,40);
        btnGenerarMonedas = new RoundedButton("Generar Monedas",150,40);
     
        setPreferredSize(new Dimension(800, 600));
    }


    private void configurarLayout() {
        setLayout(new BorderLayout());
        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelInferior.setBackground(BG_COLOR);
        panelInferior.add(btnGenerarMonedas);
        panelInferior.add(btnVolver);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnVolver.addActionListener(e -> volver());
        btnGenerarMonedas.addActionListener(e -> {
            try {
                generarMonedas();
            } catch (SQLException ex) {
                manejarError(ex);
            }
        });
    }

    private void actualizarTabla() throws SQLException {
        try {
            cotizacionesActuales = miControlador.obtenerCotizaciones();
            String[] columnas = {"Nombre", "Nomenclatura", "Icono", "Valor en dólares", "Volatilidad"};
            tablaCotizacion.setModel(new ModeloCotizacionTabla(cotizacionesActuales, columnas));
        } catch (SQLException ex) {
            manejarError(ex);
            throw ex;
        }
    }

    private void volver() {
        miControlador.redirigirIndex();
    }

    private void generarMonedas() throws SQLException {
        if (cotizacionesActuales.length == 0) {
            try {
                miControlador.generarDatosDePrueba();
                actualizarTabla();
            } catch (SQLException ex) {
                manejarError(ex);
                throw ex;
            }
        } else {
            mostrarMensajeError("Ya están generadas las monedas");
        }
    }

    private void manejarError(Exception ex) {
        mostrarMensajeError("Error: " + ex.getMessage());
        ex.printStackTrace();
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    protected void actualizarPosiciones() {
        revalidate();
        repaint();
    }

    // Métodos getter para testing y acceso desde otras clases si es necesario
    public JTable getTablaCotizacion() {
        return tablaCotizacion;
    }

    public RoundedButton getBtnGenerarMonedas() {
        return btnGenerarMonedas;
    }

    public RoundedButton getBtnVolver() {
        return btnVolver;
    }
}