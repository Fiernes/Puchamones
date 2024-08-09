package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;


public class InterfazEquipo extends PanelFondo {

    private CardLayout cardLayout;
    private JPanel paneles;
    private JButton btnAtras;
    private JButton btnAgregarP;
    private JButton btnEliminarP;
    private JLabel etiquetaImagen;
    private JLabel nombreP;
    private JLabel tipoP;
    private JLabel nivelP;
    private JLabel vidaP;
    private JLabel ataqueP;
    private JLabel defenzaP;
    private JLabel experienciaP;
    private JPanel panelElegidos;

    public JPanel getPanelElegidos() {
        return panelElegidos;
    }

    public void setPanelElegidos(JPanel panelElegidos) {
        this.panelElegidos = panelElegidos;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public JPanel getPaneles() {
        return paneles;
    }

    public void setPaneles(JPanel paneles) {
        this.paneles = paneles;
    }

    public JButton getBtnAtras() {
        return btnAtras;
    }

    public void setBtnAtras(JButton btnAtras) {
        this.btnAtras = btnAtras;
    }

    public JButton getBtnAgregarP() {
        return btnAgregarP;
    }

    public void setBtnAgregarP(JButton btnAgregarP) {
        this.btnAgregarP = btnAgregarP;
    }

    public JButton getBtnEliminarP() {
        return btnEliminarP;
    }

    public void setBtnEliminarP(JButton btnEliminarP) {
        this.btnEliminarP = btnEliminarP;
    }

    public JLabel getEtiquetaImagen() {
        return etiquetaImagen;
    }

    public void setEtiquetaImagen(JLabel etiquetaImagen) {
        this.etiquetaImagen = etiquetaImagen;
    }

    public JLabel getNombreP() {
        return nombreP;
    }

    public void setNombreP(JLabel nombreP) {
        this.nombreP = nombreP;
    }

    public JLabel getTipoP() {
        return tipoP;
    }

    public void setTipoP(JLabel tipoP) {
        this.tipoP = tipoP;
    }

    public JLabel getNivelP() {
        return nivelP;
    }

    public void setNivelP(JLabel nivelP) {
        this.nivelP = nivelP;
    }

    public JLabel getVidaP() {
        return vidaP;
    }

    public void setVidaP(JLabel vidaP) {
        this.vidaP = vidaP;
    }

    public JLabel getAtaqueP() {
        return ataqueP;
    }

    public void setAtaqueP(JLabel ataqueP) {
        this.ataqueP = ataqueP;
    }

    public JLabel getDefenzaP() {
        return defenzaP;
    }

    public void setDefenzaP(JLabel defenzaP) {
        this.defenzaP = defenzaP;
    }

    public JLabel getExperienciaP() {
        return experienciaP;
    }

    public void setExperienciaP(JLabel experienciaP) {
        this.experienciaP = experienciaP;
    }

    public InterfazEquipo(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles){
        super("/imagenes/FondoEquipo.jpg");

        this.cardLayout = cardLayout;
        this.paneles = mostrarPaneles;

        Color colorLetras = new Color(253, 157, 43, 255);
        Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);
        Color color = new Color(191, 37, 23);
        setBorder(new MatteBorder(2, 3, 2, 3, color));
        setLayout(new BorderLayout());

        panelElegidos = new JPanel();
        panelElegidos.setLayout(new GridBagLayout());
        panelElegidos.setOpaque(false);
        add(panelElegidos, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setOpaque(false);

        btnAgregarP = new JButton(config.getOrDefault("btnAgregarP", "Agregar puchamon"));
        btnAgregarP.setPreferredSize(new Dimension(150,50));
        panelBotones.add(btnAgregarP);

        btnEliminarP = new JButton(config.getOrDefault("btnEliminarP", "Eliminar puchamon"));
        btnEliminarP.setPreferredSize(new Dimension(150,50));
        panelBotones.add(btnEliminarP);

        btnAtras = new JButton(config.getOrDefault("btnRegresarR", "Volver atras"));
        btnAtras.setPreferredSize(new Dimension(150, 50));
        panelBotones.add(btnAtras);

        add(panelBotones, BorderLayout.SOUTH);

    }

    public void OyenteBtnEliminarP(ActionListener po){
        btnEliminarP.addActionListener(po);
    }

    public void OyenteBtnAgregarP(ActionListener po){
        btnAgregarP.addActionListener(po);
    }

    public void OyenteBtnAtras(ActionListener po){
        btnAtras.addActionListener(po);
    }
}
