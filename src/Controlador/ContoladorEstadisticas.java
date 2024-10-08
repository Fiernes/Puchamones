package Controlador;

import Modelo.EstadisticasJugador;
import Modelo.Jugador;
import Modelo.Sistema;
import Vista.InterfazEstadisticas;
import Vista.InterfazMPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContoladorEstadisticas {

    private List<EstadisticasJugador> listaEsta;
    private List<Jugador> listaJugador;
    private CardLayout card;
    private JPanel paneles;
    private JLabel verNombreJ;
    private JLabel verPC;
    private JLabel verPE;
    private JLabel verBA;
    private JLabel verBG;
    private JLabel verBP;
    private JLabel verDG;
    private JLabel verDP;
    private JLabel verDOMPV;
    private String Usuario;
    private JLabel verOroD;
    private JLabel verNivel;

    public ContoladorEstadisticas(InterfazEstadisticas vista, InterfazMPrincipal menu, String usuario){
        this.Usuario = usuario;
        this.card = vista.getCardLayout();
        this.paneles = vista.getMostrarPaneles();
        this.verNombreJ = vista.getVerNombreJ();
        this.verPC = vista.getVerPC();
        this.verPE = vista.getVerPE();
        this.verBA = vista.getVerBA();
        this.verBG = vista.getVerBG();
        this.verBP = vista.getVerBP();
        this.verDG = vista.getVerDG();
        this.verDP = vista.getVerDP();
        this.verDOMPV = vista.getVerDOMPV();
        this.verOroD = vista.getVerOrodisponoble();
        this.verNivel = vista.getVerNivel();

        vista.Oyente(new OyenteAtras());
        menu.OyenteEstadisticas(new OyenteEstadisticas());
    }

    public void CargarEstadisticasJugador(){
        Sistema s = new Sistema();
        s.cargarEstadisticas();
        s.cargarJugadores();
        listaEsta = s.getEstadisticas();
        listaJugador = s.getJugadores();

        for (EstadisticasJugador esta : listaEsta){
            if (Usuario.equals(esta.getNombreJugador())){
                verNombreJ.setText(esta.getNombreJugador());
                verPC.setText(String.valueOf(esta.getPuchamonesCreados()));
                verPE.setText(String.valueOf(esta.getPuchamonesEliminados()));
                verBA.setText(String.valueOf(esta.getBatallasEnArena()));
                verBG.setText(String.valueOf(esta.getBatallasGanadas()));
                verBP.setText(String.valueOf(esta.getBatallasPerdidas()));
                verDG.setText(String.valueOf(esta.getDineroGanado()));
                verDP.setText(String.valueOf(esta.getDineroPerdido()));
                verDOMPV.setText(String.valueOf(esta.getBatallasDosOMasPV()));
                break;
            }
        }

        for (Jugador jugador : listaJugador){
            if (Usuario.equals(jugador.getNombreUsuario())){
                verOroD.setText(String.valueOf(jugador.getOro()));
                verNivel.setText(String.valueOf(jugador.getNivelJu()));
                break;
            }
        }
    }

    public class OyenteAtras implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(paneles, "Principal");
        }
    }

    public class OyenteEstadisticas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(paneles, "Estadisticas");
            CargarEstadisticasJugador();
        }
    }
}
