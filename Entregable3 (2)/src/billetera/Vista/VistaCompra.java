package Vista;

import Auxiliar.Activo;
import Auxiliar.Moneda;
import Auxiliar.Panel;
import Controladores.ControladorCompra;
import Excepciones.NoExiteMonedaException;
import Excepciones.NoHayStockException;
import Excepciones.SaldoInsuficienteException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import javax.swing.*;
public class VistaCompra extends Panel {
    private JComboBox<String> comboCripto;
    private JComboBox<String> comboFiat;
    private JTextField txtCantidadFiat;
    private JButton btnComprar;
    private JButton btnVolver;
    private ControladorCompra miControlador;
    private Moneda[] criptos;
    private Activo[] activoFiats;
    private JLabel lblTitulo, lblSeleccionCripto, lblSeleccionFiat, lblCantidadFiat;
    private JPanel panelCripto, panelFiat, panelBotones;

    public VistaCompra(ControladorCompra miControlador) throws SQLException {
    	super();
    	this.miControlador = miControlador;

        setLayout(null); // Usamos layout absoluto
        setBackground(Color.WHITE);

        // Crear el título
        lblTitulo = new JLabel("Compra de Criptomonedas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        // Sección de criptomoneda
        lblSeleccionCripto = new JLabel("Seleccionar Criptomoneda:");
        criptos = miControlador.obtenerCriptos();
        comboCripto = new JComboBox<>();
        for (Moneda cripto : criptos) {
            comboCripto.addItem(cripto.getNomenclatura());
        }

        // Sección de fiat
        lblSeleccionFiat = new JLabel("Seleccionar Moneda Fiat:");
        comboFiat = new JComboBox<>();
        lblCantidadFiat = new JLabel("Cantidad de Fiat a gastar:");
        txtCantidadFiat = new JTextField(10);
        activoFiats = miControlador.obtenerActivosFiats();
        for (Activo activosFiats : activoFiats) {
            comboFiat.addItem(activosFiats.getMoneda().getNomenclatura());
        }

        // Botones
        btnComprar = new JButton("Comprar");
        btnVolver = new JButton("Volver");

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

        // Listener para actualizar posiciones cuando se cambia el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarPosiciones();
            }
        });

        actualizarPosiciones();
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
        actualizarPosicion(btnVolver, 200, -180, 400, 50);
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

            if (cantidadFiatStr.isEmpty()) {
                JOptionPane.showMessageDialog(VistaCompra.this, "Por favor ingresa la cantidad de fiat a gastar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double cantidadFiat = Double.parseDouble(cantidadFiatStr);
                if (cantidadFiat <= 0) {
                    JOptionPane.showMessageDialog(VistaCompra.this, "La cantidad debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int exito = miControlador.comprarCripto(criptos[criptoSeleccionada], activoFiats[fiatSeleccionado], cantidadFiat);
                        JOptionPane.showMessageDialog(VistaCompra.this, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SaldoInsuficienteException ex) {
                        JOptionPane.showMessageDialog(VistaCompra.this, "No tiene suficiente saldo para esta compra.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NoExiteMonedaException ex) {
                        JOptionPane.showMessageDialog(VistaCompra.this, "No existe la moneda ingresada", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NoHayStockException ex) {
                        JOptionPane.showMessageDialog(VistaCompra.this, "No hay suficiente stock para esta compra.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(VistaCompra.this, "La cantidad ingresada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}