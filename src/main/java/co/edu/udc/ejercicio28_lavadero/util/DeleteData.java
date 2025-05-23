package co.edu.udc.ejercicio28_lavadero.util;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,2);
            pstmt.executeUpdate();
            System.out.println("Datos eliminados exitosamente");
        }catch (SQLException e){
            System.out.println("Error al eliminar datos: "+e.getMessage());
        }
    }

    public static void DeleteTable(String table,String where,int codigo){
        String sql = "DELETE FROM "+table+" WHERE "+where+" = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,codigo);
            pstmt.executeUpdate();
            System.out.println("Datos eliminados exitosamente en la tabla "+table);
        }catch (SQLException e){
            System.out.println("Error al eliminar datos de la tabla "+table + ": "+e.getMessage());
        }
    }
}
