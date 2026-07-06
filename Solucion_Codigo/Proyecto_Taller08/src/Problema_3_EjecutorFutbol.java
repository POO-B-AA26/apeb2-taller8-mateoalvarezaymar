
/**
*Problema 3 - Juego de fútbol "Estadísticas"
Se desea realizar una aplicación que permita a un periodista deportivo llevar las 
estadísticas de los jugadores de un equipo de fútbol para poder valorar su actuación en el partido.
Cada jugador se identifica por su nombre, número de dorsal y Rut
Los jugadores se dividen en tres categorías:
Atacantes
Defensores
Porteros
Para todos los jugadores se desea contabilizar el número de goles marcados, además en 
el caso de los jugadores de campo se contabilizan los pases realizados con éxito y el 
número de balones recuperados. En el caso de los porteros se contabilizan las atajadas realizadas.
Valoración del jugador
Cálculo base para todos los jugadores:
valor_goles = goles * 30
Valor adicional según tipo de jugador:
Atacantes
valor += recuperaciones * 3
Defensores
valor += recuperaciones * 4
Porteros
valor += atajadas * 5
Note
Se debe aplicar polimorfismo mediante la aplicación de herencia, encapsulamiento de atributos 
y comportamientos comunes, y especializar comportamiento según el tipo de jugador.
* Problema 03:
 * @author Mateo Alavarez
 * version 1.0
 */
abstract class Jugador {
    private String nombre;
    private int dorsal;
    private String rut;
    private int goles;
    public Jugador(String nombre, int dorsal, String rut) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
        this.goles = 0;
    }
     public int valorGoles() {
        return goles * 30;
    }
     public abstract int calcularValoracion();
     public abstract String tipojugador();
 
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
 
    public int getDorsal() { return dorsal; }
    public void setDorsal(int dorsal) { this.dorsal = dorsal; }
 
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
 
    public int getGoles() { return goles; }
    public void setGoles(int goles) { this.goles = goles; }
    @Override
    public String toString() {
        return "Jugador{nombre=" + nombre +
               ", dorsal=" + dorsal +
               ", rut=" + rut +
               ", goles=" + goles +
               ", valoracion=" + calcularValoracion() + "}";
    }
}
 abstract class JugadorCampo extends Jugador {
    private int pasesExitosos;
    private int recuperaciones;
    public JugadorCampo(String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
        this.pasesExitosos = 0;
        this.recuperaciones = 0;
    }
    public int getPasesExitosos() { return pasesExitosos; }
    public void setPasesExitosos(int pasesExitosos) { this.pasesExitosos = pasesExitosos; }
    public int getRecuperaciones() { return recuperaciones; }
    public void setRecuperaciones(int recuperaciones) { this.recuperaciones = recuperaciones; }
    @Override
    public String toString() {
        return super.toString().replace("}", "") +
               ", pasesExitosos=" + pasesExitosos +
               ", recuperaciones=" + recuperaciones + "}";
    }
}
class Atacante extends JugadorCampo {
 
    public Atacante(String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
    }
    @Override
    public int calcularValoracion() {
        return valorGoles() + (getRecuperaciones() * 3);
    }
 
    @Override
    public String tipojugador() { return "Atacante"; }
 
    @Override
    public String toString() {
        return "Atacante{" + super.toString() + "}";
    }
}
 class Defensor extends JugadorCampo {
 
    public Defensor(String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
    }
     @Override
    public int calcularValoracion() {
        return valorGoles() + (getRecuperaciones() * 4);
    }
    @Override
    public String tipojugador() { return "Defensor"; }
    @Override
    public String toString() {
        return "Defensor{" + super.toString() + "}";
    }
}
 class Portero extends Jugador {
    private int atajadas;
    public Portero(String nombre, int dorsal, String rut) {
        super(nombre, dorsal, rut);
        this.atajadas = 0;
    }
    public int getAtajadas() { return atajadas; }
    public void setAtajadas(int atajadas) { this.atajadas = atajadas; }
     @Override
    public int calcularValoracion() {
        return valorGoles() + (atajadas * 5);
    }
    @Override
    public String tipojugador() { return "Portero"; }
 
    @Override
    public String toString() {
        return "Portero{" + super.toString().replace("}", "") +
               ", atajadas=" + atajadas + "}}";
    }
}
public class Problema_3_EjecutorFutbol {
    public static void main(String[] args) {
         Atacante a1 = new Atacante("Jordy Caicedo", 9, "12345678-9");
        Atacante a2 = new Atacante("Enner Valencia", 13, "98765432-1");
        Defensor d1 = new Defensor("Antonio Valencia", 5, "11223344-5");
        Defensor d2 = new Defensor("Robert Arboleda", 3, "55667788-9");
        Portero  p1 = new Portero ("Alexander Dominguez", 1, "99887766-5");
 
        a1.setGoles(2);        a1.setPasesExitosos(15); a1.setRecuperaciones(3);
        a2.setGoles(1);        a2.setPasesExitosos(20); a2.setRecuperaciones(5);
        d1.setGoles(0);        d1.setPasesExitosos(30); d1.setRecuperaciones(8);
        d2.setGoles(1);        d2.setPasesExitosos(25); d2.setRecuperaciones(10);
        p1.setGoles(0);        p1.setAtajadas(7);
 
        Jugador[] equipo = {a1, a2, d1, d2, p1};

        System.out.println("----- ESTADISTICAS DEL PARTIDO -----\n");
        for (Jugador j : equipo) {
            System.out.println("[" + j.tipojugador() + "] " + j.toString());
            System.out.println();
        }
        System.out.println("----- MEJOR JUGADOR DEL PARTIDO -----\n");
        Jugador mejor = equipo[0];
        for (Jugador j : equipo) {
            if (j.calcularValoracion() > mejor.calcularValoracion()) {
                mejor = j;
            }
        }
        System.out.println("MVP: [" + mejor.tipojugador() + "] " +
                           mejor.getNombre() +
                           " - Valoracion: " + mejor.calcularValoracion());
    }
}
/*
run:
----- ESTADISTICAS DEL PARTIDO -----

[Atacante] Atacante{Jugador{nombre=Jordy Caicedo, dorsal=9, rut=12345678-9, goles=2, valoracion=69, pasesExitosos=15, recuperaciones=3}}

[Atacante] Atacante{Jugador{nombre=Enner Valencia, dorsal=13, rut=98765432-1, goles=1, valoracion=45, pasesExitosos=20, recuperaciones=5}}

[Defensor] Defensor{Jugador{nombre=Antonio Valencia, dorsal=5, rut=11223344-5, goles=0, valoracion=32, pasesExitosos=30, recuperaciones=8}}

[Defensor] Defensor{Jugador{nombre=Robert Arboleda, dorsal=3, rut=55667788-9, goles=1, valoracion=70, pasesExitosos=25, recuperaciones=10}}

[Portero] Portero{Jugador{nombre=Alexander Dominguez, dorsal=1, rut=99887766-5, goles=0, valoracion=35, atajadas=7}}

----- MEJOR JUGADOR DEL PARTIDO -----

MVP: [Defensor] Robert Arboleda - Valoracion: 70
BUILD SUCCESSFUL (total time: 0 seconds)
*/