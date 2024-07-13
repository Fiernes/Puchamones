import javax.swing.*;
import java.awt.*;

public class PanelFondo extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imagenFondo = new ImageIcon(getClass().getResource("/imagenes/LogoPrincipal.jpg")).getImage();
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
}
