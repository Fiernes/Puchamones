package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class InterfazEstadisticas extends PanelFondo {

    private CardLayout cardLayout;
    private JPanel mostrarPaneles;
    private JLabel verNombreJ;
    private JLabel verPC;
    private JLabel verPE;
    private JLabel verBA;
    private JLabel verBG;
    private JLabel verBP;
    private JLabel verDG;
    private JLabel verDP;
    private JLabel verDOMPV;
    private JLabel verOrodisponoble;
    private JLabel verNivel;
    private JButton btnAtras;

    public JButton getBtnAtras() {
        return btnAtras;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getMostrarPaneles() {
        return mostrarPaneles;
    }

    public JLabel getVerNombreJ() {
        return verNombreJ;
    }

    public JLabel getVerPC() {
        return verPC;
    }

    public JLabel getVerPE() {
        return verPE;
    }

    public JLabel getVerBA() {
        return verBA;
    }

    public JLabel getVerBG() {
        return verBG;
    }

    public JLabel getVerBP() {
        return verBP;
    }

    public JLabel getVerDG() {
        return verDG;
    }

    public JLabel getVerDP() {
        return verDP;
    }

    public JLabel getVerDOMPV() {
        return verDOMPV;
    }

    public JLabel getVerOrodisponoble() { return verOrodisponoble; }

    public JLabel getVerNivel() { return verNivel; }

    public InterfazEstadisticas(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles) {
        super("/imagenes/Arena2.jpg");

        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        Color colorLetras = new Color(253, 157, 43, 255);
        Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);
        Color color = new Color(191, 37, 23);
        setLayout(new BorderLayout());
        setBorder(new MatteBorder(2, 3, 2, 3, color));

        JPanel panelLabel = new JPanel();
        panelLabel.setOpaque(false);
        panelLabel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear y agregar etiquetas y campos de texto a la interfaz
        JLabel labelNombreJ = new JLabel(config.getOrDefault("labelNombreJ", "Jugador"));
        labelNombreJ.setForeground(colorLetras);
        labelNombreJ.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLabel.add(labelNombreJ, gbc);

        verNombreJ = new JLabel();
        verNombreJ.setForeground(colorLetras);
        verNombreJ.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelLabel.add(verNombreJ, gbc);

        JLabel labelPC = new JLabel(config.getOrDefault("labelPC", "Puchamones Creados"));
        labelPC.setForeground(colorLetras);
        labelPC.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLabel.add(labelPC, gbc);

        verPC = new JLabel();
        verPC.setForeground(colorLetras);
        verPC.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelLabel.add(verPC, gbc);

        JLabel labelPE = new JLabel(config.getOrDefault("labelPE", "Puchamones Eliminados"));
        labelPE.setForeground(colorLetras);
        labelPE.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelLabel.add(labelPE, gbc);

        verPE = new JLabel();
        verPE.setForeground(colorLetras);
        verPE.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelLabel.add(verPE, gbc);

        JLabel labelBA = new JLabel(config.getOrDefault("labelBA", "Batallas en arena"));
        labelBA.setForeground(colorLetras);
        labelBA.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelLabel.add(labelBA, gbc);

        verBA = new JLabel();
        verBA.setForeground(colorLetras);
        verBA.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelLabel.add(verBA, gbc);

        JLabel labelBG = new JLabel(config.getOrDefault("labelBG", "Batallas ganadas"));
        labelBG.setForeground(colorLetras);
        labelBG.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelLabel.add(labelBG, gbc);

        verBG = new JLabel();
        verBG.setForeground(colorLetras);
        verBG.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelLabel.add(verBG, gbc);

        JLabel labelBP = new JLabel(config.getOrDefault("labelBP", "Batallas perdidas"));
        labelBP.setForeground(colorLetras);
        labelBP.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelLabel.add(labelBP, gbc);

        verBP = new JLabel();
        verBP.setForeground(colorLetras);
        verBP.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelLabel.add(verBP, gbc);

        JLabel labelDG = new JLabel(config.getOrDefault("labelDG", "Dinero Ganado"));
        labelDG.setForeground(colorLetras);
        labelDG.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelLabel.add(labelDG, gbc);

        verDG = new JLabel();
        verDG.setForeground(colorLetras);
        verDG.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panelLabel.add(verDG, gbc);

        JLabel labelDP = new JLabel(config.getOrDefault("labelDP", "Dinero perdido"));
        labelDP.setForeground(colorLetras);
        labelDP.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelLabel.add(labelDP, gbc);

        verDP = new JLabel();
        verDP.setForeground(colorLetras);
        verDP.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panelLabel.add(verDP, gbc);

        JLabel labelDOMPV = new JLabel(config.getOrDefault("labelDOMPV", "Batallas ganadas con 2 o mas puchamones vivos"));
        labelDOMPV.setForeground(colorLetras);
        labelDOMPV.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panelLabel.add(labelDOMPV, gbc);

        verDOMPV = new JLabel();
        verDOMPV.setForeground(colorLetras);
        verDOMPV.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panelLabel.add(verDOMPV, gbc);

        JLabel labelOroDiponible = new JLabel(config.getOrDefault("labelOroDiponible","Oro disponible"));
        labelOroDiponible.setForeground(colorLetras);
        labelOroDiponible.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 9;
        panelLabel.add(labelOroDiponible, gbc);

        verOrodisponoble = new JLabel();
        verOrodisponoble.setForeground(colorLetras);
        verOrodisponoble.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panelLabel.add(verOrodisponoble, gbc);

        JLabel labelNivel = new JLabel(config.getOrDefault("labelNivel","Nivel Jugador"));
        labelNivel.setForeground(colorLetras);
        labelNivel.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 10;
        panelLabel.add(labelNivel, gbc);

        verNivel = new JLabel();
        verNivel.setForeground(colorLetras);
        verNivel.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 10;
        panelLabel.add(verNivel, gbc);

        add(panelLabel, BorderLayout.CENTER);

        JPanel panelBtn = new JPanel();
        panelBtn.setOpaque(false);
        panelBtn.setLayout(new FlowLayout());

        btnAtras = new JButton(config.getOrDefault("btnRegresarR", "Volver atras"));
        btnAtras.setPreferredSize(new Dimension(150,50));
        panelBtn.add(btnAtras);

        add(panelBtn, BorderLayout.SOUTH);
    }
    public void Oyente(ActionListener po){
        btnAtras.addActionListener(po);
    }
}
