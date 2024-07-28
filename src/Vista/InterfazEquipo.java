package Vista;

import Modelo.PanelFondo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;


public class InterfazEquipo extends PanelFondo {

    private CardLayout cardLayout;
    private JPanel paneles;
    private JButton btnAtras;


    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public JPanel getPaneles() {
        return paneles;
    }

    public void setPaneles(JPanel paneles) {
        this.paneles = paneles;
    }

    public InterfazEquipo(Map<String, String> config, CardLayout cardLayout, JPanel mostrarPaneles){
        super("/imagenes/FondoEquipo.jpg");

        this.cardLayout = cardLayout;
        this.paneles = mostrarPaneles;

        Color colorLetras = new Color(253, 157, 43, 255);
        Font fuenteInicio = new Font("Georgia", Font.BOLD, 20);
        Color color = new Color(191, 37, 23);
        setBorder(new MatteBorder(2, 3, 2, 3, color));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel puchamonUno = new JPanel();
        puchamonUno.setLayout(new GridBagLayout());
        puchamonUno.setSize(100,250);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(puchamonUno, gbc);

        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/PuchamonUno.jpg"));
        Image imagen = icono.getImage(); // obtener el objeto Image
        Image imagenEscalada = imagen.getScaledInstance(100, 150, Image.SCALE_SMOOTH); // escalar la imagen
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada); // crear un nuevo ImageIcon con la imagen escalada

        JLabel etiquetaImagen = new JLabel(iconoEscalado);
        etiquetaImagen.setPreferredSize(new Dimension(100, 150)); // establecer el tamaño preferido del JLabel
        gbc.gridx = 0;
        gbc.gridy = 0;
        puchamonUno.add(etiquetaImagen, gbc);

        JPanel imagenPuchamon = new JPanel();

        JPanel estadisticasP = new JPanel();


        btnAtras = new JButton(config.getOrDefault("btnRegresarR", "Volver atras"));
        btnAtras.setSize(50, 50);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnAtras, gbc);
    }

    public void Oyente(ActionListener po){
        btnAtras.addActionListener(po);
    }
}
