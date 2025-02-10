package Vista;

import Auxiliar.Activo;
import Auxiliar.Moneda;
import Auxiliar.Panel;
import Auxiliar.RoundedButton;
import Auxiliar.RoundedComboBox;
import Auxiliar.RoundedTextField;
import Controladores.ControladorCompra;
import Excepciones.NoExiteMonedaException;
import Excepciones.NoHayStockException;
import Excepciones.SaldoInsuficienteException;
import Vista.Ventana.VentanaEmergente;
import etc.Colores;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
public class VistaCompra extends Panel {
    private RoundedComboBox<String> comboCripto;
    private RoundedComboBox<String> comboFiat;
    private RoundedTextField txtCantidadFiat;
    private RoundedButton btnComprar;
    private RoundedButton btnVolver;
    private ControladorCompra miControlador;
    private Moneda[] criptos;
    private Activo[] activoFiats;
    private JLabel lblTitulo, lblSeleccionCripto, lblSeleccionFiat, lblCantidadFiat;
    private static final Color BG_COLOR = Colores.FONDO.getColor();
    private static final Color TEXT_COLOR = Colores.TEXTO.getColor();
    private static final Color BUTTON_COLOR = Colores.BOTON.getColor();
    private static final Color TEXTFIELD_COLOR = Colores.CUADROS_TEXTO.getColor();
    
