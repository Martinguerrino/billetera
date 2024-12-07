package Vista;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import billetera.Controladores.ControladorBalanceYMisActivos;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VistaBalanceYMisActivos extends JFrame {
    private JTable tableFiat;
    private JTable tableCripto;
    private ControladorBalanceYMisActivos miControlador;

    public VistaBalanceYMisActivos(ControladorBalanceYMisActivos miControlador) {
        super("Balance y Mis Activos");
        this.miControlador = miControlador;

        // Configuración de la ventana
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new GridLayout(2, 1)); // Disposición en 2 filas

        // Crear título para la vista
        JLabel lblTitulo = new JLabel("Balance y Mis Activos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        String[] columnNames = {"Nombre", "Nomenclatura", "Valor en Dólar", "Icono"};
        Object[][] datos= miControlador.ObtenerActivosFiat();
        DefaultTableModel modelFiat = new DefaultTableModel(datos, columnNames);
        tableFiat = new JTable(modelFiat);
        JScrollPane scrollPaneFiat = new JScrollPane(tableFiat);
        JPanel fiatPanel = new JPanel();
        fiatPanel.setLayout(new BorderLayout());
        fiatPanel.add(new JLabel("Activos Fiat", SwingConstants.CENTER), BorderLayout.NORTH);
        fiatPanel.add(scrollPaneFiat, BorderLayout.CENTER);
        datos= miControlador.obtenerActivosCripto();
        DefaultTableModel modelCripto = new DefaultTableModel(datos, columnNames);
        tableCripto = new JTable(modelCripto);
        // Configurar la tabla de activos Cripto
        JScrollPane scrollPaneCripto = new JScrollPane(tableCripto);
        JPanel criptoPanel = new JPanel();
        criptoPanel.setLayout(new BorderLayout());
        criptoPanel.add(new JLabel("Activos Cripto", SwingConstants.CENTER), BorderLayout.NORTH);
        criptoPanel.add(scrollPaneCripto, BorderLayout.CENTER);
        
        

        // Crear panel para los botones o acciones si es necesario
        JPanel panel = new JPanel();
        add(fiatPanel);
        add(criptoPanel);
        add(panel, BorderLayout.SOUTH);
    }
}