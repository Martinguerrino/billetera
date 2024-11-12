package Prueba.Transaccion;

import java.time.LocalDateTime;

public class Transaccion 
{
    String descripcion;
    LocalDateTime fecha_hora;
    String tipo;
    
    public Transaccion(String descripcion, java.time.LocalDateTime fecha_hora, String tipo)
    {
        this.descripcion = descripcion;
        this.fecha_hora = fecha_hora;
        this.tipo=tipo;
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
    
    public void setTipo(String tipo) {
    	this.tipo=tipo;
    }
    
    public String getTipo() {
    	return tipo;
    }
    
}
