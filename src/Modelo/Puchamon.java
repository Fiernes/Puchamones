package Modelo;

import java.io.*;
import java.util.Random;

public class Puchamon {
    private String usuario;
    private String nombre;
    private String tipo;
    private int nivel;
    private int vida;
    private int ataque;
    private int defensa;
    private int experiencia;
    private String rutaImagen;

    public Puchamon() {
    }

    public Puchamon(String usuario, String nombre, String tipo, int nivel, int vida, int ataque, int defensa, int experiencia, String rutaImagen) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.experiencia = experiencia;
        this.rutaImagen = rutaImagen;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    // Ataca a otro Modelo.Puchamon
    public int atacarJugador(Puchamon puchamonNpc, Puchamon puchamonJugador, int ataquesFallidosJu) {
        int ataque = puchamonJugador.getAtaque();
        int defensa = puchamonNpc.getDefensa();

        // Calcula el daño potencial
        int damage = ataque - defensa;

        // Verifica si el daño es menor o igual a cero
        if (damage <= 0) {
            damage = 0;
            ataquesFallidosJu++;
        }

        // Si hay dos ataques fallidos consecutivos, duplica el daño en el tercer ataque
        if (ataquesFallidosJu == 2) {
            ataque = ataque * 2;
            damage = ataque - defensa;
        }

        // Genera un número aleatorio entre 0 y 99
        Random random = new Random();
        int probabilidad = random.nextInt(100);

        // Verifica si la probabilidad es menor al 40% (40 de 100)
        if (probabilidad < 40) {
            damage = damage * 2; // Duplica el daño
        }

        // Calcula la nueva vida del NPC asegurándose de que no sea negativa
        return Math.max(0, puchamonNpc.getVida() - damage);
    }


    // Defiende un ataque
    public int atacarNpc(Puchamon puchamonNpc, Puchamon puchamonJugador, int ataquesFallidosNpc) {
        int defensa = puchamonJugador.getDefensa();
        int ataque = puchamonNpc.getAtaque();
        // Calcula el daño potencial
        int damage = ataque - defensa;

        // Verifica si el daño es menor o igual a cero
        if (damage <= 0) {
            damage = 0;
        }

        // Si hay dos ataques fallidos consecutivos, duplica el daño en el tercer ataque
        if (ataquesFallidosNpc == 2) {
            ataque = ataque * 2;
            damage = ataque - defensa;
        }

        // Calcula la nueva vida del NPC asegurándose de que no sea negativa
        return Math.max(0, puchamonJugador.getVida() - damage);
    }

    // Gana experiencia
    public void ganarExperiencia(String nombrePuchamon, int nuevaExperiencia) {
        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "EquipoJugador.txt";
        String rutaTemp = currentDir + File.separator + "BaseDatos" + File.separator + "EquipoJugador_temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaBaseDatos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(rutaTemp))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("NombreP: " + nombrePuchamon + ",")) {
                    // Actualizar la experiencia
                    String[] parts = line.split(",");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].trim().startsWith("Experiencia:")) {
                            parts[i] = " Experiencia: " + nuevaExperiencia;
                        }
                    }
                    line = String.join(",", parts);
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al modificar la experiencia del Puchamon: " + e.getMessage());
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

    // Sube de nivel
    public void subirNivel(String nombrePuchamon, int nuevoNivel, int nuevoAtaque, int nuevaDefensa) {
        String currentDir = new File("").getAbsolutePath();
        String rutaBaseDatos = currentDir + File.separator + "BaseDatos" + File.separator + "EquipoJugador.txt";
        String rutaTemp = currentDir + File.separator + "BaseDatos" + File.separator + "EquipoJugador_temp.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaBaseDatos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(rutaTemp))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("NombreP: " + nombrePuchamon + ",")) {
                    // Actualizar la experiencia
                    String[] parts = line.split(",");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].trim().startsWith("NivelP:")) {
                            parts[i] = " NivelP: " + nuevoNivel;
                        }
                        if (parts[i].trim().startsWith("AtaqueP:")) {
                            parts[i] = " AtaqueP: " + nuevoAtaque;
                        }
                        if (parts[i].trim().startsWith("DefensaP:")) {
                            parts[i] = " DefensaP: " + nuevaDefensa;
                        }
                    }
                    line = String.join(",", parts);
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al modificar el nivel del Puchamon: " + e.getMessage());
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
