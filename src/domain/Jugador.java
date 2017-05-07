package domain;

import java.util.Date;

/**
 *
 * @author ferran
 */
public class Jugador {
    
    private String nombre;
    private Date nacimiento;
    private int canastas;
    private int asistencias;
    private int rebotes;
    private Posicion posicion;

    public Jugador() {}

    public Jugador(String nombre, Date nacimiento, int canasto, int asisto, int reboto, Posicion posicion) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.canastas = canasto;
        this.asistencias = asisto;
        this.rebotes = reboto;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getCanastas() {
        return canastas;
    }

    public void setCanastas(int canastas) {
        this.canastas = canastas;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", canastas=" + canastas +
                ", asisencias=" + asistencias +
                ", rebotes=" + rebotes +
                ", posicion=" + posicion +
                '}';
    }
    
}
