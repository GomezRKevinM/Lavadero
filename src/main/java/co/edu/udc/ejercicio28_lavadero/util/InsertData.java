package co.edu.udc.ejercicio28_lavadero.util;

import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.TipoID;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertData {
    public static void main(String[] args) {
        String sql = "INSERT INTO usuarios (nombre,email) VALUES (?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setString(1, "Juan Perez");
            pstmt.setString(2, "juan@example.com");
            pstmt.executeUpdate();
            System.out.println("Datos insertados exitosamente");
        }catch(SQLException e){
            System.out.println("Error al insertar datos: "+e.getMessage());
        }
    }

    public static void Categoria(String name, String icon){
        String sql = "INSERT INTO categorias (nombre,icono) VALUES (?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setString(2, icon);
            pstmt.executeUpdate();
            System.out.println("Categoria insertada exitosamente");
        }catch(SQLException e){
            System.out.println("Error al insertar nueva categoria: "+e.getMessage());
        }
    }

    public static void Catalogo(String nombre){
        String sql = "INSERT INTO catalogos (nombre) VALUES (?)";
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("Catalogo creado exitosamente");
        }catch(SQLException e){
            System.out.println("Error al insertar catalogo: "+e.getMessage());
        }
    }

    public static void Bodega(String name,String location){
        String sql = "INSERT INTO Bodegas (nombre,ubicacion) VALUES (?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.executeUpdate();
            System.out.println("Bodega insertada exitosamente");
        }catch(SQLException e){
            System.out.println("Error al insertar nueva bodega: "+e.getMessage());
        }
    }

    public static void Producto(String nombreProducto,String marca,int categoria, double precio, double precioDeCompra, int stock, int alerta, int codigoDelProveedor){
        String sql = "INSERT INTO Productos (nombre_producto,marca,categoria,precio,stock,precio_de_compra,alerta,codigo_provedor) VALUES (?,?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nombreProducto);
            pstmt.setString(2, marca);
            pstmt.setInt(3, categoria);
            pstmt.setDouble(4, precio);
            pstmt.setInt(5, stock);
            pstmt.setDouble(6, precioDeCompra);
            pstmt.setInt(7, alerta);
            pstmt.setInt(8, codigoDelProveedor);
            pstmt.executeUpdate();
            System.out.println("Producto insertado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar producto: "+e.getMessage());
        }
    }

    public static void Empresa(String nombreEmpresa, int catalogo,int informacion_pago){
        String sql = "INSERT INTO Empresas(nombre,catalogo,informacion_pago) VALUES (?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nombreEmpresa);
            pstmt.setInt(2, catalogo);
            pstmt.setInt(3, informacion_pago);
            pstmt.executeUpdate();
            System.out.println("Empresa agregada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar empresa: "+e.getMessage());
        }
    }

    public static void Empleado(String nombre, TipoID tipoID, String correo, String telefono, String direccion, int contrato, String identificacion){
        String sql = "INSERT INTO Empleados(nombre,tipoID,correo,telefono,direccion,contrato,identificacion) VALUES (?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            pstmt.setString(2, tipoID.toString());
            pstmt.setString(3, correo);
            pstmt.setString(4, telefono);
            pstmt.setString(5, direccion);
            pstmt.setInt(6, contrato);
            pstmt.setString(7, identificacion);
            pstmt.executeUpdate();
            System.out.println("Empleado agregado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar empleado: "+e.getMessage());
        }
    }

    public static void Contrato(String fechaInicio,double sueldo_base,String cargo,String fechaFinal,String horario,int empleado,int empresa,String clausulas){

        String sql = "INSERT INTO Contratos(fecha_inicio,sueldo_base,cargo,fecha_final,horario,empleado,empresa,clausulas) VALUES (?,?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, fechaInicio);
            pstmt.setDouble(2, sueldo_base);
            pstmt.setString(3, cargo);
            pstmt.setString(4, fechaFinal);
            pstmt.setString(5, horario);
            pstmt.setInt(6, empleado);
            pstmt.setInt(7, empresa);
            pstmt.setString(8, clausulas);
            pstmt.executeUpdate();
            System.out.println("Contrato agregado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar contrato: "+e.getMessage());
        }
    }

    public static void InforacionDePago(String direccion,String telefono,String email,String numeroCuenta,String banco,String tipoCuenta, double saldo){
        String sql = "INSERT INTO InforacionDePago(direccion,telefono,email,numero_cuenta,banco,tipo_cuenta,saldo_actual) VALUES (?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, direccion);
            pstmt.setString(2, telefono);
            pstmt.setString(3, email);
            pstmt.setString(4, numeroCuenta);
            pstmt.setString(5, banco);
            pstmt.setString(6, tipoCuenta);
            pstmt.setDouble(7, saldo);
            pstmt.executeUpdate();
            System.out.println("Inforacion de pago agregada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar inforacion de pago: "+e.getMessage());
        }
    }

    public static void Provedor(String nombre, int informacion_pago){
        String sql = "INSERT INTO Proveedores(nombre,informacion_pago) VALUES (?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            pstmt.setInt(2, informacion_pago);
            pstmt.executeUpdate();
            System.out.println("Proveedor registrado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al registrar proveedor: "+e.getMessage());
        }
    }

    public static void Cotizacion(Date fecha_emision,Date fecha_expiracion,int empleado_realiza,int emplreado_revisa,int empresa, String estado){
        String sql = "INSERT INTO Cotizaciones(fecha_emision,fecha_expiracion,empleado_realiza,empleado_revisa,empresa,estado) VALUES (?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, fecha_emision.toString());
            pstmt.setString(2, fecha_expiracion.toString());
            pstmt.setInt(3, empleado_realiza);
            pstmt.setInt(4, emplreado_revisa);
            pstmt.setInt(5, empresa);
            pstmt.setString(6, estado);
            pstmt.executeUpdate();
            System.out.println("Cotizacion agregada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar cotizacion: "+e.getMessage());
        }
    }

    public static void DetalleCotizacion(int producto,int cantidad,double precioUnitario,double descuento,double iva,int tiempoGarantia,int cotizacion){
        String sql = "INSERT INTO DetallesCotizacion(producto,cantidad,precio_unitario,descuento,iva,tiempo_garantia,cotizacion) VALUES (?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, producto);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precioUnitario);
            pstmt.setDouble(4, descuento);
            pstmt.setDouble(5, iva);
            pstmt.setInt(6, tiempoGarantia);
            pstmt.setInt(7, cotizacion);
            pstmt.executeUpdate();
            System.out.println("Detalle de cotizacion agregado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar detalle de cotizacion: "+e.getMessage());
        }
    }

    public static void Producto_Bodega(int idProducto,int cantidad,int idBodega){
        String sql = "INSERT INTO Productos_bodega(producto,cantidad,bodega) VALUES (?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, idProducto);
            pstmt.setInt(2, cantidad);
            pstmt.setInt(3, idBodega);
            pstmt.executeUpdate();
            System.out.println("Producto agregado a bodega exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar producto en bodega: "+e.getMessage());
        }
    }

    public static void NotaCorrecion(Date fecha_emision, Date fecha_maxima_confirmacion, int codigo_pedido, String observaciones){
        String sql = "INSERT INTO Notas_de_correcion(fecha_emision,fecha_maxima_confirmacion,codigo_pedido,observaciones) VALUES (?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDate(1, fecha_emision);
            pstmt.setDate(2, fecha_maxima_confirmacion);
            pstmt.setInt(3, codigo_pedido);
            pstmt.setString(4, observaciones);
            pstmt.executeUpdate();
            System.out.println("Nota de correcion agregada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar nota de correcion: "+e.getMessage());
        }
    }

    public static void AreaDeTrabajo(String nombre, String descripcion){
        String sql = "INSERT INTO AreasDeTrabajo(nombre,descripcion) VALUES (?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            pstmt.executeUpdate();
            System.out.println("Area de trabajo "+ nombre + " agregada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al agregar area de trabajo: "+e.getMessage());
        }
    }

    public static void Cliente(String nombre,String tipo_id,String identificacion,String correo,String telefono, String direccion){
        String sql = "INSERT INTO Clientes(nombre,tipo_id,identificacion,correo,telefono,direccion) VALUES (?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            pstmt.setString(2, tipo_id);
            pstmt.setString(3, identificacion);
            pstmt.setString(4, correo);
            pstmt.setString(5, telefono);
            pstmt.setString(6, direccion);
            pstmt.executeUpdate();
            System.out.println("Cliente "+ nombre + " agregado exitosamente");
        }catch (SQLException e){
            if(e.getMessage().contains("UNIQUE constraint failed: Clientes.identificacion")){
                System.out.println(Color.ROJO_BOLD +"Error: Ya existe un cliente con la identificacion: "+Color.PURPLE_BOLD+identificacion);
            }else{
                System.out.println("Error al agregar cliente: "+e.getMessage());
            }
        }
    }

    public static void Pedido(String estado, Date fecha_emision, Date fecha_entrega, double valor_pagar, double valor_pagado,int cotizacion_id){
        String sql = "INSERT INTO Pedidos(estado,fecha_emision,fecha_entrega,valor_pagar,valor_pagado,cotizacion_id) VALUES (?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, estado);
            pstmt.setDate(2, fecha_emision);
            pstmt.setDate(3, fecha_entrega);
            pstmt.setDouble(4, valor_pagar);
            pstmt.setDouble(5, valor_pagado);
            pstmt.setInt(6, cotizacion_id);
            pstmt.executeUpdate();
            System.out.println("Pedido agregado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar pedido: "+e.getMessage());
        }
    }

    public static void DetallePedido(int producto,int cantidad,double precioUnitario,double descuento,double iva,int cantidad_recibida,boolean verificado,int codigo_pedido){
        String sql = "INSERT INTO DetallesPedido(producto,cantidad,precio_unitario,descuento,iva,cantidad_recibida,verificado,codigo_pedido) VALUES (?,?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, producto);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precioUnitario);
            pstmt.setDouble(4, descuento);
            pstmt.setDouble(5, iva);
            pstmt.setInt(6, cantidad_recibida);
            pstmt.setBoolean(7, verificado);
            pstmt.setInt(8, codigo_pedido);
            pstmt.executeUpdate();
            System.out.println("Detalle de pedido agregado exitosamente");
        }catch (SQLException e){
            System.out.println("Error al insertar detalle de pedido: "+e.getMessage());
        }
    }

}
