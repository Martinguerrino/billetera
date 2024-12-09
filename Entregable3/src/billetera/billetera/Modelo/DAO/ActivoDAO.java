package billetera.Modelo.DAO;

import billetera.Auxiliar.Activo;
import java.sql.SQLException;
import java.util.List;

public interface ActivoDAO 
{
    void cargarActivo(Activo activo);
    void actualizarActivo(int id_usuario, int id_moneda, float cantidad);
    List<Activo> listarActivos(int id_usuario) throws SQLException;
    Activo obtenerActivoPorNomenclatura(String nomenclatura) throws SQLException;
    Activo obtenerActivoPorId(int id) throws SQLException;
}
