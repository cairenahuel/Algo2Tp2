package aed;

public class SistemaSIU {

    /*
     * Invariante de representacion:
     * 
     * -> "carreras" y "estudiantes" son instancias validas de trie.
     * 
     * -> todos los nodos con valor de "carreras" son punteros
     * a instancias de trie cuyos nodos con valor son
     * punteros a objetos de la clase "materia"
     * 
     * -> todos los nodos con valor de "estudiantes"
     * son numeros positivos o cero que representan la
     * cantidad materias a las que dicho estudiante esta incripto
     * 
     */

    private Trie<Trie<Materia>> carreras = new Trie<>();
    private Trie<Integer> estudiantes = new Trie<>();

    enum CargoDocente {
        AY2,
        AY1,
        JTP,
        PROF;
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {

        // Cargamos todas las libretas en el trie "estudiantes"
        // Como todas las libretas son acotadas:
        // O(1 * |libretasUniversitarias|)
        for (String libreta : libretasUniversitarias) {
            estudiantes.definir(0, libreta);
        }

        // Cargamos la informacion de todas las materias
        // O(|infoMaterias|)
        for (InfoMateria infoMateria : infoMaterias) {
            // Para cada materia creamos una instancia de la clase Materia
            Materia instanciaMateria = new Materia();
            ParCarreraMateria[] listaParesCarreraMateria = infoMateria.getParesCarreraMateria();

            // A cada par carrera materia le...
            // O(|listaParesCarreraMateria|)
            for (ParCarreraMateria parCarreraMateria : listaParesCarreraMateria) {
                // ¿Hay que usar los getter?
                String nombreCarrera = parCarreraMateria.carrera;
                String nombreMateria = parCarreraMateria.nombreMateria;

                // buscamos la carrera en el trie
                // O(|nombreCarrera|)
                Trie<Materia> trieCarrera = carreras.obtener(nombreCarrera);

                // Si no la encontramos...
                if (trieCarrera == null) {
                    // la creamos
                    // O(|nombreCarrera|)
                    carreras.definir(new Trie<Materia>(), nombreCarrera);
                    // y actualizamos la variable de referencia
                    // O(|nombreCarrera|)
                    trieCarrera = carreras.obtener(nombreCarrera);
                }
                // agregamos la referencia del puntero a la carrera y el nombre de la materia en
                // dicha
                // carrera a la instancia de materia
                instanciaMateria.agregarReferencia(trieCarrera, nombreMateria);

                // buscamos el trie de materias de dicha carrera
                // O(|nombreCarrera|)
                trieCarrera = carreras.obtener(nombreCarrera);
                // definimos esta materia en esa carrera
                // O(|nombreMateria|)
                trieCarrera.definir(instanciaMateria, nombreMateria);
            }
            // Entonces, este bloque for nos queda
            // -> O(Sum(0,|listaParesCarreraMateria|-1,|nombreCarrera|+|nombreMateria|))
        }
        // Entonces, este otro bloque for nos queda
        // -> O(Sum(0,|infoMaterias|-1,Sum(0,|listaParesCarreraMateria|-1,|nombreCarrera|+|nombreMateria|)))
    }

    /*
     * En total, nos queda
     * O(|libretasUniversitarias| + Sum(0,|infoMaterias|-1,Sum(0,|listaParesCarreraMateria|-1,|nombreCarrera|+|nombreMateria|)))
     * Si bien la complejidad no es exactamente como en el enunciado, creemos que
     * cumple porque solo cargamos
     * todas las materias y todas las carreras a medida que entran (y los
     * estudiantes), no veo la forma de conectar
     * el infoMaterias con las variables de complejidad que nos dan (pregunta para
     * el recuperatorio)
     */

