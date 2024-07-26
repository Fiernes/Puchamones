package Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EstadisticasJugador {
    private String nombreJugador;
    private int puchamonesCreados;
    private int puchamonesEliminados;
    private int batallasEnArena;
    private int batallasGanadas;
    private int batallasPerdidas;
    private int dineroGanado;
    private int dineroPerdido;
    private int batallasDosOMasPV;

    public EstadisticasJugador() {
    }

    public EstadisticasJugador(String nombreJugador, int puchamonesCreados, int puchamonesEliminados, int batallasEnArena, int batallasGanadas, int batallasPerdidas, int dineroGanado, int dineroPerdido, int batallasDosOMasPV) {
        this.nombreJugador = nombreJugador;
        this.puchamonesCreados = puchamonesCreados;
        this.puchamonesEliminados = puchamonesEliminados;
        this.batallasEnArena = batallasEnArena;
        this.batallasGanadas = batallasGanadas;
        this.batallasPerdidas = batallasPerdidas;
        this.dineroGanado = dineroGanado;
        this.dineroPerdido = dineroPerdido;
        this.batallasDosOMasPV = batallasDosOMasPV;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getPuchamonesCreados() {
        return puchamonesCreados;
    }

    public void setPuchamonesCreados(int puchamonesCreados) {
        this.puchamonesCreados = puchamonesCreados;
    }

    public int getPuchamonesEliminados() {
        return puchamonesEliminados;
    }

    public void setPuchamonesEliminados(int puchamonesEliminados) {
        this.puchamonesEliminados = puchamonesEliminados;
    }

    public int getBatallasEnArena() {
        return batallasEnArena;
    }

    public void setBatallasEnArena(int batallasEnArena) {
        this.batallasEnArena = batallasEnArena;
    }

    public int getBatallasGanadas() {
        return batallasGanadas;
    }

    public void setBatallasGanadas(int batallasGanadas) {
        this.batallasGanadas = batallasGanadas;
    }

    public int getBatallasPerdidas() {
        return batallasPerdidas;
    }

    public void setBatallasPerdidas(int batallasPerdidas) {
        this.batallasPerdidas = batallasPerdidas;
    }

    public int getDineroGanado() {
        return dineroGanado;
    }

    public void setDineroGanado(int dineroGanado) {
        this.dineroGanado = dineroGanado;
    }

    public int getDineroPerdido() {
        return dineroPerdido;
    }

    public void setDineroPerdido(int dineroPerdido) {
        this.dineroPerdido = dineroPerdido;
    }

    public int getBatallasDosOMasPV() {
        return batallasDosOMasPV;
    }

    public void setBatallasDosOMasPV(int batallasDosOMasPV) {
        this.batallasDosOMasPV = batallasDosOMasPV;
    }

    // Actualiza las estadísticas del jugador
    public void CrearEstadisticasCero(String usuario) {
        String nombreArchivo = "src/BaseDatos/EstadisticasJugador.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write("Nombre: " + usuario + ", ");
            writer.write("Puchamones Creados: " + puchamonesCreados + ", ");
            writer.write("Puchamones Eliminados: " + puchamonesEliminados + ", ");
            writer.write("Batallas en Arena: " + batallasEnArena + ", ");
            writer.write("Batallas Ganadas: " + batallasGanadas + ", ");
            writer.write("Batallas Perdidas: " + batallasPerdidas + ", ");
            writer.write("Dinero Ganado: " + dineroGanado + ", ");
            writer.write("Dinero Perdido: " + dineroPerdido + ", ");
            writer.write("Batallas Ganadas Dos o Mas PV: " + batallasDosOMasPV);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar las Estadisticas: " + e.getMessage());
        }

    }
}
