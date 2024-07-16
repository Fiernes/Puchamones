import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class InterfazRegistro extends PanelFondo{

    private final CardLayout cardLayout;
    private final JPanel mostrarPaneles;
    private final JTextField txtNombreCompleto;
    private final JTextField txtCorreo;
    private final JTextField txtNombreUsuario;
    private final JTextField txtEdad;
    private final JPasswordField txtPassword;
    private final JRadioButton boton;
    private final JRadioButton boton2;
    private String genero;

    public InterfazRegistro(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles) {

        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        Font fuenteInicio = new Font("Times New Roman", Font.PLAIN, 20);
        Color color = new Color(0);
        setBorder(new MatteBorder(2,2,2,2, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNombreR = new JLabel(config.getOrDefault("labelNombreR", "Ingrese su nombre completo"));
        labelNombreR.setForeground(Color.YELLOW);
        labelNombreR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelNombreR, gbc);

        txtNombreCompleto = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtNombreCompleto, gbc);

        JLabel labelMailR = new JLabel(config.getOrDefault("labelMailR", "Ingrese su correo electrónico"));
        labelMailR.setForeground(Color.YELLOW);
        labelMailR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelMailR, gbc);

        txtCorreo = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtCorreo, gbc);

        JLabel labelNombreUserR = new JLabel(config.getOrDefault("labelNombreUserR", "Ingrese el nombre que desea que se muestre"));
        labelNombreUserR.setForeground(Color.YELLOW);
        labelNombreUserR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelNombreUserR, gbc);

        txtNombreUsuario = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtNombreUsuario, gbc);

        JLabel labelPassword = new JLabel(config.getOrDefault("labelPassword", "Ingrese su contraseña"));
        labelPassword.setForeground(Color.YELLOW);
        labelPassword.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelPassword, gbc);

        txtPassword = new JPasswordField(25);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtPassword, gbc);

        JButton btnMontrarPass = new JButton();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Password15x15.png"));
        btnMontrarPass.setIcon(icono);
        btnMontrarPass.setSize(15,15);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(btnMontrarPass, gbc);
        btnMontrarPass.addMouseListener(new OyenteMostrarPass());

        JLabel labelEdadR = new JLabel(config.getOrDefault("labelEdadR", "Ingrese su edad"));
        labelEdadR.setForeground(Color.YELLOW);
        labelEdadR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelEdadR, gbc);


        txtEdad = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtEdad, gbc);

        JLabel labelGeneroR = new JLabel(config.getOrDefault("labelGeneroR", "Ingrese su género"));
        labelGeneroR.setForeground(Color.YELLOW);
        labelGeneroR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelGeneroR, gbc);

        JPanel panelRBoton = new JPanel();
        panelRBoton.setLayout(new GridBagLayout());
        panelRBoton.setOpaque(false);

        boton = new JRadioButton("F");
        boton.setOpaque(false);
        boton.setFont(fuenteInicio);
        boton.setForeground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRBoton.add(boton, gbc);

        boton2 = new JRadioButton("M");
        boton2.setOpaque(false);
        boton2.setFont(fuenteInicio);
        boton2.setForeground(Color.YELLOW);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelRBoton.add(boton2, gbc);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(boton);
        grupo.add(boton2);

        ActionListener listener = e -> {
            JRadioButton source = (JRadioButton) e.getSource();
            System.out.println("Seleccionado: " + source.getText());
            genero = source.getText();
        };

        boton.addActionListener(listener);
        boton2.addActionListener(listener);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(panelRBoton,gbc);

        JPanel panelBotonesR = new JPanel();
        panelBotonesR.setLayout(new GridBagLayout());
        panelBotonesR.setOpaque(false);

        JButton btnRegistrar = new JButton(config.getOrDefault("btnIngresarR", "Registrar nuevo usuario"));
        btnRegistrar.setSize(100,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotonesR.add(btnRegistrar, gbc);
        btnRegistrar.addActionListener(new OyenteRegistrar());

        JButton btnRegresarR = new JButton(config.getOrDefault("btnRegresarR", "Volver atrás"));
        btnRegresarR.setSize(100,50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotonesR.add(btnRegresarR, gbc);
        btnRegresarR.addActionListener(new OyenteRegresar());

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(panelBotonesR, gbc);
    }

    private class OyenteRegresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mostrarPaneles, "Inicio de sesion");
        }
    }

    private class OyenteRegistrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RegistrarNuevo();
            JOptionPane.showMessageDialog(null, "Se a ingresado con exito al nuevo usuario y se le an acreditado 2000 de oro", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            LimpiarCampos();
        }
    }

   private class OyenteMostrarPass implements MouseListener {

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

    private void RegistrarNuevo(){
        Jugador jugador = new Jugador();
        jugador.setNombre(txtNombreCompleto.getText());
        jugador.setCorreo(txtCorreo.getText());
        jugador.setNombreUsuario(txtNombreUsuario.getText());
        jugador.setPassword(String.valueOf(txtPassword.getPassword()));
        jugador.setEdad(Integer.parseInt(txtEdad.getText()));
        jugador.setGenero(genero);
        jugador.setOro(2000);
        jugador.registrarJugador();
    }

    private void LimpiarCampos(){
        txtNombreCompleto.setText("");
        txtCorreo.setText("");
        txtNombreUsuario.setText("");
        txtPassword.setText("");
        txtEdad.setText("");
        boton.setSelected(false);
        boton2.setSelected(false);
    }
}
