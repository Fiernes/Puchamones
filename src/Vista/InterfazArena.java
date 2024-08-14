package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;

public class InterfazArena extends PanelFondo {

    private CardLayout card;
    private JPanel variosPaneles;
    private Map<String, String> configuracion;
    private JPanel panelBarrasDeVida;
    private JPanel panelEquiposPuchamones;
    private JPanel panelOpcionesPelea;
    private JProgressBar vidaPuchamonJugador;
    private JLabel labelnombrePuchamonJugador;
    private JProgressBar VidaPuchamonNpc;
    private JLabel labelnombrePuchamonNpc;
    private JLabel labeltiempoJugada;
    private JLabel labeltotalApuesta;
    private JButton btnAtacar;
    private JButton btnCambiarP;
    private JButton btnSalirA;

    public JLabel getLabeltiempoJugada() {
        return labeltiempoJugada;
    }

    public JProgressBar getVidaPuchamonJugador() {
        return vidaPuchamonJugador;
    }

    public JLabel getLabelnombrePuchamonJugador() {
        return labelnombrePuchamonJugador;
    }

    public JProgressBar getVidaPuchamonNpc() {
        return VidaPuchamonNpc;
    }

    public JLabel getLabelnombrePuchamonNpc() {
        return labelnombrePuchamonNpc;
    }

    public CardLayout getCard() {
        return card;
    }

    public void setCard(CardLayout card) {
        this.card = card;
    }

    public JPanel getVariosPaneles() {
        return variosPaneles;
    }

    public void setVariosPaneles(JPanel variosPaneles) {
        this.variosPaneles = variosPaneles;
    }

    public Map<String, String> getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Map<String, String> configuracion) {
        this.configuracion = configuracion;
    }

    public JButton getBtnSalirA() {
        return btnSalirA;
    }

    public void setBtnSalirA(JButton btnSalirA) {
        this.btnSalirA = btnSalirA;
    }

    public JButton getBtnCambiarP() {
        return btnCambiarP;
    }

    public void setBtnCambiarP(JButton btnCambiarP) {
        this.btnCambiarP = btnCambiarP;
    }

    public JButton getBtnAtacar() {
        return btnAtacar;
    }

    public void setBtnAtacar(JButton btnAtacar) {
        this.btnAtacar = btnAtacar;
    }

    public JPanel getPanelEquiposPuchamones() {
        return panelEquiposPuchamones;
    }

    public void setPanelEquiposPuchamones(JPanel panelEquiposPuchamones) {
        this.panelEquiposPuchamones = panelEquiposPuchamones;
    }

    public JLabel getLabeltotalApuesta() {
        return labeltotalApuesta;
    }

    public InterfazArena(Map<String, String> config, CardLayout cardLayout, JPanel paneles){
        super("/imagenes/Arena.jpg");

        this.configuracion = config;
        this.variosPaneles = paneles;
        this.card = cardLayout;

        Color color = new Color(191, 37, 23);
        setBorder(new MatteBorder(2, 3, 2, 3, color));
        setLayout(new BorderLayout());

        Color colorLetras = new Color(253, 157, 43, 255);
        Font fuenteNombre = new Font("Georgia", Font.BOLD, 20);
        Font fuente = new Font("Georgia", Font.BOLD, 30);


        panelBarrasDeVida = new JPanel(new GridBagLayout());
        panelBarrasDeVida.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes

        // Crear la barra de vida del jugador
        vidaPuchamonJugador = new JProgressBar();
        vidaPuchamonJugador.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBarrasDeVida.add(vidaPuchamonJugador, gbc);

        // Crear la etiqueta del nombre del jugador
        labelnombrePuchamonJugador = new JLabel("CHIMUELO");
        labelnombrePuchamonJugador.setFont(fuenteNombre);
        labelnombrePuchamonJugador.setForeground(colorLetras);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH; // Alineación inferior para estar justo arriba de la barra
        panelBarrasDeVida.add(labelnombrePuchamonJugador, gbc);

        // Crear la etiqueta del tiempo de jugada
        labeltiempoJugada = new JLabel("30", SwingConstants.CENTER);
        labeltiempoJugada.setFont(fuente);
        labeltiempoJugada.setForeground(colorLetras);
        labeltiempoJugada.setOpaque(false);
        labeltiempoJugada.setPreferredSize(new Dimension(240, 60));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        panelBarrasDeVida.add(labeltiempoJugada, gbc);

        // Crear la etiqueta del total de apuesta
        labeltotalApuesta = new JLabel("3000", SwingConstants.CENTER);
        labeltotalApuesta.setFont(fuente);
        labeltotalApuesta.setForeground(colorLetras);
        labeltotalApuesta.setOpaque(false);
        labeltotalApuesta.setPreferredSize(new Dimension(120, 60));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        panelBarrasDeVida.add(labeltotalApuesta, gbc);

        // Crear la barra de vida del NPC
        VidaPuchamonNpc = new JProgressBar();
        VidaPuchamonNpc.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        panelBarrasDeVida.add(VidaPuchamonNpc, gbc);

        // Crear la etiqueta del nombre del NPC
        labelnombrePuchamonNpc = new JLabel("COCOMON");
        labelnombrePuchamonNpc.setFont(fuenteNombre);
        labelnombrePuchamonNpc.setForeground(colorLetras);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH; // Alineación inferior para estar justo arriba de la barra
        panelBarrasDeVida.add(labelnombrePuchamonNpc, gbc);

        panelEquiposPuchamones = new JPanel();
        panelEquiposPuchamones.setOpaque(false);

        panelOpcionesPelea = new JPanel();
        panelOpcionesPelea.setOpaque(false);
        panelOpcionesPelea.setLayout(new FlowLayout());

        btnAtacar = new JButton(config.getOrDefault("","Atacar"));
        btnAtacar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAtacar.setVerticalTextPosition(SwingConstants.CENTER);
        btnAtacar.setOpaque(false);
        btnAtacar.setContentAreaFilled(false);
        btnAtacar.setBorderPainted(false);

        btnCambiarP = new JButton(config.getOrDefault("","Cambiar puchamon"));
        btnCambiarP.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCambiarP.setVerticalTextPosition(SwingConstants.CENTER);
        btnCambiarP.setOpaque(false);
        btnCambiarP.setContentAreaFilled(false);
        btnCambiarP.setBorderPainted(false);

        btnSalirA = new JButton(config.getOrDefault("","Salir"));
        btnSalirA.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSalirA.setVerticalTextPosition(SwingConstants.CENTER);
        btnSalirA.setOpaque(false);
        btnSalirA.setContentAreaFilled(false);
        btnSalirA.setBorderPainted(false);

        panelOpcionesPelea.add(btnAtacar);
        panelOpcionesPelea.add(btnCambiarP);
        panelOpcionesPelea.add(btnSalirA);

        add(panelBarrasDeVida, BorderLayout.NORTH);
        add(panelEquiposPuchamones, BorderLayout.CENTER);
        add(panelOpcionesPelea, BorderLayout.SOUTH);
    }

    public void OyenteMauseBtnAtacar(MouseListener mo){
        btnAtacar.addMouseListener(mo);
    }
    public void OyenteMauseBtnCambiarP(MouseListener mo){
        btnCambiarP.addMouseListener(mo);
    }
    public void OyenteMauseBtnSalirA(MouseListener mo){
        btnSalirA.addMouseListener(mo);
    }
}
