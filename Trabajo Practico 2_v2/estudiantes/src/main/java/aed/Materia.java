package aed;

import java.util.ArrayList;

    /*
     * 
     * Invariante de representacion:
     * 
     *  -> contadorInscriptos debe ser positivo.
     * 
     *  -> la longitud de listaInscriptos es igual a
     *  contadorInscriptos
     * 
     *  -> todos los elementos de lista inscriptos 
     *  son strings que representan 
     *  el identificador de un alumno particular
     * 
     *  -> todos los elementos de listaCarreraMateria tienen
     *  en su primer indice una carrera, y en su segundo indice
     *  el nombre de esta materia en dicha carrera.
     *  
     */

public class Materia {

    private int contadorInscriptos = 0;
    private ArrayList<String> listaInscriptos = new ArrayList<>();
    private ArrayList<PunteroMateriaYNombre> listaCarreraMateria = new ArrayList<>();
    private int[] cargos = {0,0,0,0};

    public int[] getPlantel(){
        return cargos;
    }
    public void agregarReferencia(Trie<Materia> punteroCarrera, String nombreMateria){
        PunteroMateriaYNombre nueva = new PunteroMateriaYNombre(punteroCarrera, nombreMateria);
        listaCarreraMateria.add(nueva);
    }
    public int getLargoReferencias(){
        return listaCarreraMateria.size();
    }
    public PunteroMateriaYNombre getReferencia(int indice){
        return listaCarreraMateria.get(indice);
    }

    public void sumarEstudiante(String libreta) {
        listaInscriptos.add(libreta);
        this.contadorInscriptos++;
    }
    public String[] getInscriptos(){

        int largo = listaInscriptos.size();
        int i =0;
        String[] res=new String[largo];
        while (i<largo) {
            res[i]=listaInscriptos.get(i);
            i++;
        }
        return res;
    }

    public int cantidadDeInscriptos() {
        return this.contadorInscriptos;
    }

    public void agregarAY2() {
        cargos[3] += 1;
    }

    public void agregarAY1() {
        cargos[2] += 1;
    }

    public void agregarJTP() {
        cargos[1] += 1;
    }

    public void agregarProfe() {
        cargos[0] += 1;
    }
}
