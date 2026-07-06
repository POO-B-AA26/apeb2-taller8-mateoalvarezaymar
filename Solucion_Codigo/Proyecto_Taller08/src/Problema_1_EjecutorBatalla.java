import java.util.Random;
/**
*Problema 1 - Juego de roles
En un juego de rol, se desea implementar un sistema de combate en el que participen 
diferentes tipos de personajes: guerreros, magos y arqueros. Cada personaje tiene 
atributos y habilidades únicas, así como diferentes métodos de ataque y defensa.
El objetivo del juego es enfrentar a los personajes en batallas y determinar el ganador 
en función de sus habilidades, estrategias y atributos. Los guerreros se destacan por 
su fuerza y habilidades cuerpo a cuerpo, los magos por sus hechizos y poderes mágicos, 
y los arqueros por su precisión y habilidades a distancia.
El sistema debe permitir crear nuevos personajes de cada tipo, asignarles atributos 
iniciales, como puntos de vida y nivel de experiencia, y permitirles subir de nivel a 
medida que ganan batallas. Además, se debe implementar un algoritmo de combate que evalúe 
las habilidades de cada personaje y determine el resultado de la batalla.
Utilizando programación orientada a objetos, herencia y polimorfismo, implementa el 
sistema de combate y las clases necesarias para representar a los diferentes tipos de personajes. 
Asegúrate de que cada tipo de personaje tenga sus propias habilidades y métodos de ataque y defensa, 
y que puedan interactuar entre sí en las batallas.
 * Problema 01:
 * @author Mateo Alvarez
 * version 1.0
 */
abstract class Personaje {
    private int vidas;
    private int experiencia;
    private int batallasGanadas;
    public Personaje(int vidas) {
        this.vidas = vidas;
        this.experiencia = 0;
        this.batallasGanadas = 0;
    }
    public abstract boolean ataque(Personaje personaje);
    public abstract boolean defensa(Personaje personaje);
    public int getVidas() { return vidas; }
    public void setVidas(int vidas) { this.vidas = vidas; }
    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
 
    public int getBatallasGanadas() { return batallasGanadas; }
    public void setBatallasGanadas(int batallasGanadas) { this.batallasGanadas = batallasGanadas; }
 
    @Override
    public String toString() {
        return "Personaje{vidas=" + vidas +
               ", experiencia=" + experiencia +
               ", batallasGanadas=" + batallasGanadas + "}";
    }
}
class Guerrero extends Personaje {
    private int fuerza; 
    public Guerrero(int vidas, int fuerza) {
        super(vidas);
        this.fuerza = fuerza;
    }
    public int getFuerza() { return fuerza; }
    public void setFuerza(int fuerza) { this.fuerza = fuerza; }
 
