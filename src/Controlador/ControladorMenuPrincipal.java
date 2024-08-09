package Controlador;

import Vista.FrameInicio;
import Vista.FramePrincipal;
import Vista.InterfazMPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Map;

public class ControladorMenuPrincipal {

    private JButton arena;
    private JButton equipo;
    private JButton estadisticas;
    private JButton cerrarSesion;
    private FramePrincipal frame;
    private Map<String,String> config;

    public ControladorMenuPrincipal(InterfazMPrincipal menu) {
        this.cerrarSesion = menu.getBtnCerrarSesion();
        this.arena = menu.getBtnArena();
        this.equipo = menu.getBtnEquipo();
        this.estadisticas = menu.getBtnEstadisticas();
        this.frame = menu.getPrincipal();
        this.config = menu.getConfig();

        menu.OyenteBtnArena(new OyenteMouseArena());
        menu.OyenteBtnEquipo(new OyenteMouseEquipo());
        menu.OyenteBtnEstadisticas(new OyenteMouseEstadisticas());
        menu.OyenteBtnCerrar(new OyenteMouseCerrar());
        menu.OyenteCerrar(new OyenteCerrarSesion());
    }

    public void CargarImagenesBtn(){
        ImageIcon iconoArena = AjustarImagen("/imagenes/ArenaBtn.jpg", 200, 300,0.5f);
        arena.setIcon(iconoArena);
        ImageIcon iconoEquipo = AjustarImagen("/imagenes/btnEquipo.jpg", 200, 300,0.5f);
        equipo.setIcon(iconoEquipo);
        ImageIcon iconoEstadisticas = AjustarImagen("/imagenes/btnEstadisticas.jpg", 200, 300,0.5f);
        estadisticas.setIcon(iconoEstadisticas);
        ImageIcon iconoCerrar = AjustarImagen("/imagenes/btnCerrarSesion.jpg", 200, 300,0.5f);
        cerrarSesion.setIcon(iconoCerrar);


    }

    public class OyenteMouseArena implements MouseListener{

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
            ImageIcon iconoArena = AjustarImagen("/imagenes/ArenaBtn.jpg", 200, 300,1f);
            arena.setIcon(iconoArena);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoArena = AjustarImagen("/imagenes/ArenaBtn.jpg", 200, 300,0.5f);
            arena.setIcon(iconoArena);
        }
    }

    public class OyenteMouseEquipo implements MouseListener{

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
            ImageIcon iconoEquipo = AjustarImagen("/imagenes/btnEquipo.jpg", 200, 300,1f);
            equipo.setIcon(iconoEquipo);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoEquipo = AjustarImagen("/imagenes/btnEquipo.jpg", 200, 300,0.5f);
            equipo.setIcon(iconoEquipo);
        }
    }

    public class OyenteMouseEstadisticas implements MouseListener{

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
            ImageIcon iconoEstadisticas = AjustarImagen("/imagenes/btnEstadisticas.jpg", 200, 300,1f);
            estadisticas.setIcon(iconoEstadisticas);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoEstadisticas = AjustarImagen("/imagenes/btnEstadisticas.jpg", 200, 300,0.5f);
            estadisticas.setIcon(iconoEstadisticas);
        }
    }

    public class OyenteMouseCerrar implements MouseListener{

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
            ImageIcon iconoCerrar = AjustarImagen("/imagenes/btnCerrarSesion.jpg", 200, 300,1f);
            cerrarSesion.setIcon(iconoCerrar);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ImageIcon iconoCerrar = AjustarImagen("/imagenes/btnCerrarSesion.jpg", 200, 300,0.5f);
            cerrarSesion.setIcon(iconoCerrar);
        }
    }

    private class OyenteCerrarSesion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new FrameInicio(config);
            frame.dispose();
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
}
