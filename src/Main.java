import Modelo.Configuracion;
import Vista.FrameInicio;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
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

        // Obtener la ruta absoluta del directorio actual del proyecto
        String currentDir = new File("").getAbsolutePath();

        // Especificar la ruta a la carpeta "JuegoPuchamones/BaseDatos"
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos";

        // Crear la carpeta "BaseDatos" si no existe
        File carpetaBaseDatos = new File(rutaBaseDatos);
        if (!carpetaBaseDatos.exists()) {
            carpetaBaseDatos.mkdirs();
        }

        // Crear los archivos necesarios
        crearArchivo(rutaBaseDatos, "EquipoJugador.txt");
        crearArchivo(rutaBaseDatos, "EstadisticasJugador.txt");
        crearArchivo(rutaBaseDatos, "Usuarios.txt");

        String rutaArchivo = (seleccion == 1) ? "/BaseDatos/configuracion_en.txt" : "/BaseDatos/configuracion_es.txt";

        Map<String, String> config = Configuracion.cargarConfiguracion(rutaArchivo);
        FrameInicio frame = new FrameInicio(config);
        frame.setVisible(true);
    }

    // Método para crear un archivo en una ruta específica
    private static void crearArchivo(String rutaDirectorio, String nombreArchivo) {
        File archivo = new File(rutaDirectorio + File.separator + nombreArchivo);
        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe: " + archivo.getName());
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo: " + nombreArchivo);
            e.printStackTrace();
        }
    }
}
