package Controlador;

import Modelo.Equipo;
import Modelo.Puchamon;
import Vista.InterfazApuesta;
import Vista.InterfazArena;
import Vista.InterfazMPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ControladorArena {

    private CardLayout cardLayout;
    private Map<String, String> configuracion;
    private JPanel paneles;
    private JPanel panelPuchamonesElegidos;
    private String usuario;
    private JButton btnAtacar;
    private JButton btnCambiarP;
    private JButton btnSalirA;
    private int ancho = 150;
    private int alto = 75;
    private List<Puchamon> puchamonesJugador = new ArrayList<>();
    private List<Puchamon> puchamonesNpc = new ArrayList<>();
    private int apuesta2;
    private JProgressBar vidaPuJu;
    private JLabel nombrePuJu;
    private JProgressBar vidaPuNpc;
    private JLabel nombrePuNpc;

    public ControladorArena(InterfazArena vista, InterfazMPrincipal menu, String usuario, InterfazApuesta apuesta){

        this.cardLayout = vista.getCard();
        this.configuracion = vista.getConfiguracion();
        this.paneles = vista.getVariosPaneles();
        this.panelPuchamonesElegidos = vista.getPanelEquiposPuchamones();
        this.btnAtacar = vista.getBtnAtacar();
        this.btnCambiarP = vista.getBtnCambiarP();
        this.btnSalirA = vista.getBtnSalirA();
        this.usuario = usuario;
        this.vidaPuJu = vista.getVidaPuchamonJugador();
        this.nombrePuJu = vista.getLabelnombrePuchamonJugador();
        this.vidaPuNpc = vista.getVidaPuchamonNpc();
        this.nombrePuNpc = vista.getLabelnombrePuchamonNpc();

        menu.OyenteArena(new OyenteBtnArena());

        vista.OyenteMauseBtnAtacar(new OyenteMouseBtnAtacar());
        vista.OyenteMauseBtnCambiarP(new OyenteMouseBtnCambiarP());
        vista.OyenteMauseBtnSalirA(new OyenteMouseBtnSalirA());

        apuesta.getBtnContinuar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apuesta2 = (int) apuesta.getComboBox().getSelectedItem();
                Equipo qwee = new Equipo();
                qwee.GenerarRival(usuario);
                CargarImagenesBtn();
                apuesta2 = apuesta2 * 2;
                vista.getLabeltotalApuesta().setText(String.valueOf(apuesta2));
                agregarPaneles(usuario);
                cardLayout.show(paneles, "Arena");
            }
        });

        // Configurar acción para el botón "Cancelar"
        apuesta.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apuesta.getComboBox().setSelectedIndex(0);
                apuesta.getCardLayout().show(apuesta.getPaneles(), "Principal");
            }
        });
    }

    private ImageIcon AjustarImagen(String ruta, int ancho, int alto, float opacidad) {
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

    public void CargarImagenesBtn(){
        ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,0.5f);
        btnAtacar.setIcon(iconoArena);
        ImageIcon iconoEquipo = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,0.5f);
        btnCambiarP.setIcon(iconoEquipo);
        ImageIcon iconoEstadisticas = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,0.5f);
        btnSalirA.setIcon(iconoEstadisticas);


    }

    public void agregarPaneles(String jugador) {
        // Cargar la lista de Puchamones del jugador
        Equipo equipo = new Equipo();
        List<Puchamon> listaDatos = equipo.cargarEquipo();

        for (Puchamon pucha : listaDatos) {
            if (jugador.equals(pucha.getUsuario())) {
                puchamonesJugador.add(pucha);
            }
        }

        puchamonesNpc = equipo.GenerarRival(jugador);

        panelPuchamonesElegidos.removeAll();
        panelPuchamonesElegidos.setLayout(new GridBagLayout());
        GridBagConstraints gbcContenedor = new GridBagConstraints();
        gbcContenedor.insets = new Insets(10, 10, 10, 10);
        gbcContenedor.fill = GridBagConstraints.HORIZONTAL;
        gbcContenedor.anchor = GridBagConstraints.NORTH;

        // Añadir los paneles inicialmente
        añadirPaneles(puchamonesJugador, puchamonesNpc, panelPuchamonesElegidos, gbcContenedor);

        // Añadir un listener para redimensionar los paneles cuando el tamaño del JFrame cambie
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPuchamonesElegidos);
        if (frame != null) {
            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    añadirPaneles(puchamonesJugador, puchamonesNpc, panelPuchamonesElegidos, gbcContenedor);
                }
            });
        }
    }

    private void añadirPaneles(List<Puchamon> puchamonesJugador, List<Puchamon> puchamonesNpc, JPanel panelElegidos, GridBagConstraints gbcContenedor) {
        panelElegidos.removeAll();
        int panelWidth = panelElegidos.getWidth() /9; // Ajusta el ancho del panel
        int panelHeight = panelElegidos.getHeight() / 3; // Ajusta la altura del panel

        int x = 0;

        // Añadir los Puchamones del jugador
        for (int i = 0; i < puchamonesJugador.size(); i++) {
            Puchamon datos = puchamonesJugador.get(i);
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setOpaque(false);

            // Cambiar borde a azul si es el primer Puchamon
            if (i == 2) {
                panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            } else {
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(10, 10, 10, 10);

            String rutaImagen = datos.getRutaImagen();
            ImageIcon imagenIcono = new ImageIcon(rutaImagen);
            Image imagenEscalada = imagenIcono.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
            JLabel imagenLabel = new JLabel(iconoEscalado);

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(imagenLabel, gbc);

            gbcContenedor.gridx = x++;
            gbcContenedor.gridy = 0;
            gbcContenedor.weightx = 1.0; // Se asegura de que los paneles se expandan proporcionalmente
            gbcContenedor.weighty = 1.0;
            gbcContenedor.fill = GridBagConstraints.BOTH; // Los paneles deben ocupar todo el espacio disponible
            panelElegidos.add(panel, gbcContenedor);
        }

        // Añadir los Puchamones del NPC
        for (int i = 0; i < puchamonesNpc.size(); i++) {
            Puchamon datos = puchamonesNpc.get(i);
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setOpaque(false);

            // Cambiar borde a azul si es el primer Puchamon
            if (i == 0) {
                panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            } else {
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            }

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(10, 10, 10, 10);

            String rutaImagen = datos.getRutaImagen();
            ImageIcon imagenIcono = new ImageIcon(rutaImagen);
            Image imagenEscalada = imagenIcono.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
            JLabel imagenLabel = new JLabel(iconoEscalado);

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(imagenLabel, gbc);

            gbcContenedor.gridx = x++;
            gbcContenedor.gridy = 1; // Cambiar la fila para los Puchamones del NPC
            gbcContenedor.weightx = 1.0; // Se asegura de que los paneles se expandan proporcionalmente
            gbcContenedor.weighty = 1.0;
            gbcContenedor.fill = GridBagConstraints.BOTH; // Los paneles deben ocupar todo el espacio disponible
            panelElegidos.add(panel, gbcContenedor);
        }

        // Cargar la vida y los nombres de los Puchamones en la posición 0
        if (!puchamonesJugador.isEmpty() && !puchamonesNpc.isEmpty()) {
            vidaPuJu.setForeground(Color.GREEN);
            vidaPuNpc.setForeground(Color.GREEN);
            // Suponiendo que tienes getters para estas barras de progreso y labels
            vidaPuJu.setValue(puchamonesJugador.get(2).getVida());
            vidaPuNpc.setValue(puchamonesNpc.get(0).getVida());
            nombrePuJu.setText(puchamonesJugador.get(2).getNombre());
            nombrePuNpc.setText(puchamonesNpc.get(0).getNombre());
        }

        panelElegidos.revalidate();
        panelElegidos.repaint();
    }


    public class OyenteMouseBtnAtacar implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,1f);
            btnAtacar.setIcon(iconoArena);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,0.5f);
            btnAtacar.setIcon(iconoArena);
        }
    }
    public class OyenteMouseBtnCambiarP implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,1f);
            btnCambiarP.setIcon(iconoArena);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,0.5f);
            btnCambiarP.setIcon(iconoArena);
        }
    }
    public class OyenteMouseBtnSalirA implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,1f);
            btnSalirA.setIcon(iconoArena);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto,0.5f);
            btnSalirA.setIcon(iconoArena);
        }
    }

    public class OyenteBtnArena implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(paneles,"Apuesta");
        }
    }
}
