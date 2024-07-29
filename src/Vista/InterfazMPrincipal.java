package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Map;

public class InterfazMPrincipal extends PanelFondo {

    private final CardLayout cardLayout;
    private final JPanel mostrarPaneles;
    private FramePrincipal principal;
    private Map<String, String> config2;
    private JButton btnEstadisticas;
    private JButton btnEquipo;

    public InterfazMPrincipal(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles, FramePrincipal prin) {
        super("/imagenes/LogoPrincipal(2).jpg");

        this.config2 = config;
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

        ImageIcon iconoArena = AjustarImagen("/imagenes/Arena.jpg", 200, 300,0.5f);
        JButton btnArena = new JButton("Arena");
        btnArena.setIcon(iconoArena);
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

        ImageIcon iconoEstadisticas = AjustarImagen("/imagenes/btnEstadisticas.jpg", 200, 300,0.5f);
        btnEstadisticas = new JButton(config.getOrDefault("btnEstadisticas", "Estadísticas"));
        btnEstadisticas.setIcon(iconoEstadisticas);
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

        ImageIcon iconoEquipo = AjustarImagen("/imagenes/btnEquipo.jpg",200,300,0.5f);
        btnEquipo = new JButton(config.getOrDefault("btnEquipo", "Equipo"));
        btnEquipo.setIcon(iconoEquipo);
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

        ImageIcon iconoCerrar = AjustarImagen("/imagenes/btnCerrarSesion.jpg",200,300,0.5f);
        JButton btnCerrarSesion = new JButton(config.getOrDefault("btnCerrarSesion", "Cerrar sesión"));
        btnCerrarSesion.setIcon(iconoCerrar);
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
        btnCerrarSesion.addActionListener(new OyenteBtnCerrarSesion());
    }

    public ImageIcon AjustarImagen(String ruta, int ancho, int alto, float opacidad) {
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image imagen = icono.getImage();

        BufferedImage bufferedImage = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));
        g2d.drawImage(imagen, 0, 0, ancho, alto, null);
        g2d.dispose();

        ImageIcon iconoOpaco = new ImageIcon(bufferedImage);

        return iconoOpaco;
    }

    public void Oyente(ActionListener po) {
        btnEstadisticas.addActionListener(po);
    }

    public void OyenteEquipo(ActionListener po) {
        btnEquipo.addActionListener(po);
    }

    private class OyenteBtnCerrarSesion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new FrameInicio(config2);
            principal.dispose();
        }
    }

    private class OyenteBtnArena implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class OyenteBtnEquipo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
