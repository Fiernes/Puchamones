package Modelo;

import java.io.*;

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
        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "EstadisticasJugador.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaBaseDatos, true))) {
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

    public void ModificarEstadisticas(){
        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "EstadisticasJugador.txt";
        String rutaTemp = currentDir + File.separator + "BaseDatos" + File.separator + "EstadisticasJugador_temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaBaseDatos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(rutaTemp))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Nombre: " + nombreJugador + ",")) {
                    // Actualizar la experiencia
                    String[] parts = line.split(",");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].trim().startsWith("Puchamones Creados:")) {
                            parts[i] = " Puchamones Creados: " + puchamonesCreados;
                        }
                        if (parts[i].trim().startsWith("Puchamones Eliminados:")) {
                            parts[i] = " Puchamones Eliminados: " + puchamonesEliminados;
                        }
                        if (parts[i].trim().startsWith("Batallas en Arena:")) {
                            parts[i] = " Batallas en Arena: " + batallasEnArena;
                        }
                        if (parts[i].trim().startsWith("Batallas Ganadas:")) {
                            parts[i] = " Batallas Ganadas: " + batallasGanadas;
                        }
                        if (parts[i].trim().startsWith("Batallas Perdidas:")) {
                            parts[i] = " Batallas Perdidas: " + batallasPerdidas;
                        }
                        if (parts[i].trim().startsWith("Dinero Ganado:")) {
                            parts[i] = " Dinero Ganado: " + dineroGanado;
                        }
                        if (parts[i].trim().startsWith("Dinero Perdido:")) {
                            parts[i] = " Dinero Perdido: " + dineroPerdido;
                        }
                        if (parts[i].trim().startsWith("Batallas Ganadas Dos o Mas PV:")) {
                            parts[i] = " Batallas Ganadas Dos o Mas PV: " + batallasDosOMasPV;
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
}
