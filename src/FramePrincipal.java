import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class FramePrincipal extends JFrame {

    public JPanel mostrarPaneles;
    public final CardLayout cardLayout;

    public FramePrincipal(){

        String[] opciones = {"Español", "English"};

        int seleccion = JOptionPane.showOptionDialog(null,
                "Seleccione el idioma / Select the language",
                "Idioma / Language",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);

        String rutaArchivo = (seleccion == 1) ? "/BaseDatos/configuracion_en.txt" : "/BaseDatos/configuracion_es.txt";

        Map<String, String> config = Configuracion.cargarConfiguracion(rutaArchivo);

        Sistema sistema = new Sistema();
        sistema.cargarDatos();

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
        InterfazRegistro reg = new InterfazRegistro(config, cardLayout, mostrarPaneles);
        InterfazMPrincipal MP = new InterfazMPrincipal(config, cardLayout, mostrarPaneles);

        mostrarPaneles.add(ini, "Inicio de sesion");
        mostrarPaneles.add(reg, "Registro de nuevo usuario");
        mostrarPaneles.add(MP,"Menu Principal");

        add(mostrarPaneles);

        setVisible(true);
    }
}
