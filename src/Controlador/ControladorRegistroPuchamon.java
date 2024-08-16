package Controlador;

import Modelo.Equipo;
import Modelo.EstadisticasJugador;
import Modelo.Puchamon;
import Modelo.Sistema;
import Vista.InterfazEquipo;
import Vista.InterfazRegistroPuchamon;
import Vista.InterfazMPrincipal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ControladorRegistroPuchamon {

    private CardLayout card;
    private JPanel panel;
    private JTextField txtNombreP;
    private JLabel labelultimaSeleccionada;
    private JPanel panelImagenes;
    private String usuario;
    private String rutaImagen;
    private JComboBox<String> tipo;
    private InterfazEquipo interfazEquipo;
    private InterfazMPrincipal menu;

    public ControladorRegistroPuchamon(InterfazRegistroPuchamon puchamon, InterfazEquipo equipo, InterfazMPrincipal menu, String usuario){
        this.card = puchamon.getCardLayout();
        this.panel = puchamon.getMostrarPaneles();
        this.txtNombreP = puchamon.getTxtNombreP();
        this.labelultimaSeleccionada = puchamon.getUltimaSeleccionada();
        this.panelImagenes = puchamon.getPanelImagenes();
        this.usuario = usuario;
        this.tipo = puchamon.getTipoPuchamon();
        this.interfazEquipo = equipo;
        this.menu = menu;

        puchamon.OyenteAtras(new OyenteAtras2());
        puchamon.OyenteJComboBox(new OyenteTipo());
        puchamon.OyenteCrear(new OyenteAgregarP());
        equipo.OyenteBtnAgregarP(new OyenteRegistrar());

    }

    public void PanelImagenes(String ruta, String criterio) {
        File imagesDir = new File(ruta);
        if (imagesDir.isDirectory()) {
            panelImagenes.removeAll();
            for (File file : imagesDir.listFiles()) {
                if (file.isFile() && isImageFile(file)) {
                    String fileName = file.getName().toLowerCase(); // Convertimos el nombre a minúsculas para evitar problemas con mayúsculas y minúsculas
                    if (fileName.contains(criterio.toLowerCase())) { // Verificamos si el nombre contiene el criterio
                        try {
                            BufferedImage img = ImageIO.read(file);
                            ImageIcon icon = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                            JLabel imageLabel = new JLabel(icon);
                            imageLabel.setOpaque(false);
                            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            imageLabel.addMouseListener(new MouseAdapter() {
                                public void mouseReleased(MouseEvent evt) {

                                    if (labelultimaSeleccionada != null) {
                                        labelultimaSeleccionada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                    }

                                    imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                                    labelultimaSeleccionada = imageLabel;

                                    rutaImagen = file.getPath();
                                }
                            });
                            panelImagenes.add(imageLabel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            panelImagenes.revalidate();
            panelImagenes.repaint();
        }
    }

    private boolean isImageFile(File file) {
        String[] validExtensions = {"jpg", "jpeg", "png", "gif"};
        String fileName = file.getName().toLowerCase();
        for (String ext : validExtensions) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private void CrearPuchamon(){
        int maxPuchamones = 1;
        Equipo equi = new Equipo();
        List<Puchamon> listaDatos =  equi.cargarEquipo();

        for (Puchamon pucha : listaDatos){
            if (usuario.equals(pucha.getUsuario())){
                maxPuchamones++;
            }
        }

        if (maxPuchamones > 3){
            JOptionPane.showMessageDialog(null, "A alcanzado el maximo de puchamones");
        }else {
            Puchamon pu = new Puchamon();
            Equipo e = new Equipo();
            String nombre = txtNombreP.getText();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre del Puchamon no puede estar vacío.");
                return;
            }
            Random rand = new Random();
            int ataqueAleatorio = rand.nextInt(51) + 50; // Genera un valor entre 50 y 100
            int defensaAleatoria = rand.nextInt(51) + 50; // Genera un valor entre 50 y 100

            pu.setNombre(nombre);
            pu.setUsuario(usuario);
            pu.setTipo(tipo.getSelectedItem().toString());
            pu.setNivel(1);
            pu.setVida(250);
            pu.setAtaque(ataqueAleatorio);
            pu.setDefensa(defensaAleatoria);
            pu.setExperiencia(0);
            pu.setRutaImagen(rutaImagen);
            e.agregarPuchamon(pu);
            txtNombreP.setText("");
            JOptionPane.showMessageDialog(null, "Puchamon creado con exito");
        }
    }

    private void actualizarEstadisticas() {
        EstadisticasJugador estadisticas = new EstadisticasJugador();
        Sistema sistema = new Sistema();
        sistema.cargarEstadisticas();
        List<EstadisticasJugador> estadisticasTemp = sistema.getEstadisticas();

        for (EstadisticasJugador estasTemp : estadisticasTemp) {
            if (usuario.equals(estasTemp.getNombreJugador())) {
                estadisticas.setNombreJugador(estasTemp.getNombreJugador());
                estadisticas.setPuchamonesCreados(estasTemp.getPuchamonesCreados() + 1);
                estadisticas.setPuchamonesEliminados(estasTemp.getPuchamonesEliminados());
                estadisticas.setBatallasEnArena(estasTemp.getBatallasEnArena());
                estadisticas.setBatallasGanadas(estasTemp.getBatallasGanadas());
                estadisticas.setBatallasPerdidas(estasTemp.getBatallasPerdidas());
                estadisticas.setDineroGanado(estasTemp.getDineroGanado());
                estadisticas.setDineroPerdido(estasTemp.getDineroPerdido());
                estadisticas.setBatallasDosOMasPV(estasTemp.getBatallasDosOMasPV());
                estadisticas.ModificarEstadisticas();
                break;
            }
        }
    }

    public class OyenteAgregarP implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            CrearPuchamon();
            actualizarEstadisticas();
        }
    }

    public  class  OyenteRegistrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(panel, "Requipo");
            String tip = tipo.getSelectedItem().toString();
            PanelImagenes("src/imagenes/Puchamones", tip);
        }
    }

    public class  OyenteTipo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tip = tipo.getSelectedItem().toString();
            PanelImagenes("src/imagenes/Puchamones", tip);
        }
    }

    public class OyenteAtras2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ControladorEquipo equipo = new ControladorEquipo(interfazEquipo, menu, usuario);
            equipo.agregarPaneles(usuario);
            card.show(panel, "Equipo");
        }
    }
}
