package Controlador;

import Modelo.Jugador;
import Modelo.Sistema;
import Vista.InterfazRegistro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;


public class ControladorRegistro {

    private CardLayout card;
    private JPanel paneles;
    private Map<String, String> config;
    private JTextField txtNombreCompleto;
    private JTextField txtCorreo;
    private JTextField txtNombreUsuario;
    private JComboBox<Integer> comboEdad;
    private JPasswordField txtPassword;
    private String genero;
    private JRadioButton boton;
    private JRadioButton boton2;
    private String mensajeCuerpo;
    private String mensajeCabezera;
    private List<Jugador> jugador;

    public ControladorRegistro(InterfazRegistro registro){
        this.card = registro.getCardLayout();
        this.paneles = registro.getMostrarPaneles();
        this.txtNombreCompleto = registro.getTxtNombreCompleto();
        this.txtCorreo = registro.getTxtCorreo();
        this.txtNombreUsuario = registro.getTxtNombreUsuario();
        this.comboEdad = registro.getComboEdad();
        this.txtPassword = registro.getTxtPassword();
        this.boton = registro.getBoton();
        this.boton2 = registro.getBoton2();
        this.mensajeCuerpo = registro.getMensajeCuerpo();
        this.mensajeCabezera = registro.getMensajeCabezera();

        registro.OyenteRegresar(new OyenteRegresar());
        registro.OyenteContra(new OyenteMostrarPass());
        registro.OyenteRadioB(new OyenteRadionButton());
        registro.OyenteRegis(new OyenteRegistrar());
    }



    public class OyenteRegresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            card.show(paneles, "Inicio de sesion");
        }
    }

    public class OyenteRegistrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            StringBuilder mensaje = new StringBuilder();

            // Verificación de cada campo
            if (txtNombreCompleto.getText().isEmpty()) {
                mensaje.append("Falta el nombre completo.\n");
            }
            if (txtCorreo.getText().isEmpty()) {
                mensaje.append("Falta el correo.\n");
            }
            if (txtNombreUsuario.getText().isEmpty()) {
                mensaje.append("Falta el nombre de usuario.\n");
            }
            if (comboEdad.getSelectedItem() == null) {
                mensaje.append("Falta seleccionar la edad.\n");
            }
            if (new String(txtPassword.getPassword()).isEmpty()) {
                mensaje.append("Falta la contraseña.\n");
            }
            if (!boton.isSelected() && !boton2.isSelected()) {
                mensaje.append("Falta seleccionar el género.\n");
            }

            // Mostrar mensaje si falta algún dato
            if (mensaje.length() > 0) {
                JOptionPane.showMessageDialog(null, mensaje.toString(), "Campos Faltantes", JOptionPane.WARNING_MESSAGE);
            } else {
                Sistema sis = new Sistema();
                sis.cargarJugadores();
                jugador = sis.getJugadores();

                boolean encontrado = false;

                for(Jugador ju : jugador){
                    if(ju.getNombreUsuario().equals(txtNombreUsuario.getText())){
                        JOptionPane.showMessageDialog(null, "El nombre de Usuario ya a sido registrado");
                        encontrado = true;
                        break;
                    }
                }

                if(!encontrado){
                        RegistrarNuevo();
                        JOptionPane.showMessageDialog(null, mensajeCuerpo, mensajeCabezera, JOptionPane.INFORMATION_MESSAGE);
                        LimpiarCampos();
                }
            }
        }
    }

    public class OyenteRadionButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton source = (JRadioButton) e.getSource();
            System.out.println("Seleccionado: " + source.getText());
            genero = source.getText();
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

    private void RegistrarNuevo(){
        Jugador jugador = new Jugador();
        jugador.setNombre(txtNombreCompleto.getText());
        jugador.setCorreo(txtCorreo.getText());
        jugador.setNombreUsuario(txtNombreUsuario.getText());
        jugador.setPassword(String.valueOf(txtPassword.getPassword()));
        jugador.setEdad((Integer) comboEdad.getSelectedItem());
        jugador.setGenero(genero);
        jugador.setOro(2000);
        jugador.registrarJugador();
    }

    private void LimpiarCampos(){
        txtNombreCompleto.setText("");
        txtCorreo.setText("");
        txtNombreUsuario.setText("");
        txtPassword.setText("");
        comboEdad.setSelectedIndex(0);
        boton.setSelected(false);
        boton2.setSelected(false);
    }
}
