package Modelo;

import java.io.*;
import java.util.List;

public class Jugador {
    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String genero;
    private int edad;
    private String password;
    private int oro;
    private int nivelJu;
    private List<Puchamon> equipo;
    private EstadisticasJugador estadisticas;

    public Jugador() {
    }

    public Jugador(String nombre, String correo, String nombreUsuario, String genero, int edad, String password, int oro, int nivelJu) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.genero = genero;
        this.edad = edad;
        this.password = password;
        this.oro = oro;
        this.nivelJu = nivelJu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getNivelJu() {
        return nivelJu;
    }

    public void setNivelJu(int nivelJu) {
        this.nivelJu = nivelJu;
    }

    public void registrarJugador() {

        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "Usuarios.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(rutaBaseDatos), true))) {
            writer.write("Nombre: " + nombre + ", ");
            writer.write("Correo: " + correo + ", ");
            writer.write("Nombre de Usuario: " + nombreUsuario + ", ");
            writer.write("Género: " + genero + ", ");
            writer.write("Edad: " + edad + ", ");
            writer.write("Password: " + password + ", ");
            writer.write("Oro: " + oro + ", ");
            writer.write("NivelJu: " + nivelJu);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el jugador: " + e.getMessage());
        }
    }



    // Realiza una apuesta
    public void ModificarOroyNivel(String nombre, int cantidad, int nivel) {

        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "Usuarios.txt";
        String rutaTemp = currentDir + File.separator + "BaseDatos" + File.separator + "Usuarios_temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaBaseDatos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(rutaTemp))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Nombre de Usuario: " + nombre + ",")) {
                    // Actualizar la experiencia
                    String[] parts = line.split(",");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].trim().startsWith("Oro:")) {
                            parts[i] = " Oro: " + cantidad;
                        }
                        if (parts[i].trim().startsWith("Nivel:")) {
                            parts[i] = " Nivel: " + nivel;
                        }
                    }
                    line = String.join(",", parts);
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al modificar el oro del jugador: " + e.getMessage());
        }

        // Eliminar el archivo original y renombrar el temporal
        File originalFile = new File(rutaBaseDatos);
        File tempFile = new File(rutaTemp);

        if (originalFile.delete()) {
            if (!tempFile.renameTo(originalFile)) {
                System.err.println("No se pudo renombrar el archivo temporal.");
            }
        } else {
            System.err.println("No se pudo eliminar el archivo original.");
        }
    }

    public String toString() {
        return nombre + "," + correo + "," + nombreUsuario + "," + genero + "," + edad + "," + password + "," + oro;
    }
}
