package Prueba.Monedas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Prueba.MyConnection;

public class MonedaDAOjdbc implements MonedaDAO {

    
    @Override
    public void crearMoneda(Moneda moneda) throws SQLException {
        Connection connection = MyConnection.getCon();  // Obtener la conexión desde el Singleton
        String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, moneda.getTipo().toUpperCase());
            pstmt.setString(2, moneda.getNombre().toUpperCase());
            pstmt.setString(3, moneda.getNomenclatura().toUpperCase());
            pstmt.setDouble(4, moneda.getValorDolar());
            pstmt.setDouble(5, moneda.getVolatilidad());
            pstmt.setDouble(6, moneda.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al crear moneda: " + e.getMessage(), e);
        }
    }

    
     // Método para listar todas las monedas sin ordenar
     @Override
     public  List<Moneda> listarMonedas() throws SQLException {
         Connection connection = MyConnection.getCon();  // Obtener la conexión desde el Singleton
         List<Moneda> monedas = new ArrayList<>();
         String sql = "SELECT * FROM MONEDA";
 
         try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
             while (rs.next()) {
                 Moneda moneda = new Moneda(
                     rs.getString("TIPO"), 
                     rs.getString("NOMBRE"),
                     rs.getString("NOMENCLATURA"), 
                     rs.getFloat("VALOR_DOLAR"),
                     rs.getFloat("VOLATILIDAD"), 
                     rs.getFloat("STOCK")
                 );
                 monedas.add(moneda);
             }
         } catch (SQLException e) {
             throw new SQLException("Error al listar monedas: " + e.getMessage(), e);
         }
         return monedas;
     }
    @Override
    public  void generarStockAleatorio() throws SQLException {
        Connection connection = MyConnection.getCon();  // Obtener la conexión desde el Singleton
        List<Moneda> monedas = listarMonedas();  // Obtener todas las monedas
        Random random = new Random();

        String sql = "UPDATE MONEDA SET STOCK = ? WHERE NOMENCLATURA = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Moneda moneda : monedas) {
                // Generar un stock aleatorio entre 1000 y 10000 (puedes ajustar este rango)
                float stockAleatorio = 1000 + (10000 - 1000) * random.nextFloat();
                moneda.setStock(stockAleatorio);  // Actualizar el stock en el objeto Moneda

                // Actualizar la base de datos
                pstmt.setDouble(1, stockAleatorio);
                pstmt.setString(2, moneda.getNomenclatura());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Error al generar stock aleatorio: " + e.getMessage(), e);
        }
    }
    
    @Override
    public  Moneda buscarMonedaPorNomenclatura(String nomenclatura) throws SQLException {
        Connection connection = MyConnection.getCon();
        Moneda moneda = null;
        String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                moneda = new Moneda(
                    rs.getString("TIPO"),
                    rs.getString("NOMBRE"),
                    rs.getString("NOMENCLATURA"),
                    rs.getFloat("VALOR_DOLAR"),
                    rs.getFloat("VOLATILIDAD"),
                    rs.getFloat("STOCK")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error al buscar moneda: " + e.getMessage(), e);
        }
        return moneda;
    }
    // Verificar si la nomenclatura existe en la base de datos, delegando a MonedaDAO
    @Override
    public boolean existeNomenclatura(String nomenclatura) throws SQLException {
    Connection connection = MyConnection.getCon();
    String sql = "SELECT COUNT(*) FROM MONEDA WHERE NOMENCLATURA = ?";
    boolean existe = false;

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, nomenclatura);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        throw new SQLException("Error al verificar la nomenclatura: " + e.getMessage(), e);
    }
    return existe;
}
    @Override
    public void actualizarStock(Moneda moneda,float stock) throws SQLException
    {
        Connection connection = MyConnection.getCon();
        String sql = "UPDATE MONEDA SET STOCK = ? WHERE NOMENCLATURA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, stock);
            pstmt.setString(2, moneda.getNomenclatura());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar stock: " + e.getMessage(), e);
        }
    }
}