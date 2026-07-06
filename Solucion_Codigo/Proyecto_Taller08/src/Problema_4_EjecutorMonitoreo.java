
/**
 *Problema 4 - Sistema de monitoreo de impactos del cambio climático en Ecuador
Una red de monitoreo ambiental tiene como objetivo registrar, analizar y reportar 
los impactos del cambio climático en diferentes regiones. En cada ubicación se 
instalan dispositivos capaces de medir distintos indicadores climáticos como 
temperatura, precipitación, calidad del aire, y humedad del suelo. Dependiendo 
de la región (costa, sierra y oriente), los dispositivos pueden variar en capacidades 
y protocolos de recolección.
Los datos recolectados deben almacenarse y analizarse periódicamente. Además, ciertas 
ubicaciones requieren generar reportes personalizados que destaquen riesgos ambientales 
como sequías, deslizamientos o contaminación del aire. Algunos dispositivos pueden 
comportarse de forma especializada para detectar únicamente ciertos tipos de indicadores 
dependiendo de la región (costa, sierra y oriente).
Requisitos funcionales:
Representar diferentes tipos de dispositivos y sus especializaciones, para la costa, sierra y oriente.
Implementar métodos polimórficos que permitan procesar los datos según los tipos de dispositivos 
y sus especializaciones, para la costa, sierra y oriente.
Generar reportes dinámicos en función del tipo de riesgo ambiental detectado según la región
* Problema 04:
 * @author Mateo Alvarez
 * version 1.0
 */
abstract class Dispositivo{
    private String ubicacion;
    private double temperatura;
    private double precipitacion;
    public Dispositivo(String ubicacion, double temperatura, double precipitacion) {
        this.ubicacion = ubicacion;
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
    }
    public abstract void medirIndicadores();
    public abstract String generarReporte();
    public String getUbicacion(){
        return ubicacion;
    }
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
    public double getTemperatura(){
        return temperatura;
    }
    public void setTemperatura(double temperatura){
        this.temperatura = temperatura;
    }
    public double getPrecipitacion(){
        return precipitacion;
    }
    public void setPrecipitacion(double precipitacion){
        this.precipitacion = precipitacion;
    }
    @Override
    public String toString() {
        return "Dispositivo{" + "ubicacion=" + ubicacion + ", temperatura=" + temperatura + ", precipitacion=" + precipitacion + '}';
    }
}
class DispositivoCosta extends Dispositivo {
    private double calidadAire;   
    private double humedad;
    public DispositivoCosta(String ubicacion, double temperatura,
                            double precipitacion, double calidadAire, double humedad) {
        super(ubicacion, temperatura, precipitacion);
        this.calidadAire = calidadAire;
        this.humedad = humedad;
    }
    public double getCalidadAire() { return calidadAire; }
    public void setCalidadAire(double calidadAire) { this.calidadAire = calidadAire; }

    public double getHumedad() { return humedad; }
    public void setHumedad(double humedad) { this.humedad = humedad; }
 
    @Override
    public void medirIndicadores() {
        System.out.println("[COSTA] Midiendo: temperatura=" + getTemperatura() +
                           "C, calidad aire=" + calidadAire +
                           ", humedad=" + humedad + "%");
    }
     @Override
    public String generarReporte() {
        String riesgo = calidadAire < 40 ? "ALTO riesgo de contaminacion del aire" :
                        calidadAire < 70 ? "MODERADO riesgo de contaminacion del aire" :
                                           "Calidad del aire ACEPTABLE";
        return toString() + " -- " + riesgo;
    }
    @Override
    public String toString() {
        return "DispositivoCosta{calidadAire=" + calidadAire +
               ", humedad=" + humedad +
               ", " + super.toString() + "}";
    }
}
class DispositivoSierra extends Dispositivo {
    private double humedadSuelo; 
    private double nivelRio;      
    public DispositivoSierra(String ubicacion, double temperatura,
                             double precipitacion, double humedadSuelo, double nivelRio) {
        super(ubicacion, temperatura, precipitacion);
        this.humedadSuelo = humedadSuelo;
        this.nivelRio = nivelRio;
    }
    public double getHumedadSuelo() { return humedadSuelo; }
    public void setHumedadSuelo(double humedadSuelo) { this.humedadSuelo = humedadSuelo; }
 
