package Vista;

import Auxiliar.Panel;
import Auxiliar.PanelTablas;
import Auxiliar.RoundedButton;
import Auxiliar.Tabla;
import Controladores.ControladorTransacciones;
import etc.Colores;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;


public class VistaTransacciones extends Panel {
    private Tabla tablaTransacciones;
    private JLabel lblTitulo;
    private ControladorTransacciones miControlador;
    private RoundedButton btnVolver;
    private RoundedButton btnGenerarCSV;
    
    // Definición de colores y fuentes
    private static final Color BG_COLOR = Colores.FONDO.getColor();
    private static final Color TEXT_COLOR = Colores.TEXTO.getColor();
    private static final Color BUTTON_COLOR = Colores.BOTON.getColor();
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public VistaTransacciones(ControladorTransacciones miControlador) throws SQLException {
        super();
        this.miControlador = miControlador;
        setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarTabla();
        configurarBotones();

        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelInferior(), BorderLayout.SOUTH);
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Transacciones", SwingConstants.CENTER);
        lblTitulo.setFont(TITLE_FONT);
        lblTitulo.setForeground(TEXT_COLOR);

        btnVolver = new RoundedButton("Volver",150,40);
        btnGenerarCSV = new RoundedButton("Generar CSV",150,40);
    }

    private void configurarTabla() throws SQLException {
        String[] columnas = {"Usuario", "Fecha", "Resumen"};
        Object[][] datos = miControlador.obtenerTransacciones();
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaTransacciones = new Tabla(modelo);
       
    }

    private void configurarBotones() {
        configurarBoton(btnVolver);
        configurarBoton(btnGenerarCSV);

        btnVolver.addActionListener(e -> volver());
        btnGenerarCSV.addActionListener(e -> generarCSV());
    }

    private void configurarBoton(RoundedButton boton) {
        boton.setBackground(BUTTON_COLOR);
        boton.setForeground(Color.BLACK);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
        // Remover el borde de forma completa
        boton.setBorder(null);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(150, 40));
    }

    private JPanel crearPanelSuperior() {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(BG_COLOR);
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBusqueda.setBackground(BG_COLOR);
        panelSuperior.add(panelBusqueda, BorderLayout.SOUTH);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panelSuperior;
    }

    private JPanel crearPanelCentral() {
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Colores.FONDO.getColor());
        
        // Crear un JScrollPane personalizado sin bordes
        JScrollPane scrollPane = new PanelTablas(tablaTransacciones);
        
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        return panelCentral;
    }

    private JPanel crearPanelInferior() {
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelInferior.setBackground(BG_COLOR);
        panelInferior.add(btnVolver);
        panelInferior.add(btnGenerarCSV);
        return panelInferior;
    }

    private void volver() {
        miControlador.redirigirIndex();
    }

    private void generarCSV() {
        // Implementar la lógica para generar CSV
        JOptionPane.showMessageDialog(this, "Función de generar CSV no implementada", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void actualizarPosiciones() {
        revalidate();
        repaint();
    }
}