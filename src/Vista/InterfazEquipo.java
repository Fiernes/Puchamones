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
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel puchamonUno = new JPanel();
        puchamonUno.setLayout(new GridBagLayout());
        puchamonUno.setSize(100,250);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(puchamonUno, gbc);

        etiquetaImagen = new JLabel();
        etiquetaImagen.setOpaque(false);
        etiquetaImagen.setPreferredSize(new Dimension(100, 150));
        gbc.gridx = 0;
        gbc.gridy = 0;
        puchamonUno.add(etiquetaImagen, gbc);





        btnAgregarP = new JButton(config.getOrDefault("btnAgregarP", "Agregar puchamon"));
        btnAgregarP.setPreferredSize(new Dimension(150,50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnAgregarP, gbc);

        btnEliminarP = new JButton(config.getOrDefault("btnEliminarP", "Eliminar puchamon"));
        btnEliminarP.setPreferredSize(new Dimension(150,50));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(btnEliminarP, gbc);

        btnAtras = new JButton(config.getOrDefault("btnRegresarR", "Volver atras"));
        btnAtras.setPreferredSize(new Dimension(150, 50));
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(btnAtras, gbc);

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
