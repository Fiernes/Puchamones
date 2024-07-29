package Controlador;

import Vista.InterfazEquipo;
import Vista.InterfazMPrincipal;
import Vista.InterfazRegistroPuchamon;

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

public class ControladorEquipo {
    private CardLayout card;
    private JPanel panel;
    private JLabel imagen1;
    private JTextField txtNombreP;
    private JLabel labelultimaSeleccionada;
    private JLabel labelseleccionada;
    private JPanel panelImagenes;

    public  ControladorEquipo(InterfazEquipo vista, InterfazMPrincipal menu, InterfazRegistroPuchamon puchamon){
        this.card = vista.getCardLayout();
        this.panel = vista.getPaneles();
        this.imagen1 = vista.getEtiquetaImagen();

        this.txtNombreP = puchamon.getTxtNombreP();
        this.labelultimaSeleccionada = puchamon.getUltimaSeleccionada();
        this.labelseleccionada = puchamon.getSeleccionada();
        this.panelImagenes = puchamon.getPanelImagenes();


        vista.OyenteBtnAtras(new OyenteAtras());
        vista.OyenteBtnAgregarP(new OyenteRegistrar());
        menu.OyenteEquipo(new OyenteEquipo());
        puchamon.OyenteAtras(new OyenteAtras2());
    }

    public void CargarImagenes(){

        imagen1.setIcon(ReescaladoImagen("/imagenes/PuchamonUno.jpg", 100, 150));
    }


    private ImageIcon ReescaladoImagen(String ruta, int alto, int ancho){

        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image imagen = icono.getImage(); // obtener el objeto Image
        Image imagenEscalada = imagen.getScaledInstance(alto, ancho, Image.SCALE_SMOOTH);
        //Image imagenEscalada = imagen.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        return  iconoEscalado;
    }

    public void PanelImagenes(String ruta){
        File imagesDir = new File(ruta);
        if (imagesDir.isDirectory()) {
            panelImagenes.removeAll();
            for (File file : imagesDir.listFiles()) {
                if (file.isFile() && isImageFile(file)) {
                    try {
                        BufferedImage img = ImageIO.read(file);
                        ImageIcon icon = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                        JLabel imageLabel = new JLabel(icon);
                        imageLabel.setOpaque(false);
                        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        imageLabel.addMouseListener(new MouseAdapter() {
                            public void mousePressed(MouseEvent evt) {

                                if (labelultimaSeleccionada != null) {
                                    labelultimaSeleccionada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                }

                                //selectedImagePath = file.getAbsolutePath();
                               // selectedImageLabel.setIcon(icon);

                                imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                                labelultimaSeleccionada = imageLabel;
                            }
                        });
                        panelImagenes.add(imageLabel);
                    } catch (IOException e) {
                        e.printStackTrace();
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

    public  class  OyenteRegistrar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(panel, "Requipo");
            PanelImagenes("src/imagenes/Puchamones");
        }
    }

    public class OyenteAtras implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(panel, "Principal");
        }
    }

    public class OyenteAtras2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(panel, "Equipo");
        }
    }

    public class OyenteEquipo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CargarImagenes();
            card.show(panel, "Equipo");
        }
    }
}
