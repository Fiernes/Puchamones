package Controlador;

import Modelo.EstadisticasJugador;
import Modelo.Sistema;

import java.util.List;

public class ContoladorEstadisticas {

    private List<EstadisticasJugador> listaEsta;

    public void CargarEstadisticasJugador(){
        Sistema s = new Sistema();
        s.cargarEstadisticas();
        listaEsta = s.getEstadisticas();

        for (EstadisticasJugador esta : listaEsta){
        }
    }
}
