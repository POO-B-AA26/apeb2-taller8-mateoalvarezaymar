import java.util.ArrayList;
/**
 *Problema 5 - Plataforma de apoyo a emprendimientos en Loja
Una organización local ha creado una plataforma digital para registrar y promover 
emprendimientos de la ciudad de Loja. Los emprendimientos pueden clasificarse por tipo 
(tecnológico, artesanal, agrícola, gastronómico, etc.), y cada uno presenta información 
detallada sobre su misión, productos o servicios, y datos de contacto. Algunos 
emprendimientos requieren acompañamiento técnico por parte de mentores especializados, 
quienes brindan asesoría en áreas como marketing, contabilidad o desarrollo de software. 
Además, los emprendimientos pueden participar en ferias locales, donde presentan sus productos 
y compiten por reconocimientos. Existen emprendimientos que evolucionan a lo largo del tiempo 
y extienden sus líneas de productos o abren nuevas sedes.
Requisitos funcionales:
Diferenciar los distintos tipos de emprendimientos mediante herencia.
Asociar uno o más mentores a los emprendimientos que lo requieran.
Implementar comportamientos polimórficos en función del tipo de feria o actividad en la que participan.
Permitir registrar productos o servicios que ofrece cada emprendimiento.
Simular la evolución de un emprendimiento con el tiempo (crecimiento, diversificación, expansión).
* Problema 05:
 * @author Mateo Alvarez
 * version 1.0
 */
class Mentor{
    private String nombre;
    private String areaEspecial;
    public Mentor(String nombre, String areaEspecial) {
        this.nombre = nombre;
        this.areaEspecial = areaEspecial;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getAreaEspecial(){
        return areaEspecial;
    }
    public void setAreaEspecial(String areaEspecial){
        this.areaEspecial = areaEspecial;
    }
    @Override
    public String toString() {
        return "Mentor{" + "nombre=" + nombre + ", areaEspecial=" + areaEspecial + '}';
    }
}
abstract class Emprendimiento {
    private String nombre;
    private String mision;
    private String contacto;
    private int sedes;
    private ArrayList<String> productos;
    private ArrayList<Mentor> mentores;
    public Emprendimiento(String nombre, String mision, String contacto) {
        this.nombre = nombre;
        this.mision = mision;
        this.contacto = contacto;
        this.sedes = 1;
        this.productos = new ArrayList<String>();
        this.mentores = new ArrayList<Mentor>();
    }
     public void agregarProducto(String producto) {
        productos.add(producto);
    }
     public void agregarMentor(Mentor mentor) {
        mentores.add(mentor);
    }
     public abstract String participarEnFeria(String tipoferia);
     public abstract String evolucionar();
 
    public abstract String tipoEmprendimiento();
 
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
 
    public String getMision() { return mision; }
    public void setMision(String mision) { this.mision = mision; }
 
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
 
    public int getSedes() { return sedes; }
    public void setSedes(int sedes) { this.sedes = sedes; }
 
    public ArrayList<String> getProductos() { return productos; }
    public ArrayList<Mentor> getMentores() { return mentores; }
    @Override
    public String toString() {
        return "Emprendimiento{nombre=" + nombre +
               ", tipo=" + tipoEmprendimiento() +
               ", mision=" + mision +
               ", contacto=" + contacto +
               ", sedes=" + sedes +
               ", productos=" + productos +
               ", mentores=" + mentores + "}";
    }
}
 class EmprendimientoTecnologico extends Emprendimiento {
    private String tecnologiaPrincipal;
    public EmprendimientoTecnologico(String nombre, String mision,
                                     String contacto, String tecnologiaPrincipal) {
        super(nombre, mision, contacto);
        this.tecnologiaPrincipal = tecnologiaPrincipal;
    }
    public String getTecnologiaPrincipal() { return tecnologiaPrincipal; }
    public void setTecnologiaPrincipal(String t) { this.tecnologiaPrincipal = t; }
     @Override
    public String participarEnFeria(String tipoferia) {
        return "[" + tipoEmprendimiento() + "] " + getNombre() +
               " participa en feria " + tipoferia +
               " con demo de " + tecnologiaPrincipal;
    }
     @Override
    public String evolucionar() {
        setSedes(getSedes() + 1);
        agregarProducto("Nuevo modulo digital");
        return getNombre() + " evoluciono: nueva sede y modulo digital agregado. Sedes: " + getSedes();
    }
    @Override
    public String tipoEmprendimiento() { return "Tecnologico"; }
 
