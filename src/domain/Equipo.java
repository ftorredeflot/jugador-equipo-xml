package domain;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ferran
 */
public class Equipo {
    
    private String nombre;
    private String localidad;
    private Date creacion;
    private ArrayList<Jugador> jugadores;

    public Equipo( String nombre, String localidad, Date creacion) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.creacion = creacion;
        jugadores = new ArrayList<>();
    }

    public Equipo() {
        jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", creacion=" + creacion +
                ", jugadores=" + jugadores +
                '}';
    }
}
