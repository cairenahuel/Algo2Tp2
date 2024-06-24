package aed;

import java.util.ArrayList;

/*
 * 
 * Invariante de representacion:
 * 
 *  -> contadorInscriptos debe ser positivo o 0.
 * 
 *  -> la longitud de listaInscriptos es igual a
 *  contadorInscriptos
 * 
 *  -> todos los elementos de lista inscriptos 
 *  son strings que representan 
 *  el identificador de un alumno particular
 * 
 *  -> todos los elementos de listaCarreraMateria son de la clase
 *  PunteroMateriaYNombre, los cuales contienen un puntero a una carrera
 *  dentro del trie de carreras y el nombre específico de una materia en dicha carrera.
 *  
 */

public class Materia {

    private int contadorInscriptos = 0;
    private ArrayList<String> listaInscriptos = new ArrayList<>();
    private ArrayList<PunteroMateriaYNombre> listaCarreraMateria = new ArrayList<>();
    private int[] cargos = { 0, 0, 0, 0 };

    public int[] getPlantel() {
        // O(1)
        return cargos;
    }

    public void agregarReferencia(Trie<Materia> punteroCarrera, String nombreMateria) {
        // O(1)
        PunteroMateriaYNombre nueva = new PunteroMateriaYNombre(punteroCarrera, nombreMateria);
        // O(1)
        listaCarreraMateria.add(nueva);
    }

    public int getLargoReferencias() {
        // O(1)
        return listaCarreraMateria.size();
    }

    public PunteroMateriaYNombre getReferencia(int indice) {
        // O(1)
        return listaCarreraMateria.get(indice);
    }

    public void sumarEstudiante(String libreta) {
        // O(1)
        listaInscriptos.add(libreta);
        // O(1)
        this.contadorInscriptos++;
    }

    public String[] getInscriptos() {

        // O(1)
        int cantidadInscriptos = listaInscriptos.size();
        // O(1)
        int i = 0;
        // O(1)
        String[] res = new String[cantidadInscriptos];

        // O(cantidadInscriptos)
        while (i < cantidadInscriptos) {
            // O(1)
            res[i] = listaInscriptos.get(i);
            // O(1)
            i++;
        }
        // O(1)
        return res;
    }
    // O(cantidadInscriptos)

    public int cantidadDeInscriptos() {
        // O(1)
        return this.contadorInscriptos;
    }

    public void agregarAY2() {
        // O(1)
        cargos[3] += 1;
    }

    public void agregarAY1() {
        // O(1)
        cargos[2] += 1;
    }

    public void agregarJTP() {
        // O(1)
        cargos[1] += 1;
    }

    public void agregarProfe() {
        // O(1)
        cargos[0] += 1;
    }
}
