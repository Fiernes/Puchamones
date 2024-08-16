package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sistema {
    private List<Jugador> jugadores;
    private List<EstadisticasJugador> estadisticas;

    public Sistema() {
        this.jugadores = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<EstadisticasJugador> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(List<EstadisticasJugador> estadisticas) {
        this.estadisticas = estadisticas;
    }

    // Inicia sesión de un jugador
    public boolean iniciarSesion(String usuario, String pass) {
        cargarJugadores();
        for (Jugador jugador : jugadores){
            if (Objects.equals(jugador.getNombreUsuario(), usuario) && Objects.equals(jugador.getPassword(), pass)){
                return true;
            }
        }
        return false;
    }

    public void cargarJugadores() {
        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "Usuarios.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaBaseDatos))) {
            String line;
            while ((line = br.readLine()) != null) {
                Jugador jugador = parseJugador(line);
                if (jugador != null) {
                    this.jugadores.add(jugador);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarEstadisticas(){
        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "EstadisticasJugador.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaBaseDatos))) {
            String line;
            while ((line = br.readLine()) != null) {
                EstadisticasJugador estadisticas = parseEstadisticas(line);
                if (estadisticas != null) {
                    this.estadisticas.add(estadisticas);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Jugador parseJugador(String line) {
        try {
            String[] parts = line.split(", ");
            String nombre = parts[0].split(": ")[1];
            String correo = parts[1].split(": ")[1];
            String nombreUsuario = parts[2].split(": ")[1];
            String genero = parts[3].split(": ")[1];
            int edad = Integer.parseInt(parts[4].split(": ")[1]);
            String password = parts[5].split(": ")[1];
            int oro = Integer.parseInt(parts[6].split(": ")[1]);
            int nivel = Integer.parseInt(parts[7].split(": ")[1]);

            return new Jugador(nombre, correo, nombreUsuario, genero, edad, password, oro, nivel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private EstadisticasJugador parseEstadisticas(String line) {
        try {
            String[] parts = line.split(", ");
            String nombre = parts[0].split(": ")[1];
            int puchamonesCreados = Integer.parseInt(parts[1].split(": ")[1]);
            int puchamonesEliminados = Integer.parseInt(parts[2].split(": ")[1]);
            int batallasEnArena = Integer.parseInt(parts[3].split(": ")[1]);
            int batallasGanadas = Integer.parseInt(parts[4].split(": ")[1]);
            int batallasPerdidas = Integer.parseInt(parts[5].split(": ")[1]);
            int oroGanado = Integer.parseInt(parts[6].split(": ")[1]);
            int oroPerdido = Integer.parseInt(parts[7].split(": ")[1]);
            int batallasDosOMasPV = Integer.parseInt(parts[8].split(": ")[1]);

            return new EstadisticasJugador(nombre, puchamonesCreados, puchamonesEliminados, batallasEnArena, batallasGanadas, batallasPerdidas, oroGanado, oroPerdido, batallasDosOMasPV);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
