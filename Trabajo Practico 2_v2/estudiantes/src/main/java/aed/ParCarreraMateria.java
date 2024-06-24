package aed;

public class ParCarreraMateria {

    /*
     * Invariante de representacion:
     * 
     * -> carrera y nombreMateria no son nulos
     * 
     */

    String carrera;
    String nombreMateria;

    public ParCarreraMateria(String carrera, String nombreMateria) {
        //O(1)
        this.carrera = carrera;
        //O(1)
        this.nombreMateria = nombreMateria;
    }

    public String getNombreMateria() {
        //O(1)
        return this.nombreMateria;
    }
    
    public String getCarrera() {
        //O(1)
        return this.carrera;
    }
}
