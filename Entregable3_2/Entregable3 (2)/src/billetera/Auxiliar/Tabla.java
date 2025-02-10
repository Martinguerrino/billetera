package Auxiliar;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import etc.Colores;

import java.awt.*;

public class Tabla extends JTable{
	
	public Tabla(DefaultTableModel modelo) {
        super(modelo);
        configurarTabla();
    }

    private void configurarTabla() {
        setShowGrid(false); // Oculta la cuadrícula
        setBackground(Colores.FONDO.getColor());
        setForeground(Colores.TEXTO.getColor());
        setRowHeight(40);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionBackground(new Color(30, 39, 58));
        setSelectionForeground(Colores.TEXTO.getColor());
        setIntercellSpacing(new Dimension(6,6));
        
        JTableHeader header = getTableHeader();
        header.setBackground(Colores.FONDO.getColor());
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setFont(new Font("Arial", Font.BOLD, 14)); // Negrita
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setOpaque(false); // Sin fondo
                label.setBackground(Colores.FONDO.getColor());
                label.setForeground(Colores.TEXTO.getColor());
                label.setBorder(BorderFactory.createEmptyBorder(7,7,7,7));
                return label;
            }
        });
    }

}
