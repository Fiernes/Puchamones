package Controlador;

import Vista.InterfazEquipo;
import Vista.InterfazMPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEquipo {
    private CardLayout card;
    private JPanel panel;

    public  ControladorEquipo(InterfazEquipo vista, InterfazMPrincipal menu){
        this.card = vista.getCardLayout();
        this.panel = vista.getPaneles();

        vista.Oyente(new OyenteAtras());
        menu.OyenteEquipo(new OyenteEquipo());
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
            card.show(panel, "Equipo");
        }
    }
}
