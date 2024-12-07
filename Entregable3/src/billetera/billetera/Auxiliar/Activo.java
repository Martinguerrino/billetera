package billetera.Auxiliar;

public class Activo 
{
    private int id;
    private int id_usuario;
    private int id_moneda;
    private float cantidad;

    public Activo() {
    }
    public Activo(int id, int id_usuario, int id_moneda, float cantidad) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_moneda = id_moneda;
        this.cantidad = cantidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public int getId_moneda() {
        return id_moneda;
    }
    public void setId_moneda(int id_moneda) {
        this.id_moneda = id_moneda;
    }
    public float getCantidad() {
        return cantidad;
    }
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    

}
