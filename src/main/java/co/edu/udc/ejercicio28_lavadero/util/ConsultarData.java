package co.edu.udc.ejercicio28_lavadero.util;

import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.modelo.crud.ProductoCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import java.sql.*;
import java.util.ArrayList;

public class ConsultarData {
    public static void main(String[] args) {
        String sql = "SELECT * FROM usuarios";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                System.out.println(Color.CYAN_BOLD+"ID: "+Color.BLANCO_BOLD+rs.getInt("id")+Color.CYAN_BOLD+" Nombre: "+Color.BLANCO_BOLD+rs.getString("nombre")+Color.CYAN_BOLD+" Email: "+Color.BLANCO_BOLD+rs.getString("email"));
            }
        }catch (SQLException e){
            System.out.println(Color.ROJO_BLINK+"Error al consultar datos: "+e.getMessage());
        }
    }

    public static ArrayList<Categoria> Categorias(){
        String sql = "SELECT * FROM categorias";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){

            ArrayList<Categoria> categorias = new ArrayList<>();
            while (rs.next()){
                Categoria categoria = new Categoria(rs.getString("nombre"),rs.getString("icono"),String.valueOf(rs.getInt("codigo")));
                categorias.add(categoria);
            }
            return categorias;

        }catch (SQLException e){
            System.out.println(Color.ROJO_BLINK+"Error al consultar datos: "+e.getMessage());
        }
        throw new RuntimeException("Error al consultar datos");
    }

    public static Categoria Categoria(String codigo){
        String sql = "SELECT * FROM categorias WHERE nombre = ? OR codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,codigo);
            pstmt.setString(2,codigo);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Categoria(rs.getString("nombre"),rs.getString("icono"),String.valueOf(rs.getInt("codigo")));
            }else{
                throw new RuntimeException("No se encontro categoria con codigo: "+codigo);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error al consultar datos");
        }
    }

    public static ArrayList<Producto> productosBodega(String idBodega){
        String sql = "SELECT * FROM productos_bodega WHERE bodega = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,idBodega);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Producto> listaProductos = new ArrayList<>();
            while (rs.next()){
                Producto nuevo = Producto(rs.getString("producto"));
                listaProductos.add(nuevo);
            }
            return listaProductos;

        }catch (SQLException e){
            System.out.println("Error al Seleccionar productos: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar productos");
    }

    public static ArrayList<Bodega> Bodegas(){
        String sql = "SELECT * FROM Bodegas";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            ArrayList<Bodega> bodegas = new ArrayList<>();
            while (rs.next()){
                Bodega nueva = new Bodega(rs.getString("codigo"), rs.getString("ubicacion"), rs.getString("nombre") );
                bodegas.add(nueva);
            }
            return bodegas;

        }catch (SQLException e){
            System.out.println("Error al Seleccionar bodegas: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar bodegas");
    }

    public static Bodega Bodega(String busqueda){
        String sql = "SELECT * FROM Bodegas WHERE nombre = ? OR codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,busqueda);
            pstmt.setString(2,busqueda);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Bodega(rs.getString("codigo"), rs.getString("ubicacion"), rs.getString("nombre") );
            }else{
                throw new RuntimeException("No se encontro bodega con codigo: "+busqueda);
            }
        }catch (SQLException e){
            System.out.println("Error al Seleccionar bodega: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar bodega");
    }

    public static ArrayList<Producto> Productos(){
        String sql = "SELECT * FROM Productos";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<Producto> productos = new ArrayList<>();

            while (rs.next()){
                Producto nuevo = new Producto(rs.getString("nombre_producto"),rs.getString("marca"),rs.getInt("categoria"),rs.getString("codigo"),rs.getDouble("precio"), rs.getDouble("precio_de_compra"),rs.getInt("stock"),rs.getInt("alerta"),rs.getInt("codigo_provedor"));
                productos.add(nuevo);
            }
            return productos;

        }catch (SQLException e){
            System.out.println("Error al Seleccionar productos: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar productos");
    }

    public static Producto Producto(String codigo){
        String sql = "SELECT * FROM Productos WHERE codigo = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,codigo);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Producto(rs.getString("nombre_producto"),rs.getString("marca"),rs.getInt("categoria"),rs.getString("codigo"),rs.getDouble("precio"), rs.getDouble("precio_de_compra"),rs.getInt("stock"),rs.getInt("alerta"),rs.getInt("codigo_provedor"));
            }else{
                throw new RuntimeException("No se encontro producto con codigo: "+codigo);
            }
        }catch (SQLException e){
            System.out.println("Error al Seleccionar producto: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar producto");
    }

    public static ArrayList<InformacionPago> InformacionDePagos(){
        String sql = "SELECT * FROM InformacionDePago;";
        try (Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<InformacionPago> informacionDePagos = new ArrayList<>();
            ArrayList<MetodoPago> metodos = new ArrayList<>();
            for(MetodoPago metodo : MetodoPago.values()){
                metodos.add(metodo);
            }
            CuentaBancaria cuenta = new CuentaBancaria(rs.getString("numero_cuenta"),rs.getString("banco"),rs.getString("tipo_cuenta"),rs.getDouble("saldo_actual"));
            while (rs.next()){

                InformacionPago nuevo = new InformacionPago(rs.getInt("id"),metodos, rs.getString("direccion"), rs.getString("telefono"),rs.getString("email"),cuenta);
                informacionDePagos.add(nuevo);
            }
            return informacionDePagos;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar informacion de pagos: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar informacion de pagos");
    }

    public static InformacionPago InformacionDePago(int id){
        String sql = "SELECT * FROM InformacionDePago WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                ArrayList<MetodoPago> metodos = new ArrayList<>();
                for(MetodoPago metodo : MetodoPago.values()){
                    metodos.add(metodo);
                }
                CuentaBancaria cuenta = new CuentaBancaria(rs.getString("numero_cuenta"),rs.getString("banco"),rs.getString("tipo_cuenta"),rs.getDouble("saldo_actual"));
                return new InformacionPago(rs.getInt("id"),metodos, rs.getString("direccion"), rs.getString("telefono"),rs.getString("email"),cuenta);
            }else{
                throw new RuntimeException("No se encontro informacion de pago con id: "+id);
            }
        }catch (SQLException e){
            System.out.println("Error al Seleccionar informacion de pagos: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar informacion de pagos");
    }

    public static ArrayList<Empresa> Empresas(){
        String sql = "SELECT * FROM Empresas";
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<Empresa> empresas = new ArrayList<>();
            while (rs.next()){
                Empresa nueva = new Empresa(rs.getString("nombre"),String.valueOf(rs.getInt("id")));
                nueva.setInformacionDePago(InformacionDePago(rs.getInt("informacion_pago")));
                nueva.setCatalogo(catalogo(rs.getString("catalogo")));
                empresas.add(nueva);
            }
            return empresas;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar empresas: "+e.getMessage());
        }
        throw new RuntimeException("Empresa no encontrada 🔍");
    }

    public static Empresa Empresa(int id){
        String sql = "SELECT * FROM Empresas WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Empresa nueva = new Empresa(rs.getString("nombre"),String.valueOf(rs.getInt("id")));
                nueva.setInformacionDePago(InformacionDePago(rs.getInt("informacion_pago")));
                nueva.setCatalogo(catalogo(rs.getString("catalogo")));
                return nueva;
            }
        }catch (SQLException e){
            System.out.println("Error al Seleccionar empresas: "+e.getMessage());
        }
        throw new RuntimeException("Empresa no encontrada en la base de datos 🔍");
    }

    public static ArrayList<Catalogo> catalogos(){
        String sql = "SELECT * FROM Catalogo";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<Catalogo> catalogos = new ArrayList<>();

            while (rs.next()){
                Catalogo nuevo = new Catalogo(rs.getString("id"),rs.getString("nombre"));
                catalogos.add(nuevo);
            }
            return catalogos;

        }catch (SQLException e){
            System.out.println("Error al Seleccionar catalogo: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar catalogo");
    }

    public static Catalogo catalogo(String id){
        String sql = "SELECT * FROM Catalogo WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1,Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Catalogo(rs.getString("id"),rs.getString("nombre"));
            }else{
                throw new RuntimeException("No se encontro catalogo con id: "+id);
            }
        }catch (SQLException e){
            System.out.println("Error al Seleccionar catalogo: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar catalogo");
    }

    public static ArrayList<Contrato> Contratos(){
        String sql = "SELECT * FROM Contratos";

        try(Connection conn =  DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<Contrato> contratos = new ArrayList<>();

            while (rs.next()){
                Contrato nuevo = new Contrato(rs.getString("id"),rs.getString("fecha_inicio"),rs.getDouble("sueldo_base"),Cargo.valueOf(rs.getString("cargo")),rs.getString("fecha_fin"),rs.getString("horario"));
                nuevo.agregarClausula(rs.getString("clausulas"));
                contratos.add(nuevo);
            }
            return contratos;
        }catch (SQLException e){
            System.out.println("Contrato no encontrado");
        }
        throw new RuntimeException("Contrato no encontrado");
    }

    public static Contrato Contrato(int id){
        String sql = "SELECT * FROM Contratos WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                Contrato encontrado = new Contrato(rs.getString("id"),rs.getString("fecha_inicio"),rs.getDouble("sueldo_base"),Cargo.valueOf(rs.getString("cargo")),rs.getString("fecha_final"),rs.getString("horario"));
                encontrado.setContratado(rs.getString("empleado"));
                encontrado.setContratante(rs.getString("empresa"));
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String clausulas = rs.getString("clausulas");
                ArrayList<String> listaClausulas = new ArrayList<>();
                listaClausulas.addAll(gson.fromJson(rs.getString("clausulas") , ArrayList.class));
                encontrado.setClausulas(listaClausulas);
                return encontrado;

            }else{
                throw new RuntimeException("No se encontro contrato con id: "+id);
            }
        }catch (SQLException e) {
            System.out.println("Error al Seleccionar contrato: " + e.getMessage());
        }
        throw new RuntimeException("Contrato no encontrado");
    }

    public static ArrayList<Empleado> Empleados(){
        String sql = "SELECT * FROM Empleados";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){

            ArrayList<Empleado> empleados = new ArrayList<>();

            while (rs.next()){
                Contrato contrato = Contrato(rs.getInt("contrato"));
                Empleado nuevo = new Empleado(rs.getString("nombre"),TipoID.valueOf(rs.getString("tipoID")),rs.getString("identificacion"),rs.getString("correo"),rs.getString("telefono"),rs.getString("direccion"),contrato);
                nuevo.setId(rs.getInt("id"));
                empleados.add(nuevo);
            }

            return empleados;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar empleados: "+e.getMessage());
        }
        throw new RuntimeException("Empleado no encontrado");
    }

    public static Empleado Empleado(String identificacion){
        String sql = "SELECT * FROM Empleados WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,identificacion);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Empleado encontrado = new Empleado(rs.getString("nombre"),TipoID.valueOf(rs.getString("tipoID")),rs.getString("identificacion"),rs.getString("correo"),rs.getString("telefono"),rs.getString("direccion"),Contrato(rs.getInt("contrato")));
                encontrado.setId(rs.getInt("id"));
                return encontrado;
            }else{
                throw new RuntimeException("No se encontro empleado con identificacion: "+identificacion);
            }

        }catch (SQLException e){
            System.out.println("Error al Seleccionar empleados: "+e.getMessage());
        }
        throw new RuntimeException("Empleado no encontrado");
    }

    public static ArrayList<Cotizacion> Cotizaciones(){
        String sql = "SELECT * FROM Cotizaciones";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(); ){

            ArrayList<Cotizacion> cotizaciones = new ArrayList<>();
            while (rs.next()){
                Cotizacion nueva = new Cotizacion(rs.getString("id"),rs.getDate("fecha_emision"),rs.getDate("fecha_expiracion"),Empleado(String.valueOf(rs.getInt("empleado_realiza"))));
                nueva.setEstado(rs.getString("estado"));
                if(rs.getInt("empleado_revisa") != 0){
                    nueva.setRevisa(Empleado(String.valueOf(rs.getInt("empleado_revisa"))));
                }
            }
            return cotizaciones;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar cotizaciones: "+e.getMessage());
        }
        throw new RuntimeException("Lista de cotizaciones vacia");
    }

    public static Cotizacion Cotizacion(String id){
        String sql = "SELECT * FROM Cotizaciones WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Cotizacion(rs.getString("id"),rs.getDate("fecha_emision"),rs.getDate("fecha_expiracion"),Empleado(String.valueOf(rs.getInt("empleado_realiza"))));
            }else{
                throw new RuntimeException("No se encontro cotizacion con id: "+id);
            }
        }catch (SQLException e){
            System.out.println("Error al Seleccionar cotizaciones: "+e.getMessage());
        }
        throw new RuntimeException("Cotización no encontrada");
    }

    public static ArrayList<DetalleCotizacion> DetallesCotiaciones(){
        String sql = "SELECT * FROM DetallesCotizacion";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<DetalleCotizacion> detalles = new ArrayList<>();
            while(rs.next()){
                Producto producto = Producto(rs.getString("producto"));
                DetalleCotizacion nuevo = new DetalleCotizacion(producto,rs.getInt("cantidad"),rs.getDouble("precio_unitario"),rs.getDouble("descuento"),rs.getDouble("iva"),rs.getInt("tiempo_garantia"));
                Cotizacion cotizacion = Cotizacion(rs.getString("cotizacion"));
                nuevo.setCotizacion(cotizacion);
                detalles.add(nuevo);
            }
            return detalles;

        }catch (SQLException e){
            System.out.println("Error al Seleccionar detalles de cotizaciones: "+e.getMessage());
        }
        throw new RuntimeException("Lista de detalles de cotizaciones vacia");
    }

    public static DetalleCotizacion DetalleCotizacion(String id){
        String sql = "SELECT * FROM DetallesCotizacion WHERE id = ?";

        try (Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                ProductoCrudl crud = new ProductoCrudl();
                Producto encontrado = crud.buscar(rs.getString("producto"));
                System.out.println(encontrado.getNombreProducto());
                DetalleCotizacion detalle = new DetalleCotizacion(encontrado,rs.getInt("cantidad"),rs.getDouble("precio_unitario"),rs.getDouble("descuento"),rs.getDouble("iva"),rs.getInt("tiempo_garantia"));
                Cotizacion cotizacion = Cotizacion(rs.getString("cotizacion"));
                return detalle;
            }else{
                throw new RuntimeException("No se encontro detalle de cotizacion con id: "+id);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error al Seleccionar detalle de cotizacion: "+e.getMessage());
        }
    }

    public static ArrayList<AreaDeTrabajo> AreasDeTrabajo(){
        String sql = "SELECT * FROM AreasDeTrabajo";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<AreaDeTrabajo> listaDeAreas = new ArrayList<>();

            while (rs.next()){
                AreaDeTrabajo nueva = new AreaDeTrabajo(rs.getString("nombre"),rs.getString("descripcion"));
                nueva.setIdAreaDeTrabajo(rs.getString("id"));
                listaDeAreas.add(nueva);
            }
            return listaDeAreas;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar areas de trabajo: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar areas de trabajo");
    }

    public static ArrayList<Cliente> Clientes(){
        String sql = "SELECT * FROM Clientes";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){
            ArrayList<Cliente> listaDeClientes = new ArrayList<>();
            while (rs.next()){
                Cliente nuevo = new Cliente(rs.getString("nombre"),TipoID.valueOf(rs.getString("tipo_id")),rs.getString("identificacion"),rs.getString("correo"),rs.getString("telefono"),rs.getString("direccion"));
                listaDeClientes.add(nuevo);
            }
            return listaDeClientes;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar Clientes de trabajo: "+e.getMessage());
        }
        throw new RuntimeException("Error: ");
    }

    public static Cliente Cliente(String identificacion){
        String sql = "SELECT * FROM Clientes WHERE identificacion = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,identificacion);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return new Cliente(rs.getString("nombre"),TipoID.valueOf(rs.getString("tipo:id")),rs.getString("identificacion"),rs.getString("correo"),rs.getString("telefono"),rs.getString("direccion"));
            }else{
                throw new RuntimeException(Color.ROJO_BOLD+"No se encontro cliente con identificacion: "+Color.PURPLE_BOLD+identificacion);
            }

        }catch (SQLException e){
            System.out.println("Error al Seleccionar cliente: "+e.getMessage());
        }

        throw new RuntimeException("Error al Seleccionar cliente");
    }

    public static ArrayList<Provedor> Provedores(){
        String sql = "SELECT * FROM provedores";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(); ){
            ArrayList<Provedor> listaDeProvedores = new ArrayList<>();
            while (rs.next()){
                Provedor nuevo = new Provedor(rs.getString("nombre"),rs.getString("id"));
                listaDeProvedores.add(nuevo);
            }
            return listaDeProvedores;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar provedores: "+e.getMessage());
        }
        throw new RuntimeException("Error al establecer conexión con la base de datos");
    }

    public static Provedor Provedor(String id) throws Exception {
    String sql = "SELECT * FROM provedores WHERE codigo = ?";

    try (Connection conn = DatabaseConexion.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return new Provedor(rs.getString("nombre"), rs.getString("codigo"));
        } else {
            throw new Exception("No se encontró proveedor con id: " + id);
        }
    } catch (SQLException e) {
        throw new Exception("Error al seleccionar proveedor: " + e.getMessage());
    }
}

    public static Pedido Pedido(String id){
        String sql = "SELECT * FROM Pedidos WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Pedido encontrado = new Pedido(rs.getString("codigo"),rs.getString("estado"), rs.getDate("fecha_emision"),rs.getDate("fecha_entrega"));
                encontrado.setDetalles(DetallesPedido(rs.getString("codigo")));
                return encontrado;
            }else{
                throw new RuntimeException("No se encontro pedido con id: "+id);
            }

        }catch (SQLException e){
            System.out.println("Error al Seleccionar pedido: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar pedido");
    }

    public static ArrayList<Pedido> Pedidos(){
        String sql = "SELECT * FROM Pedidos";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery(); ){
            ArrayList<Pedido> listaDePedidos = new ArrayList<>();
            while (rs.next()){
                Pedido nuevo = new Pedido(rs.getString("codigo"),rs.getString("estado"), rs.getDate("fecha_emision"),rs.getDate("fecha_entrega"));
                nuevo.setDetalles(DetallesPedido(rs.getString("codigo")));
                listaDePedidos.add(nuevo);
            }
            return listaDePedidos;

        }catch (SQLException e){
            System.out.println("Error al Seleccionar pedidos: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar pedidos");
    }

    public static ArrayList<DetallePedido> DetallesPedido(String codigoPedido){
        String sql = "SELECT * FROM DetallesPedido WHERE codigo_pedido = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,codigoPedido);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<DetallePedido> listaDePedidos = new ArrayList<>();

            while (rs.next()){
                DetallePedido nuevo = new DetallePedido(rs.getInt("codigo_pedido"),Producto(rs.getString("producto")),rs.getInt("cantidad"),rs.getDouble("precio_unitario"),rs.getDouble("descuento"),rs.getDouble("iva"),rs.getInt("cantidad"), rs.getBoolean("verificado"));
                nuevo.setId(rs.getInt("id"));
                listaDePedidos.add(nuevo);
            }
            return listaDePedidos;
        }catch (SQLException e){
            System.out.println("Error al Seleccionar pedidos: "+e.getMessage());
        }
        throw new RuntimeException("Error al Seleccionar pedidos");
    }
}