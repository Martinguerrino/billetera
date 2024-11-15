package billetera.Modelo;
public class Usuario 
{
    /*
     * "CREATE TABLE  IF NOT EXISTS USUARIO " + "(" + " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
			+ " ID_PERSONA       INTEGER   NOT NULL, "
			+ " PASSWORD       VARCHAR(50)    NOT NULL, "
			+ " ACEPTA_TERMINOS       BOOLEAN    NOT NULL, "
			+ " FOREIGN KEY(ID_PERSONA) REFERENCES PERSONA(ID),"
			+ " MAIL      VARCHAR(50)    NOT NULL " 
			+ ")";
     */
    private int id;
    private int idPersona;
    private String password;
    private boolean aceptaTerminos;
    private String mail;

    public Usuario(int id, int idPersona, String password, boolean aceptaTerminos, String mail) {
        this.id = id;
        this.idPersona = idPersona;
        this.password = password;
        this.aceptaTerminos = aceptaTerminos;
        this.mail = mail;
    }

    public Usuario(String mail2, String password2, boolean aceptaTerminos2) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAceptaTerminos() {
        return aceptaTerminos;
    }

    public void setAceptaTerminos(boolean aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
