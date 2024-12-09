package billetera.Auxiliar;

public class Usuario {
	private int id;
	private Persona persona;
	private String passwd;
	private boolean aceptaTerminos;
	private String gmail;
	public Usuario(int id, Persona persona, String passwd, boolean aceptaTerminos, String gmail) {
		super();
		this.id = id;
		this.persona = persona;
		this.passwd = passwd;
		this.aceptaTerminos = aceptaTerminos;
		this.gmail = gmail;
	}
	public Usuario(Persona persona, String passwd, boolean aceptaTerminos, String gmail) {
		super();
		this.persona = persona;
		this.passwd = passwd;
		this.aceptaTerminos = aceptaTerminos;
		this.gmail = gmail;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public boolean isAceptaTerminos() {
		return aceptaTerminos;
	}
	public void setAceptaTerminos(boolean aceptaTerminos) {
		this.aceptaTerminos = aceptaTerminos;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
