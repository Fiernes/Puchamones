public class Puchamon {
    private String nombre;
    private String tipo;
    private int nivel;
    private int vida;
    private int ataque;
    private int defensa;
    private int experiencia;

    public Puchamon() {
    }

    public Puchamon(String nombre, String tipo, int nivel, int vida, int ataque, int defensa, int experiencia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.experiencia = experiencia;
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

    // Ataca a otro Puchamon
    public void atacar(Puchamon enemigo) {
    }

    // Defiende un ataque
    public void defender(int damage) {
    }

    // Gana experiencia
    public void ganarExperiencia(int puntos) {
    }

    // Sube de nivel
    public void subirNivel() {
    }
}
