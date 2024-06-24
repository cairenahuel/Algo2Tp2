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
        // O(1)
        this.punteroCarrera = carrera;
        // O(1)
        this.nombreEspecifico = nombre;
    }

    public Trie<Materia> getInstanciaCarrera() {
        // O(1)
        return punteroCarrera;
    }
    
    public String getNombreEspecifico() {
        // O(1)
        return nombreEspecifico;
    }
}
