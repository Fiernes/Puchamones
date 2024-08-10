package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<Puchamon> GenerarRival(String usuario){
        List<Puchamon> puchamonesJugador = new ArrayList<>();
        List<Puchamon> puchamonesRival = new ArrayList<>();
        List<Integer> vidaPuchamones = new ArrayList<>();

        Random random = new Random();

        Equipo equi = new Equipo();
        List<Puchamon> comparar = equi.cargarEquipo();

        // Filtrar los Pokémon del usuario actual
        for (Puchamon pucha : comparar) {
            if (usuario.equals(pucha.getUsuario())) {
                puchamonesJugador.add(pucha);
            }
        }

        // Calcular el nivel promedio de los Pokémon del jugador
        int nivelTotal = 0;
        for (Puchamon pucha : puchamonesJugador) {
            nivelTotal += pucha.getNivel();
            vidaPuchamones.add(pucha.getVida());
        }
        int nivelPromedio = puchamonesJugador.size() > 0 ? nivelTotal / puchamonesJugador.size() : 1;

        // Obtener la lista de imágenes
        File imagesDir = new File("src/imagenes/Puchamones");
        File[] archivosImagenes = imagesDir.isDirectory() ? imagesDir.listFiles() : new File[0];

        // Generar los Pokémon rivales
        for (int i = 0; i < 3; i++) {
            Puchamon puchamonRival = new Puchamon();
            int nivelRival = nivelPromedio; // Nivel igual al promedio

            // Ajustar niveles según las reglas
            if (i == 1) { // Segundo Pokémon de menor nivel
                nivelRival = Math.max(nivelPromedio - 1, 1);
            } else if (i == 2) { // Tercer Pokémon de menor nivel
                nivelRival = Math.max(nivelPromedio - 2, 1);
            } else if (i == 3) { // Primer Pokémon de mayor nivel
                nivelRival = nivelPromedio + 1;
            }

            puchamonRival.setNivel(nivelRival);

            // Generar valores de ataque y defensa aleatorios y ajustarlos
            int ataque = 50 + random.nextInt(51); // Valor inicial entre 50 y 100
            int defensa = 50 + random.nextInt(51); // Valor inicial entre 50 y 100

            // Ajustar ataque y defensa según el nivel
            ataque += random.nextInt(3) + 3 * nivelRival;
            defensa += random.nextInt(3) + 3 * nivelRival;
            int vida = vidaPuchamones.get(i);

            puchamonRival.setAtaque(ataque);
            puchamonRival.setDefensa(defensa);
            puchamonRival.setVida(vida);
            puchamonRival.setNombre("NPC" + " " + i+1);

            // Asignar una imagen aleatoria del directorio
            if (archivosImagenes.length > 0) {
                File imagenFile = archivosImagenes[random.nextInt(archivosImagenes.length)];
                puchamonRival.setRutaImagen(imagenFile.getPath());
            }

            puchamonesRival.add(puchamonRival);
        }

        return puchamonesRival;
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
