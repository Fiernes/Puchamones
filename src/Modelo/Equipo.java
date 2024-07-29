package Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Equipo {
    private List<Puchamon> puchamones;
    private int maxPuchamones;

    public Equipo() {
    }

    public Equipo(List<Puchamon> puchamones, int maxPuchamones) {
        this.puchamones = puchamones;
        this.maxPuchamones = maxPuchamones;
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
            writer.write("Experiencia: " + puchamon.getExperiencia());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el jugador: " + e.getMessage());
        }

    }

    // Elimina un Modelo.Puchamon del equipo
    public void eliminarPuchamon(Puchamon puchamon) {


    }

    // Revisa el equipo
    public List<Puchamon> revisarEquipo() {

        return List.of();
    }
}
