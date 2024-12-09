// Excepción para moneda no soportada
package Excepciones;

//Excepciones personalizadas
class MonedaNoSoportadaException extends Exception {
 public MonedaNoSoportadaException(String mensaje) {
     super(mensaje);
 }
}