    @Override
    public String toString() {
        return "EmprendimientoTecnologico{tecnologia=" + tecnologiaPrincipal +
               ", " + super.toString() + "}";
    }
}
 class EmprendimientoArtesanal extends Emprendimiento {
    private String materialPrincipal;
    public EmprendimientoArtesanal(String nombre, String mision,
                                   String contacto, String materialPrincipal) {
        super(nombre, mision, contacto);
        this.materialPrincipal = materialPrincipal;
    }
    public String getMaterialPrincipal() { return materialPrincipal; }
    public void setMaterialPrincipal(String m) { this.materialPrincipal = m; }
     @Override
    public String participarEnFeria(String tipoferia) {
        return "[" + tipoEmprendimiento() + "] " + getNombre() +
               " participa en feria " + tipoferia +
               " exhibiendo artesanias de " + materialPrincipal;
    }
     @Override
    public String evolucionar() {
        agregarProducto("Nueva linea artesanal");
        return getNombre() + " evoluciono: nueva linea de productos artesanales agregada.";
    }
    @Override
    public String tipoEmprendimiento() { return "Artesanal"; }
    @Override
    public String toString() {
        return "EmprendimientoArtesanal{material=" + materialPrincipal +
               ", " + super.toString() + "}";
    }
}
 class EmprendimientoAgricola extends Emprendimiento {
    private String cultivoPrincipal;
    public EmprendimientoAgricola(String nombre, String mision,
                                  String contacto, String cultivoPrincipal) {
        super(nombre, mision, contacto);
        this.cultivoPrincipal = cultivoPrincipal;
    }
    public String getCultivoPrincipal() { return cultivoPrincipal; }
    public void setCultivoPrincipal(String c) { this.cultivoPrincipal = c; }
     @Override
    public String participarEnFeria(String tipoferia) {
        return "[" + tipoEmprendimiento() + "] " + getNombre() +
               " participa en feria " + tipoferia +
               " con cosecha de " + cultivoPrincipal;
    }
     @Override
    public String evolucionar() {
        agregarProducto("Nuevo cultivo organico");
        setSedes(getSedes() + 1);
        return getNombre() + " evoluciono: nueva parcela y cultivo organico. Sedes: " + getSedes();
    }
    @Override
    public String tipoEmprendimiento() { return "Agricola"; }
    @Override
    public String toString() {
        return "EmprendimientoAgricola{cultivo=" + cultivoPrincipal +
               ", " + super.toString() + "}";
    }
}
 class EmprendimientoGastronomico extends Emprendimiento {
    private String especialidad;
    public EmprendimientoGastronomico(String nombre, String mision,
                                      String contacto, String especialidad) {
        super(nombre, mision, contacto);
        this.especialidad = especialidad;
    }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String e) { this.especialidad = e; }
     @Override
    public String participarEnFeria(String tipoferia) {
        return "[" + tipoEmprendimiento() + "] " + getNombre() +
               " participa en feria " + tipoferia +
               " con degustacion de " + especialidad;
    }
     @Override
    public String evolucionar() {
        setSedes(getSedes() + 1);
        agregarProducto("Nuevo menu de temporada");
        return getNombre() + " evoluciono: nueva sede y menu. Sedes: " + getSedes();
    }
    @Override
    public String tipoEmprendimiento() { return "Gastronomico"; }
 
