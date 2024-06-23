package aed;
import java.util.ArrayList;

public class Trie<T> {

    /*
     * 
     * Invariante de representacion:
     * 
     * -> Hay una unica forma de llegar a cada nodo. (No hay ciclos)
     * 
     * -> Todos los nodos tienen hijos o tienen significado.
     * 
     */

    private class Nodo {

        /*
         * 
         * Invariante de representacion:
         * 
         * -> hijos es mayor a 0 o tengo valor distinto de null
         * 
         */

        ArrayList<Nodo> siguientes = new ArrayList<>(256);
        int hijos = 0;

        T valor = null;

        Nodo(T valorDado) {
            // O(1)
            this.valor = valorDado;
            // O(1)
            int i = 0;
            // O(1) porque declare el largo del arreglo al construir el nodo.
            while (i < 256) {
                // O(1)
                siguientes.add(null);
                // O(1)
                i++;
            }
            /*
             * Nos queda O(1)
             */
        }
    }

    Nodo raiz;
    int tamanio;

    public void definir(T valorDado, String donde) {
        // O(1)
        if (this.raiz == null) {
            // O(1)
            raiz = new Nodo(null);
        }
        // O(1)
        int i = 0;
        // O(1)
        Nodo actual = raiz;
        // O(1)
        int largo = donde.length();

        // O(sum(0,largo - 1, 1)) = O(largo)
        while (i < largo) {
            // O(1)
            int numeroDelChar = (int) donde.charAt(i);
            // O(1)
            Nodo siguiente = actual.siguientes.get(numeroDelChar);

            // O(1)
            if (siguiente == null) {
 
                // O(1)
                if (i == largo - 1) { // Si el siguiente es nulo y ademas estoy en el ultimo char, ese nodo
                                      // va a ser el definido.
                    // O(1)
                    Nodo nuevo = new Nodo(valorDado);
                    // O(1)
                    actual.siguientes.set(numeroDelChar, nuevo);
                    // O(1)
                    actual.hijos += 1;
                    // O(1)
                    actual = nuevo;
                    // O(1)
                    this.tamanio += 1;
                } else { // Sino, creo un nuevo nodo y lo agrego al trie
                    // O(1)
                    Nodo nuevo = new Nodo(null);
                    // O(1)
                    actual.hijos += 1;
                    // O(1)
                    actual.siguientes.set(numeroDelChar, nuevo);
                    // O(1)
                    actual = nuevo;
                }

            } else {

                // O(1)
                if (i == largo - 1) { // Si hay siguiente nodo y ademas estoy en el ultimo char del
                                      // string, defino este nodo.
                    // O(1)
                    if (siguiente.valor == null) {
                        // O(1)
                        this.tamanio += 1;
                    }
                    // O(1)
                    siguiente.valor = valorDado;
                } else {
                    // O(1)
                    actual = siguiente;
                }
            }
            // O(1)
            i++;
        }

        /*
         * Entonces el metodo definir tiene una complejidad
         * O(largo)
         * y largo es la longitud del String "donde"
         */

    }

    public boolean definido(String buscado) {
        // O(1)
        Nodo actual = raiz;
        // O(1)
        if (actual == null) {
            // O(1)
            return false;
        }

        // O(1)
        int i = 0;
        // O(1)
        int largo = buscado.length();
        // O(sum(0,largo-1,1)) = O(largo)
        while (i < largo) {
            // O(1)
            int numeroDelChar = (int) buscado.charAt(i);
            // O(1)
            Nodo siguiente = actual.siguientes.get(numeroDelChar);
            // O(1)
            if (i == buscado.length() - 1) {
                // O(1)
                return siguiente.valor != null;
            } else {
                // O(1)
                if (siguiente == null) {
                    // O(1)
                    return false;
                } else {
                    // O(1)
                    actual = siguiente;
                }
            }
            // O(1)
            i++;
        }
        // O(1)
        return false;

        /*
         * Entonces el metodo eliminar tiene una complejidad
         * O(largo)
         * y largo es la longitud del String "buscado"
         */

    }

