package Prueba.Activos;
import java.sql.SQLException;
import java.util.List;

import Aux.Activo;

public interface ActivoDAO  {
    
    /**
     * Verifica si existe una nomenclatura específica de moneda en la base de datos.
     *
     * @param nomenclatura Nomenclatura de la moneda a verificar.
     * @return true si la nomenclatura existe, false en caso contrario.
     * @throws SQLException en caso de error en la consulta a la base de datos.
     */
    

    /**
     * Guarda un nuevo activo en la base de datos.
     *
     * @param activo El objeto Activo a guardar.
     * @throws SQLException en caso de error en la consulta a la base de datos.
     */
    void cargarActivo(Activo activo) throws SQLException;

    
    /**
     * Lista todos los activos guardados en la base de datos.
     *
     * @return Una lista de todos los activos.
     * @throws SQLException en caso de error en la consulta a la base de datos.
     */
    List<Activo> listarActivos() throws SQLException;

    Activo obtenerActivoPorNomenclatura(String nomenclatura) throws SQLException;
    
    boolean actualizarActivo(String nomenclatura, float cantidad) throws SQLException;
    float obtenerCantidad(String nomenclatura) throws SQLException;
    

}   