    public VistaCompra(ControladorCompra miControlador) throws SQLException {
        super();
        this.miControlador = miControlador;
        setLayout(null); // Layout absoluto
        setBackground(BG_COLOR);
        
        // Crear componentes
        lblTitulo = new JLabel("Compra de Criptomonedas", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(TEXT_COLOR);
        
        lblSeleccionCripto = new JLabel("Seleccionar Criptomoneda:");
        lblSeleccionCripto.setForeground(TEXT_COLOR);
        
        // Usar RoundedComboBox con radio de 10
        comboCripto = new RoundedComboBox<>(10);
        criptos = miControlador.obtenerCriptos();
        for (Moneda cripto : criptos) {
            comboCripto.addItem(cripto.getNomenclatura());
        }
        configurarComboBox(comboCripto);
        
        lblSeleccionFiat = new JLabel("Seleccionar Moneda Fiat:");
        lblSeleccionFiat.setForeground(TEXT_COLOR);
        
        comboFiat = new RoundedComboBox<>(10);
        lblCantidadFiat = new JLabel("Cantidad de Fiat a gastar:");
        lblCantidadFiat.setForeground(TEXT_COLOR);
        
        // Usar RoundedTextField y configurarlo
        txtCantidadFiat = new RoundedTextField(10);
        configurarTexto(txtCantidadFiat);
        
        activoFiats = miControlador.obtenerActivosFiats();
        for (Activo activo : activoFiats) {
            comboFiat.addItem(activo.getMoneda().getNomenclatura());
        }
        configurarComboBox(comboFiat);
        
        // Crear botones RoundedButton y configurarlos usando el método configurarBoton
        btnComprar = new RoundedButton("Comprar", 15, 15);
        btnVolver = new RoundedButton("Volver",15,15);
        configurarBoton(btnComprar);
        configurarBoton(btnVolver);
        
        // Agregar componentes
        add(lblTitulo);
        add(lblSeleccionCripto);
        add(comboCripto);
        add(lblSeleccionFiat);
        add(comboFiat);
        add(lblCantidadFiat);
        add(txtCantidadFiat);
        add(btnComprar);
        add(btnVolver);
        
        // Agregar listeners
        btnComprar.addActionListener(new LComprar());
        btnVolver.addActionListener(e -> volver());
        
        // Listener para actualizar posiciones cuando se modifica el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarPosiciones();
            }
        });
        
        actualizarPosiciones();
    }
    
    private void configurarBoton(RoundedButton boton) {
        boton.setBackground(BUTTON_COLOR);
        boton.setForeground(Color.BLACK);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
        // Remover el borde de forma completa
        boton.setBorder(null);
        boton.setBorderPainted(false);
        // Ajuste de tamaño para que los botones no queden tan pegados
        boton.setPreferredSize(new Dimension(170, 50));
    }
    
    private void configurarTexto(RoundedTextField campo) {
        campo.setForeground(TEXT_COLOR);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Fondo definido por TEXTFIELD_COLOR para integración visual
        campo.setBackground(TEXTFIELD_COLOR);
        // Remover borde para un aspecto más limpio
        campo.setBorder(null);
    }
    
    private void configurarComboBox(RoundedComboBox<String> combo) {
        // Configuramos la fuente y colores del texto
        combo.setForeground(TEXT_COLOR);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(BG_COLOR);
        
        // Configuramos el renderer de la lista desplegable
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index,
                                                                          isSelected, cellHasFocus);
                lbl.setOpaque(true);
                if (isSelected) {
                    lbl.setBackground(BUTTON_COLOR);
                    lbl.setForeground(BG_COLOR);
                } else {
                    lbl.setBackground(BG_COLOR);
                    lbl.setForeground(TEXT_COLOR);
                }
                return lbl;
            }
        });
        
        // Configuramos el UI para personalizar el botón de flecha
        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setBackground(BUTTON_COLOR);
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
        });
    }
    
    
    protected void actualizarPosiciones() {
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        
        actualizarPosicion(lblTitulo, 200, 150, 400, 40);
        actualizarPosicion(lblSeleccionCripto, 200, 100, 400, 20);
        actualizarPosicion(comboCripto, 200, 70, 400, 40);
        actualizarPosicion(lblSeleccionFiat, 200, 20, 400, 20);
        actualizarPosicion(comboFiat, 200, -10, 400, 40);
        actualizarPosicion(lblCantidadFiat, 200, -50, 400, 20);
        actualizarPosicion(txtCantidadFiat, 200, -80, 400, 40);
        actualizarPosicion(btnComprar, 200, -130, 400, 50);
        actualizarPosicion(btnVolver, 200, -190, 400, 50);
    }
    
    protected void actualizarPosicion(JComponent element, int x, int y, int eWidth, int eHeight) {
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        element.setBounds(centerX - x, centerY - y, eWidth, eHeight);
    }
    
    private void volver() {
        miControlador.redirigirIndex();
    }
    
    private class LComprar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int criptoSeleccionada = comboCripto.getSelectedIndex();
            int fiatSeleccionado = comboFiat.getSelectedIndex();
            String cantidadFiatStr = txtCantidadFiat.getText();
            JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(VistaCompra.this);

            if (cantidadFiatStr.isEmpty()) {
            	VentanaEmergente.llamarVentanaEmergente("Por favor ingresa la cantidad de fiat a gastar.", 
                    "Error",owner, Colores.ERROR.getColor());
                return;
            }
            try {
                double cantidadFiat = Double.parseDouble(cantidadFiatStr);
                if (cantidadFiat <= 0) {
                	VentanaEmergente.llamarVentanaEmergente("La cantidad debe ser mayor que 0.", 
                            "Error", owner,Colores.ERROR.getColor());
                } else {
                    try {
                        int exito = miControlador.comprarCripto(
                            criptos[criptoSeleccionada],
                            activoFiats[fiatSeleccionado],
                            cantidadFiat);
                        // Se muestra un dialogo de éxito. Si se requiere que también sea VentanaError, se podría modificar.
                        VentanaEmergente.llamarVentanaEmergente("La cantidad debe ser mayor que 0.", 
                                "Error", owner,Colores.AMARILLO.getColor());
                    } catch (SaldoInsuficienteException ex) {
                    	VentanaEmergente.llamarVentanaEmergente("No tiene suficiente saldo para esta compra", 
                                "Error", owner,Colores.ERROR.getColor());

                    } catch (NoExiteMonedaException ex) {
                    	VentanaEmergente.llamarVentanaEmergente("No existe la moneda ingresada.", 
                                "Error", owner, Colores.ERROR.getColor());
                            
                    } catch (NoHayStockException ex) {
                    	VentanaEmergente.llamarVentanaEmergente("No existe la moneda ingresada.", 
                                "Error", owner,Colores.ERROR.getColor());
                        
                    }
                }
            } catch (NumberFormatException | SQLException ex) {
            	VentanaEmergente.llamarVentanaEmergente("La cantidad ingresada no es válida.", 
                        "Error", owner,Colores.ERROR.getColor());
                
        }
    }
}
    }