package co.edu.udc.ejercicio28_lavadero.util;

import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Categoria;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Contrato;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Producto;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Servicio;

import javax.xml.crypto.Data;
import java.sql.Array;
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

    public static void Categoria(Categoria categoria){
        String sql = "UPDATE categorias SET nombre = ?, icono = ? WHERE codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getIcono());
            pstmt.setString(3,categoria.getCodigo());
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

    public static void Producto(Producto producto){
        String sql = "UPDATE Productos SET nombre_producto = ?, categoria = ?, precio = ?, stock = ?, marca = ?, precio_de_compra = ?, alerta = ?, codigo_provedor = ?, img = ? WHERE codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, producto.getNombreProducto());
            pstmt.setInt(2,producto.getCategoria());
            pstmt.setDouble(3,producto.getPrecio());
            pstmt.setInt(4,producto.getStock());
            pstmt.setString(5,producto.getMarca());
            pstmt.setDouble(6,producto.getPrecioDeCompra());
            pstmt.setInt(7,producto.getAlerta());
            pstmt.setInt(8,producto.getCodigoDelProveedor());
            pstmt.setString(9,producto.getImg());
            pstmt.setString(10,producto.getCodigo());

            pstmt.executeUpdate();
            System.out.println("Producto "+producto.getCodigo()+" actualizado exitosamente");
        }catch (SQLException e){
            new Exception("Error: "+e.getMessage());
        }
    }

    public static void Servicio(Servicio servicio) throws SQLException{
        String sql = "UPDATE Servicios SET nombre = ?, descripcion = ?, precio = ?, imagen = ?, disponibilidad = ?, categoria = ?, funcionarios = ? WHERE codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,servicio.getNombre());
            pstmt.setString(2,servicio.getDescripcion());
            pstmt.setDouble(3,servicio.getPrecioDeVenta());
            pstmt.setString(4,servicio.getImagen());
            pstmt.setBoolean(5,servicio.getDisponibilidad());
            pstmt.setInt(6,Integer.parseInt(servicio.getCategoria().getCodigo()));
            pstmt.setString(7,servicio.getFuncionariosJSON());
            pstmt.setInt(8,servicio.getCodigo());
            pstmt.executeUpdate();
            System.out.println("Servicio "+servicio.getCodigo()+" actualizado exitosamente");
        }catch (SQLException e){
            throw new SQLException("Error: "+e.getMessage());
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

    public static void Contrato(Contrato contrato){
        String sql = "UPDATE Contratos SET fecha_inicio = ?, sueldo_base = ?, cargo = ?, fecha_final = ?, horario = ?, empleado = ?, empresa = ?, clausular = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, contrato.getFechaInicio());
            pstmt.setDouble(2, contrato.getSueldoBase());
            pstmt.setString(3, contrato.getCargo().name());
            pstmt.setString(4, contrato.getFechaFinal());
            pstmt.setString(5, contrato.getHorario());
            pstmt.setString(6, contrato.getContratado());
            pstmt.setString(7, contrato.getContratante());
            pstmt.setArray(8, conn.createArrayOf("varchar", contrato.getClausulas().toArray()));
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
