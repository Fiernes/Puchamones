package Modelo;

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

public class Combate {

    private CardLayout cardLayout;
    private Map<String, String> configuracion;
    private JPanel paneles;
    private JPanel panelPuchamonesElegidos;
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
    private boolean isWindowOpen = false; // Bandera para verificar si la ventana está abierta
    private String jugador;
    private boolean juegoTerminado;

    public Combate(InterfazArena vista, InterfazMPrincipal menu, String usuario, InterfazApuesta apuesta){

        this.cardLayout = vista.getCard();
        this.configuracion = vista.getConfiguracion();
        this.paneles = vista.getVariosPaneles();
        this.panelPuchamonesElegidos = vista.getPanelEquiposPuchamones();
        this.btnAtacar = vista.getBtnAtacar();
        this.btnCambiarP = vista.getBtnCambiarP();
        this.btnSalirA = vista.getBtnSalirA();
        this.vidaPuJu = vista.getVidaPuchamonJugador();
        this.nombrePuJu = vista.getLabelnombrePuchamonJugador();
        this.vidaPuNpc = vista.getVidaPuchamonNpc();
        this.nombrePuNpc = vista.getLabelnombrePuchamonNpc();
        this.labelTiempoTurno = vista.getLabeltiempoJugada();
        this.jugador = usuario;

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
                IniciarCombate();
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

    private void CalcularGanador(List<Puchamon> equipoJugador, List<Puchamon> equipoNpc, String usuario) {
        // Inicializar variables para la vida total de cada equipo
        int vidaTotalJugador = 0;
        int vidaTotalNpc = 0;
        int puchamonesVivosJugador = 0;
        int oro = apuesta2 / 2;

        // Sumar la vida de todos los Puchamones del equipo del jugador
        for (Puchamon pucha : equipoJugador) {
            vidaTotalJugador += pucha.getVida();
            if (pucha.getVida() > 0) {
                puchamonesVivosJugador++;
            }
        }

        // Sumar la vida de todos los Puchamones del equipo del NPC
        for (Puchamon pucha : equipoNpc) {
            vidaTotalNpc += pucha.getVida();
        }

        // Comparar la vida total de los dos equipos
        if (vidaTotalNpc == 0) {
            // El jugador gana
            detenerTimer();
            actualizarEstadisticas(usuario, oro, true, puchamonesVivosJugador > 1);
            actualizarJugador(usuario, oro, true);
            JOptionPane.showMessageDialog(panelPuchamonesElegidos, "¡Felicidades, has ganado!");
            juegoTerminado = true;
            cardLayout.show(paneles, "Principal");
        } else if (vidaTotalJugador == 0) {
            // El NPC gana
            detenerTimer();
            actualizarEstadisticas(usuario, oro, false, false);
            actualizarJugador(usuario, oro, false);
            JOptionPane.showMessageDialog(panelPuchamonesElegidos, "¡El equipo del NPC es el ganador!");
            juegoTerminado = true;
            cardLayout.show(paneles, "Principal");
        }
    }

    private void detenerTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    /**
     * Actualiza las estadísticas del jugador.
     *
     * @param usuario                El nombre de usuario del jugador.
     * @param oro                    La cantidad de oro a sumar o restar.
     * @param esGanador              Indica si el jugador ganó o no.
     * @param ganoConDosOMasPuchamones Indica si el jugador ganó con dos o más Puchamones vivos.
     */
    private void actualizarEstadisticas(String usuario, int oro, boolean esGanador, boolean ganoConDosOMasPuchamones) {
        EstadisticasJugador estadisticas = new EstadisticasJugador();
        Sistema sistema = new Sistema();
        sistema.cargarEstadisticas();
        List<EstadisticasJugador> estadisticasTemp = sistema.getEstadisticas();

        for (EstadisticasJugador estasTemp : estadisticasTemp) {
            if (usuario.equals(estasTemp.getNombreJugador())) {
                estadisticas.setNombreJugador(estasTemp.getNombreJugador());
                estadisticas.setPuchamonesCreados(estasTemp.getPuchamonesCreados());
                estadisticas.setPuchamonesEliminados(estasTemp.getPuchamonesEliminados());
                estadisticas.setBatallasEnArena(estasTemp.getBatallasEnArena() + 1);
                estadisticas.setBatallasGanadas(estasTemp.getBatallasGanadas() + (esGanador ? 1 : 0));
                estadisticas.setBatallasPerdidas(estasTemp.getBatallasPerdidas() + (esGanador ? 0 : 1));
                estadisticas.setDineroGanado(estasTemp.getDineroGanado() + (esGanador ? oro : 0));
                estadisticas.setDineroPerdido(estasTemp.getDineroPerdido() + (esGanador ? 0 : oro));
                if (ganoConDosOMasPuchamones) {
                    estadisticas.setBatallasDosOMasPV(estasTemp.getBatallasDosOMasPV() + 1);
                } else {
                    estadisticas.setBatallasDosOMasPV(estasTemp.getBatallasDosOMasPV());
                }
                estadisticas.ModificarEstadisticas();
                break;
            }
        }
    }

    /**
     * Actualiza el oro y el nivel del jugador.
     *
     * @param usuario El nombre de usuario del jugador.
     * @param oro     La cantidad de oro a sumar o restar.
     * @param esGanador Indica si el jugador ganó o no.
     */
    private void actualizarJugador(String usuario, int oro, boolean esGanador) {
        Jugador jugador = new Jugador();
        Sistema sistema = new Sistema();
        sistema.cargarJugadores();
        List<Jugador> datosJugadores = sistema.getJugadores();

        for (Jugador jugadores : datosJugadores) {
            if (usuario.equals(jugadores.getNombreUsuario())) {
                int oroActualizado = jugadores.getOro() + (esGanador ? oro : -oro);
                jugador.ModificarOroyNivel(usuario, oroActualizado, jugadores.getNivelJu());
                break;
            }
        }
    }

    private void IniciarCombate() {
        // Instanciar la clase Random para generar un valor aleatorio
        Random random = new Random();
        juegoTerminado = false;

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

        Puchamon puchamon = new Puchamon();
        int nuevaVidaJugador = puchamon.atacarNpc(puchamonNpc, puchamonJugador, ataquesFallidosConsecutivosNpc);

        if (puchamonJugador.getVida() == nuevaVidaJugador){
            ataquesFallidosConsecutivosNpc++;
        }else{
            ataquesFallidosConsecutivosNpc = 0;
        }

        puchamonJugador.setVida(nuevaVidaJugador);
        vidaPuJu.setValue(nuevaVidaJugador);

        // Actualiza el estado del Puchamon del jugador en la lista
        puchamonesJugador.set(puchamonSeleccionadoJugador, puchamonJugador);

        if (puchamonesJugador.get(puchamonSeleccionadoJugador).getVida() == 0) {
            CalcularGanador(puchamonesJugador, puchamonesNpc, jugador);
            if (!juegoTerminado) {
                CambioPuchamon();
            }
        }

        if (!juegoTerminado) {
            cambiarTurno();
        }
    }

    private void realizarAtaqueJugador() {

        Puchamon puchamonJugador = puchamonesJugador.get(puchamonSeleccionadoJugador);
        Puchamon puchamonNpc = puchamonesNpc.get(puchamonSeleccionadoNpc);

        Puchamon puchamon = new Puchamon();
        int nuevaVidaNpc = puchamon.atacarJugador(puchamonNpc, puchamonJugador, ataquesFallidosConsecutivosJugador);

        if (puchamonNpc.getVida() == nuevaVidaNpc){
            ataquesFallidosConsecutivosJugador++;
        }else{
            ataquesFallidosConsecutivosJugador = 0;
        }

        puchamonNpc.setVida(nuevaVidaNpc);
        vidaPuNpc.setValue(nuevaVidaNpc);

        // Actualiza el estado del Puchamon del NPC en la lista
        puchamonesNpc.set(puchamonSeleccionadoNpc, puchamonNpc);

        // Verifica si el Puchamon del NPC ha sido derrotado
        if (puchamonesNpc.get(puchamonSeleccionadoNpc).getVida() == 0) {
            String nombre = puchamonJugador.getNombre();
            int vida = puchamonJugador.getVida();
            int mitadVida = (int) Math.round(vida / 2.0);
            int experienciaTemp = puchamonJugador.getExperiencia();
            experienciaTemp = experienciaTemp + mitadVida;
            puchamon.ganarExperiencia(nombre, experienciaTemp);
            RevisarNivel(jugador, nombre);

            CalcularGanador(puchamonesJugador, puchamonesNpc, jugador);
            if (!juegoTerminado){
            CambiarPuchamonNpc();
            }
        }

        if (!juegoTerminado) {
            cambiarTurno();
        }
    }

    private void RevisarNivel(String jugador, String nombrePuchamon){
        Equipo equipo = new Equipo();
        Puchamon puchamon = new Puchamon();
        Jugador ju = new Jugador();
        Sistema sis = new Sistema();
        sis.cargarJugadores();
        List<Jugador> jugadores = sis.getJugadores();
        List<Puchamon> listaDatos = equipo.cargarEquipo();

        for (Puchamon pucha : listaDatos) {
            if (jugador.equals(pucha.getUsuario())) {
                if (nombrePuchamon.equals(pucha.getNombre())) {
                    if (pucha.getExperiencia() >= 100) {
                        Random random = new Random();
                        int nuevoAtaque = 3 + random.nextInt(3);
                        int nuevaDefensa = 3 + random.nextInt(3);
                        int experienciaTemp = pucha.getExperiencia() - 100;
                        int nivelTemp = pucha.getNivel() + 1;
                        int ataqueTemp = pucha.getAtaque() + nuevoAtaque;
                        int defensaTemp = pucha.getDefensa() + nuevaDefensa;

                        puchamon.subirNivel(nombrePuchamon, nivelTemp, ataqueTemp, defensaTemp);
                        puchamon.ganarExperiencia(nombrePuchamon, experienciaTemp);

                        for (Jugador juga : jugadores){
                            if (juga.getNombreUsuario().equals(jugador)){
                                int oroTemp = juga.getOro();
                                oroTemp = oroTemp + 2000;
                                int nivelTempJu = juga.getNivelJu();
                                nivelTempJu = nivelTempJu + 1;
                                ju.ModificarOroyNivel(jugador, oroTemp, nivelTempJu);
                            }
                        }

                    }
                }
            }
        }
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
        MostrarEstadoCombate(puchamonesJugador, puchamonesNpc, panelPuchamonesElegidos, gbcContenedor);

        // Añadir un listener para redimensionar los paneles cuando el tamaño del JFrame cambie
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPuchamonesElegidos);
        if (frame != null) {
            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    MostrarEstadoCombate(puchamonesJugador, puchamonesNpc, panelPuchamonesElegidos, gbcContenedor);
                }
            });
        }
    }

    private void MostrarEstadoCombate(List<Puchamon> puchamonesJugador, List<Puchamon> puchamonesNpc, JPanel panelElegidos, GridBagConstraints gbcContenedor) {
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
            if (esTurnoJugador){
                realizarAtaqueJugador();
            }
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
            if (esTurnoJugador){
                CambioPuchamon();
                cambiarTurno();
            }
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
    }

    private void CambioPuchamon(){
        seleccionarPuchamonFrame.getContentPane().removeAll();
        isWindowOpen = true; // Actualizamos la bandera a true porque la ventana está a punto de abrirse

        // Crear el nuevo JFrame para seleccionar Puchamon
        seleccionarPuchamonFrame.setLayout(new GridLayout(3, 1));
        seleccionarPuchamonFrame.setSize(300, 200);
        seleccionarPuchamonFrame.setUndecorated(true);
        seleccionarPuchamonFrame.setLocationRelativeTo(panelPuchamonesElegidos);

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
                cambiarTurno();
                seleccionarPuchamonFrame.dispose();
                isWindowOpen = false; // Actualizamos la bandera a false porque la ventana se está cerrando
            }
        });
        return btnPuchamon;
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
