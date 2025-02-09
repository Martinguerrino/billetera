package Auxiliar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImagenCircular extends JLabel {
    private ImageIcon imageIcon;

    public ImagenCircular(String imagePath) {
        // Cargar la imagen
        imageIcon = new ImageIcon(imagePath);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtener el tamaño del JLabel
        int width = getWidth();
        int height = getHeight();

        // Dibujar un círculo en el área del JLabel
        g.setClip(0, 0, width, height); // Establece un recorte para que todo fuera del círculo no se dibuje
        g.setColor(getBackground()); // Fondo del JLabel, para asegurar que el fondo de la imagen sea transparente
        g.fillOval(0, 0, width, height); // Dibuja el círculo

        // Dibujar la imagen dentro del círculo
        g.drawImage(imageIcon.getImage(), 0, 0, width, height, (ImageObserver) this); // Dibuja la imagen dentro del círculo
    }

    public static void main(String[] args) {
        // Crear la ventana
        JFrame frame = new JFrame("Imagen Circular");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        // Crear la instancia del JLabel con imagen circular
        ImagenCircular label = new ImagenCircular("ruta/a/tu/logo.png"); // Reemplaza con la ruta de tu imagen
        label.setPreferredSize(new Dimension(150, 150)); // Establecer el tamaño preferido del JLabel

        // Agregar el JLabel con la imagen circular
        frame.add(label);
        frame.setVisible(true);
    }
}