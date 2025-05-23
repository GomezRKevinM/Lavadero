package co.edu.udc.ejercicio28_lavadero.util;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void main(String[] args) {
        String sql = "UPDATE usuarios SET nombre = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,"Gary Castaño");
            pstmt.setInt(2,1);
            pstmt.executeUpdate();
            System.out.println("Datos actualizados exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Categoria(String campo, String valor, int codigo){
        String sql = "UPDATE categorias SET "+campo+" = ? WHERE codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, valor);
            pstmt.setInt(2, codigo);
            pstmt.executeUpdate();
            System.out.println("Categoria actualizada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void UpdateData(String table, String campo, String valor,String where, String codigo){
        String sql = "UPDATE "+table+" SET "+campo+" = ? WHERE "+where+" = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,valor);
            pstmt.setString(2,codigo);
            pstmt.executeUpdate();
            System.out.println("Datos actualizados exitosamente en la tabla "+table);
        }catch (SQLException e){
            System.out.println("Error al actualizar datos de la tabla "+table + ": "+e.getMessage());
        }
    }

    public static void Bodega(String campo, String valor, String codigo){
        String sql = "UPDATE Bodegas SET "+campo+" = ? WHERE codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, valor);
            pstmt.setString(2, codigo);
            pstmt.executeUpdate();
            System.out.println("Bodega actualizada exitosamente");

        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Producto(String campo, String valor, int codigo){
        String sql = "UPDATE Productos SET "+campo+" = ? WHERE codigo = ?";

        try (Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            if(campo.equals("stock")){
                pstmt.setInt(1, Integer.parseInt(valor));
            }else{
                pstmt.setString(1,valor);
            }
            pstmt.setInt(2,codigo);
            pstmt.executeUpdate();
            System.out.println("Producto actualizado exitosamente");

        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Empresa(String campo, String valor, int codigo){
        String sql = "UPDATE Empresas SET "+campo+" = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, valor);
            pstmt.setInt(2, codigo);
            pstmt.executeUpdate();
            System.out.println("Empresa actualizada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Contrato(String campo, String valor, int codigo){
        String sql = "UPDATE Contratos SET "+campo+" = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, valor);
            pstmt.setInt(2, codigo);
            pstmt.executeUpdate();
            System.out.println("Contrato actualizado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void InformacionDePago(String campo, String valor, int codigo){
        String sql = "UPDATE InformacionDePago SET "+campo+" = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, valor);
            pstmt.setInt(2, codigo);
            pstmt.executeUpdate();
            System.out.println("Informacion de pago actualizada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Empleado(String campo, String valor, String codigo){
        String sql = "UPDATE Empleados SET "+campo+" = ? WHERE identificacion = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, valor);
            pstmt.setString(2, codigo);
            pstmt.executeUpdate();
            System.out.println("Empleado actualizado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Provedor(String campo, String valor, int codigo){
        String sql = "UPDATE Provedores SET "+campo+" = ? WHERE codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,valor);
            pstmt.setInt(2,codigo);
            pstmt.executeUpdate();
            System.out.println("Proveedor actualizado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Cotizacion(String campo, String valor, int codigo){
        String sql = "UPDATE Cotizaciones SET "+campo+" = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,valor);
                pstmt.setInt(2,codigo);
                pstmt.executeUpdate();
            System.out.println("Cotización actualizada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Catalogo(String campo, String valor, int codigo){
        String sql = "UPDATE Catalogo SET "+campo+" = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,valor);
                pstmt.setInt(2,codigo);
                pstmt.executeUpdate();
            System.out.println("Catalogo actualizado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

    public static void Producto_bodega(int cantidad,int bodega){
        String sql = "UPDATE Productos_bodega SET cantidad = ? WHERE bodega = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,cantidad);
            pstmt.setInt(2,bodega);
            pstmt.executeUpdate();
            System.out.println("Cantidad actualizada exitosamente a "+cantidad);
        }catch (SQLException e){
            System.out.println("Error al actualizar cantidad de producto : "+e.getMessage());
        }
    }

    public static void DetalleCotizacion(String campo, String valor, int codigo){
        String sql = "UPDATE DetalleCotizaciones SET "+campo+" = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,valor);
            pstmt.setInt(2,codigo);
            pstmt.executeUpdate();
            System.out.println("Detalle actualizado exitosamente ✔️");
        }catch (SQLException e){
            System.out.println("Error al actualizar datos: "+e.getMessage());
        }
    }

}
