package Controlador;

import Modelo.Equipo;
import Modelo.Puchamon;
import Vista.InterfazEquipo;
import Vista.InterfazMPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorEquipo {
    private CardLayout card;
    private JPanel panel;
    private JPanel panelElegidos;
    private String nombrePuchamonSeleccionado;
    private JPanel panelUltimaSeleccionada;
    private String jugador;

    public ControladorEquipo(){
        this.card = new CardLayout(); // Inicializa card con una nueva instancia de CardLayout
        this.panel = new JPanel(card); // Inicializa panel con una nueva instancia de JPanel utilizando el CardLayout
        this.panelElegidos = new JPanel(); // Inicializa panelElegidos con una nueva instancia de JPanel
        this.nombrePuchamonSeleccionado = ""; // Inicializa nombrePuchamonSeleccionado con una cadena vacía
        this.panelUltimaSeleccionada = new JPanel(); // Inicializa panelUltimaSeleccionada con una nueva instancia de JPanel
    }

    public  ControladorEquipo(InterfazEquipo vista, InterfazMPrincipal menu, String usuario){
        this.card = vista.getCardLayout();
        this.panel = vista.getPaneles();
        this.panelElegidos = vista.getPanelElegidos();
        this.jugador = usuario;

        vista.OyenteBtnAtras(new OyenteAtras());
        menu.OyenteEquipo(new OyenteEquipo());
    }

    public void agregarPaneles(String jugador) {
        // Suponiendo que tienes una lista o array de objetos con los datos que necesitas
        Equipo equi = new Equipo();
        List<Puchamon> listaDatos = equi.cargarEquipo();
        List<Puchamon> puchamonesJugador = new ArrayList<>();

        for (Puchamon pucha : listaDatos) {
            if (jugador.equals(pucha.getUsuario())) {
                puchamonesJugador.add(pucha);
            }
        }

        panelElegidos.removeAll();
        panelElegidos.setLayout(new GridBagLayout()); // Usa GridBagLayout para el panel contenedor
        GridBagConstraints gbcContenedor = new GridBagConstraints();
        gbcContenedor.insets = new Insets(10, 10, 10, 10);
        gbcContenedor.fill = GridBagConstraints.HORIZONTAL;
        gbcContenedor.anchor = GridBagConstraints.NORTH;

        // Añadir los paneles inicialmente
        añadirPaneles(puchamonesJugador, panelElegidos, gbcContenedor);

        // Añadir un listener para redimensionar los paneles cuando el tamaño del JFrame cambie
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelElegidos);
        if (frame != null) {
            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    SwingUtilities.invokeLater(() -> {
                        añadirPaneles(puchamonesJugador, panelElegidos, gbcContenedor);
                    });
                }
            });
        }
    }

    private void añadirPaneles(List<Puchamon> puchamonesJugador, JPanel panelElegidos, GridBagConstraints gbcContenedor) {
        panelElegidos.removeAll();
        int panelWidth = panelElegidos.getWidth() / 9; // Ajusta el ancho del panel
        int panelHeight = panelElegidos.getHeight() / 3; // Ajusta la altura del panel

        int x = 0;

        for (Puchamon datos : puchamonesJugador) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));

            panel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    if (panelUltimaSeleccionada != null) {
                        panelUltimaSeleccionada.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
                    }
                    panel.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
                    panelUltimaSeleccionada = panel;

                    // Guardar el nombre del Puchamon seleccionado
                    nombrePuchamonSeleccionado = datos.getNombre();
                }
            });

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH; // Cambiado a BOTH para que el componente ocupe todo el espacio disponible
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(10, 10, 10, 10);

            Color colorLetras = new Color(253, 157, 43, 255);
            Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);

            JLabel nombreLabel = new JLabel("Nombre: " + datos.getNombre());
            nombreLabel.setForeground(colorLetras);
            nombreLabel.setFont(fuenteInicio);
            JLabel tipoLabel = new JLabel("Tipo: " + datos.getTipo());
            tipoLabel.setForeground(colorLetras);
            tipoLabel.setFont(fuenteInicio);
            JLabel nivelLabel = new JLabel("Nivel: " + datos.getNivel());
            nivelLabel.setForeground(colorLetras);
            nivelLabel.setFont(fuenteInicio);
            JLabel vidaLabel = new JLabel("Vida: " + datos.getVida());
            vidaLabel.setForeground(colorLetras);
            vidaLabel.setFont(fuenteInicio);
            JLabel ataqueLabel = new JLabel("Ataque: " + datos.getAtaque());
            ataqueLabel.setForeground(colorLetras);
            ataqueLabel.setFont(fuenteInicio);
            JLabel defensaLabel = new JLabel("Defensa: " + datos.getDefensa());
            defensaLabel.setForeground(colorLetras);
            defensaLabel.setFont(fuenteInicio);
            JLabel experienciaLabel = new JLabel("Experiencia: " + datos.getExperiencia());
            experienciaLabel.setForeground(colorLetras);
            experienciaLabel.setFont(fuenteInicio);

            String rutaImagen = datos.getRutaImagen();
            ImageIcon imagenIcono = new ImageIcon(rutaImagen);
            Image imagenEscalada = imagenIcono.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
            JLabel imagenLabel = new JLabel(iconoEscalado);

            // Añadir los componentes al panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(imagenLabel, gbc);
            gbc.gridy = 1;
            panel.add(nombreLabel, gbc);
            gbc.gridy = 2;
            panel.add(tipoLabel, gbc);
            gbc.gridy = 3;
            panel.add(nivelLabel, gbc);
            gbc.gridy = 4;
            panel.add(vidaLabel, gbc);
            gbc.gridy = 5;
            panel.add(ataqueLabel, gbc);
            gbc.gridy = 6;
            panel.add(defensaLabel, gbc);
            gbc.gridy = 7;
            panel.add(experienciaLabel, gbc);

            gbcContenedor.gridx = x++;
            gbcContenedor.gridy = 0;
            gbcContenedor.weightx = 1.0; // Se asegura de que los paneles se expandan proporcionalmente
            gbcContenedor.weighty = 1.0;
            gbcContenedor.fill = GridBagConstraints.BOTH; // Los paneles deben ocupar todo el espacio disponible
            panelElegidos.add(panel, gbcContenedor);
        }

        panelElegidos.revalidate();
        panelElegidos.repaint();
    }


    public class OyenteAtras implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(panel, "Principal");
        }
    }

    public class OyenteEquipo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            agregarPaneles(jugador);
            card.show(panel, "Equipo");
        }
    }
}
