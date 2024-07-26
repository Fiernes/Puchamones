package Controlador;

import Modelo.EstadisticasJugador;
import Modelo.Sistema;
import Vista.FrameInicio;
import Vista.FramePrincipal;
import Vista.InterfazInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

public class ControladorInicio {

    private final JTextField txtUsuario;
    private final JPasswordField txtPassword;
    private final CardLayout card;
    private final JPanel pan;
    private final Map<String, String> configuracion;
    private final FrameInicio frameInicio;

    public ControladorInicio(InterfazInicio vista, FrameInicio frameInicio){
        this.txtUsuario = vista.getTxtUsuario();
        this.txtPassword = vista.getTxtPassword();
        this.card = vista.getCardLayout();
        this.pan = vista.getMostrarPaneles();
        this.configuracion = vista.getConfig();
        vista.oyente(new OyenteBtnRegistro());
        vista.oyente2(new OyenteMostrarPass());
        vista.oyente3(new OyenteBtnInicio());

        this.frameInicio = frameInicio;
    }

    public void ValidarUsuario(Map<String, String> config, FrameInicio inicio2){
        String usuario = txtUsuario.getText().trim();
        String pass = String.valueOf(txtPassword.getPassword()).trim();

        // Verificar si ambos campos están llenos
        if (!usuario.isEmpty() && !pass.isEmpty()){
            Sistema sistema = new Sistema();
            boolean verificado = sistema.iniciarSesion(usuario, pass);
            if (verificado){
                FramePrincipal f = new FramePrincipal(config);
                f.setVisible(true);
                inicio2.dispose();

                sistema.cargarEstadisticas();
                List<EstadisticasJugador> estadisticas = sistema.getEstadisticas();

                boolean jugadorEncontrado = false;
                for (EstadisticasJugador esta : estadisticas) {
                    if (usuario.equals(esta.getNombreJugador())) {
                        jugadorEncontrado = true;
                        estadisticas.clear();
                        break;
                    }
                }

                if (!jugadorEncontrado) {
                    EstadisticasJugador estadis = new EstadisticasJugador();
                    estadis.CrearEstadisticasCero(usuario);
                }

                LimpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
        }
    }

    private void LimpiarCampos(){
        txtUsuario.setText("");
        txtPassword.setText("");
    }

    private class OyenteBtnRegistro implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           card.show(pan, "Registro de nuevo usuario");
        }
    }

    private class OyenteBtnInicio implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ValidarUsuario(configuracion, frameInicio);
        }
    }

    private class OyenteMostrarPass implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            txtPassword.setEchoChar((char) 0);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            txtPassword.setEchoChar('*');
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
