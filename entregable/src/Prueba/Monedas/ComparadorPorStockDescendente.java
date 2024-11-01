package Prueba.Monedas;
import java.util.Comparator;
// Comparador para ordenar por stock descendente
public class ComparadorPorStockDescendente implements Comparator<Moneda> {
    
    @Override
    public int compare(Moneda m1, Moneda m2) {
        return Float.compare(m2.getStock(), m1.getStock());
    }
}