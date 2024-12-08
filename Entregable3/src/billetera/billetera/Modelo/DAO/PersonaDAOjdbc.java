package billetera.Modelo.DAO;
import billetera.Auxiliar.Persona;
import billetera.Modelo.MyConnection;
import java.sql.*;

public class PersonaDAOjdbc implements PersonaDAO
{
    @Override
    public void cargarPersona(Persona persona) 
    {
        Connection con = null;
        con = MyConnection.getCon();
        try
        {
            String query = "INSERT INTO persona (nombre, apellido) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void actualizarPersona(Persona persona) 
    {
        Connection con = null;
        con = MyConnection.getCon();
        String sql = "UPDATE persona SET nombre = ?, apellido = ? WHERE id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setInt(3, persona.getId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int obtenerIdPersona(String nombre, String apellido) 
    {
        Connection con = null;
        con = MyConnection.getCon();
        try
        {
            String query = "SELECT id FROM persona WHERE nombre = ? AND apellido = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt("id");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
                return -1;
    }
}