    @Override
    public String toString() {
        return "EmprendimientoGastronomico{especialidad=" + especialidad +
               ", " + super.toString() + "}";
    }
}
public class Problema_5_EjecutorEmprendimientos {
    public static void main(String[] args) {
         Mentor m1 = new Mentor("Ana Torres",    "Marketing");
        Mentor m2 = new Mentor("Carlos Ruiz",   "Contabilidad");
        Mentor m3 = new Mentor("Luis Perez",    "Desarrollo de Software");
 
        EmprendimientoTecnologico e1 = new EmprendimientoTecnologico(
            "TechLoja", "Digitalizar Loja", "techloja@gmail.com", "Inteligencia Artificial");
 
        EmprendimientoArtesanal e2 = new EmprendimientoArtesanal(
            "ManosDeOro", "Preservar artesania lojana", "manosdeoroloja@gmail.com", "Barro");
 
        EmprendimientoAgricola e3 = new EmprendimientoAgricola(
            "CampoVerde", "Agricultura organica sustentable", "campoverde@gmail.com", "Cafe organico");
 
        EmprendimientoGastronomico e4 = new EmprendimientoGastronomico(
            "SaborLojano", "Rescatar gastronomia lojana", "saborlojano@gmail.com", "Repe blanco");
 
        e1.agregarProducto("App de gestion empresarial");
        e1.agregarProducto("Consultoria tecnologica");
        e2.agregarProducto("Vasijas de barro");
        e2.agregarProducto("Figuras decorativas");
        e3.agregarProducto("Cafe organico molido");
        e3.agregarProducto("Miel de abeja");
        e4.agregarProducto("Repe blanco");
        e4.agregarProducto("Cecina lojana");
 
        e1.agregarMentor(m1);
        e1.agregarMentor(m3);
        e2.agregarMentor(m2);
        e3.agregarMentor(m1);
        e4.agregarMentor(m2);
 
        Emprendimiento[] plataforma = {e1, e2, e3, e4};
 
        System.out.println("----- EMPRENDIMIENTOS REGISTRADOS -----\n");
        for (Emprendimiento e : plataforma) {
            System.out.println(e.toString());
            System.out.println();
        }
        System.out.println("----- FERIA DE EMPRENDIMIENTOS LOJA 2025 -----\n");
        for (Emprendimiento e : plataforma) {
            System.out.println(e.participarEnFeria("Loja Emprende 2025"));
        }
        System.out.println("\n----- EVOLUCION DE EMPRENDIMIENTOS -----\n");
        for (Emprendimiento e : plataforma) {
            System.out.println(e.evolucionar());
        }
        System.out.println("\n----- ESTADO FINAL -----\n");
        for (Emprendimiento e : plataforma) {
            System.out.println(e.toString());
            System.out.println();
        }
    }
}
/*
run:
----- EMPRENDIMIENTOS REGISTRADOS -----

EmprendimientoTecnologico{tecnologia=Inteligencia Artificial, Emprendimiento{nombre=TechLoja, tipo=Tecnologico, mision=Digitalizar Loja, contacto=techloja@gmail.com, sedes=1, productos=[App de gestion empresarial, Consultoria tecnologica], mentores=[Mentor{nombre=Ana Torres, areaEspecial=Marketing}, Mentor{nombre=Luis Perez, areaEspecial=Desarrollo de Software}]}}

EmprendimientoArtesanal{material=Barro, Emprendimiento{nombre=ManosDeOro, tipo=Artesanal, mision=Preservar artesania lojana, contacto=manosdeoroloja@gmail.com, sedes=1, productos=[Vasijas de barro, Figuras decorativas], mentores=[Mentor{nombre=Carlos Ruiz, areaEspecial=Contabilidad}]}}

EmprendimientoAgricola{cultivo=Cafe organico, Emprendimiento{nombre=CampoVerde, tipo=Agricola, mision=Agricultura organica sustentable, contacto=campoverde@gmail.com, sedes=1, productos=[Cafe organico molido, Miel de abeja], mentores=[Mentor{nombre=Ana Torres, areaEspecial=Marketing}]}}

EmprendimientoGastronomico{especialidad=Repe blanco, Emprendimiento{nombre=SaborLojano, tipo=Gastronomico, mision=Rescatar gastronomia lojana, contacto=saborlojano@gmail.com, sedes=1, productos=[Repe blanco, Cecina lojana], mentores=[Mentor{nombre=Carlos Ruiz, areaEspecial=Contabilidad}]}}

----- FERIA DE EMPRENDIMIENTOS LOJA 2025 -----

[Tecnologico] TechLoja participa en feria Loja Emprende 2025 con demo de Inteligencia Artificial
[Artesanal] ManosDeOro participa en feria Loja Emprende 2025 exhibiendo artesanias de Barro
[Agricola] CampoVerde participa en feria Loja Emprende 2025 con cosecha de Cafe organico
[Gastronomico] SaborLojano participa en feria Loja Emprende 2025 con degustacion de Repe blanco

----- EVOLUCION DE EMPRENDIMIENTOS -----

TechLoja evoluciono: nueva sede y modulo digital agregado. Sedes: 2
ManosDeOro evoluciono: nueva linea de productos artesanales agregada.
CampoVerde evoluciono: nueva parcela y cultivo organico. Sedes: 2
SaborLojano evoluciono: nueva sede y menu. Sedes: 2

----- ESTADO FINAL -----

EmprendimientoTecnologico{tecnologia=Inteligencia Artificial, Emprendimiento{nombre=TechLoja, tipo=Tecnologico, mision=Digitalizar Loja, contacto=techloja@gmail.com, sedes=2, productos=[App de gestion empresarial, Consultoria tecnologica, Nuevo modulo digital], mentores=[Mentor{nombre=Ana Torres, areaEspecial=Marketing}, Mentor{nombre=Luis Perez, areaEspecial=Desarrollo de Software}]}}

EmprendimientoArtesanal{material=Barro, Emprendimiento{nombre=ManosDeOro, tipo=Artesanal, mision=Preservar artesania lojana, contacto=manosdeoroloja@gmail.com, sedes=1, productos=[Vasijas de barro, Figuras decorativas, Nueva linea artesanal], mentores=[Mentor{nombre=Carlos Ruiz, areaEspecial=Contabilidad}]}}

EmprendimientoAgricola{cultivo=Cafe organico, Emprendimiento{nombre=CampoVerde, tipo=Agricola, mision=Agricultura organica sustentable, contacto=campoverde@gmail.com, sedes=2, productos=[Cafe organico molido, Miel de abeja, Nuevo cultivo organico], mentores=[Mentor{nombre=Ana Torres, areaEspecial=Marketing}]}}

EmprendimientoGastronomico{especialidad=Repe blanco, Emprendimiento{nombre=SaborLojano, tipo=Gastronomico, mision=Rescatar gastronomia lojana, contacto=saborlojano@gmail.com, sedes=2, productos=[Repe blanco, Cecina lojana, Nuevo menu de temporada], mentores=[Mentor{nombre=Carlos Ruiz, areaEspecial=Contabilidad}]}}

BUILD SUCCESSFUL (total time: 0 seconds)

*/