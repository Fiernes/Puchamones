package Vista;

import Controlador.ControladorInicio;
import Controlador.ControladorRegistro;
import Controlador.ControladorRegistroPuchamon;
import Modelo.Configuracion;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class FrameInicio extends JFrame {

    public JPanel mostrarPaneles;
    public final CardLayout cardLayout;

    public FrameInicio(Map<String, String> config){

        setTitle("Batalla de puchamones");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mostrarPaneles = new JPanel(cardLayout);

        String rutaLogo = "/imagenes/LogoPrincipal.jpg";
        ImageIcon imagenObtenida = new ImageIcon(Objects.requireNonNull(getClass().getResource(rutaLogo)));
        Image imagenLogo = imagenObtenida.getImage();
        setIconImage(imagenLogo);

        InterfazInicio ini = new InterfazInicio(config, cardLayout, mostrarPaneles);
        new ControladorInicio(ini, this);
        InterfazRegistro reg = new InterfazRegistro(config, cardLayout, mostrarPaneles);
        new ControladorRegistro(reg);

        mostrarPaneles.add(ini, "Inicio de sesion");
        mostrarPaneles.add(reg, "Registro de nuevo usuario");

        add(mostrarPaneles);

        setVisible(true);
    }
}
