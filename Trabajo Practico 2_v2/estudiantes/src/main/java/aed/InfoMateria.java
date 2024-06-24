package aed;

public class InfoMateria{

    /*
     * Invariante de Representacion:
     * 
     * -> No puede haber dos elementos de la lista cuyos
     * elemento.getCarrera() sean iguales y a su vez
     * elemento.getMateria() sean distintos
     * 
     */

    private ParCarreraMateria[] paresCarreraMateria;

    public InfoMateria(ParCarreraMateria[] paresCarreraMateria){
        //O(1)
        this.paresCarreraMateria = paresCarreraMateria;
    }

    public ParCarreraMateria[] getParesCarreraMateria() {
        //O(1)
        return this.paresCarreraMateria;
    }
}
