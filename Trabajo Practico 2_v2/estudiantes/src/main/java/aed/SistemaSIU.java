package aed;

public class SistemaSIU {

    private Trie<Trie<Materia>> carreras = new Trie<>();
    private Trie<Integer> estudiantes = new Trie<>();

    enum CargoDocente {
        AY2,
        AY1,
        JTP,
        PROF;
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        for (String libreta : libretasUniversitarias) {
            estudiantes.definir(0, libreta);
        }
        for (InfoMateria infoMateria : infoMaterias) {
            Materia instanciaMateria = new Materia();
            for (ParCarreraMateria parCarreraMateria : infoMateria.getParesCarreraMateria()) {
                String nombreCarrera = parCarreraMateria.carrera;
                String nombreMateria = parCarreraMateria.nombreMateria;
                Trie<Materia> trieCarrera = carreras.obtener(nombreCarrera);
                if (trieCarrera == null) {
                    carreras.definir(new Trie<Materia>(), nombreCarrera);
                    trieCarrera = carreras.obtener(nombreCarrera);
                }
                instanciaMateria.agregarReferencia(trieCarrera, nombreMateria);
                trieCarrera = carreras.obtener(nombreCarrera);
                trieCarrera.definir(instanciaMateria, nombreMateria);
            }
        }
    }

    public void inscribir(String estudiante, String carrera, String materia) {
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        Materia instanciaMateria = materiasTrie.obtener(materia);
        instanciaMateria.sumarEstudiante(estudiante);
        int cantInscripciones = estudiantes.obtener(estudiante);
        estudiantes.definir(cantInscripciones + 1, estudiante);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia) {
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        Materia instanciaMateria = materiasTrie.obtener(materia);
        if (cargo == CargoDocente.AY2) {
            instanciaMateria.agregarAY2();
        }
        if (cargo == CargoDocente.AY1) {
            instanciaMateria.agregarAY1();
        }
        if (cargo == CargoDocente.JTP) {
            instanciaMateria.agregarJTP();
        }
        if (cargo == CargoDocente.PROF) {
            instanciaMateria.agregarProfe();
        }
    }

    public int[] plantelDocente(String materia, String carrera) {
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        Materia instanciaMateria = materiasTrie.obtener(materia);
        return instanciaMateria.getPlantel();
    }

    public void cerrarMateria(String materia, String carrera) {
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        Materia instanciaMateria = materiasTrie.obtener(materia);
        int i = 0;
        int largo = instanciaMateria.getLargoReferencias();
        while (i < largo) {
            PunteroMateriaYNombre info = instanciaMateria.getReferencia(i);
            Trie<Materia> trieCarrera = info.getInstanciaCarrera();
            String nombre = info.getNombreEspecifico();
            trieCarrera.eliminar(nombre);
            i++;
        }
        String[] lista = instanciaMateria.getInscriptos();
        int j = 0;
        while (j<lista.length) {
            int cantMaterias = estudiantes.obtener(lista[j]);
            estudiantes.definir(cantMaterias-1, lista[j]);
            j++;
        }
    }

    public int inscriptos(String materia, String carrera) {
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        Materia instanciaMateria = materiasTrie.obtener(materia);
        return instanciaMateria.cantidadDeInscriptos();
    }

    public boolean excedeCupo(String materia, String carrera) {
        int estudiantes = inscriptos(materia, carrera);
        int[] plantel = plantelDocente(materia, carrera);

        if (estudiantes > plantel[0] * 250) {
            return true;
        } else if (estudiantes > plantel[1] * 100) {
            return true;
        } else if (estudiantes > plantel[2] * 20) {
            return true;
        } else if (estudiantes > plantel[3] * 30) {
            return true;
        }
        return false;
    }

    public String[] carreras() {
        return carreras.imprimir();
    }

    public String[] materias(String carrera) {
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        return materiasTrie.imprimir();
    }

    public int materiasInscriptas(String estudiante) {
        return estudiantes.obtener(estudiante);
    }
}
