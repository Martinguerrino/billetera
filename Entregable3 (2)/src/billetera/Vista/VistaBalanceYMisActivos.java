package Vista;

import Auxiliar.ModeloActivosTabla;
import Auxiliar.Panel;
import Auxiliar.PanelTablas;
import Auxiliar.RoundedButton;
import Auxiliar.Tabla;
import Controladores.ControladorBalanceYMisActivos;
import Excepciones.NoHayMonedasCargadasException;
import Vista.Ventana.VentanaEmergente;
import etc.Colores;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.*;

public class VistaBalanceYMisActivos extends Panel {
    private RoundedButton btnVolver;
    private RoundedButton btnGenerarDatos;
    private RoundedButton btnGenerarCSV;
    private Tabla tableFiat;
    private Tabla tableCripto;
    private JLabel lblSaldo;
    private ControladorBalanceYMisActivos miControlador;
    private Object[][] activoFiat;
    private Object[][] activoCripto;
    private String[] columnNames = {"Nombre", "Nomenclatura", "Cantidad", "Icono", "Valor en Dólar"};

    // Definición de colores usando constantes similares a las de VistaTransacciones
    private static final Color BG_COLOR = Colores.FONDO.getColor();
    private static final Color TEXT_COLOR = Colores.TEXTO.getColor();
    private static final Color BUTTON_COLOR = Colores.BOTON.getColor();
    // Se mantiene TABLE_HEADER_COLOR sin equivalente en Colores, si se requiere cambiar, se puede agregar una constante en Colores
    private static final Color TABLE_HEADER_COLOR = new Color(30, 39, 58);

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public VistaBalanceYMisActivos(ControladorBalanceYMisActivos miControlador) throws SQLException {
        super();
        this.miControlador = miControlador;
        activoFiat = miControlador.ObtenerActivosFiat();
        activoCripto = miControlador.obtenerActivosCripto();

        setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarTablas();

        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelTablas(), BorderLayout.CENTER);
        add(crearPanelInferior(), BorderLayout.SOUTH);
    }

    private void inicializarComponentes() {
        btnVolver = new RoundedButton("Volver",150,40);
        btnGenerarDatos = new RoundedButton("Generar Datos",150,40);
        btnGenerarCSV = new RoundedButton("Generar CSV",150,40);


        btnVolver.addActionListener(e -> volver());
        btnGenerarDatos.addActionListener(new LGenerarAleatorio());
        btnGenerarCSV.addActionListener(new LGenerarCSV());
    }


    private void configurarTablas() throws SQLException {
        tableFiat = new Tabla(null);
        tableCripto = new Tabla(null);


        actualizarTablaFiat();
        actualizarTablaCripto();
    }
    private JPanel crearPanelSuperior() throws SQLException {
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS)); // Usamos BoxLayout en dirección vertical
        panelSuperior.setBackground(BG_COLOR);

        // Crear el JLabel para el título y centrarlo en su propio contenedor
        JLabel lblTitulo = new JLabel("Balance y Mis Activos", SwingConstants.CENTER);
        lblTitulo.setFont(TITLE_FONT);
        lblTitulo.setForeground(TEXT_COLOR);

        // Crear un JPanel solo para contener el JLabel y centrarlo
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.X_AXIS)); // Centra el JLabel en eje horizontal
        panelTitulo.setBackground(BG_COLOR);
        panelTitulo.add(Box.createHorizontalGlue());  // Centra el JLabel horizontalmente
        panelTitulo.add(lblTitulo); // Añade el JLabel
        panelTitulo.add(Box.createHorizontalGlue());  // Centra el JLabel horizontalmente

        // Crear panel de saldo con FlowLayout centrado
        JPanel panelSaldo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSaldo.setBackground(BG_COLOR);
        
        JLabel lblTextoSaldo = new JLabel("Saldo:");
        lblTextoSaldo.setFont(MAIN_FONT);
        lblTextoSaldo.setForeground(TEXT_COLOR);
        
        lblSaldo = new JLabel(String.format("$ %.4f", miControlador.ObtenerSaldo()));
        lblSaldo.setFont(MAIN_FONT);
        lblSaldo.setForeground(TEXT_COLOR);
        
        panelSaldo.add(lblTextoSaldo);
        panelSaldo.add(lblSaldo);

        // Añadir los componentes al panel superior
        panelSuperior.add(Box.createVerticalGlue());  // Espacio flexible para centrar el contenido verticalmente
        panelSuperior.add(panelTitulo);  // Añadir el panel del título
        panelSuperior.add(Box.createVerticalGlue());  // Espacio flexible para centrar el contenido verticalmente
        panelSuperior.add(panelSaldo);  // Añadir el panel de saldo
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Espaciado alrededor del panel

        return panelSuperior;
    }



    private JPanel crearPanelTablas() {
        JPanel panelTablas = new JPanel(new GridLayout(2, 1, 10, 10));
        panelTablas.setBackground(BG_COLOR);

        panelTablas.add(crearPanelTabla(tableFiat, "Activos Fiat"));
        panelTablas.add(crearPanelTabla(tableCripto, "Activos Cripto"));

        return panelTablas;
    }

    private JPanel crearPanelTabla(Tabla tabla, String titulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(HEADER_FONT);
        lblTitulo.setForeground(TEXT_COLOR);
        panel.add(lblTitulo, BorderLayout.NORTH);

        PanelTablas scrollPane = new PanelTablas(tabla);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelInferior.setBackground(BG_COLOR);
        panelInferior.add(btnGenerarDatos);
        panelInferior.add(btnGenerarCSV);
        panelInferior.add(btnVolver);
        return panelInferior;
    }

    private void actualizarTablaFiat() throws SQLException {
        tableFiat.setModel(new ModeloActivosTabla(miControlador.ObtenerActivosFiat(), columnNames));
    }

    private void actualizarTablaCripto() throws SQLException {
        tableCripto.setModel(new ModeloActivosTabla(miControlador.obtenerActivosCripto(), columnNames));
    }

    private void volver() {
        miControlador.redirigirIndex();
    }

    private class LGenerarAleatorio implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        	try {
                if (activoFiat.length == 0) {
                    miControlador.generarDatosDePrueba();
                    activoFiat = miControlador.ObtenerActivosFiat();
                    actualizarTablaFiat();
                    actualizarTablaCripto();
                } else {
                    JOptionPane.showMessageDialog(VistaBalanceYMisActivos.this,
                            "Datos de prueba ya generados", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(NoHayMonedasCargadasException ex) {

                JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(VistaBalanceYMisActivos.this);
            	VentanaEmergente.llamarVentanaEmergente("No hay aun monedas cargadas, dirigete a cotizaciones para cargarlas", "No hay monedas", 
            			owner, Colores.AMARILLO.getColor());
            }
        	catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(VistaBalanceYMisActivos.this,
                        "Error al generar datos de prueba: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

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

    @Override
    protected void actualizarPosiciones() {
        revalidate();
        repaint();
    }
}