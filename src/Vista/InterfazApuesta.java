package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class InterfazApuesta extends PanelFondo {
    private JComboBox<Integer> comboBox;
    private JButton btnContinuar;
    private JButton btnCancelar;
    private Map<String, String> config;
    private CardLayout cardLayout;
    private JPanel paneles;
    private String usuario;

    public InterfazApuesta(Map<String, String> config, CardLayout cardLayout, JPanel paneles) {
        super("/imagenes/MonedasOro.jpg");

        this.config = config;
        this.cardLayout = cardLayout;
        this.paneles = paneles;
        this.usuario = usuario;

        setLayout(new BorderLayout());

        Font fuenteInicio = new Font("Georgia", Font.BOLD, 40);
        Color colorLetras = new Color(253, 157, 43, 255);

        // Crear JComboBox con valores de 200 a 1000
        Integer[] cantidades = {200, 300, 400, 500, 600, 700, 800, 900, 1000};

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        p.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelApuesta = new JLabel(config.getOrDefault("","Seleccione su apuesta"));
        labelApuesta.setForeground(colorLetras);
        labelApuesta.setFont(fuenteInicio);
        gbc.gridx = 0;
        gbc.gridy = 0;
        p.add(labelApuesta, gbc);

        comboBox = new JComboBox<>(cantidades);
        comboBox.setPreferredSize(new Dimension(300,50));
        comboBox.setForeground(colorLetras);
        comboBox.setFont(fuenteInicio);
        gbc.gridx = 1;
        gbc.gridy = 0;

        p.add(comboBox, gbc);

        add(p, BorderLayout.CENTER);

        // Crear panel para botones
        JPanel panelBotones = new JPanel();
        btnContinuar = new JButton("Continuar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnContinuar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public String getUsuario() {
        return usuario;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getPaneles() {
        return paneles;
    }

    public JComboBox<Integer> getComboBox() {
        return comboBox;
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}

