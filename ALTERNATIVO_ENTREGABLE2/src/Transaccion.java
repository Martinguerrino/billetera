public class Transaccion 
{
    String descripcion;
    java.time.LocalDateTime fecha_hora;
    
    public Transaccion(String descripcion, java.time.LocalDateTime fecha_hora)
    {
        this.descripcion = descripcion;
        this.fecha_hora = fecha_hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public java.time.LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(java.time.LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    
}
