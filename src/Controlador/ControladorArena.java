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
import java.util.Random;

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
    private List<Integer> vidasMaximasPu = new ArrayList<>();
    private int apuesta2;
    private JProgressBar vidaPuJu;
    private JLabel nombrePuJu;
    private JProgressBar vidaPuNpc;
    private JLabel nombrePuNpc;
    private JLabel labelTiempoTurno;
    private int puchamonSeleccionadoNpc;
    private int puchamonSeleccionadoJugador;
    private Timer timer;
    private int tiempoRestante = 30;
    private boolean esTurnoJugador = true; // Comienza con el turno del jugador
    private JFrame seleccionarPuchamonFrame = new JFrame("Seleccionar Puchamon");
    private int ataquesFallidosConsecutivosJugador = 0;
    private int ataquesFallidosConsecutivosNpc = 0;

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
        this.labelTiempoTurno = vista.getLabeltiempoJugada();

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
                iniciarTimer();
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

    private void iniciarTimer() {
        // Instanciar la clase Random para generar un valor aleatorio
        Random random = new Random();

        // Generar un valor booleano aleatorio para determinar quién empieza
        esTurnoJugador = random.nextBoolean();

        // Actualizar la etiqueta del turno basado en quién empieza
        if (esTurnoJugador) {
            JPanel panelJugador = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoJugador);
            panelJugador.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

            JPanel panelNpc = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoNpc + 3);
            panelNpc.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

        } else {

            JPanel panelNpc = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoNpc + 3);
            panelNpc.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

            JPanel panelJugador = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoJugador);
            panelJugador.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

            realizarAtaqueNpc(); // Inicia el ataque del NPC
        }

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                    labelTiempoTurno.setText("Tiempo: " + tiempoRestante + "s");
                } else {
                    cambiarTurno();
                }
            }
        });
        timer.start();
    }

    private void cambiarTurno() {
        Random random = new Random();
        esTurnoJugador = !esTurnoJugador;

        // Probabilidad del 30% de que el turno se repita
        if (random.nextInt(100) < 30) {
            // Si se repite el turno, revertimos el cambio de turno
            esTurnoJugador = !esTurnoJugador;
        }

        tiempoRestante = 30; // Reiniciar el tiempo
        labelTiempoTurno.setText("Tiempo: " + tiempoRestante + "s");

        // Panel del NPC por defecto
        JPanel panelNpcDefault = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoNpc + 3);
        panelNpcDefault.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

        // Panel del jugador por defecto
        JPanel panelJugador = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoJugador);
        panelJugador.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

        if (!esTurnoJugador) {
            // Generar un tiempo de espera aleatorio entre 1 y 10 segundos
            int tiempoEspera = (random.nextInt(10) + 1) * 1000; // Convertir a milisegundos

            // Panel del jugador por defecto
            JPanel panelJugadorDefault = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoJugador);
            panelJugadorDefault.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

            // Panel del NPC en turno
            JPanel panelNpc = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoNpc + 3);
            panelNpc.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

            // Iniciar el temporizador para el ataque del NPC
            Timer timer = new Timer(tiempoEspera, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizarAtaqueNpc();
                }
            });

            // Asegurarse de que el temporizador solo se dispare una vez
            timer.setRepeats(false);
            timer.start();
        }
    }


    private void realizarAtaqueNpc() {
        Puchamon puchamonJugador = puchamonesJugador.get(puchamonSeleccionadoJugador);
        Puchamon puchamonNpc = puchamonesNpc.get(puchamonSeleccionadoNpc);

        int ataque = puchamonNpc.getAtaque();
        int defensa = puchamonJugador.getDefensa();

        // Calcula el daño potencial
        int damage = ataque - defensa;

        // Verifica si el daño es menor o igual a cero
        if (damage <= 0) {
            damage = 0;
            ataquesFallidosConsecutivosNpc++;
        } else {
            // Si el daño es positivo, reinicia el contador de ataques fallidos del NPC
            ataquesFallidosConsecutivosNpc = 0;
        }

        // Si hay dos ataques fallidos consecutivos, duplica el daño en el tercer ataque
        if (ataquesFallidosConsecutivosNpc == 2) {
            ataque = ataque * 2;
            damage = ataque - defensa;
            ataquesFallidosConsecutivosNpc = 0; // Reinicia el contador después del ataque potenciado
        }

        // Calcula la nueva vida del jugador asegurándose de que no sea negativa
        int nuevaVidaJugador = Math.max(0, puchamonJugador.getVida() - damage);
        puchamonJugador.setVida(nuevaVidaJugador);
        vidaPuJu.setValue(nuevaVidaJugador);

        // Actualiza el estado del Puchamon del jugador en la lista
        puchamonesJugador.set(puchamonSeleccionadoJugador, puchamonJugador);

        // Cambia el turno
        cambiarTurno();
    }

    private void realizarAtaqueJugador() {
        // Instancia de la clase Random para generar números aleatorios
        Random random = new Random();

        Puchamon puchamonJugador = puchamonesJugador.get(puchamonSeleccionadoJugador);
        Puchamon puchamonNpc = puchamonesNpc.get(puchamonSeleccionadoNpc);

        int ataque = puchamonJugador.getAtaque();
        int defensa = puchamonNpc.getDefensa();

        // Calcula el daño potencial
        int damage = ataque - defensa;

        // Verifica si el daño es menor o igual a cero
        if (damage <= 0) {
            damage = 0;
            ataquesFallidosConsecutivosJugador++;
        } else {
            // Si el daño es positivo, reinicia el contador de ataques fallidos del jugador
            ataquesFallidosConsecutivosJugador = 0;
        }

        // Si hay dos ataques fallidos consecutivos, duplica el daño en el tercer ataque
        if (ataquesFallidosConsecutivosJugador == 2) {
            ataque = ataque * 2;
            damage = ataque - defensa;
            ataquesFallidosConsecutivosJugador = 0; // Reinicia el contador después del ataque potenciado
        }

        // Genera un número aleatorio entre 0 y 99
        int probabilidad = random.nextInt(100);

        // Verifica si la probabilidad es menor al 40% (40 de 100)
        if (probabilidad < 40) {
            damage = damage * 2; // Duplica el daño
        }

        // Calcula la nueva vida del NPC asegurándose de que no sea negativa
        int nuevaVidaNpc = Math.max(0, puchamonNpc.getVida() - damage);
        puchamonNpc.setVida(nuevaVidaNpc);
        vidaPuNpc.setValue(nuevaVidaNpc);

        // Actualiza el estado del Puchamon del NPC en la lista
        puchamonesNpc.set(puchamonSeleccionadoNpc, puchamonNpc);

        // Verifica si el Puchamon del NPC ha sido derrotado
        if (puchamonesNpc.get(puchamonSeleccionadoNpc).getVida() == 0) {
            CambiarPuchamonNpc();
        }

        // Cambia el turno
        cambiarTurno();
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
                vidasMaximasPu.add(pucha.getVida());
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

        puchamonSeleccionadoJugador = 0;

        for (int i = 0; i < puchamonesJugador.size(); i++) {
            Puchamon datos = puchamonesJugador.get(i);
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setOpaque(false);

            // Cambiar borde a azul si es el primer Puchamon
            if (i == puchamonSeleccionadoJugador) {
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

        puchamonSeleccionadoNpc = 0;

        // Añadir los Puchamones del NPC
        for (int i = 0; i < puchamonesNpc.size(); i++) {
            Puchamon datos = puchamonesNpc.get(i);
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setOpaque(false);

            // Cambiar borde a azul si es el primer Puchamon
            if (i == puchamonSeleccionadoNpc) {
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
            vidaPuJu.setMaximum(puchamonesJugador.get(puchamonSeleccionadoJugador).getVida());
            vidaPuJu.setValue(puchamonesJugador.get(puchamonSeleccionadoJugador).getVida());
            vidaPuNpc.setMaximum(puchamonesNpc.get(puchamonSeleccionadoJugador).getVida());
            vidaPuNpc.setValue(puchamonesNpc.get(puchamonSeleccionadoNpc).getVida());
            nombrePuJu.setText(puchamonesJugador.get(puchamonSeleccionadoJugador).getNombre());
            nombrePuNpc.setText(puchamonesNpc.get(puchamonSeleccionadoNpc).getNombre());
        }

        panelElegidos.revalidate();
        panelElegidos.repaint();
    }


    // Oyente modificado para el botón "Atacar"
    public class OyenteMouseBtnAtacar implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            realizarAtaqueJugador();
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

    public class OyenteMouseBtnCambiarP implements MouseListener {

        private boolean isWindowOpen = false; // Bandera para verificar si la ventana está abierta

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            // Si la ventana está abierta, no hacer nada
            if (isWindowOpen) {
                return;
            }
            // Si no está abierta, la abrimos
            CambioPuchamon();
            cambiarTurno();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto, 1f);
            btnCambiarP.setIcon(iconoArena);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/panelBtnArena.png", ancho, alto, 0.5f);
            btnCambiarP.setIcon(iconoArena);
        }

        private void CambioPuchamon(){
            seleccionarPuchamonFrame.getContentPane().removeAll();
            isWindowOpen = true; // Actualizamos la bandera a true porque la ventana está a punto de abrirse

            // Crear el nuevo JFrame para seleccionar Puchamon
            seleccionarPuchamonFrame.setLayout(new GridLayout(3, 1));
            seleccionarPuchamonFrame.setSize(300, 200);
            seleccionarPuchamonFrame.setUndecorated(true);

            // Añadir un WindowListener para cambiar la bandera al cerrar la ventana
            seleccionarPuchamonFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    isWindowOpen = false; // Actualizamos la bandera a false porque la ventana se está cerrando
                }

                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    isWindowOpen = false; // Actualizamos la bandera a false porque la ventana está cerrada
                }
            });

            // Crear botones con los nombres de los Puchamones del jugador
            for (int i = 0; i < puchamonesJugador.size(); i++) {
                JButton btnPuchamon = getjButton(i, seleccionarPuchamonFrame);
                seleccionarPuchamonFrame.add(btnPuchamon);
            }
            seleccionarPuchamonFrame.setVisible(true);
        }

        private JButton getjButton(int i, JFrame seleccionarPuchamonFrame) {
            final int indicePuchamon = i; // Necesario para referenciar dentro del ActionListener
            Puchamon puchamon = puchamonesJugador.get(i);
            JButton btnPuchamon = new JButton(puchamon.getNombre());
            btnPuchamon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Actualizar el puchamonSeleccionadoJugador y cerrar el JFrame
                    JPanel panelJugador = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoJugador);
                    panelJugador.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                    puchamonSeleccionadoJugador = indicePuchamon;
                    actualizarPanelesJugador();
                    seleccionarPuchamonFrame.dispose();
                    isWindowOpen = false; // Actualizamos la bandera a false porque la ventana se está cerrando
                }
            });
            return btnPuchamon;
        }
    }


    private void actualizarPanelesJugador() {
        // Actualiza el borde del Puchamon seleccionado
        JPanel panelJugador = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoJugador);
        panelJugador.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

        // Actualiza la vida y el nombre en la interfaz
        Puchamon puchamon = puchamonesJugador.get(puchamonSeleccionadoJugador);
        vidaPuJu.setMaximum(vidasMaximasPu.get(puchamonSeleccionadoJugador));
        vidaPuJu.setValue(puchamon.getVida());
        nombrePuJu.setText(puchamon.getNombre());

        panelPuchamonesElegidos.revalidate();
        panelPuchamonesElegidos.repaint();
    }

    private void CambiarPuchamonNpc() {
        // Actualiza el borde del Puchamon seleccionado
        Random random = new Random();
        int[] numerosPermitidos = {0, 1, 2};

        JPanel panelNpcAnterior = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoNpc + 3);
        panelNpcAnterior.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        do {
            puchamonSeleccionadoNpc = random.nextInt(numerosPermitidos.length);
        } while (puchamonesNpc.get(puchamonSeleccionadoNpc).getVida() == 0);

        JPanel panelNpc = (JPanel) panelPuchamonesElegidos.getComponent(puchamonSeleccionadoNpc + 3);
        panelNpc.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

        // Actualiza la vida y el nombre en la interfaz
        Puchamon puchamon = puchamonesNpc.get(puchamonSeleccionadoNpc);
        vidaPuNpc.setMaximum(vidasMaximasPu.get(puchamonSeleccionadoNpc));
        vidaPuNpc.setValue(puchamon.getVida());
        nombrePuNpc.setText(puchamon.getNombre());

        panelPuchamonesElegidos.revalidate();
        panelPuchamonesElegidos.repaint();
    }

}
