package aed;

import java.util.ArrayList;

// import aed.SistemaSIU.CargoDocente;

public class Materia {

    private int contadorInscriptos = 0;
    private ArrayList<String> listaInscriptos = new ArrayList<>();
    private ArrayList<PunteroMateriaYNombre> listaCarreraMateria = new ArrayList<>();
    private int[] cargos = {0,0,0,0};

    public int[] getPlantel(){
        return cargos;
    }

    public void sumarEstudiante(String libreta) {
        // ############RECONTRA PLACEHOLDER NO SE SI ESTA BIEN
        listaInscriptos.add(libreta);
        this.contadorInscriptos++;
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
