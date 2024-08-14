import Modelo.Configuracion;
import Vista.FrameInicio;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        String[] opciones = {"Español", "English"};

        int seleccion = JOptionPane.showOptionDialog(null,
                "Seleccione el idioma / Select the language",
                "Idioma / Language",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);

        if (seleccion == JOptionPane.CLOSED_OPTION) {
            // Cerrar el programa
            System.exit(0);
        }

        String rutaArchivo = (seleccion == 1) ? "/BaseDatos/configuracion_en.txt" : "/BaseDatos/configuracion_es.txt";

        Map<String, String> config = Configuracion.cargarConfiguracion(rutaArchivo);
        new FrameInicio(config);
    }
}
