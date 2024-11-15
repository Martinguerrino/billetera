package billetera.Modelo.DAO;

import java.time.LocalDateTime;

public class Transaccion 
{
    int id;
    String descripcion;
    LocalDateTime fecha_hora;
    int id_Usuario;
    

    
   
    

    public Transaccion(int id, String descripcion, java.time.LocalDateTime fecha_hora, int id_Usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha_hora = fecha_hora;
        this.id_Usuario = id_Usuario;
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

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }
    
    
    
}
