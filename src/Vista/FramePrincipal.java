package Vista;

import Controlador.*;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class FramePrincipal extends JFrame {

    public JPanel paneles;
    public final CardLayout controladorPaneles;

    public FramePrincipal(Map<String, String> config, String usuario){
        setTitle("Batalla de puchamones");
        setSize(900, 650);
        setMinimumSize(new Dimension(900,650));
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
        InterfazEquipo equipo = new InterfazEquipo(config,controladorPaneles, paneles);
        InterfazRegistroPuchamon registroPuchamon = new InterfazRegistroPuchamon(config, controladorPaneles, paneles);
        InterfazArena arena = new InterfazArena(config, controladorPaneles, paneles);
        InterfazApuesta apuesta = new InterfazApuesta(config, controladorPaneles, paneles);

        paneles.add(menu, "Principal");
        paneles.add(estadisticas, "Estadisticas");
        paneles.add(equipo, "Equipo");
        paneles.add(registroPuchamon, "Requipo");
        paneles.add(arena, "Arena");
        paneles.add(apuesta, "Apuesta");

        new ContoladorEstadisticas(estadisticas, menu, usuario);
        new ControladorEquipo(equipo, menu, usuario);
        new ControladorRegistroPuchamon(registroPuchamon, equipo, menu, usuario);
        new ControladorArena(arena, menu, usuario, apuesta);
        ControladorMenuPrincipal con = new ControladorMenuPrincipal(menu);
        con.CargarImagenesBtn();

        add(paneles);
    }

}
