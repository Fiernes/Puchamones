import java.util.List;

public class Jugador {
    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String genero;
    private int edad;
    private String password;
    private int oro;
    private List<Puchamon> equipo;
    private EstadisticasJugador estadisticas;

    public Jugador() {
    }

    public Jugador(String nombre, String correo, String nombreUsuario, String genero, int edad, String password, int oro, List<Puchamon> equipo, EstadisticasJugador estadisticas) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.genero = genero;
        this.edad = edad;
        this.password = password;
        this.oro = oro;
        this.equipo = equipo;
        this.estadisticas = estadisticas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public List<Puchamon> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Puchamon> equipo) {
        this.equipo = equipo;
    }

    public EstadisticasJugador getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(EstadisticasJugador estadisticas) {
        this.estadisticas = estadisticas;
    }

    // Registra un nuevo jugador
    public void registrarJugador() {

    }

    // Agrega un Puchamon al equipo del jugador
    public boolean agregarPuchamon(Puchamon puchamon) {
        return false;
    }

    // Elimina un Puchamon del equipo del jugador
    public boolean eliminarPuchamon(Puchamon puchamon) {
        return false;
    }

    // Realiza una apuesta
    public int realizarApuesta(int cantidad) {
        return cantidad;
    }

    // Guarda los cambios del jugador
    public void guardarCambios() {
    }

    public String toString() {
        return nombre + "," + correo + "," + nombreUsuario + "," + genero + "," + edad + "," + password + "," + oro;
    }
}