    public double getNivelRio() { return nivelRio; }
    public void setNivelRio(double nivelRio) { this.nivelRio = nivelRio; }
     @Override
    public void medirIndicadores() {
        System.out.println("[SIERRA] Midiendo: temperatura=" + getTemperatura() +
                           "C, precipitacion=" + getPrecipitacion() +
                           "mm, humedadSuelo=" + humedadSuelo +
                           "%, nivelRio=" + nivelRio + "m");
    }
     @Override
    public String generarReporte() {
        String riesgo = (humedadSuelo > 80 && getPrecipitacion() > 50) ?
                        "ALTO riesgo de deslizamiento" :
                        humedadSuelo > 60 ? "MODERADO riesgo de deslizamiento" :
                                            "Condiciones ESTABLES";
        return toString() + " -- " + riesgo;
    }
    @Override
    public String toString() {
        return "DispositivoSierra{humedadSuelo=" + humedadSuelo +
               "%, nivelRio=" + nivelRio +
               "m, " + super.toString() + "}";
    }
}
class DispositivoOriente extends Dispositivo {
    private double humedadBosque; 
    private double nivelInundacion;
    public DispositivoOriente(String ubicacion, double temperatura,
                              double precipitacion, double humedadBosque, double nivelInundacion) {
        super(ubicacion, temperatura, precipitacion);
        this.humedadBosque = humedadBosque;
        this.nivelInundacion = nivelInundacion;
    }
    public double getHumedadBosque() { return humedadBosque; }
    public void setHumedadBosque(double humedadBosque) { this.humedadBosque = humedadBosque; }
 
    public double getNivelInundacion() { return nivelInundacion; }
    public void setNivelInundacion(double nivelInundacion) { this.nivelInundacion = nivelInundacion; }
     @Override
    public void medirIndicadores() {
        System.out.println("[ORIENTE] Midiendo: temperatura=" + getTemperatura() +
                           "C, precipitacion=" + getPrecipitacion() +
                           "mm, humedadBosque=" + humedadBosque +
                           "%, nivelInundacion=" + nivelInundacion + "m");
    }
     @Override
    public String generarReporte() {
        String riesgo = nivelInundacion > 2.0 ? "ALTO riesgo de inundacion" :
                        humedadBosque < 30    ? "ALTO riesgo de sequia" :
                        nivelInundacion > 1.0 ? "MODERADO riesgo de inundacion" :
                                                "Condiciones NORMALES";
        return toString() + " -- " + riesgo;
    }
    @Override
    public String toString() {
        return "DispositivoOriente{humedadBosque=" + humedadBosque +
               "%, nivelInundacion=" + nivelInundacion +
               "m, " + super.toString() + "}";
    }
}
public class Problema_4_EjecutorMonitoreo {
    public static void main(String[] args) {
         Dispositivo costa   = new DispositivoCosta  ("Guayaquil",  32.5, 15.0, 35.0, 78.0);
        Dispositivo sierra  = new DispositivoSierra ("Loja",      18.0, 65.0, 85.0,  3.2);
        Dispositivo oriente = new DispositivoOriente("Tena",       27.0, 90.0, 25.0,  2.5);
 
        Dispositivo[] dispositivos = {costa, sierra, oriente};
        System.out.println("----- MEDICION DE INDICADORES -----\n");
        for (Dispositivo d : dispositivos) {
            d.medirIndicadores();
        }
        System.out.println("\n----- REPORTES DE RIESGO AMBIENTAL -----\n");
        for (Dispositivo d : dispositivos) {
            System.out.println(d.generarReporte());
            System.out.println();
        }
    }
}
/*
run:
----- MEDICION DE INDICADORES -----

[COSTA] Midiendo: temperatura=32.5C, calidad aire=35.0, humedad=78.0%
[SIERRA] Midiendo: temperatura=18.0C, precipitacion=65.0mm, humedadSuelo=85.0%, nivelRio=3.2m
[ORIENTE] Midiendo: temperatura=27.0C, precipitacion=90.0mm, humedadBosque=25.0%, nivelInundacion=2.5m

----- REPORTES DE RIESGO AMBIENTAL -----

DispositivoCosta{calidadAire=35.0, humedad=78.0, Dispositivo{ubicacion=Guayaquil, temperatura=32.5, precipitacion=15.0}} -- ALTO riesgo de contaminacion del aire

DispositivoSierra{humedadSuelo=85.0%, nivelRio=3.2m, Dispositivo{ubicacion=Loja, temperatura=18.0, precipitacion=65.0}} -- ALTO riesgo de deslizamiento

DispositivoOriente{humedadBosque=25.0%, nivelInundacion=2.5m, Dispositivo{ubicacion=Tena, temperatura=27.0, precipitacion=90.0}} -- ALTO riesgo de inundacion

BUILD SUCCESSFUL (total time: 0 seconds)

*/