package Auxiliar;

import java.time.LocalDateTime;

public class Transaccion 
{
    int id;
    String descripcion;
    LocalDateTime fecha_hora;
    Usuario usuario;

    public Transaccion() {
    }


    public Transaccion(int id, String descripcion, java.time.LocalDateTime fecha_hora, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha_hora = fecha_hora;
        this.usuario = usuario;
        
    }

    public Transaccion(String descripcion, java.time.LocalDateTime fecha_hora, Usuario usuario) {
   
        this.descripcion = descripcion;
        this.fecha_hora = fecha_hora;
        this.usuario = usuario;
        
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

    
    
    
    
}
