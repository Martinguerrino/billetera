package Auxiliar;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ModeloCotizacionTabla extends DefaultTableModel{
	public ModeloCotizacionTabla(final Object[][] datos, final String[] titulos) {
		super(datos, titulos);
	}

	public ModeloCotizacionTabla(String[] columnas, int i) {
		// TODO Auto-generated constructor stub
		super(columnas, i);
	}

	public Class<?> getColumnClass(final int column) {
        // Si la columna es la que contiene imágenes, devolvemos ImageIcon.class
        if (column == 2) {  // Por ejemplo, si la columna "Icono" está en la posición 2
            return ImageIcon.class;
        }
        // Si no, devolvemos el tipo de la columna por defecto
        return super.getColumnClass(column);
    }
}
