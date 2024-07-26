package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class FramePrincipal extends JFrame {

    public JPanel paneles;
    public final CardLayout controladorPaneles;

    public FramePrincipal(Map<String, String> config){
        setTitle("Batalla de puchamones");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        controladorPaneles = new CardLayout();
        paneles = new JPanel(controladorPaneles);

        String rutaLogo = "/imagenes/LogoPrincipal.jpg";
        ImageIcon imagenObtenida = new ImageIcon(Objects.requireNonNull(getClass().getResource(rutaLogo)));
        Image imagenLogo = imagenObtenida.getImage();
        setIconImage(imagenLogo);

        InterfazMPrincipal menu = new InterfazMPrincipal(config, controladorPaneles, paneles, this);
        InterfazEstadisticas estadisticas = new InterfazEstadisticas(config, controladorPaneles, paneles);

        paneles.add(menu, "Principal");
        paneles.add(estadisticas, "Estadisticas");

        add(paneles);
    }

}
