package Vista;

import javax.swing.*;

import billetera.Auxiliar.Activo;
import billetera.Auxiliar.Moneda;
import billetera.Controladores.ControladorCompra;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VistaCompra extends JFrame {
    private JComboBox<String> comboCripto;
    private JComboBox<String> comboFiat;
    private JTextField txtCantidadFiat;
    private JButton btnComprar;
    private ControladorCompra miControlador;
    private Moneda[] criptos;
    private Activo[] activoFiats;
    
    public VistaCompra(ControladorCompra miControlador) throws SQLException {
        super("Compra de Criptomonedas");
        this.miControlador = miControlador;

        // Configuración de la ventana
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar las secciones

        // Crear el título
        JLabel lblTitulo = new JLabel("Compra de Criptomonedas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(2, 1, 10, 10)); // 2 secciones: Cripto y Fiat
        add(panelPrincipal, BorderLayout.CENTER);

        // Sección de criptomoneda
        JPanel panelCripto = new JPanel(new GridLayout(2, 1));
        JLabel lblSeleccionCripto = new JLabel("Seleccionar Criptomoneda:");
        criptos = miControlador.obtenerCriptos();
        comboCripto = new JComboBox<>();
        for (Moneda cripto : criptos) {
			comboCripto.addItem(cripto.getNomenclatura());
		}
        panelCripto.add(lblSeleccionCripto);
        panelCripto.add(comboCripto);

        // Sección de fiat
        comboFiat = new JComboBox<>();
        JLabel lblCantidadFiat = new JLabel("Cantidad de Fiat a gastar:");
        txtCantidadFiat = new JTextField(10);
        JPanel panelFiat = new JPanel(new GridLayout(3, 1));
        JLabel lblSeleccionFiat = new JLabel("Seleccionar Moneda Fiat:");
        activoFiats = miControlador.obtenerActivosFiats(); // Método que obtiene las monedas fiat disponibles
        
        for (Activo activosFiats : activoFiats) {
			comboFiat.addItem(activosFiats.getMoneda().getNomenclatura());
		}
        panelFiat.add(lblSeleccionFiat);
        panelFiat.add(comboFiat);
        panelFiat.add(lblCantidadFiat);
        panelFiat.add(txtCantidadFiat);

        // Agregar las secciones al panel principal
        panelPrincipal.add(panelCripto);
        panelPrincipal.add(panelFiat);

        // Botón de comprar
        btnComprar = new JButton("Comprar");
        btnComprar.setPreferredSize(new Dimension(200, 40));
        btnComprar.addActionListener(new LComprar());
        add(btnComprar, BorderLayout.SOUTH);
    }

    // Listener para el botón de compra
    private class LComprar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int criptoSeleccionada = comboCripto.getSelectedIndex();
            int fiatSeleccionado = comboFiat.getSelectedIndex();
            String cantidadFiatStr = txtCantidadFiat.getText();

            // Validar campos
            if (cantidadFiatStr.isEmpty()) {
                JOptionPane.showMessageDialog(VistaCompra.this, "Por favor ingresa la cantidad de fiat a gastar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Validar que la cantidad es un número positivo
                double cantidadFiat = Double.parseDouble(cantidadFiatStr);
                System.out.println(cantidadFiat);
                if (cantidadFiat <= 0) {
                    JOptionPane.showMessageDialog(VistaCompra.this, "La cantidad debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Llamada al controlador para realizar la compra
                    int exito = miControlador.comprarCripto(criptos[criptoSeleccionada], activoFiats[fiatSeleccionado], cantidadFiat);
                    if (exito!=0) {
                    	if(exito==1) {
                    		JOptionPane.showMessageDialog(VistaCompra.this, "No tiene suficiente saldo para esta compra.", "Error", JOptionPane.ERROR_MESSAGE);

                    	}else if (exito==2) {
                    		JOptionPane.showMessageDialog(VistaCompra.this, "No existe la moneda ingresada", "Error", JOptionPane.ERROR_MESSAGE);
                    		
                    	}else {
                    		
                    		JOptionPane.showMessageDialog(VistaCompra.this, "No hay suficiente stock para esta compra.", "Error", JOptionPane.ERROR_MESSAGE);
                    	}
                    } else {
                        JOptionPane.showMessageDialog(VistaCompra.this, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(VistaCompra.this, "La cantidad ingresada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
