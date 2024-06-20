package aed;

public class PunteroMateriaYNombre {

    /*
     * 
     * Invariante de representacion:
     * 
     * -> nombreEspecifico es un elemento de punteroCarrera
     * 
     */

    // Puntero hacia la carrera a la cual pertenece la materia
    private Trie<Materia> punteroCarrera;

    // Nombre especifico de esta materia en esta carrera
    private String nombreEspecifico;

    public PunteroMateriaYNombre(Trie<Materia> carrera, String nombre) {
        this.punteroCarrera = carrera;
        this.nombreEspecifico = nombre;
    }

    public Trie<Materia> getInstanciaCarrera() {
        return punteroCarrera;
    }

    public String getNombreEspecifico() {
        return nombreEspecifico;
    }
}
