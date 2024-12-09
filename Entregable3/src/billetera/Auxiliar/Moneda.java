package Auxiliar;
public class Moneda 
{
    int id;
    String tipo;
    String nombre;
    String nomenclatura;
    float valorDolar;
    float volatilidad;
    String nombreIcono;
    float stock;

    public Moneda(int id, String tipo, String nombre, String nomenclatura, float valorDolar, float volatilidad, String nombreIcono, float stock) {
        this.id = id;
        this.tipo = tipo.toUpperCase();
        this.nombre = nombre.toUpperCase();
        this.nomenclatura = nomenclatura.toUpperCase();
        this.valorDolar = valorDolar;
        this.volatilidad = volatilidad;
        this.nombreIcono = nombreIcono;
        this.stock = stock;
    }

    
    public Moneda() {
    }

    public Moneda(String tipo, String nombre, String nomenclatura, float valorDolar, float volatilidad,
            String nombreIcono, float stock) {
        this.tipo = tipo.toUpperCase();
        this.nombre = nombre.toUpperCase();
        this.nomenclatura = nomenclatura.toUpperCase();
        this.valorDolar = valorDolar;
        this.volatilidad = volatilidad;
        this.nombreIcono = nombreIcono;
        this.stock = stock;
    }


    public Moneda(String nomenclatura, float valorDolar) {
        this.nomenclatura = nomenclatura.toUpperCase();
        this.valorDolar = valorDolar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNombreIcono() {
        return nombreIcono;
    }

    public void setNombreIcono(String nombreIcono) {
        this.nombreIcono = nombreIcono;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }
    
    
    
}
