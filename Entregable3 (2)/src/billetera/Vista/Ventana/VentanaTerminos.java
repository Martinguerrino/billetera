package Vista.Ventana;
import javax.swing.*;
import java.awt.*;

public class VentanaTerminos extends JFrame {

    public VentanaTerminos() {
        // Configuración de la ventana
        setTitle("Términos y Condiciones");
        setSize(600, 400); // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana al hacer clic en el botón de cerrar
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new BorderLayout()); // Usa BorderLayout para centrar el contenido

        // Establecer fondo negro para toda la ventana
        getContentPane().setBackground(Color.BLACK);

        // Crear un JLabel con mucho texto Lorem Ipsum
        String loremIpsum = "<html><body style='width: 500px; color: white;'>" +
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. " +
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus semper porta. Mauris massa.</body></html>";

        JLabel label = new JLabel(loremIpsum);
        label.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
        label.setVerticalAlignment(SwingConstants.CENTER); // Centra el texto verticalmente

        // Añadir el JLabel al centro de la ventana
        add(label, BorderLayout.CENTER);

        // Hacer la ventana visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Llamar a la ventana de términos
        new VentanaTerminos();
    }
}

