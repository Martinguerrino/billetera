public class Activo implements Comparable<Activo>{
    private Float cantidad;          // Cantidad de la criptomoneda en posesión
    private String direccion;        // Dirección asociada con el activo
    private Moneda moneda;           // Objeto Moneda que representa el tipo de moneda

    // Constructor
    public Activo(Float cantidad, String direccion, Moneda moneda) {
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.moneda = moneda;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    @Override
    public int compareTo(Activo otroActivo) {
        return Float.compare(this.cantidad, otroActivo.cantidad); // Comparar por cantidad de manera ascendente
    }

    
}