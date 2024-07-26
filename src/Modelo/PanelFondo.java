package Modelo;

import javax.swing.*;
import java.awt.*;

public class PanelFondo extends JPanel {
    private String rutaImagen;
    public PanelFondo(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imagenFondo = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }
}