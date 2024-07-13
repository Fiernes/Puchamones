import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;

public class FramePrincipal extends JFrame {
    Color color = new Color(0);
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel labelUsuario, labelPassword, labelNombreR, labelMailR, labelNombreUserR, labelGeneroR, labelEdadR;
    JTextField txtUsuario, txtNombreR, txtMailR, txtNombreUserR, txtGeneroR, txtEdadR;
    JPasswordField txtPassword, txtPasswordR;
    JPanel panelBotones, mostrarPaneles, Inicio, Registro;
    JButton btnIngresar, btnRegistrar, btnIngresarR;
    PanelFondo panel;
    CardLayout cardLayout;
    Font FuenteInicio = new Font("Times New Roman", Font.PLAIN, 20);

    public FramePrincipal(){

        String[] opciones = {"Español", "English"};

        int seleccion = JOptionPane.showOptionDialog(null,
                "Seleccione el idioma / Select the language",
                "Idioma / Language",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);

        String rutaArchivo = (seleccion == 1) ? "/configuracion_en.txt" : "/configuracion_es.txt";

        Map<String, String> config = Configuracion.cargarConfiguracion(rutaArchivo);

        setTitle("Batalla de puchamones");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mostrarPaneles = new JPanel(cardLayout);

        String rutaLogo = "/imagenes/LogoPrincipal.jpg";
        ImageIcon imagenObtenida = new ImageIcon(Objects.requireNonNull(getClass().getResource(rutaLogo)));
        Image imagenLogo = imagenObtenida.getImage();
        setIconImage(imagenLogo);

        Inicio = InterfazInicio(config);
        Registro = InterfazRegistro(config);

        mostrarPaneles.add(Inicio, "Inicio de sesion");
        mostrarPaneles.add(Registro, "Registro de nuevo usuario");

        add(mostrarPaneles);

        setVisible(true);

    }

    private JPanel InterfazInicio(Map<String, String> config) {

        panel = new PanelFondo();
        panel.setBorder(new MatteBorder(2,2,2,2,color));
        panel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta de usuario
        labelUsuario = new JLabel(config.getOrDefault("labelUsuario", "Usuario"));
        labelUsuario.setForeground(Color.YELLOW);
        labelUsuario.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelUsuario, gbc);

        // Campo de texto para el usuario
        txtUsuario = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtUsuario, gbc);

        // Etiqueta de contraseña
        labelPassword = new JLabel(config.getOrDefault("labelPassword", "Contraseña"));
        labelPassword.setForeground(Color.YELLOW);
        labelPassword.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelPassword, gbc);

        // Campo de texto para la contraseña
        txtPassword = new JPasswordField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtPassword, gbc);

        // Panel de botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        panelBotones.setOpaque(false);

        // Botón Ingresar
        btnIngresar = new JButton(config.getOrDefault("btnIngresar", "Ingresar"));
        btnIngresar.setSize(50, 50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(btnIngresar, gbc);
        btnIngresar.addActionListener(new OyenteBtnIngreso());

        // Botón Registrar
        btnRegistrar = new JButton(config.getOrDefault("btnRegistrar", "Registrar"));
        btnRegistrar.setSize(50, 50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotones.add(btnRegistrar, gbc);
        btnRegistrar.addActionListener(new OyenteBtnRegistro());

        // Añadir el panel de botones al panel principal
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(panelBotones, gbc);

        // Añadir el panel principal al JFrame
        //add(panel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel InterfazRegistro(Map<String, String> config) {

        panel = new PanelFondo();
        panel.setBorder(new MatteBorder(2,2,2,2,color));
        panel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labelNombreR = new JLabel(config.getOrDefault("labelUsuario", "Ingrese su nombre completo"));
        labelNombreR.setForeground(Color.YELLOW);
        labelNombreR.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelNombreR,gbc);

        txtNombreR = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNombreR, gbc);

        labelMailR = new JLabel(config.getOrDefault("labelMailR", "Ingrese su correo electrónico"));
        labelMailR.setForeground(Color.YELLOW);
        labelMailR.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelMailR, gbc);

        txtMailR = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtMailR, gbc);

        labelNombreUserR = new JLabel(config.getOrDefault("labelUsuarioR", "Ingrese el nombre que desea que se muestre"));
        labelNombreUserR.setForeground(Color.YELLOW);
        labelNombreUserR.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelNombreUserR, gbc);

        txtNombreUserR = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtNombreUserR, gbc);

        return panel;
    }


    class OyenteBtnIngreso implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "hola munto");
        }
    }
    class OyenteBtnRegistro implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mostrarPaneles, "Registro de nuevo usuario");
        }
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FramePrincipal();
            }
        });
    }
}
