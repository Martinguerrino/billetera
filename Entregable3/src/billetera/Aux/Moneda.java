package Aux;


public class Moneda implements Comparable<Moneda> {
    private String tipo;
    private String nombre;
    private String nomenclatura;
    private String nombreIcono;
    private float valorDolar;
    private float volatilidad;
    private float stock;

    public Moneda  (String tipo, String nombre, String nomenclatura,String nombreIcono, float valorDolar, float volatilidad, float stock) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.nomenclatura = nomenclatura;
        this.valorDolar = valorDolar;
        this.volatilidad = volatilidad;
        this.stock = stock;
        this.setNombreIcono(nombreIcono);
    }
    public Moneda(Moneda moneda)
    {
        this.tipo = moneda.tipo;
        this.nombre = moneda.nombre;
        this.nomenclatura = moneda.nomenclatura;
        this.valorDolar = moneda.valorDolar;
        this.volatilidad = moneda.volatilidad;
        this.stock = moneda.stock;
    }

    public Moneda() {
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public float getValorDolar() {
        return valorDolar;
    }

    public void setValorDolar(float valorDolar) {
        this.valorDolar = valorDolar;
    }

    public float getVolatilidad() {
        return volatilidad;
    }

    public void setVolatilidad(float volatilidad) {
        this.volatilidad = volatilidad;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }
    @Override
    public int compareTo(Moneda otraMoneda) {
        return Float.compare(this.valorDolar, otraMoneda.valorDolar);
    }
    @Override
    public String toString()
    {
        return "Tipo: " + tipo + " | Nombre: " + nombre + " | Nomenclatura: " + nomenclatura + " | Valor Dolar: " + valorDolar + " | Volatilidad: " + volatilidad + " | Stock: " + stock;
    }
	public String getNombreIcono() {
		return nombreIcono;
	}
	public void setNombreIcono(String nombreIcono) {
		this.nombreIcono = nombreIcono;
	}
    
}