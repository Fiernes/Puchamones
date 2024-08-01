package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private List<Puchamon> puchamones;
    private int maxPuchamones;

    public Equipo() {
        this.puchamones = new ArrayList<>();
    }

    public Equipo(int maxPuchamones, List<Puchamon> puchamones) {
        this.maxPuchamones = maxPuchamones;
        this.puchamones = puchamones;
    }

    public List<Puchamon> getPuchamones() {
        return puchamones;
    }

    public void setPuchamones(List<Puchamon> puchamones) {
        this.puchamones = puchamones;
    }

    public int getMaxPuchamones() {
        return maxPuchamones;
    }

    public void setMaxPuchamones(int maxPuchamones) {
        this.maxPuchamones = maxPuchamones;
    }

    public void agregarPuchamon(Puchamon puchamon) {
        String nombreArchivo = "src/BaseDatos/EquipoJugador.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write("Usuario: " + puchamon.getUsuario() + ", ");
            writer.write("NombreP: " + puchamon.getNombre() + ", ");
            writer.write("TipoP: " + puchamon.getTipo() + ", ");
            writer.write("NivelP: " + puchamon.getNivel() + ", ");
            writer.write("VidaP: " + puchamon.getVida() + ", ");
            writer.write("AtaqueP: " + puchamon.getAtaque() + ", ");
            writer.write("DefensaP: " + puchamon.getDefensa() + ", ");
            writer.write("Experiencia: " + puchamon.getExperiencia() +  ", ");
            writer.write("Imagen: " + puchamon.getRutaImagen());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el jugador: " + e.getMessage());
        }

    }

    // Elimina un Modelo.Puchamon del equipo
    public void eliminarPuchamon(Puchamon puchamon) {


    }

    // Revisa el equipo
    public List<Puchamon> cargarEquipo() {

        String filePath = "src/BaseDatos/EquipoJugador.txt"; // Cambia esto a la ruta de tu archivo
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Puchamon puchamon = parsePuchamon(line);
                if (puchamon != null) {
                    this.puchamones.add(puchamon);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return puchamones;
    }

    private Puchamon parsePuchamon(String line) {
        try {
            String[] parts = line.split(", ");
            String usuario = parts[0].split(": ")[1];
            String nombrePuchamon = parts[1].split(": ")[1];
            String tipoPuchamon = parts[2].split(": ")[1];
            int nivel = Integer.parseInt(parts[3].split(": ")[1]);
            int vida = Integer.parseInt(parts[4].split(": ")[1]);
            int ataque = Integer.parseInt(parts[5].split(": ")[1]);
            int defensa = Integer.parseInt(parts[6].split(": ")[1]);
            int experiencia = Integer.parseInt(parts[7].split(": ")[1]);
            String imagen = parts[8].split(": ")[1];

            return new Puchamon(usuario, nombrePuchamon, tipoPuchamon, nivel, vida, ataque, defensa, experiencia, imagen);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
