public class EstadisticasJugador {
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

    public EstadisticasJugador(int puchamonesCreados, int puchamonesEliminados, int batallasEnArena, int batallasGanadas, int batallasPerdidas, int dineroGanado, int dineroPerdido, int batallasDosOMasPV) {
        this.puchamonesCreados = puchamonesCreados;
        this.puchamonesEliminados = puchamonesEliminados;
        this.batallasEnArena = batallasEnArena;
        this.batallasGanadas = batallasGanadas;
        this.batallasPerdidas = batallasPerdidas;
        this.dineroGanado = dineroGanado;
        this.dineroPerdido = dineroPerdido;
        this.batallasDosOMasPV = batallasDosOMasPV;
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
    public void actualizarEstadisticas() {
    }
}
