package Modelo;

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

    // Agrega un Modelo.Puchamon al equipo
    public boolean agregarPuchamon(Puchamon puchamon) {
        return false;
    }

    // Elimina un Modelo.Puchamon del equipo
    public boolean eliminarPuchamon(Puchamon puchamon) {
        return false;
    }

    // Revisa el equipo
    public List<Puchamon> revisarEquipo() {
        return List.of();
    }
}
