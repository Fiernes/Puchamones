import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class a {
    public static void main(String[] args) {
        ArrayList<Jugador> DatosJugador = new ArrayList<>();

        // Ejemplo de cómo agregar un jugador
        Jugador ju = new Jugador();
        ju.setNombre("John");
        ju.setCorreo("john@example.com");
        ju.setNombreUsuario("johnny");
        ju.setGenero("Masculino");
        ju.setEdad(25);
        ju.setPassword("password123");
        ju.setOro(2000);
        DatosJugador.add(ju);

        // Escribir datos en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Usuarios.txt"))) {
            for (Jugador jugador : DatosJugador) {
                writer.write(jugador.toString());
                writer.newLine(); // Para agregar una nueva línea después de cada jugador
            }
            System.out.println("Datos escritos en el archivo jugadores.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
