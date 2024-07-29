package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class InterfazRegistroPuchamon extends PanelFondo {

    private CardLayout cardLayout;
    private JPanel mostrarPaneles;
    private JTextField txtNombreP;
    private JLabel ultimaSeleccionada;
    private JLabel seleccionada;
    private JButton btnCrear;
    private JButton btnAtras;

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

    public JTextField getTxtNombreP() {
        return txtNombreP;
    }

    public void setTxtNombreP(JTextField txtNombreP) {
        this.txtNombreP = txtNombreP;
    }

    public JLabel getUltimaSeleccionada() {
        return ultimaSeleccionada;
    }

    public void setUltimaSeleccionada(JLabel ultimaSeleccionada) {
        this.ultimaSeleccionada = ultimaSeleccionada;
    }

    public JLabel getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(JLabel seleccionada) {
        this.seleccionada = seleccionada;
    }

    public JPanel getPanelImagenes() {
        return panelImagenes;
    }

    public void setPanelImagenes(JPanel panelImagenes) {
        this.panelImagenes = panelImagenes;
    }

    private JPanel panelImagenes;

    public InterfazRegistroPuchamon(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles){
        super("/imagenes/FondoMprincipal3.jpg");

        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        setLayout(new BorderLayout());

        JPanel panelLabel = new JPanel();
        panelLabel.setLayout(new FlowLayout());
        JLabel nombrePuchamon = new JLabel(config.getOrDefault("labelNombrePuchamon", "Nombre puchamon"));
        panelLabel.add(nombrePuchamon);

        txtNombreP = new JTextField(30);
        panelLabel.add(txtNombreP);

        add(panelLabel, BorderLayout.NORTH);

        panelImagenes = new JPanel();
        panelImagenes.setOpaque(false);
        panelImagenes.setLayout(new GridLayout(0, 3));

        JScrollPane scroll = new JScrollPane(panelImagenes);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        btnCrear = new JButton(config.getOrDefault("btnCrear","Crear puchamon"));
        btnCrear.setPreferredSize(new Dimension(150,50));
        panelBotones.add(btnCrear);

        btnAtras = new JButton(config.getOrDefault("btnRegresarR", "Volver atras"));
        btnAtras.setPreferredSize(new Dimension(150,50));
        panelBotones.add(btnAtras);

        add(panelBotones, BorderLayout.SOUTH);
    }
     public void OyenteCrear(ActionListener po){
        btnCrear.addActionListener(po);
     }

     public void OyenteAtras(ActionListener po){
        btnAtras.addActionListener(po);
     }
}