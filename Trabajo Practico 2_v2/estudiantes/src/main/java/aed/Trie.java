package aed;

import java.util.ArrayList;
import java.util.List;

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
            this.valor = valorDado;
            int i = 0; // O(1)
            while (i < 256) {// O(256)*(O(1)+O(1))
                siguientes.add(null);
                i++;
            }
        }
    }

    Nodo raiz;
    int tamanio;

    public void definir(T valorDado, String donde) {
        if (this.raiz == null) { // O(1)
            raiz = new Nodo(null);// O(256)
        }
        int i = 0;// O(1)
        Nodo actual = raiz;// O(1)
        while (i < donde.length()) {// O(sum_{0}^{|donde|}) * O(1) acá no se si iria O(1) o O(256) o si como 256 es
                                    // constante simplificamos a O(1)
            int numeroDelChar = (int) donde.charAt(i);
            Nodo siguiente = actual.siguientes.get(numeroDelChar);

            if (siguiente == null) {

                if (i == donde.length() - 1) { // Si el siguiente es nulo y ademas estoy en el ultimo char, ese nodo
                                               // va a ser el definido.
                    Nodo nuevo = new Nodo(valorDado);
                    actual.siguientes.set(numeroDelChar, nuevo);
                    actual.hijos += 1;
                    actual = nuevo;
                    this.tamanio += 1;
                } else { // Sino, creo un nuevo nodo y lo agrego al trie
                    Nodo nuevo = new Nodo(null);
                    actual.hijos += 1;
                    actual.siguientes.set(numeroDelChar, nuevo);
                    actual = nuevo;
                }

            } else {

                if (i == donde.length() - 1) { // Si hay siguiente nodo y ademas estoy en el ultimo char del
                                               // string, defino este nodo.
                    if (siguiente.valor == null) {
                        this.tamanio += 1;
                    }
                    siguiente.valor = valorDado;
                } else {
                    actual = siguiente;
                }
            }
            i++;
        }
    }

    public boolean definido(String buscado) {
        Nodo actual = raiz;
        if (actual == null) {
            return false;
        }

        int i = 0;
        while (i < buscado.length()) {
            int numeroDelChar = (int) buscado.charAt(i);
            Nodo siguiente = actual.siguientes.get(numeroDelChar);
            if (i == buscado.length() - 1) {
                return siguiente.valor != null;
            } else {
                if (siguiente == null) {
                    return false;
                } else {
                    actual = siguiente;
                }
            }
            i++;
        }
        return false;
    }

    public void eliminar(String buscado) {
        Nodo actual = raiz;
        Nodo ultimoSignificativo = raiz;
        Integer ultimoIndiceSignificativo = null;
        if (actual == null) {
            return;
        }

        int i = 0;
        while (i < buscado.length()) {
            int numeroDelChar = (int) buscado.charAt(i);
            Nodo siguiente = actual.siguientes.get(numeroDelChar);

            if (actual.hijos > 1 || actual.valor != null) {
                ultimoSignificativo = actual;
                ultimoIndiceSignificativo = i;
            }

            if (i == buscado.length() - 1) {
                if (siguiente == null) {
                    return;
                } else {
                    if (siguiente.valor != null) {
                        // si lo encuentra y no es nulo
                        if (siguiente.hijos != 0) { // Si tiene hijos
                            siguiente.valor = null;
                            this.tamanio -= 1;
                        } else { // Si no tiene hijos
                            if (ultimoSignificativo == raiz) {
                                if (raiz.hijos == 1) {
                                    this.raiz = null;
                                    this.tamanio -= 1;
                                    return;
                                }
                            }
                            int numCharSignificativo = buscado.charAt(ultimoIndiceSignificativo);
                            ultimoSignificativo.siguientes.set(numCharSignificativo, null);
                            ultimoSignificativo.hijos -= 1;
                            this.tamanio -= 1;
                        }
                    }
                }
            } else {
                if (siguiente == null) {
                    return;
                } else {
                    actual = siguiente;
                }
            }
            i++;
        }
    }

    public T obtener(String buscado) {

        // Si lo encuentra devuelve el valor del nodo, sino devuelve null
        if (raiz == null) {
            return null;
        }
        int largo = buscado.length();
        int i = 0;
        Nodo actual = raiz;
        while (i < largo) {
            int indiceCaracter = (int) buscado.charAt(i);
            if (i == largo - 1) {
                if (actual == null) {
                    return null;
                }
                Nodo siguiente = actual.siguientes.get(indiceCaracter);
                if (siguiente == null || siguiente.valor == null) {
                    return null;
                }
                return siguiente.valor;
            } else {
                if (actual == null) {
                    return null;
                }
                Nodo siguiente = actual.siguientes.get(indiceCaracter);
                actual = siguiente;
            }
            i++;
        }
        return null;
    }

    public String[] imprimir() {
        List<String> cadena = new ArrayList<>();
        imprimirAux(raiz, new StringBuilder(), cadena);
        return cadena.toArray(new String[0]);
    }

    private void imprimirAux(Nodo nodo, StringBuilder cadena, List<String> lista) {
        if (nodo == null) {
            return;
        }

        int i = 0;
        int hijosEncontrados = 0;
        while (i < 255 && hijosEncontrados < nodo.hijos) {

            Nodo actual = nodo.siguientes.get(i);

            if (actual != null) {
                char caracter = (char) i;
                cadena.append(caracter);

                if (actual.valor != null) {
                    lista.add(cadena.toString());
                }

                imprimirAux(actual, cadena, lista);
                cadena.deleteCharAt(cadena.length() - 1);
                hijosEncontrados++;
            }

            i++;
        }

        // en general creo que las complejidades dan

        // por si quieren testearlo

        // public static void main(String[] args) {
        // Trie<Integer> trie = new Trie<Integer>();
        // trie.definir(1, "que");
        // trie.definir(2, "que");
        // trie.definir(4, "peso");
        // trie.inorder();
        // }

    }
}