    public void inscribir(String estudiante, String carrera, String materia) {
        // O(|carrera|)
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        // O(|materia|)
        Materia instanciaMateria = materiasTrie.obtener(materia);
        // O(1)
        instanciaMateria.sumarEstudiante(estudiante);
        // O(1) (por acotada)
        int cantInscripciones = estudiantes.obtener(estudiante);
        // O(1) (por acotada)
        estudiantes.definir(cantInscripciones + 1, estudiante);

        /*
         * Entonces tenemos:
         * O(|carrera|)+O(|materia|)
         */

    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia) {
        // O(|carrera|)
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        // O(|materia|)
        Materia instanciaMateria = materiasTrie.obtener(materia);

        // De acá al final es todo O(1)
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

        /*
         * Entonces tenemos:
         * O(|carrera|)+O(|materia|)
         */
    }

    public int[] plantelDocente(String materia, String carrera) {
        // O(|carrera|)
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        // O(|materia|)
        Materia instanciaMateria = materiasTrie.obtener(materia);
        // O(1)
        return instanciaMateria.getPlantel();

        /*
         * Entonces tenemos:
         * O(|carrera|)+O(|materia|)
         */
    }

    public void cerrarMateria(String materia, String carrera) {

        // O(|carrera|)
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        // O(|materia|)
        Materia instanciaMateria = materiasTrie.obtener(materia);
        // O(1)
        int i = 0;
        // O(1)
        int largoReferencias = instanciaMateria.getLargoReferencias();
        // O(sum(0,largoReferencias-1,|nombre|))
        while (i < largoReferencias) {
            // O(1)
            PunteroMateriaYNombre info = instanciaMateria.getReferencia(i);
            // O(1)
            Trie<Materia> trieCarrera = info.getInstanciaCarrera();
            // O(1)
            String nombre = info.getNombreEspecifico();
            // O(|nombre|)
            trieCarrera.eliminar(nombre);
            // O(1)
            i++;
        }
        // O(1)
        String[] lista = instanciaMateria.getInscriptos();
        // O(1)
        int cantidadInscriptos = lista.length;
        // O(1)
        int j = 0;
        // O(sum(0,largoInscriptos-1,1)) = O(largoInscriptos)
        while (j < cantidadInscriptos) {
            // O(1)
            int cantMaterias = estudiantes.obtener(lista[j]);
            // O(1)
            estudiantes.definir(cantMaterias - 1, lista[j]);
            // O(1)
            j++;
        }
        /*
         * O(|carrera|)+O(|materia|)+O(sum(ref in referencias,|nombre|))+ O(sum(0,|cantidadInscriptos|, 1))
         * = O(|carreras| + |materia| + sum(ref in referencias, |nombre|) + |cantidadInscriptos|)
         * 
         * referencias es una lista con duplas de punteros y strings, es basicamente
         * todos los nombres de esta materia
         *
         * Y listaIncriptos es la lista de inscriptos cuyo while corresponde al ultimo
         * termino de la suma anterior.
         * 
         */
    }

    public int inscriptos(String materia, String carrera) {
        // O(|carrera|)
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        // O(|materia|)
        Materia instanciaMateria = materiasTrie.obtener(materia);
        // O(1)
        return instanciaMateria.cantidadDeInscriptos();
        /*
         * Entonces tenemos:
         * O(|carrera|)+O(|materia|)
         */
    }

    public boolean excedeCupo(String materia, String carrera) {
        int estudiantes = inscriptos(materia, carrera);
        // O(|carrera| + |materia|)
        int[] plantel = plantelDocente(materia, carrera);
        // O(|carrera| + |materia|)

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
        /*
         * Entonces tenemos:
         * O(|carrera|+|materia|)
         */
    }

    public String[] carreras() {
        return carreras.imprimir();
        // O(|carreras|)
    }

    public String[] materias(String carrera) {
        Trie<Materia> materiasTrie = carreras.obtener(carrera);
        return materiasTrie.imprimir();
        // O(|materiasTrie|)
    }

    public int materiasInscriptas(String estudiante) {
        return estudiantes.obtener(estudiante);
        // O(1)
    }
}
