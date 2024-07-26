package Vista;

import Modelo.EstadisticasJugador;
import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class InterfazEstadisticas extends PanelFondo {

    private final CardLayout cardLayout;
    private final JPanel mostrarPaneles;
    private List<EstadisticasJugador> listaEsta;

    private JLabel verNombreJ;
    private JLabel verPC;
    private JLabel verPE;
    private JLabel verBA;
    private JLabel verBG;
    private JLabel verBP;
    private JLabel verDG;
    private JLabel verDP;
    private JLabel verDOMPV;

    public JLabel getVerNombreJ() {
        return verNombreJ;
    }

    public void setVerNombreJ(JLabel verNombreJ) {
        this.verNombreJ = verNombreJ;
    }

    public JLabel getVerPC() {
        return verPC;
    }

    public void setVerPC(JLabel verPC) {
        this.verPC = verPC;
    }

    public JLabel getVerPE() {
        return verPE;
    }

    public void setVerPE(JLabel verPE) {
        this.verPE = verPE;
    }

    public JLabel getVerBA() {
        return verBA;
    }

    public void setVerBA(JLabel verBA) {
        this.verBA = verBA;
    }

    public JLabel getVerBG() {
        return verBG;
    }

    public void setVerBG(JLabel verBG) {
        this.verBG = verBG;
    }

    public JLabel getVerBP() {
        return verBP;
    }

    public void setVerBP(JLabel verBP) {
        this.verBP = verBP;
    }

    public JLabel getVerDG() {
        return verDG;
    }

    public void setVerDG(JLabel verDG) {
        this.verDG = verDG;
    }

    public JLabel getVerDP() {
        return verDP;
    }

    public void setVerDP(JLabel verDP) {
        this.verDP = verDP;
    }

    public JLabel getVerDOMPV() {
        return verDOMPV;
    }

    public void setVerDOMPV(JLabel verDOMPV) {
        this.verDOMPV = verDOMPV;
    }

    // Constructor de la clase
    public InterfazEstadisticas(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles) {
        super("/imagenes/Estadisticas.jpg");

        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        // Establecer colores y fuentes
        Color colorLetras = new Color(253, 157, 43, 255);
        Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);
        Color color = new Color(191, 37, 23);
        setBorder(new MatteBorder(2, 3, 2, 3, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Crear y agregar etiquetas y campos de texto a la interfaz
        JLabel labelNombreJ = new JLabel(config.getOrDefault("nombreJ", "Jugador"));
        labelNombreJ.setForeground(colorLetras);
        labelNombreJ.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelNombreJ, gbc);

        verNombreJ = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(verNombreJ, gbc);

        JLabel labelPC = new JLabel(config.getOrDefault("puchamonesCreados", "Puchamones Creados"));
        labelPC.setForeground(colorLetras);
        labelPC.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelPC, gbc);

        verPC = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(verPC, gbc);

        JLabel labelPE = new JLabel(config.getOrDefault("puchamonesEliminados", "Puchamones Eliminados"));
        labelPE.setForeground(colorLetras);
        labelPE.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelPE, gbc);

        verPE = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(verPE, gbc);

        JLabel labelBA = new JLabel(config.getOrDefault("batallasArena", "Batallas en arena"));
        labelBA.setForeground(colorLetras);
        labelBA.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(labelBA, gbc);

        verBA = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(verBA, gbc);

        JLabel labelBG = new JLabel(config.getOrDefault("batallasGanadas", "Batallas ganadas"));
        labelBG.setForeground(colorLetras);
        labelBG.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(labelBG, gbc);

        verBG = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(verBG, gbc);

        JLabel labelBP = new JLabel(config.getOrDefault("batallasPerdidas", "Batallas perdidas"));
        labelBP.setForeground(colorLetras);
        labelBP.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(labelBP, gbc);

        verBP = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(verBP, gbc);

        JLabel labelDG = new JLabel(config.getOrDefault("dineroGanado", "Dinero Ganado"));
        labelDG.setForeground(colorLetras);
        labelDG.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(labelDG, gbc);

        verDG = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(verDG, gbc);

        JLabel labelDP = new JLabel(config.getOrDefault("dineroPerdido", "Dinero perdido"));
        labelDP.setForeground(colorLetras);
        labelDP.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(labelDP, gbc);

        verDP = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(verDP, gbc);

        JLabel labelDOMPV = new JLabel(config.getOrDefault("batallasGanadasCon2OMasVivos", "Batallas ganadas con 2 o mas puchamones vivos"));
        labelDOMPV.setForeground(colorLetras);
        labelDOMPV.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(labelDOMPV, gbc);

        verDOMPV = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(verDOMPV, gbc);
    }
}
