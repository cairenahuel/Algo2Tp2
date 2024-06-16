package aed;

import java.util.ArrayList;

// import aed.SistemaSIU.CargoDocente;

public class Materia {

    private int contadorInscriptos=0;
    private ArrayList<String> listaInscriptos=new ArrayList<>();
    private ArrayList<PunteroMateriaYNombre> listaCarreraMateria=new ArrayList<>();
    private Integer[] cargos = { 0, 0, 0, 0 };

    public void sumarEstudiante(String libreta){
        //############RECONTRA PLACEHOLDER NO SE SI ESTA BIEN
        listaInscriptos.add(libreta);
        this.contadorInscriptos++;
    }

    public int cantidadDeInscriptos() {
        return this.contadorInscriptos;
    }
}
