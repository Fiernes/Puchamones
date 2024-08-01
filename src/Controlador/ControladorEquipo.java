package Controlador;

import Modelo.Equipo;
import Modelo.Puchamon;
import Vista.InterfazEquipo;
import Vista.InterfazMPrincipal;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorEquipo {
    private CardLayout card;
    private JPanel panel;
    private JPanel panelElegidos;
    private JLabel imagen1;
    private JLabel labelultimaSeleccionada;
    private String usuario;

    public  ControladorEquipo(InterfazEquipo vista, InterfazMPrincipal menu, String usuario){
        this.card = vista.getCardLayout();
        this.panel = vista.getPaneles();
        this.panelElegidos = vista.getPanelElegidos();
        this.usuario = usuario;

        vista.OyenteBtnAtras(new OyenteAtras());
        menu.OyenteEquipo(new OyenteEquipo());
    }

    public void CargarImagenes(){

        imagen1.setIcon(ReescaladoImagen("/imagenes/PuchamonUno.jpg", 100, 150));
    }

    public void agregarPaneles() {
        // Suponiendo que tienes una lista o array de objetos con los datos que necesitas
        Equipo equi = new Equipo();
        List<Puchamon> listaDatos =  equi.cargarEquipo();
        List<Puchamon> puchamones = new ArrayList<>();

        for (Puchamon pucha : listaDatos){
            if (usuario.equals(pucha.getUsuario())){
                puchamones.add(pucha);
            }
        }

        panelElegidos.removeAll();
        panelElegidos.setLayout(new GridBagLayout()); // Usa GridBagLayout para el panel contenedor
        GridBagConstraints gbcContenedor = new GridBagConstraints();
        gbcContenedor.insets = new Insets(10, 10, 10, 10); // Margen entre paneles
        gbcContenedor.fill = GridBagConstraints.HORIZONTAL;
        gbcContenedor.anchor = GridBagConstraints.NORTH;

        int x = 0;

        for (Puchamon datos : puchamones) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setOpaque(false);
            panel.setBorder(new MatteBorder(1,1,1,1, new Color(0,0,0)));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

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
            Image imagenEscalada = imagenIcono.getImage().getScaledInstance(150, 225, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
            JLabel imagenLabel = new JLabel(iconoEscalado);
            // JLabel imagenLabel = new JLabel(new ImageIcon(datos.getRutaImagen()));

            // Añadir los componentes al panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(imagenLabel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(nombreLabel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(tipoLabel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(nivelLabel,gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(vidaLabel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(ataqueLabel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 6;
            panel.add(defensaLabel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 7;
            panel.add(experienciaLabel, gbc);


            gbcContenedor.gridx = x++;
            gbcContenedor.gridy = 0;
            panelElegidos.add(panel, gbcContenedor);
        }

        panelElegidos.revalidate();
        panelElegidos.repaint();
    }


    private ImageIcon ReescaladoImagen(String ruta, int alto, int ancho){

        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image imagen = icono.getImage(); // obtener el objeto Image
        Image imagenEscalada = imagen.getScaledInstance(alto, ancho, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        return  iconoEscalado;
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
            agregarPaneles();
            card.show(panel, "Equipo");
        }
    }
}
