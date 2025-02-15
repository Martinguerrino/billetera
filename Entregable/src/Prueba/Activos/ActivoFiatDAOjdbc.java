package Prueba.Activos;

import Prueba.Monedas.Moneda;
import Prueba.Monedas.MonedaDAOjdbc;
import Prueba.MyConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivoFiatDAOjdbc implements ActivoDAO
{
    // Método para guardar un nuevo activo
    @Override
    public void cargarActivo(Activo activo) throws SQLException 
    {
        String sql = "INSERT INTO ACTIVO_FIAT (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";
        try (PreparedStatement stmt = MyConnection.getCon().prepareStatement(sql)) {
            stmt.setString(1, activo.getMoneda().getNomenclatura().toUpperCase());
            stmt.setFloat(2, activo.getCantidad());
            stmt.executeUpdate();
            System.out.println("Activo guardado correctamente.");
        } catch (SQLException e) {
        	System.out.println("YA EXISTE UN ACTIVO DE DICHA NOMENCLATURA1");
            throw new SQLException("Error al guardar el activo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Activo> listarActivos() throws SQLException {
        List<Activo> activos = new ArrayList<>();
        Connection connection = MyConnection.getCon();
        String sql = "SELECT * FROM ACTIVO_FIAT";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nomenclatura = rs.getString("NOMENCLATURA").toUpperCase();
                float cantidad = rs.getFloat("CANTIDAD");

                // Buscar la moneda correspondiente en la base de datos
                MonedaDAOjdbc monedaDAOjdbc = new MonedaDAOjdbc();
                Moneda moneda = monedaDAOjdbc.buscarMonedaPorNomenclatura(nomenclatura);
                if (moneda != null) {
                    Activo activo = new Activo(cantidad,moneda);
                    activos.add(activo);
                } else {
                    // Manejar caso donde la moneda no se encuentra (opcional)
                    System.out.println("Moneda con nomenclatura " + nomenclatura + " no encontrada en la base de datos.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar activos: " + e.getMessage(), e);
        }

        return activos;
    }



    @Override
    public Activo obtenerActivoPorNomenclatura(String nomenclatura) throws SQLException {
        Connection connection = MyConnection.getCon(); // Obtener la conexión desde el Singleton
        String sql = "SELECT NOMENCLATURA, CANTIDAD FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
        Activo activo = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Crear una nueva instancia de Moneda con solo la nomenclatura
                    Moneda moneda = new Moneda();
                    moneda.setNomenclatura(rs.getString("NOMENCLATURA").toUpperCase());
                    
                    // Crear el activo usando la cantidad y la moneda obtenidas de la base de datos
                    activo = new Activo(rs.getFloat("CANTIDAD"), moneda);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener activo por nomenclatura: " + e.getMessage(), e);
        }

        return activo;
    }
    @Override
    public boolean actualizarActivo(String nomenclatura, float cantidad) throws SQLException {
        Connection connection = MyConnection.getCon(); // Obtener la conexión desde el Singleton
        String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, cantidad);
            pstmt.setString(2, nomenclatura);
            
            int filasActualizadas = pstmt.executeUpdate();
            
            // Verifica si alguna fila fue actualizada (si existe el activo en la base de datos)
            return filasActualizadas > 0;
            
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el activo: " + e.getMessage(), e);
        }
    }

    @Override
    public float obtenerCantidad(String nomenclatura) throws SQLException {
        Connection connection = MyConnection.getCon();
        String sql = "SELECT CANTIDAD FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
        float cantidad = 0;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cantidad = rs.getFloat("CANTIDAD");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener cantidad: " + e.getMessage(), e);
        }

        return cantidad;
    }
}
