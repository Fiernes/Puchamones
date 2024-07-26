package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class InterfazMPrincipal extends PanelFondo {

    private final CardLayout cardLayout;
    private final JPanel mostrarPaneles;
    private FramePrincipal principal;
    private Map<String, String> config2;

    public InterfazMPrincipal(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles, FramePrincipal prin){
        super("/imagenes/FondoMprincipal3.jpg");

        this.config2 = config;
        this.principal = prin;
        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        Color color = new Color(191,37,23);
        setBorder(new MatteBorder(2,3,2,3, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        Font buttonFont = new Font("Arial", Font.BOLD, 20);

        JButton btnArena = new JButton(config.getOrDefault("btnArena","Arena"));
        btnArena.setPreferredSize(new Dimension(400, 80));
        btnArena.setFont(buttonFont);
        gbc.gridy = 0;
        add(btnArena, gbc);


        JButton btnEstadisticas = new JButton(config.getOrDefault("btnEstadisticas","Estadisticas"));
        btnEstadisticas.setPreferredSize(new Dimension(400, 80));
        btnEstadisticas.setFont(buttonFont);
        gbc.gridy = 1;
        add(btnEstadisticas, gbc);
        btnEstadisticas.addActionListener(new OyenteBtnEstadisticas());

        JButton btnEquipo = new JButton(config.getOrDefault("btnEquipo","Equipo"));
        btnEquipo.setPreferredSize(new Dimension(400, 80));
        btnEquipo.setFont(buttonFont);
        gbc.gridy = 2;
        add(btnEquipo, gbc);

        JButton btnCerrarSesion = new JButton(config.getOrDefault("btnCerrarSesion","Cerrar sesion"));
        btnCerrarSesion.setPreferredSize(new Dimension(400, 80));
        btnCerrarSesion.setFont(buttonFont);
        gbc.gridy = 3;
        add(btnCerrarSesion, gbc);
        btnCerrarSesion.addActionListener(new OyenteBtnCerrarSesion());
    }

    private class OyenteBtnCerrarSesion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new FrameInicio(config2);
            principal.dispose();
        }
    }
    private class OyenteBtnArena implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class OyenteBtnEstadisticas implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mostrarPaneles, "Estadisticas");
        }

    }
    private class OyenteBtnEquipo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
