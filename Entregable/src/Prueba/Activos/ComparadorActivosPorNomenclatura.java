package Prueba.Activos;
import java.util.Comparator;

import Aux.Activo;

public class ComparadorActivosPorNomenclatura implements Comparator<Activo> {
    @Override
    public int compare(Activo a1, Activo a2) {
        return a1.getMoneda().getNomenclatura().compareTo(a2.getMoneda().getNomenclatura());
    }
}