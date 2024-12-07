package Prueba.Monedas;
import java.util.Comparator;

import Aux.Moneda;

public class ComparadorPorNomenclatura implements Comparator<Moneda> {
    @Override
    public int compare(Moneda m1, Moneda m2) {
        return m1.getNomenclatura().compareTo(m2.getNomenclatura());
    }
}