    @Override
    public boolean ataque(Personaje personaje) {
        Random ale = new Random();
        boolean lucha = ale.nextInt(10) < fuerza;
        setExperiencia(getExperiencia() + 1);
        personaje.setExperiencia(personaje.getExperiencia() + 1);
 
        if (lucha) {
            setBatallasGanadas(getBatallasGanadas() + 1);
            personaje.setVidas(personaje.getVidas() - 1);
        } else {
            setVidas(getVidas() - 1);
            personaje.setBatallasGanadas(personaje.getBatallasGanadas() + 1);
        }
        return lucha;
    }
     @Override
    public boolean defensa(Personaje personaje) {
        Random ale = new Random();
        return ale.nextInt(10) < fuerza;
    }
    @Override
    public String toString() {
        return "Guerrero{fuerza=" + fuerza + ", " + super.toString() + "}";
    }
}
class Mago extends Personaje {
    private String hechizo;
    public Mago(int vidas, String hechizo) {
        super(vidas);
        this.hechizo = hechizo;
    }
    public String getHechizo() { return hechizo; }
    public void setHechizo(String hechizo) { this.hechizo = hechizo; }
     @Override
    public boolean ataque(Personaje personaje) {
        Random ale = new Random();
        boolean hechizoCertero = ale.nextInt(10) < 6;
        setExperiencia(getExperiencia() + 1);
        personaje.setExperiencia(personaje.getExperiencia() + 1);
        if (hechizoCertero) {
            setBatallasGanadas(getBatallasGanadas() + 1);
            personaje.setVidas(personaje.getVidas() - 1);
        } else {
            setVidas(getVidas() - 1);
            personaje.setBatallasGanadas(personaje.getBatallasGanadas() + 1);
        }
        return hechizoCertero;
    }
     @Override
    public boolean defensa(Personaje personaje) {
        Random ale = new Random();
        return ale.nextInt(10) < 4;
    }
    @Override
    public String toString() {
        return "Mago{hechizo=" + hechizo + ", " + super.toString() + "}";
    }
}
class Arquero extends Personaje {
    private int precision;
    private int cantidadFlechas;
    public Arquero(int vidas, int precision, int cantidadFlechas) {
        super(vidas);
        this.precision = precision;
        this.cantidadFlechas = cantidadFlechas;
    }
    public int getPrecision() { return precision; }
    public void setPrecision(int precision) { this.precision = precision; }
    public int getCantidadFlechas() { return cantidadFlechas; }
    public void setCantidadFlechas(int cantidadFlechas) { this.cantidadFlechas = cantidadFlechas; }
     @Override
    public boolean ataque(Personaje personaje) {
        Random ale = new Random();
        boolean flechaCertena = cantidadFlechas > 0 && ale.nextInt(10) < precision;
        if (cantidadFlechas > 0) cantidadFlechas--;
        setExperiencia(getExperiencia() + 1);
        personaje.setExperiencia(personaje.getExperiencia() + 1);
 
        if (flechaCertena) {
            setBatallasGanadas(getBatallasGanadas() + 1);
            personaje.setVidas(personaje.getVidas() - 1);
        } else {
            setVidas(getVidas() - 1);
            personaje.setBatallasGanadas(personaje.getBatallasGanadas() + 1);
        }
        return flechaCertena;
    }
     @Override
    public boolean defensa(Personaje personaje) {
        Random ale = new Random();
        return ale.nextInt(10) < precision;
    }
    @Override
    public String toString() {
        return "Arquero{precision=" + precision +
               ", cantidadFlechas=" + cantidadFlechas +
               ", " + super.toString() + "}";
    }
}
public class Problema_1_EjecutorBatalla {
    public static void main(String[] args) {
         Personaje guerrero1 = new Guerrero(3, 7);
        Personaje mago1     = new Mago(2, "Abracadabra");
        Personaje arquero1  = new Arquero(3, 9, 10);
 
        System.out.println("=== Guerrero vs Mago ===");
        System.out.println("Resultado ataque: " + guerrero1.ataque(mago1));
        System.out.println(guerrero1);
        System.out.println(mago1);
 
        System.out.println("");
 
        System.out.println("=== Guerrero vs Arquero ===");
        System.out.println("Resultado ataque: " + guerrero1.ataque(arquero1));
        System.out.println(guerrero1);
        System.out.println(arquero1);
 
        System.out.println("");
         System.out.println("=== Mago vs Arquero ===");
        System.out.println("Resultado ataque: " + mago1.ataque(arquero1));
        System.out.println(mago1);
        System.out.println(arquero1);
    }
}
 /*
run:
=== Guerrero vs Mago ===
Resultado ataque: true
Guerrero{fuerza=7, Personaje{vidas=3, experiencia=1, batallasGanadas=1}}
Mago{hechizo=Abracadabra, Personaje{vidas=1, experiencia=1, batallasGanadas=0}}

=== Guerrero vs Arquero ===
Resultado ataque: true
Guerrero{fuerza=7, Personaje{vidas=3, experiencia=2, batallasGanadas=2}}
Arquero{precision=9, cantidadFlechas=10, Personaje{vidas=2, experiencia=1, batallasGanadas=0}}

=== Mago vs Arquero ===
Resultado ataque: false
Mago{hechizo=Abracadabra, Personaje{vidas=0, experiencia=2, batallasGanadas=0}}
Arquero{precision=9, cantidadFlechas=10, Personaje{vidas=2, experiencia=2, batallasGanadas=1}}
BUILD SUCCESSFUL (total time: 0 seconds)
*/