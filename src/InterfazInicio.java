import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class InterfazInicio extends PanelFondo {

    private final CardLayout cardLayout;
    private final JPanel mostrarPaneles;
    private JPasswordField txtPassword;

    public InterfazInicio(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles) {
        super("/imagenes/LogoPrincipal.jpg");

        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        Color colorLetras = new Color(253, 157, 43, 255);
        Color color = new Color(191,37,23);
        setBorder(new MatteBorder(2,3,2,3, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta de usuario
        JLabel labelUsuario = new JLabel(config.getOrDefault("labelUsuario", "Usuario"));
        labelUsuario.setForeground(colorLetras);
        Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);
        labelUsuario.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelUsuario, gbc);

        // Campo de texto para el usuario
        JTextField txtUsuario = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsuario, gbc);

        // Etiqueta de contraseña
        JLabel labelPassword = new JLabel(config.getOrDefault("labelPassword", "Contraseña"));
        labelPassword.setForeground(colorLetras);
        labelPassword.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelPassword, gbc);

        // Campo de texto para la contraseña
        txtPassword = new JPasswordField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        JButton btnMontrarPass = new JButton();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Password15x15.png"));
        btnMontrarPass.setIcon(icono);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(btnMontrarPass, gbc);
        btnMontrarPass.addMouseListener(new OyenteMostrarPass());

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        panelBotones.setOpaque(false);

        // Botón Ingresar
        JButton btnIngresar = new JButton(config.getOrDefault("btnIngresar", "Ingresar"));
        btnIngresar.setSize(50, 50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(btnIngresar, gbc);
        btnIngresar.addActionListener(new OyenteBtnInicio());

        // Botón Registrar
        JButton btnRegistrar = new JButton(config.getOrDefault("btnRegistrar", "Registrar"));
        btnRegistrar.setSize(50, 50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotones.add(btnRegistrar, gbc);
        btnRegistrar.addActionListener(new OyenteBtnRegistro());

        // Añadir el panel de botones al panel principal
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(panelBotones, gbc);
    }

    private class OyenteBtnRegistro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mostrarPaneles, "Registro de nuevo usuario");
        }
    }

    private class OyenteBtnInicio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mostrarPaneles, "Menu Principal");
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

}
