import java.util.List;

public class Sistema {
    private List<Jugador> jugadores;

    public Sistema() {
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

    // Registra un nuevo jugador
    public Jugador registrarJugador() {
        return null;
    }

    // Inicia sesión de un jugador
    public String iniciarSesion() {
        return "";
    }

    // Guarda datos del sistema
    public void guardarDatos() {
    }

    // Carga datos del sistema
    public void cargarDatos() {
    }
}
