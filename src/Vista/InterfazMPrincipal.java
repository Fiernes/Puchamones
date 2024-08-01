package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;

public class InterfazMPrincipal extends PanelFondo {

    private final CardLayout cardLayout;
    private final JPanel mostrarPaneles;
    private FramePrincipal principal;
    private Map<String, String> config;
    private JButton btnEstadisticas;
    private JButton btnEquipo;
    private JButton btnArena;
    private JButton btnCerrarSesion;

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public FramePrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(FramePrincipal principal) {
        this.principal = principal;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public void setBtnCerrarSesion(JButton btnCerrarSesion) {
        this.btnCerrarSesion = btnCerrarSesion;
    }

    public JButton getBtnArena() {
        return btnArena;
    }

    public void setBtnArena(JButton btnArena) {
        this.btnArena = btnArena;
    }

    public JButton getBtnEquipo() {
        return btnEquipo;
    }

    public void setBtnEquipo(JButton btnEquipo) {
        this.btnEquipo = btnEquipo;
    }

    public JButton getBtnEstadisticas() {
        return btnEstadisticas;
    }

    public void setBtnEstadisticas(JButton btnEstadisticas) {
        this.btnEstadisticas = btnEstadisticas;
    }

    public InterfazMPrincipal(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles, FramePrincipal prin) {
        super("/imagenes/LogoPrincipal(2).jpg");

        this.config = config;
        this.principal = prin;
        this.cardLayout = cardLayout;
        this.mostrarPaneles = mostrarPaneles;

        Color color = new Color(191, 37, 23);
        setBorder(new MatteBorder(2, 3, 2, 3, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);
        Color colorLetras = new Color(253, 157, 43, 255);

        btnArena = new JButton("Arena");
        btnArena.setPreferredSize(new Dimension(200, 300));
        btnArena.setFont(fuenteInicio);
        btnArena.setForeground(colorLetras);

        btnArena.setHorizontalTextPosition(SwingConstants.CENTER);
        btnArena.setVerticalTextPosition(SwingConstants.CENTER);
        btnArena.setOpaque(false);
        btnArena.setContentAreaFilled(false);
        btnArena.setBorderPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 40, 100, 100);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(btnArena, gbc);

        btnEstadisticas = new JButton(config.getOrDefault("btnEstadisticas", "Estadísticas"));
        btnEstadisticas.setPreferredSize(new Dimension(200, 300));
        btnEstadisticas.setFont(fuenteInicio);
        btnEstadisticas.setForeground(colorLetras);

        btnEstadisticas.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEstadisticas.setVerticalTextPosition(SwingConstants.CENTER);
        btnEstadisticas.setOpaque(false);
        btnEstadisticas.setContentAreaFilled(false);
        btnEstadisticas.setBorderPainted(false);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 100, 100, 40);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(btnEstadisticas, gbc);

        btnEquipo = new JButton(config.getOrDefault("btnEquipo", "Equipo"));
        btnEquipo.setPreferredSize(new Dimension(200, 300));
        btnEquipo.setFont(fuenteInicio);
        btnEquipo.setForeground(colorLetras);

        btnEquipo.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEquipo.setVerticalTextPosition(SwingConstants.CENTER);
        btnEquipo.setOpaque(false);
        btnEquipo.setContentAreaFilled(false);
        btnEquipo.setBorderPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(100, 40, 40, 100);
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        add(btnEquipo, gbc);

        btnCerrarSesion = new JButton(config.getOrDefault("btnCerrarSesion", "Cerrar sesión"));
        btnCerrarSesion.setPreferredSize(new Dimension(200, 300));
        btnCerrarSesion.setFont(fuenteInicio);
        btnCerrarSesion.setForeground(colorLetras);

        btnCerrarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCerrarSesion.setVerticalTextPosition(SwingConstants.CENTER);
        btnCerrarSesion.setOpaque(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setBorderPainted(false);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(100, 100, 40, 40);
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        add(btnCerrarSesion, gbc);
    }

    public void OyenteBtnArena(MouseListener mo){
        btnArena.addMouseListener(mo);
    }
    public void OyenteBtnEquipo(MouseListener mo){
        btnEquipo.addMouseListener(mo);
    }
    public void OyenteBtnEstadisticas(MouseListener mo){
        btnEstadisticas.addMouseListener(mo);
    }
    public void OyenteBtnCerrar(MouseListener mo){
        btnCerrarSesion.addMouseListener(mo);
    }

    public void OyenteEstadisticas(ActionListener po) {
        btnEstadisticas.addActionListener(po);
    }

    public void OyenteEquipo(ActionListener po) {
        btnEquipo.addActionListener(po);
    }

    public void OyenteCerrar(ActionListener po){
        btnCerrarSesion.addActionListener(po);
    }
}
