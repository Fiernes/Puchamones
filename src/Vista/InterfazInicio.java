package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;

public class InterfazInicio extends PanelFondo {

    private CardLayout cardLayout;
    private JPanel mostrarPaneles;
    private JPasswordField txtPassword;
    private JTextField txtUsuario;
    private JButton btnMostrarPass;
    private JButton btnIngresar;
    private JButton btnRegistrar;
    private Map<String, String> Config;

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public JPanel getMostrarPaneles() {
        return mostrarPaneles;
    }

    public void setMostrarPaneles(JPanel mostrarPaneles) {
        this.mostrarPaneles = mostrarPaneles;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JButton getBtnMostrarPass() {
        return btnMostrarPass;
    }

    public void setBtnMostrarPass(JButton btnMostrarPass) {
        this.btnMostrarPass = btnMostrarPass;
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public void setBtnIngresar(JButton btnIngresar) {
        this.btnIngresar = btnIngresar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public Map<String, String> getConfig() {
        return Config;
    }

    public void setConfig(Map<String, String> config) {
        Config = config;
    }

    public InterfazInicio(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles) {
        super("/imagenes/LogoPrincipal.jpg");

        this.Config = config;
        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        Color colorFuente = new Color(253, 157, 43, 255);
        Font fuente = new Font("Georgia", Font.BOLD, 20);
        Color color = new Color(191,37,23);
        setBorder(new MatteBorder(2,3,2,3, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelUsuario = new JLabel(config.getOrDefault("labelUsuario", "Usuario"));
        labelUsuario.setForeground(colorFuente);
        labelUsuario.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelUsuario, gbc);

        txtUsuario = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsuario, gbc);

        JLabel labelPassword = new JLabel(config.getOrDefault("labelPassword", "Contraseña"));
        labelPassword.setForeground(colorFuente);
        labelPassword.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelPassword, gbc);

        txtPassword = new JPasswordField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        btnMostrarPass = new JButton();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Password15x15.png"));
        btnMostrarPass.setIcon(icono);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(btnMostrarPass, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        panelBotones.setOpaque(false);

        btnIngresar = new JButton(config.getOrDefault("btnIngresar", "Ingresar"));
        btnIngresar.setSize(50, 50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(btnIngresar, gbc);

        btnRegistrar = new JButton(config.getOrDefault("btnRegistrar", "Registrar"));
        btnRegistrar.setSize(50, 50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotones.add(btnRegistrar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(panelBotones, gbc);
    }

    public void oyente(ActionListener po){
        btnRegistrar.addActionListener(po);
    }

    public void oyente2(MouseListener po){
        btnMostrarPass.addMouseListener(po);
    }

    public void oyente3(ActionListener po){
        btnIngresar.addActionListener(po);
    }
}