    public void eliminar(String buscado) {
        // O(1)
        Nodo actual = raiz;
        // O(1)
        Nodo ultimoSignificativo = raiz;
        // O(1)
        Integer ultimoIndiceSignificativo = null;
        // O(1)
        if (actual == null) {
            // O(1)
            return;
        }
        // O(1)
        int i = 0;
        // O(1)
        int largo = buscado.length();
        // O(sum(0,largo-1, 1) = O(largo)
        while (i < largo) {
            // O(1)
            int numeroDelChar = (int) buscado.charAt(i);
            // O(1)
            Nodo siguiente = actual.siguientes.get(numeroDelChar);
            // O(1)
            if (actual.hijos > 1 || actual.valor != null) {
                // O(1)
                ultimoSignificativo = actual;
                // O(1)
                ultimoIndiceSignificativo = i;
            }
            // O(1)
            if (i == largo - 1) {
                // O(1)
                if (siguiente == null) {
                    // O(1)
                    return;
                } else {
                    // O(1)
                    if (siguiente.valor != null) { // si lo encuentra y no es nulo
                        // O(1)
                        if (siguiente.hijos != 0) { // Si tiene hijos
                            // O(1)
                            siguiente.valor = null;
                            // O(1)
                            this.tamanio -= 1;
                        } else { // Si no tiene hijos
                            // O(1)
                            if (ultimoSignificativo == raiz) {
                                // O(1)
                                if (raiz.hijos == 1) {
                                    // O(1)
                                    this.raiz = null;
                                    // O(1)
                                    this.tamanio -= 1;
                                    // O(1)
                                    return;
                                }
                            }
                            // O(1)
                            int numCharSignificativo = buscado.charAt(ultimoIndiceSignificativo);
                            // O(1)
                            ultimoSignificativo.siguientes.set(numCharSignificativo, null);
                            // O(1)
                            ultimoSignificativo.hijos -= 1;
                            // O(1)
                            this.tamanio -= 1;
                        }
                    }
                }
            } else {
                // O(1)
                if (siguiente == null) {
                    // O(1)
                    return;
                } else {
                    // O(1)
                    actual = siguiente;
                }
            }
            // O(1)
            i++;
        }

        /*
         * Entonces el metodo eliminar tiene una complejidad
         * O(largo)
         * y largo es la longitud del String "buscado"
         */

    }

    public T obtener(String buscado) {

        // Si lo encuentra devuelve el valor del nodo, sino devuelve null

        // O(1)
        if (raiz == null) {
            // O(1)
            return null;
        }
        // O(1) (es un atributo guardado)
        int largo = buscado.length();
        // O(1)
        int i = 0;
        // O(1)
        Nodo actual = raiz;
        // O(sum(0,largo-1, 1) = largo
        while (i < largo) {
            // O(1)
            int indiceCaracter = (int) buscado.charAt(i);
            // O(1)
            if (i == largo - 1) {
                // O(1)
                if (actual == null) {
                    // O(1)
                    return null;
                }
                // O(1)
                Nodo siguiente = actual.siguientes.get(indiceCaracter);
                // O(1)
                if (siguiente == null || siguiente.valor == null) {
                    // O(1)
                    return null;
                }
                // O(1)
                return siguiente.valor;
            } else {
                // O(1)
                if (actual == null) {
                    // O(1)
                    return null;
                }
                // O(1)
                Nodo siguiente = actual.siguientes.get(indiceCaracter);
                // O(1)
                actual = siguiente;
            }
            // O(1)
            i++;
        }
        // O(1)
        return null;

        /*
         * Entonces el metodo obtener tiene una complejidad
         * O(largo)
         */
    }


    public String[] imprimir() {
        // O(1)
        ArrayList<String> lista = new ArrayList<>();
        
        //Revisa todos los nodos del trie por lo que
        imprimirAux(raiz, new StringBuilder(), lista);

        // imprimirAux va a ser llamado n veces, donde
        // n es la cantidad de nodos (caracteries) de mi trie 

        // acá .toArray tiene O(|lista|), osea de la cantidad
        // de nodos significativos que tiene mi trie
        return lista.toArray(new String[0]);

        /* 
         * Nos queda la siguiente complejidad
         * O(alturaDelTrie + |lista|)
         * Si el tamaño de las claves se acota
         * O(|lista|)
         */
    }

    /* ATENCIÓN:
     * No sabemos bien cómo calcular la complejidad de algo recursivo
     * (┬┬﹏┬┬)
     */
    private void imprimirAux(Nodo nodo, StringBuilder cadena, ArrayList<String> lista) {
        // caso base
        if (nodo == null) {
            return;
        }

        int i = 0;
        int cantHijos = nodo.hijos;
        int hijosEncontrados = 0;
        while (i < 256 && hijosEncontrados < cantHijos) {

            // O(1) 
            Nodo actual = nodo.siguientes.get(i);

            if (actual != null) {
                // O(1)
                char caracter = (char) i;
                // O(1)
                cadena.append(caracter);

                if (actual.valor != null) {
                    // O(1)
                    lista.add(cadena.toString());
                }

                // en el peor de los casos, imprimirAux es llamado
                // tantas veces cómo la altura del trie * 256
                imprimirAux(actual, cadena, lista);

                //O(1)
                cadena.deleteCharAt(cadena.length() - 1);
                //O(1)
                hijosEncontrados++;
            }

            i++;
        }

        /* 
         * Esta función recursiva tiene una complejidad
         * O(256*alturaDelTrie) = O(alturaDelTrie)
         * Observación: si el tamaño de las claves es acotado
         * O(1)
         */
    }
}
