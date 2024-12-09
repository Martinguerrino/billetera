package billetera.Auxiliar;

public class Activo implements Comparable<Activo> 
{
    private int id;
    private Usuario usuario;
    private Moneda moneda;
    private float cantidad;

    public Activo() {
    }
    
    public Activo(Usuario usuario, Moneda moneda, float cantidad) {
        this.usuario = usuario;
        this.moneda = moneda;
        this.cantidad = cantidad;
    }

    public Activo(int id, Usuario usuario, Moneda moneda, float cantidad) {
        this.id = id;
        this.usuario = usuario;
        this.moneda = moneda;
        this.cantidad = cantidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Moneda getMoneda() {
        return moneda;
    }
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    public float getCantidad() {
        return cantidad;
    }
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public int compareTo(Activo otroActivo) {
        return Float.compare(this.cantidad, otroActivo.cantidad); // Comparar por cantidad de manera ascendente
    }
}
