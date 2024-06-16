package aed;

import java.util.ArrayList;

public class SistemaSIU {

    private Trie<Trie<Materia>> carreras = new Trie<>();
    private Trie<Integer> estudiantes=new Trie<>();

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF;
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        for (String libreta : libretasUniversitarias) {
            estudiantes.definir(0, libreta);
        }
        for (InfoMateria infoMateria : infoMaterias) {
            Materia instanciaMateria=new Materia();
            for (ParCarreraMateria parCarreraMateria : infoMateria.getParesCarreraMateria()) {
                String nombreCarrera =  parCarreraMateria.carrera;
                String nombreMateria = parCarreraMateria.nombreMateria;
                Trie<Materia> trieCarrera = carreras.obtener(nombreCarrera);
                if (trieCarrera==null){
                    carreras.definir(new Trie<Materia>(), nombreCarrera);
                }
                trieCarrera = carreras.obtener(nombreCarrera);
                trieCarrera.definir(instanciaMateria, nombreMateria);
            }
        }
    }

    public void inscribir(String estudiante, String carrera, String materia){
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        Materia instanciaMateria=materiasTrie.obtener(materia);
        instanciaMateria.sumarEstudiante(estudiante);
        estudiantes.definir(+1, estudiante);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int[] plantelDocente(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int inscriptos(String materia, String carrera){
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        Materia instanciaMateria=materiasTrie.obtener(materia);
        return instanciaMateria.cantidadDeInscriptos();
    }

    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
