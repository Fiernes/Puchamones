package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;

public class InterfazRegistro extends PanelFondo {

    private CardLayout cardLayout;
    private JPanel mostrarPaneles;
    private Map<String, String> configuracion;
    private JTextField txtNombreCompleto;
    private JTextField txtCorreo;
    private JTextField txtNombreUsuario;
    private JComboBox<Integer> comboEdad;
    private JPasswordField txtPassword;
    private JRadioButton boton;
    private JRadioButton boton2;
    private String generoRegistro;
    private String mensajeCuerpo;
    private String mensajeCabezera;
    private JButton btnMontrarPass;
    private JButton btnRegistrar;
    private JButton btnRegresarR;

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

    public Map<String, String> getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Map<String, String> configuracion) {
        this.configuracion = configuracion;
    }

    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public void setTxtNombreCompleto(JTextField txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public JTextField getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public void setTxtNombreUsuario(JTextField txtNombreUsuario) {
        this.txtNombreUsuario = txtNombreUsuario;
    }

    public JComboBox<Integer> getComboEdad() {
        return comboEdad;
    }

    public void setComboEdad(JComboBox<Integer> comboEdad) {
        this.comboEdad = comboEdad;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JRadioButton getBoton() {
        return boton;
    }

    public void setBoton(JRadioButton boton) {
        this.boton = boton;
    }

    public JRadioButton getBoton2() {
        return boton2;
    }

    public void setBoton2(JRadioButton boton2) {
        this.boton2 = boton2;
    }

    public String getGeneroRegistro() {
        return generoRegistro;
    }

    public void setGeneroRegistro(String generoRegistro) {
        this.generoRegistro = generoRegistro;
    }

    public String getMensajeCuerpo() {
        return mensajeCuerpo;
    }

    public void setMensajeCuerpo(String mensajeCuerpo) {
        this.mensajeCuerpo = mensajeCuerpo;
    }

    public String getMensajeCabezera() {
        return mensajeCabezera;
    }

    public void setMensajeCabezera(String mensajeCabezera) {
        this.mensajeCabezera = mensajeCabezera;
    }

    public JButton getBtnMontrarPass() {
        return btnMontrarPass;
    }

    public void setBtnMontrarPass(JButton btnMontrarPass) {
        this.btnMontrarPass = btnMontrarPass;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public JButton getBtnRegresarR() {
        return btnRegresarR;
    }

    public void setBtnRegresarR(JButton btnRegresarR) {
        this.btnRegresarR = btnRegresarR;
    }

    public InterfazRegistro(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles) {
        super("/imagenes/LogoRegistro.jpg");

        this.configuracion = config;
        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        mensajeCuerpo = config.getOrDefault("JOptionPane", "Se a ingresado con exito al nuevo usuario y se le an acreditado 2000 de oro");
        mensajeCabezera = config.getOrDefault("JOptionPaneCabezera", "Registro exitoso");

        Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);
        Color colorLetras = new Color(253, 157, 43, 255);
        Color color = new Color(191,37,23);
        setBorder(new MatteBorder(2,3,2,3, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNombreR = new JLabel(config.getOrDefault("labelNombreR", "Ingrese su nombre completo"));
        labelNombreR.setForeground(colorLetras);
        labelNombreR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelNombreR, gbc);

        txtNombreCompleto = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtNombreCompleto, gbc);

        JLabel labelMailR = new JLabel(config.getOrDefault("labelMailR", "Ingrese su correo electrónico"));
        labelMailR.setForeground(colorLetras);
        labelMailR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelMailR, gbc);

        txtCorreo = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtCorreo, gbc);

        JLabel labelNombreUserR = new JLabel(config.getOrDefault("labelNombreUserR", "Ingrese el nombre que desea que se muestre"));
        labelNombreUserR.setForeground(colorLetras);
        labelNombreUserR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelNombreUserR, gbc);

        txtNombreUsuario = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtNombreUsuario, gbc);

        JLabel labelPassword = new JLabel(config.getOrDefault("labelPassword", "Ingrese su contraseña"));
        labelPassword.setForeground(colorLetras);
        labelPassword.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelPassword, gbc);

        txtPassword = new JPasswordField(25);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtPassword, gbc);

        btnMontrarPass = new JButton();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Password15x15.png"));
        btnMontrarPass.setIcon(icono);
        btnMontrarPass.setSize(15,15);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(btnMontrarPass, gbc);

        JLabel labelEdadR = new JLabel(config.getOrDefault("labelEdadR", "Ingrese su edad"));
        labelEdadR.setForeground(colorLetras);
        labelEdadR.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelEdadR, gbc);


        Integer[] edades = new Integer[100];
        for (int i = 0; i < 100; i++) {
            edades[i] = i + 1;
        }
        JPanel edad = new JPanel();
        edad.setLayout(new BorderLayout());
        edad.setOpaque(false);
        comboEdad = new JComboBox<>(edades);
        gbc.gridx = 1;
        gbc.gridy = 4;
        edad.add(comboEdad, BorderLayout.WEST);
        add(edad, gbc);

        JLabel labelGeneroR = new JLabel(config.getOrDefault("labelGeneroR", "Ingrese su género"));
        labelGeneroR.setForeground(colorLetras);
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
        boton.setForeground(colorLetras);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRBoton.add(boton, gbc);

        boton2 = new JRadioButton("M");
        boton2.setOpaque(false);
        boton2.setFont(fuenteInicio);
        boton2.setForeground(colorLetras);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelRBoton.add(boton2, gbc);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(boton);
        grupo.add(boton2);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(panelRBoton,gbc);

        JPanel panelBotonesR = new JPanel();
        panelBotonesR.setLayout(new GridBagLayout());
        panelBotonesR.setOpaque(false);

        btnRegistrar = new JButton(config.getOrDefault("btnIngresarR", "Registrar nuevo usuario"));
        btnRegistrar.setSize(100,50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotonesR.add(btnRegistrar, gbc);

        btnRegresarR = new JButton(config.getOrDefault("btnRegresarR", "Volver atrás"));
        btnRegresarR.setSize(100,50);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotonesR.add(btnRegresarR, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(panelBotonesR, gbc);
    }

    public void OyenteContra(MouseListener po){
        btnMontrarPass.addMouseListener(po);
    }
    public void OyenteRadioB(ActionListener po){
        boton.addActionListener(po);
        boton2.addActionListener(po);
    }
    public void OyenteRegis(ActionListener po){
        btnRegistrar.addActionListener(po);
    }
    public void OyenteRegresar(ActionListener po){
        btnRegresarR.addActionListener(po);
    }
}
