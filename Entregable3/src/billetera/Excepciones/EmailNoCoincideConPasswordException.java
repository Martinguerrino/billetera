package Excepciones;

@SuppressWarnings("serial")
class EmailNoCoincideConPasswordException extends Exception {

	public EmailNoCoincideConPasswordException(String mensaje) {
	     super(mensaje);
	 }
	}
