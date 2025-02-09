package Auxiliar;

import java.time.LocalDateTime;

public class BalanceYMiActivo 
{
    int id;
    String nombre;
    String nomenclatura;
    int cantidad;
    int valorDolar;
    Usuario usuario;
    
	public BalanceYMiActivo(int id, String nombre, String nomenclatura, int cantidad, int valorDolar, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.cantidad = cantidad;
		this.valorDolar = valorDolar;
		this.usuario = usuario;
	}
	
	public BalanceYMiActivo(String nombre, String nomenclatura, int cantidad, int valorDolar, Usuario usuario) {
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.cantidad = cantidad;
		this.valorDolar = valorDolar;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNomenclatura() {
		return nomenclatura;
	}
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getValorDolar() {
		return valorDolar;
	}
	public void setValorDolar(int valorDolar) {
		this.valorDolar = valorDolar;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
  
    
    
}

