package Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jugador {
    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String genero;
    private int edad;
    private String password;
    private int oro;
    private List<Puchamon> equipo;
    private EstadisticasJugador estadisticas;

    public Jugador() {
    }

    public Jugador(String nombre, String correo, String nombreUsuario, String genero, int edad, String password, int oro) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.genero = genero;
        this.edad = edad;
        this.password = password;
        this.oro = oro;
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

    public List<Puchamon> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Puchamon> equipo) {
        this.equipo = equipo;
    }

    public EstadisticasJugador getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(EstadisticasJugador estadisticas) {
        this.estadisticas = estadisticas;
    }

    // Registra un nuevo jugador
    public void registrarJugador() {
        String nombreArchivo = "src/BaseDatos/Usuarios.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write("Nombre: " + nombre + ", ");
            writer.write("Correo: " + correo + ", ");
            writer.write("Nombre de Usuario: " + nombreUsuario + ", ");
            writer.write("Género: " + genero + ", ");
            writer.write("Edad: " + edad + ", ");
            writer.write("Password: " + password + ", ");
            writer.write("Oro: " + oro);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el jugador: " + e.getMessage());
        }
    }

    // Realiza una apuesta
    public int realizarApuesta(int cantidad) {
        return cantidad;
    }

    public String toString() {
        return nombre + "," + correo + "," + nombreUsuario + "," + genero + "," + edad + "," + password + "," + oro;
    }
}
