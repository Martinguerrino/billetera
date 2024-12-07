package Vista;

import javax.swing.*;

import billetera.Controladores.ControladorCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCompra extends JFrame {
    private JComboBox<String> comboCripto;
    private JTextField txtCantidad;
    private JButton btnComprar;
    private ControladorCompra miControlador;

    public VistaCompra(ControladorCompra miControlador) {
        super("Compra de Criptomonedas");
        this.miControlador = miControlador;

        // Configuración de la ventana
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new FlowLayout()); // Usamos FlowLayout para organizar los componentes

        // Crear el título
        JLabel lblTitulo = new JLabel("Compra Criptomonedas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo);

        // Crear el JComboBox para seleccionar criptomonedas
        String[] criptos = miControlador.obtenerCriptos();
        comboCripto = new JComboBox<>(criptos);
        comboCripto.setPreferredSize(new Dimension(200, 30));
        add(new JLabel("Seleccionar Criptomoneda:"));
        add(comboCripto);

        // Crear el campo de texto para ingresar la cantidad
        txtCantidad = new JTextField(10);
        txtCantidad.setPreferredSize(new Dimension(200, 30));
        add(new JLabel("Cantidad:"));
        add(txtCantidad);

        // Crear el botón de compra
        btnComprar = new JButton("Comprar");
        btnComprar.setPreferredSize(new Dimension(200, 40));
        btnComprar.addActionListener(new LComprar()); // Acción al presionar el botón
        add(btnComprar);
    }

    // Listener para el botón de compra
    private class LComprar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String criptoSeleccionada = (String) comboCripto.getSelectedItem();
            String cantidadStr = txtCantidad.getText();

            if (cantidadStr.isEmpty()) {
                JOptionPane.showMessageDialog(VistaCompra.this, "Por favor ingresa la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

                // Validar que la cantidad es un número
                double cantidad = Double.parseDouble(cantidadStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(VistaCompra.this, "La cantidad debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Llamada al controlador para realizar la compra
                    if(!miControlador.comprarCripto(criptoSeleccionada, cantidad)) {
                    	JOptionPane.showMessageDialog(VistaCompra.this, "No hay suficiente Stock de dicha"+ criptoSeleccionada, "Error", JOptionPane.ERROR_MESSAGE);
                    	
                    }
                }
        }
    }

    
}
