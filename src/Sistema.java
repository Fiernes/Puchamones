import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Jugador> jugadores;

    public Sistema() {
        this.jugadores = new ArrayList<>();
    }

    public Sistema(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    // Inicia sesión de un jugador
    public String iniciarSesion() {

        return "";
    }

    // Guarda datos del sistema
    public void guardarDatos() {
    }

    public void cargarDatos() {
        String filePath = "src/BaseDatos/Usuarios.txt"; // Cambia esto a la ruta de tu archivo
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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

            return new Jugador(nombre, correo, nombreUsuario, genero, edad, password, oro);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
