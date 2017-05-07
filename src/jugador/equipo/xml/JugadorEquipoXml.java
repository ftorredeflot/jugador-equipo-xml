package jugador.equipo.xml;

import tools.*;
import domain.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import static tools.InputData.pedirCadena;
import static tools.InputData.pedirEntero;

/**
 *
 * @author ferran
 */
public class JugadorEquipoXml {

    private static Fichero fichero;
    private static HashMap<String, Equipo> hashmapEquipos;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //abrimos/creamos fichero
        fichero = new Fichero("bbdd.xml");
        hashmapEquipos = (HashMap<String, Equipo>) fichero.leer();
        //si el fichero esta vacio crea un hashmap vacio
        if (hashmapEquipos == null) {
            hashmapEquipos = new HashMap<>();
        }

        int opcion = 0;
        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1:
                    registrarEquipo();
                    fichero.grabar(hashmapEquipos);
                    break;
                case 2:
                    registrarJugador();
                    fichero.grabar(hashmapEquipos);
                    break;
                case 3:
                    consultaJugadores();
                    break;
                case 4:
                    consultaEquipos();
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
        System.out.println("Hasta Pronto!!!");
    }

    public static int menuPrincipal() {
        System.out.println("-----MENU PRINCIPAL------");
        System.out.println("1- Dar de Alta un Equipo");
        System.out.println("2- Dar de Alta un Jugador");
        System.out.println("3- Consultas de Jugadores");
        System.out.println("4- Consultas de Equipos");
        System.out.println("5- Salir");
        int opcion = pedirEntero("");
        return opcion;
    }

    public static int menu2() {
        System.out.println("--------MENU JUGADORES---------");
        System.out.println("1. Buscar jugadores por nombre");
        System.out.println("2. Buscar jugadores por Canastas mayor/igual");
        System.out.println("3. Buscar jugadores por Asistencias rango");
        System.out.println("4. Buscar jugadores por Posicion");
        System.out.println("5. Buscar jugadores por fecha de nacimiento anterior a la introducida");
        System.out.println("6. Buscar jugadores agrupados por posicion y con media");
        System.out.println("7. Buscar jugadores agrupados por posicion y con media, maximo y minimo");
        System.out.println("8. Salir");
        int opcion = pedirEntero("");
        return opcion;
    }

    public static int menu3() {
        System.out.println("---------MENU EQUIPOS----------");
        System.out.println("1. Buscar equipo por localidad");
        System.out.println("2. Buscar jugadores de un equipo");
        System.out.println("3. buscar por posicion en un equipo");
        System.out.println("4. jugador con mas canastas de un equipo");
        System.out.println("5. salir");
        int opcion = pedirEntero("");
        return opcion;
    }

    public static void registrarEquipo() {
        Equipo equipo = new Equipo();

        equipo.setNombre(pedirCadena("Introduce el nombre del equipo:"));
        equipo.setLocalidad(pedirCadena("Introduce localización:"));

        SimpleDateFormat fechaCreacion = new SimpleDateFormat("mm/dd/yyyy");
        Date testDate = null;
        String date = "";
        try {
            date = pedirCadena("Introduce la fecha de creación con el siguiente formato: mm/dd/yyyy");
            testDate = fechaCreacion.parse(date);
            if (!fechaCreacion.format(testDate).equals(date)) {
                System.out.println("Fecha no valida");
            } else {
                System.out.println("Fecha valida");
                equipo.setCreacion(testDate);
                hashmapEquipos.put(equipo.getNombre(), equipo);
                System.out.println("Equipo añadido correctamente");
            }
        } catch (ParseException e) {
            System.out.println("Formato incorrecto");
//            registrarEquipo();
        }

    }

    public static void registrarJugador() {
        Jugador jugador = new Jugador();
        String equipo = pedirCadena("Introduce el equipo en el que juega el jugador:");
        if (!hashmapEquipos.containsKey(equipo)) {
            System.out.println("Equipo no disponible");
        } else {
            jugador.setNombre(pedirCadena("Introduce el nombre del jugador:"));
            jugador.setCanastas(pedirEntero("Introduce numero de canastas"));
            jugador.setAsistencias(pedirEntero("Introduce numero de asistencias"));
            jugador.setRebotes(pedirEntero("Introduce numero de rebotes"));
            int aux = 0;
            do {
                System.out.println("Posiciones:");
                System.out.println("ala");
                System.out.println("pibot");
                System.out.println("base");
                String posicion = pedirCadena("posicion del jugador?");

                if (posicion.equalsIgnoreCase("ala")) {
                    jugador.setPosicion(Posicion.ala);
                    aux = 1;
                }
                if (posicion.equalsIgnoreCase("pibot")) {
                    jugador.setPosicion(Posicion.pibot);
                    aux = 1;
                }
                if (posicion.equalsIgnoreCase("base")) {
                    jugador.setPosicion(Posicion.base);
                    aux = 1;
                }
            } while (aux != 1);
            SimpleDateFormat fechaNacimiento = new SimpleDateFormat("MM/dd/yyyy");
            Date testDate = null;
            String date = "";
            try {
                date = pedirCadena("Introduce la fecha de nacimiento con formato: MM/dd/yyyy");
                testDate = fechaNacimiento.parse(date);
                if (!fechaNacimiento.format(testDate).equals(date)) {
                    System.out.println("Fecha no valida");
                } else {
                    System.out.println("Fecha valida");
                    jugador.setNacimiento(testDate);
                    Equipo equipoAux = hashmapEquipos.get(equipo);
                    ArrayList<Jugador> jugadoresEquipo = equipoAux.getJugadores();
                    jugadoresEquipo.add(jugador);
                    equipoAux.setJugadores(jugadoresEquipo);
                    hashmapEquipos.put(equipo, equipoAux); //actualiza el equipo por equipoAux
                    System.out.println("Jugador añadido correctamente");
                }

            } catch (ParseException e) {
                System.out.println("invalid format");
            }

        }
    }

    public static void consultaJugadores() {
        ArrayList<Integer> alac = new ArrayList<>();
        ArrayList<Integer> alar = new ArrayList<>();
        ArrayList<Integer> alaa = new ArrayList<>();

        ArrayList<Integer> basec = new ArrayList<>();
        ArrayList<Integer> baser = new ArrayList<>();
        ArrayList<Integer> basea = new ArrayList<>();

        ArrayList<Integer> pibotc = new ArrayList<>();
        ArrayList<Integer> pibotr = new ArrayList<>();
        ArrayList<Integer> pibota = new ArrayList<>();

        int opcion2;
        int param1;
        int param2;
        ArrayList<Jugador> lista = new ArrayList<>();
        lista = listaCompletaJugadores(lista);
        do {
            opcion2 = menu2();
            switch (opcion2) {
                case 1:
                    String nombre = pedirCadena("Introduce el nombre del jugador:");
                    for (Jugador j : lista) {
                        if (j.getNombre().contains(nombre)) {
                            System.out.println(j);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Introduce el numero de canastas mayor/igual:");
                    param1 = intParametro();
                    for (Jugador j : lista) {
                        if (j.getCanastas() >= param1) {
                            System.out.println(j);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Introduce el primer numero del rango:");
                    param1 = intParametro();
                    System.out.println("Introduce el segundo numero del rango:");
                    param2 = intParametro();
                    for (Jugador j : lista) {
                        if (j.getAsistencias() > param1 && j.getAsistencias() < param2) {
                            System.out.println(j);
                        }
                    }
                    break;
                case 4:
                    String posicion = pedirCadena("Introduce la posicion que quieres buscar:");
                    for (Jugador j : lista) {
                        if (posicion.equalsIgnoreCase(String.valueOf(j.getPosicion()))) {
                            System.out.println(j);
                        }
                    }
                    break;
                case 5:
                    SimpleDateFormat fecha = new SimpleDateFormat("MM/dd/yyyy");
                    Date testDate = null;
                    String date = "";
                    try {
                        date = pedirCadena("Introduce la fecha con el siguiente formato: MM/dd/yyyy");
                        testDate = fecha.parse(date);
                    } catch (ParseException e) {
                        System.out.println("invalid format");
                    }

                    if (!fecha.format(testDate).equals(date)) {
                        System.out.println("invalid date!!");
                    } else {
                        System.out.println("valid date");
                    }
                    for (Jugador j : lista) {
                        if (j.getNacimiento().before(testDate)) {
                            System.out.println(j);
                        }
                    }
                    break;
                case 6:
                    for (Jugador j : lista) {
                        if (Posicion.ala == j.getPosicion()) {
                            alac.add(j.getCanastas());
                            alar.add(j.getRebotes());
                            alaa.add(j.getAsistencias());
                        }
                        if (Posicion.base == j.getPosicion()) {
                            basec.add(j.getCanastas());
                            baser.add(j.getRebotes());
                            basea.add(j.getAsistencias());
                        }
                        if (Posicion.pibot == j.getPosicion()) {
                            pibotc.add(j.getCanastas());
                            pibotr.add(j.getRebotes());
                            pibota.add(j.getAsistencias());
                        }
                    }
                    if (!alac.isEmpty()) {
                        media(alac, "ala", "canastas");
                        media(alaa, "ala", "asistencias");
                        media(alar, "ala", "rebotes");
                    }
                    if (!basec.isEmpty()) {
                        media(basec, "base", "canastas");
                        media(basea, "base", "asistencias");
                        media(baser, "base", "rebotes");
                    }
                    if (!pibotc.isEmpty()) {
                        media(pibotc, "pibot", "rebocanastastes");
                        media(pibota, "pibot", "asistencias");
                        media(pibotr, "pibot", "rebotes");
                    }
                    break;
                case 7:
                    for (Jugador j : lista) {
                        if (Posicion.ala == j.getPosicion()) {
                            alac.add(j.getCanastas());
                            alar.add(j.getRebotes());
                            alaa.add(j.getAsistencias());
                        }
                        if (Posicion.base == j.getPosicion()) {
                            basec.add(j.getCanastas());
                            baser.add(j.getRebotes());
                            basea.add(j.getAsistencias());
                        }
                        if (Posicion.pibot == j.getPosicion()) {
                            pibotc.add(j.getCanastas());
                            pibotr.add(j.getRebotes());
                            pibota.add(j.getAsistencias());
                        }
                    }
                    if (!alac.isEmpty()) {
                        media(alac, "ala", "canastas");
                        media(alaa, "ala", "asistencias");
                        media(alar, "ala", "rebotes");

                        max(alac, "ala", "canastas");
                        max(alaa, "ala", "asistencias");
                        max(alar, "ala", "rebotes");

                        min(alac, "ala", "canastas");
                        min(alaa, "ala", "asistencias");
                        min(alar, "ala", "rebotes");
                    }
                    if (!basec.isEmpty()) {
                        media(basec, "base", "canastas");
                        media(basea, "base", "asistencias");
                        media(baser, "base", "rebotes");

                        max(basec, "base", "canastas");
                        max(basea, "base", "asistencias");
                        max(baser, "base", "rebotes");

                        min(basec, "base", "canastas");
                        min(basea, "base", "asistencias");
                        min(baser, "base", "rebotes");
                    }
                    if (!pibotc.isEmpty()) {
                        media(pibotc, "pibot", "rebocanastastes");
                        media(pibota, "pibot", "asistencias");
                        media(pibotr, "pibot", "rebotes");

                        max(pibotc, "pibot", "rebocanastastes");
                        max(pibota, "pibot", "asistencias");
                        max(pibotr, "pibot", "rebotes");

                        min(pibotc, "pibot", "rebocanastastes");
                        min(pibota, "pibot", "asistencias");
                        min(pibotr, "pibot", "rebotes");
                    }
                    break;
                default:
                    break;
            }
        } while (opcion2 != 8);
    }

    public static ArrayList<Jugador> listaCompletaJugadores(ArrayList<Jugador> lista) {
        ArrayList<Equipo> listaEquipos = new ArrayList<>(hashmapEquipos.values());
        for (Equipo equipos : listaEquipos) {
            for (Jugador jugadores : equipos.getJugadores()) {
                lista.add(jugadores);
            }
        }
        return lista;
    }

    public static int intParametro() {
        int param;
        int repe = 0;
        do {
            if (repe == 0) {
                param = pedirEntero("");
                repe = 1;
            } else {
                param = pedirEntero("Introduce un parametro mayor que 0");
            }
        } while (param < 0);
        return param;
    }

    public static void media(ArrayList<Integer> jugadores, String posicion, String para) {
        int media = 0;
        for (int i : jugadores) {
            media += i;
        }
        System.out.println("La media de " + para + " de " + posicion + " es " + media / jugadores.size());
    }

    public static void max(ArrayList<Integer> jugadores, String posicion, String para) {
        int max = 0;
        for (int i : jugadores) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println("La media de " + para + " de " + posicion + " es " + max);
    }

    public static void min(ArrayList<Integer> jugadores, String posicion, String para) {
        int max = 0;
        for (int i : jugadores) {
            if (i > max) {
                max = i;
            }
        }
        int min = max;
        for (int i : jugadores) {
            if (i < min) {
                min = i;
            }
        }
        System.out.println("La media de " + para + " de " + posicion + " es " + min);
    }

    public static void consultaEquipos() {
        int opcion2 = 0;
        do {
            opcion2 = menu3();
            switch (opcion2) {
                case 1:
                    String localidad = pedirCadena("Introduce la localidad:");
                    for (Equipo e : hashmapEquipos.values()) {
                        if (e.getLocalidad().equalsIgnoreCase(localidad)) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 2:
                    String equipo = pedirCadena("Introduce el nombre del equipo:");
                    if (hashmapEquipos.containsKey(equipo)) {
                        System.out.println(hashmapEquipos.get(equipo).getJugadores());
                    } else {
                        System.out.println("Equipo no encontrado");
                    }
                    break;
                case 3:
                    equipo = pedirCadena("Introduce el nombre del equipo:");
                    String posicion = pedirCadena("Introduce la posicion:");
                    if (hashmapEquipos.containsKey(equipo)) {
                        for (Jugador j : hashmapEquipos.get(equipo).getJugadores()) {
                            if (posicion.equalsIgnoreCase(String.valueOf(j.getPosicion()))) {
                                System.out.println(j);
                            }
                        }
                    }
                    break;
                case 4:
                    equipo = pedirCadena("Introduce el nombre del equipo:");
                    int canastas = 0;
                    Jugador maxc = new Jugador();
                    if (hashmapEquipos.containsKey(equipo)) {
                        for (Jugador j : hashmapEquipos.get(equipo).getJugadores()) {
                            if (j.getCanastas() > canastas) {
                                canastas = j.getCanastas();
                                maxc = j;
                            }
                        }
                        System.out.println("El jugador con mas canastas del equpo " + equipo + "es: " + maxc);
                    }
                    break;
                default:
                    break;
            }
        } while (opcion2 != 5);
    }

}
