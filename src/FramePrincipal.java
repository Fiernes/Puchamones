import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class FramePrincipal extends JFrame {
    private final Color color = new Color(0);
    private final GridBagConstraints gbc = new GridBagConstraints();
    private JLabel labelUsuario, labelPassword, labelNombreR, labelMailR, labelNombreUserR, labelGeneroR, labelEdadR;
    private JTextField txtUsuario, txtNombreR, txtMailR, txtNombreUserR, txtGeneroR, txtEdadR;
    private JPasswordField txtPassword, txtPasswordR;
    private JPanel panelBotones, panelBotonesR, mostrarPaneles, Inicio, Registro;
    private JButton btnIngresar, btnRegistrar, btnIngresarR, btnRegresarR, btnMontrarPass;
    private PanelFondo panel;
    private final CardLayout cardLayout;
    private final Font FuenteInicio = new Font("Times New Roman", Font.PLAIN, 20);
    private Jugador ju = new Jugador();
    private Configuracion con = new Configuracion();

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

        btnMontrarPass = new JButton();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Password15x15.png"));
        btnMontrarPass.setIcon(icono);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(btnMontrarPass, gbc);

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

        return panel;
    }

    private JPanel InterfazRegistro(Map<String, String> config) {

        panel = new PanelFondo();
        panel.setBorder(new MatteBorder(2,2,2,2,color));
        panel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labelNombreR = new JLabel(config.getOrDefault("labelNombreR", "Ingrese su nombre completo"));
        labelNombreR.setForeground(Color.YELLOW);
        labelNombreR.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelNombreR,gbc);

        txtNombreR = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNombreR, gbc);
        txtNombreR.addKeyListener(new SoloTexto());

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

        labelNombreUserR = new JLabel(config.getOrDefault("labelNombreUserR", "Ingrese el nombre que desea que se muestre"));
        labelNombreUserR.setForeground(Color.YELLOW);
        labelNombreUserR.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelNombreUserR, gbc);

        txtNombreUserR = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtNombreUserR, gbc);

        labelPassword = new JLabel(config.getOrDefault("labelPassword", "Ingrese su contraseña"));
        labelPassword.setForeground(Color.YELLOW);
        labelPassword.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelPassword, gbc);

        txtPassword = new JPasswordField(25);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtPassword, gbc);

        btnMontrarPass = new JButton();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Password15x15.png"));
        btnMontrarPass.setIcon(icono);
        btnMontrarPass.setSize(15,15);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(btnMontrarPass, gbc);
        btnMontrarPass.addMouseListener(new OyenteBtnMostrarPass());

        labelEdadR = new JLabel(config.getOrDefault("labelEdadR","Ingrese su edad"));
        labelEdadR.setForeground(Color.YELLOW);
        labelEdadR.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelEdadR, gbc);


        txtEdadR = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(txtEdadR, gbc);
        txtEdadR.addKeyListener(new SoloNumeros());

        labelGeneroR = new JLabel(config.getOrDefault("labelGeneroR","Ingrese su género"));
        labelGeneroR.setForeground(Color.YELLOW);
        labelGeneroR.setFont(FuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(labelGeneroR, gbc);

        txtGeneroR = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(txtGeneroR, gbc);
        txtGeneroR.addKeyListener(new SoloTexto());

        panelBotonesR = new JPanel();
        panelBotonesR.setLayout(new GridBagLayout());
        panelBotonesR.setOpaque(false);

        btnIngresarR = new JButton(config.getOrDefault("btnIngresarR","Registrar nuevo usuario"));
        btnIngresarR.setSize(100,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotonesR.add(btnIngresarR, gbc);

        btnRegresarR = new JButton(config.getOrDefault("btnRegresarR","Volver atrás"));
        btnRegresarR.setSize(100,50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotonesR.add(btnRegresarR, gbc);
        btnRegresarR.addActionListener(new OyenteBtnRegresarR());

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(panelBotonesR, gbc);

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
    class OyenteBtnRegresarR implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mostrarPaneles, "Inicio de sesion");
        }
    }
    class OyenteBtnRegistrarUsuario implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Jugador> DatosJugador = new ArrayList<Jugador>();

            String valnombre = txtNombreR.getText();

            ju.setNombre(txtNombreR.getText());
            ju.setCorreo(txtMailR.getText());
            ju.setNombreUsuario(txtUsuario.getText());
            ju.setGenero(txtGeneroR.getText());
            ju.setEdad(Integer.parseInt(txtEdadR.getText()));
            ju.setPassword(String.valueOf(txtPasswordR.getPassword()));
            ju.setOro(2000);
            DatosJugador.add(ju);

        }
    }

    class SoloNumeros implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            con.numberKeyPress(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    class SoloTexto implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            con.textKeyPress(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
    class OyenteBtnMostrarPass implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            txtPassword.setEchoChar((char) 0);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            txtPassword.setEchoChar('*');
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
