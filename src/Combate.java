public class Combate {
    private int apuesta;
    private Jugador jugadorUno;
    private Jugador jugadorDos;

    public Combate() {
    }

    public Combate(int apuesta, Jugador jugadorUno, Jugador jugadorDos) {
        this.apuesta = apuesta;
        this.jugadorUno = jugadorUno;
        this.jugadorDos = jugadorDos;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public Jugador getJugadorUno() {
        return jugadorUno;
    }

    public void setJugadorUno(Jugador jugadorUno) {
        this.jugadorUno = jugadorUno;
    }

    public Jugador getJugadorDos() {
        return jugadorDos;
    }

    public void setJugadorDos(Jugador jugadorDos) {
        this.jugadorDos = jugadorDos;
    }

    // Inicia un combate
    public void iniciarCombate() {
    }

    // Calcula el ganador del combate
    public Jugador calcularGanador() {
        return null;
    }

    // Realiza un turno del combate
    public void realizarTurno() {
    }

    // Muestra el estado del combate
    public void mostrarEstadoCombate() {
    }
}
