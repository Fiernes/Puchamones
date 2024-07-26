package Modelo;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Configuracion {

    public static Map<String, String> cargarConfiguracion(String rutaArchivo) {
        Map<String, String> configuracion = new HashMap<>();

        // Utiliza getClass().getResourceAsStream() para leer el archivo desde el classpath
        try (InputStream inputStream = Configuracion.class.getResourceAsStream(rutaArchivo);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("=");
                if (datos.length == 2) {
                    configuracion.put(datos[0].trim(), datos[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de configuración: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Archivo no encontrado: " + rutaArchivo);
        }

        return configuracion;
    }

    public void textKeyPress(KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && (car != (char) KeyEvent.VK_BACK_SPACE) && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }


    public void numberKeyPress(KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }
}
