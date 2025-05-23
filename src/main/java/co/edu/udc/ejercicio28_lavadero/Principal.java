package co.edu.udc.ejercicio28_lavadero;

import java.io.IOException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import co.edu.udc.ejercicio28_lavadero.modelo.crud.*;
import co.edu.udc.ejercicio28_lavadero.util.*;


import co.edu.udc.ejercicio28_lavadero.modelo.entidades.*;

import java.util.*;

import com.google.gson.Gson;

import java.io.FileReader;




/**
 *
 * @author Kevin Manuel Gómez Rojas
 */
public class Principal {
    

    public static void main(String[] args) throws Exception {
        EmpleadoCrudl empleadoCrud = new EmpleadoCrudl();
        CotizacionCrudl cotizacionCrud = new CotizacionCrudl();
        PedidoCrudl pedidoCrud = new PedidoCrudl();
        ClienteCrudl clienteCrud = new ClienteCrudl();
        BodegaCrudl bodegaCrud = new BodegaCrudl();
        ProveedorCrudl proveedorCrud = new ProveedorCrudl();
        CatalogoCrudl catalogoCrud = new CatalogoCrudl();
        EmpresaCrudl empresaCrud = new EmpresaCrudl();
        ProductoCrudl productoCrud = new ProductoCrudl();
        ServicioCrudl servicioCrudl = new ServicioCrudl();
        CategoriaCrudl categoriaCrud = new CategoriaCrudl();

        Cliente nuevoCliente = new Cliente("Jhon Arrieta",TipoID.Cedula,"73546388","Jarrieta@unicartagena.edu.co","3154512301","Manga mz 4 Cr5 #56B-44");
        clienteCrud.agregar(nuevoCliente);
        System.out.println("Total de clientes registrados: "+clienteCrud.contar());
        for(Cliente cliente: clienteCrud.listarTodo()){
            System.out.println(cliente.getNombre()+" CC: "+cliente.getIdentificacion()+" tipo de id:"+cliente.getTipoID().name());
            System.out.println("\n "+cliente.getInfoContacto());
        };
    }

    public static void categoriaMenu() throws Exception {
        CategoriaCrudl crud = new CategoriaCrudl();
        limpiarPantalla();
        Scanner input = new Scanner(System.in);
        System.out.println(Color.CYAN_BOLD+"Menu Categorias"+Color.RESET);
        System.out.println("1) Crear Categoria");
        System.out.println("2) Buscar Categoria");
        System.out.println("3) Editar Categoria");
        System.out.println("4) Eliminar Categoria");
        String select = input.nextLine();
        switch (select){
            case "1":
                limpiarPantalla();
                System.out.println("Nombre de la categoria: ");
                String nombreCategoria = input.nextLine();
                limpiarPantalla();
                System.out.println("Icono: ");
                String icono = input.nextLine();
                Categoria nueva = new Categoria(nombreCategoria,icono);
                crud.agregar(nueva);
                categoriaMenu();
                break;
            case "2":
                limpiarPantalla();
                System.out.print(Color.CYAN_BLINK+"Buscar Categoria (nombre o codigo): ");
                String busqueda = input.nextLine();
                Categoria encontrada = crud.buscar(Integer.parseInt(busqueda));
                System.out.println("Codigo: "+encontrada.getCodigo());
                System.out.println("Categoria: "+encontrada.getNombre());
                System.out.println("Icono: "+encontrada.getIcono());
                break;
            case "3":
                System.out.print("Ingrese el codigo de la categoria a editar: ");
                String codigoCategoria = input.nextLine();
                crud.editar(crud.buscar(Integer.parseInt(codigoCategoria)));
                break;
            case "4":
                System.out.print("Ingrese el codigo de la categoria a eliminar: ");
                String codigoCategoriaEliminar = input.nextLine();
                DeleteData.DeleteTable("Categorias","codigo",Integer.parseInt(codigoCategoriaEliminar));
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
        input.close();
    }

    public static void menuPrincipal() throws InterruptedException, IOException{
        System.out.println("Menu principal de lavadero");
        Scanner entrada = new Scanner(System.in);
        System.out.println("1.Ingresar");
        System.out.println("2.Listar empresas");
        System.out.println("3.Crear empresa");
        System.out.println("4.Salir");
        int opcion = Integer.parseInt(entrada.nextLine());
        switch(opcion){
            case 1:
                System.out.println("Ingrese nombre de la empresa: ");
                String empresa = entrada.nextLine();
                Empresa empresaCargada = cargarEmpresa(empresa);
                System.out.println("Empresa cargada: "+empresaCargada.getNombre());
                menuEmpresa(empresaCargada);
                break;
            case 2:
                listarEmpresas();
                Scanner sc = new Scanner(System.in);
                System.out.println("Presione enter para continuar");
                sc.nextLine();
                menuPrincipal();
                break;
            case 3:
                crearEmpresa();
                break;
            case 4:
                System.exit(0);
                break;
        }
        entrada.close();
    }

    public static void menuEmpresa(Empresa empresa) throws InterruptedException, IOException{
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
       while(opcion > 5 || opcion < 1){
        limpiarPantalla();
        System.out.println(Color.PURPLE_BOLD+" Bienvenido a la empresa "+Color.VERDE_BLINK+empresa.getNombre()+Color.CYAN_BOLD);
        System.out.println("  1."+Color.BLANCO_BOLD+" catalogo"+Color.CYAN_BOLD);
        System.out.println("  2."+Color.BLANCO_BOLD+" empleados"+Color.CYAN_BOLD);
        System.out.println("  3."+Color.BLANCO_BOLD+" clientes"+Color.CYAN_BOLD);
        System.out.println("  4."+Color.BLANCO_BOLD+" Empresa"+Color.CYAN_BOLD);
        System.out.println("  5."+Color.BLANCO_BOLD+" Salir"+Color.RESET);
        try{
            opcion = Integer.parseInt(sc.nextLine());
            if(opcion > 5 || opcion < 1){
                System.out.println(Color.ROJO_BOLD+"Opcion no valida"+Color.RESET);
                System.out.println("Presione enter para continuar");
                sc.nextLine();
            }
        }catch(Exception e){
            System.out.println(Color.ROJO_BOLD+"Opcion no valida"+Color.RESET);
            System.out.println("Presione enter para continuar");
            sc.nextLine();
        }
       }
        switch(opcion){
            case 1:
            limpiarPantalla();
                menuCatalogo(empresa);
                break;
            case 2:
            limpiarPantalla();
                menuEmpleados(empresa);
                break;
            case 3:
            limpiarPantalla();

                menuClientes(empresa);
                break;
            case 4:
            limpiarPantalla();

                System.out.println(Color.PURPLE_BOLD+"Empresa "+Color.VERDE_BLINK+empresa.getNombre()+Color.BLANCO_BOLD+" config"+Color.CYAN_BOLD);
                System.out.println("  1."+Color.BLANCO_BOLD+" Informacion de la empresa"+Color.CYAN_BOLD);
                System.out.println("  2."+Color.BLANCO_BOLD+" Documentos de la empresa"+Color.CYAN_BOLD);
                System.out.println("  3."+Color.BLANCO_BOLD+" Cuentas de bancos"+Color.CYAN_BOLD);
                System.out.println("  4."+Color.BLANCO_BOLD+" Salir"+Color.RESET);
                int opcion2 = Integer.parseInt(sc.nextLine());
                switch(opcion2){
                    case 1:
                    limpiarPantalla();
                    System.out.println(Color.PURPLE_BOLD+" Informacion de la empresa "+Color.RESET);

                    System.out.println(Color.CYAN_BLINK+"Empresa: "+Color.RESET+empresa.getNombre());
                    System.out.println(Color.CYAN_BLINK+"Codigo: "+Color.RESET+empresa.getCodigo());
                    System.out.println(Color.PURPLE_BOLD+"Información de Pago: ["+Color.RESET);
                    System.out.println(Color.CYAN_BLINK+"Metodos de pago existentes: "+Color.RESET+empresa.getInformacionDePago().getMetodos());
                    System.out.println(Color.CYAN_BLINK+"Direccion: "+Color.RESET+empresa.getInformacionDePago().getDireccion());
                    System.out.println(Color.CYAN_BLINK+"Telefono: "+Color.RESET+empresa.getInformacionDePago().getTelefono());
                    System.out.println(Color.CYAN_BLINK+"Correo: "+Color.RESET+empresa.getInformacionDePago().getEmail());
                    System.out.println(Color.PURPLE_BOLD+"]"+Color.RESET);
                    System.out.println(Color.CYAN_BLINK+"Cuentas de bancos existentes: "+Color.RESET+empresa.getInformacionDePago().getCuentas().size());
                    System.out.println(Color.PURPLE_BOLD+"[");
                    for(int i = 0; i < empresa.getInformacionDePago().getCuentas().size(); i++){
                        System.out.println(Color.VERDE_UNDERLINE+"Cuenta "+Color.BLANCO_BOLD+(i+1)+Color.CYAN_BLINK+" {");
                        System.out.println(Color.CYAN_BLINK+"Banco: "+Color.BLANCO_BOLD+empresa.getInformacionDePago().getCuentas().get(i).getBanco());
                        System.out.println(Color.CYAN_BLINK+"Tipo de cuenta: "+Color.BLANCO_BOLD+empresa.getInformacionDePago().getCuentas().get(i).getTipoCuenta());
                        System.out.println(Color.CYAN_BLINK+"Numero de cuenta: "+Color.BLANCO_BOLD+empresa.getInformacionDePago().getCuentas().get(i).getNumeroCuenta());
                        System.out.println(Color.CYAN_BLINK+"Titular: "+Color.BLANCO_BOLD+empresa.getInformacionDePago().getCuentas().get(i).getTitular());
                        System.out.println(Color.CYAN_BLINK+"}"+Color.RESET);
                    }
                    System.out.println(Color.PURPLE_BOLD+"]"+Color.RESET);
                    // System.out.println(Color.CYAN_BLINK+"Documentos existentes cantidad: "+Color.BLANCO_BOLD+empresa.getDocumentos().size());
                    System.out.println(Color.CYAN_BLINK+"Empleados contratados: "+Color.BLANCO_BOLD+empresa.getEmpleados().size());
                    System.out.println(Color.CYAN_BLINK+"Clientes registrados: "+ Color.BLANCO_BOLD+empresa.getClientes().size());
                    System.out.println(Color.CYAN_BLINK+"Bodegas existentes: "+Color.BLANCO_BOLD+empresa.getBodegas().size());
                    System.out.println(Color.CYAN_BLINK+"Proveedores existentes: "+Color.BLANCO_BOLD+empresa.getProveedores().size());
                    System.out.println(Color.CYAN_UNDERLINE+"Categorias existentes: "+Color.BLANCO_BOLD+empresa.getCatalogo().getCategorias().size());
                    System.out.println(Color.CYAN_UNDERLINE+"Productos existentes: "+Color.BLANCO_BOLD+empresa.getCatalogo().getProductos().size());
                    System.out.println(Color.CYAN_UNDERLINE+"Servicios existentes: "+Color.BLANCO_BOLD+empresa.getCatalogo().getServicios().size());
                    System.out.println("Presione enter para continuar");
                    sc.nextLine();
                    menuEmpresa(empresa);
                    break;
                    case 2:
                    menuDocumentos(empresa);
                    break;
                    case 3:
                    menuCategorias(empresa);
                    break;
                    case 4:
                    System.out.println("Volviendo a la pantalla principal...");
                    System.out.println("Presione enter para continuar");
                    sc.nextLine();
                    menuEmpresa(empresa);
                    break;
                }
                break;
            case 5:
                System.out.println("Cerrando programa...");
                System.out.println("Presione enter para salir");
                sc.nextLine();
                System.exit(0);
                break;
        }
        
        sc.close();
    }

    private static void menuDocumentos(Empresa empresa) throws InterruptedException, IOException {
      
    }

    public static void menuCatalogo(Empresa empresa) throws InterruptedException, IOException{
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        while(opcion < 1 || opcion > 4){
            limpiarPantalla();
            System.out.println(Color.PURPLE_BOLD+"Menu catalogo\n"+Color.CYAN_BOLD);
            System.out.println("  1."+Color.BLANCO_BOLD+" Productos"+Color.CYAN_BOLD);
            System.out.println("  2."+Color.BLANCO_BOLD+" Servicios"+Color.CYAN_BOLD);
            System.out.println("  3."+Color.BLANCO_BOLD+" Categorias"+Color.CYAN_BOLD);
            System.out.println("  4."+Color.BLANCO_BOLD+" Volver atras"+Color.RESET);
            
            try {
             opcion = Integer.parseInt(sc.nextLine());
                if(opcion < 1 || opcion > 4){
                    System.out.println(Color.ROJO_BOLD+"Opcion no valida"+Color.RESET);
                    System.out.println("Presione enter para continuar");
                    sc.nextLine();
                }         
            } catch (Exception e) {
                System.out.println(Color.ROJO_BOLD+"Opcion no valida"+Color.RESET);
                    System.out.println("Presione enter para continuar");
                    sc.nextLine();
            }
        }
        switch(opcion){
            case 1:
                menuProductos(empresa);
                break;
            case 2:
                menuServicios(empresa);
                break;
            case 3:
                menuCategorias(empresa);
                break;
            case 4:
                menuEmpresa(empresa);
                break;
        }
    }
    
    public static void menuProductos(Empresa empresa) throws InterruptedException, IOException{
        Scanner sc = new Scanner(System.in);

        int select = 0;
        while(select < 1 || select > 6){
            limpiarPantalla();
            System.out.println(Color.PURPLE_ITALIC+"\r\nCategoria > Productos \n"+Color.CYAN_BOLD);
            System.out.println(" 1."+Color.BLANCO_BOLD+" Ver Productos"+Color.CYAN_BOLD);
            System.out.println(" 2."+Color.BLANCO_BOLD+" Agregar Producto"+Color.CYAN_BOLD);
            System.out.println(" 3."+Color.BLANCO_BOLD+" Editar Producto"+Color.CYAN_BOLD);
            System.out.println(" 4."+Color.BLANCO_BOLD+" Eliminar Producto"+Color.CYAN_BOLD);
            System.out.println(" 5."+Color.BLANCO_BOLD+" Buscar Producto"+Color.CYAN_BOLD);
            System.out.println(" 6."+Color.BLANCO_BOLD+" Volver atrás"+Color.RESET);
            
            select = Integer.parseInt(sc.nextLine());
            
            try {
                if(select < 1 || select > 6){
                    System.out.println(Color.ROJO_BOLD+"Opcion no valida"+Color.RESET);
                    System.out.println("Presione enter para continuar");
                    sc.nextLine();
                   
                }
            } catch (Exception e) {
                System.out.println(Color.ROJO_BOLD+"Opcion no valida"+Color.RESET);
                System.out.println("Presione enter para continuar");
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        switch (select) {
            case 1:
                limpiarPantalla();
                System.out.println(Color.CYAN_BG+"Ver productos: \n"+Color.RESET);
                int i = 0;
                for (Bodega bodega: empresa.getBodegas()) {
                    for(Producto producto: bodega.getProductos()){
                        i++;
                        System.out.println(Color.BLANCO_BOLD+producto.getNombreProducto()+Color.AMARILLO_BLINK+" Valor: "+Color.VERDE_BOLD+convertirDivisa(producto.getPrecio())+Color.BLANCO_BOLD+" Codigo: "+Color.PURPLE+producto.getCodigo()+Color.RESET);
                        System.out.println(Color.BLANCO_BOLD+"Marca: "+Color.CYAN+producto.getMarca()+Color.BLANCO_BOLD+" Stock: "+Color.VERDE_BOLD+producto.getStock()+Color.RESET);
                    }
                }

                if(i == 0){
                    System.out.println(Color.ROJO_BOLD+"No se encontraron productos"+Color.RESET);
                }else{
                    System.out.println(Color.BLANCO_BOLD+"Productos encontrados: "+Color.VERDE_BOLD+i+Color.RESET);
                }

                System.out.println("\nPresione enter para continuar");
                
                sc.nextLine();

                menuProductos(empresa);
                break;
            case 2:
                limpiarPantalla();
                String bodegaCodigo;
                if(empresa.getBodegas().size() == 0){
                    System.out.println(Color.ROJO_BOLD+"No hay bodegas creadas, creemos una"+Color.RESET);

                    System.out.println(Color.CYAN_BG+"Agregar Bodega"+Color.RESET);
                    System.out.print(Color.CYAN_BLINK+"Nombre de la bodega: "+Color.BLANCO_BLINK);
                    String nombreBodega = sc.nextLine();
                    System.out.println(Color.CYAN_BLINK+"Ubicación de la bodega: "+Color.BLANCO_BLINK);
                    String direccionBodega = sc.nextLine();
                    String codigoBodega = String.valueOf((nombreBodega+empresa.getNombre()).hashCode());
                    bodegaCodigo = codigoBodega;
                    Bodega bodega = new Bodega(codigoBodega,direccionBodega,nombreBodega);
                    empresa.agregarBodega(bodega);
                    System.out.println(Color.VERDE_BOLD+"Bodega creada con exito"+Color.RESET);
                    System.out.println("Presione enter para continuar");
                   

                    ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));

                }else{
                    limpiarPantalla();
                    System.out.println(Color.AZUL_BG+" Agregar Producto "+Color.RESET+"\n");
                    for(Bodega bodega: empresa.getBodegas()){
                        System.out.println(Color.BLANCO_BOLD+"Nombre:"+Color.VERDE_ITALIC+bodega.getNombre()+Color.BLANCO_BOLD+" codigo:"+Color.VERDE_ITALIC+bodega.getCodigo()+Color.RESET);
                    }
                    System.out.println("Seleccione la bodega a la que desea agregar el producto");
                    System.out.print(Color.CYAN_BLINK+"Codigo de la bodega: "+Color.BLANCO_BLINK);
                    bodegaCodigo = sc.nextLine();
                }

                System.out.println(Color.CYAN_BG+"Agregar Producto"+Color.RESET);
                System.out.println(Color.CYAN_BLINK+"Nombre del producto: "+Color.BLANCO_BLINK);
                String nombre = sc.nextLine();
                System.out.println(Color.CYAN_BLINK+"Precio: "+Color.BLANCO_BLINK);
                double precio = Double.parseDouble(sc.nextLine());
                System.out.println(Color.CYAN_BLINK+"Precio de compra: "+Color.BLANCO_BLINK);
                double precioCompra =  Double.parseDouble(sc.nextLine());
                System.out.println(Color.CYAN_BLINK+"Marca: "+Color.BLANCO_BLINK);
                String marca = sc.nextLine();
                System.out.println(Color.CYAN_BLINK+"Stock: "+Color.BLANCO_BLINK);
                int stock = Integer.parseInt(sc.nextLine());
               
                String codigo = String.valueOf((nombre+empresa.getNombre()).hashCode());
                for(Bodega bodega: empresa.getBodegas()){
                    if(bodega.getCodigo().equalsIgnoreCase(bodegaCodigo)){
                        ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                        System.out.println(Color.VERDE_BOLD+"Producto creado con exito"+Color.RESET);
                        System.out.println("Presione enter para continuar");
                        sc.nextLine();
                        break;
                    }else{
                        System.out.println(Color.ROJO_BOLD+"Bodega no encontrada"+Color.RESET);
                        System.out.println("Codigo buscado: "+bodegaCodigo);
                    }
                }
                System.out.println("Desea registrar el producto en una categoria? S/N");
                String respuesta = sc.nextLine();
                if(respuesta.equalsIgnoreCase("S")){
                    System.out.println("Seleccione la categoria del producto");
                    int cantidadCategorias = 0;
                    for(Categoria categoria: empresa.getCatalogo().getCategorias()){
                        System.out.println(Color.BLANCO_BOLD+"Nombre:"+Color.VERDE_ITALIC+categoria.getNombre()+Color.BLANCO_BOLD+" codigo:"+Color.VERDE_ITALIC+categoria.getCodigo()+Color.RESET);
                        cantidadCategorias++;
                    }
                    if(cantidadCategorias == 0){
                        System.out.println(Color.ROJO_BOLD+"No se encontraron categorias"+Color.RESET);
                        System.out.println("Quiere crear una? S/N");
                         respuesta = sc.nextLine();
                        if(respuesta.equalsIgnoreCase("S")){
                            limpiarPantalla();
                            System.out.println(Color.CYAN_BG+"Agregar Categoria"+Color.RESET);
                            System.out.println(Color.CYAN_BLINK+"Nombre de la categoria: "+Color.BLANCO_BLINK);
                            String nombreCategoria = sc.next();
                            String codigoCategoria = String.valueOf((nombreCategoria+empresa.getNombre()).hashCode());
                            Categoria categoria = new Categoria(nombreCategoria,"default.png",codigoCategoria);

                            ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                            System.out.println(Color.VERDE_BOLD+"Categoria creada con exito"+Color.RESET);
                            System.out.println(Color.VERDE_BOLD+"Producto agregado a la categoria"+Color.PURPLE_BOLD+categoria.getNombre()+Color.RESET);
                            System.out.println("Presione enter para continuar");
                            sc.nextLine();
                        }else{
                            System.out.println("Presione enter para continuar");
                            sc.nextLine();
                            menuProductos(empresa);
                        }
                    }else{
                        System.out.println(Color.CYAN_BLINK+"Categorias encontradas: "+cantidadCategorias+Color.RESET);
                        System.out.println(Color.CYAN_BLINK+"Codigo de la categoria: "+Color.BLANCO_BLINK);
                        sc.nextLine();
                        String codigoCategoria = sc.nextLine();
                        for(Categoria categoria: empresa.getCatalogo().getCategorias()){
                            if(categoria.getCodigo().equalsIgnoreCase(codigoCategoria)){

                                System.out.println(Color.VERDE_BOLD+"Producto agregado a la categoria"+Color.PURPLE_BOLD+categoria.getNombre()+Color.RESET);
                                System.out.println("Presione enter para continuar");
                                sc.nextLine();
                                menuProductos(empresa);
                            }else{
                                continue;
                            }
                        }
                    }
                }else{
                    menuProductos(empresa);
                }
                break;
            case 3:
                System.out.println(Color.CYAN_BLINK+"Productos existentes: ");

                for(Bodega bodega: empresa.getBodegas()){
                   System.out.println("Nombre:"+bodega.getNombre()+" codigo:"+bodega.getCodigo()+Color.CYAN_BLINK+" Productos:");
                   for(Producto product: bodega.getProductos()){
                       System.out.println(Color.CYAN_REVERSE+"Nombre: "+Color.BLANCO_REVERSE+" "+product.getNombreProducto()+" "+Color.CYAN_REVERSE+" Precio venta: "+Color.BLANCO_REVERSE+" "+convertirDivisa(product.getPrecio())+" "+Color.CYAN_REVERSE+" Stock: "+Color.BLANCO_REVERSE+" "+product.getStock()+" "+Color.CYAN_REVERSE+"Codigo: "+Color.BLANCO_REVERSE+" "+product.getCodigo()+" "+Color.RESET);
                   }
                }
                break;
            case 4:
            limpiarPantalla();

                System.out.println(Color.CYAN_BLINK+"Editar Producto");
                System.out.println("1. Cambiar Nombre");
                System.out.println("2. Cambiar precio");
                System.out.println("3. Cambiar stock");

                break;
            case 5:
                buscarProducto(empresa);
                break;
            case 6:
                menuCatalogo(empresa);
                break;
        }
        sc.close();

    }

    public static void buscarProducto(Empresa empresa) throws InterruptedException, IOException{
        Scanner entrada = new Scanner(System.in);
        limpiarPantalla();
        
        System.out.println();
        System.out.println("Buscar por:");
        System.out.println("1. Nombre");
        System.out.println("2. Categoria");
        System.out.println("3. Precio");
        System.out.println("4. Volver atrás");

        String select = entrada.nextLine();
        switch (select) {
            case "1":
            System.out.print("\nNombre del producto: ");
            String search = entrada.nextLine();
                for(Bodega bodega: empresa.getBodegas()){
                    bodega.buscarProducto(search, "Nombre");
                }
                System.out.println("\n Presiona Enter para volver al menu");
                select = entrada.nextLine();
                buscarProducto(empresa);
                break;
            case "2":
                System.out.println("Buscar por Categoria");
                search = entrada.nextLine();
                for(Bodega bodega: empresa.getBodegas()){
                    bodega.buscarProducto(search, "Categoria");
                }
                System.out.println("\n Presiona Enter para volver al menu");
                select = entrada.nextLine();
                buscarProducto(empresa);
                break;
            case "3":
                System.out.println("Buscar por Precio");
                search = entrada.nextLine();
                for(Bodega bodega: empresa.getBodegas()){
                    bodega.buscarProducto(search, "Precio");
                }
                System.out.println("\n Presiona Enter para volver al menu");
                select = entrada.nextLine();
                buscarProducto(empresa);
                break;
            case "4":
               menuProductos(empresa);
               break;
            default:
                buscarProducto(empresa);
                break;
        }
        entrada.close();
    }

    public static void menuServicios(Empresa empresa) throws InterruptedException, IOException{
        limpiarPantalla();

        System.out.println("Catalogo > Servicios ");
        System.out.println("1. Ver servicios"); 
        System.out.println("2. Crear servicio");
        System.out.println("3. Eliminar servicio");
        System.out.println("4. Editar servicio");
        System.out.println("5. Volver atrás");
    }
    
    public static void menuCategorias(Empresa empresa) throws InterruptedException, IOException{
        limpiarPantalla();

        System.out.println("Catalogo > Categorias ");
        System.out.println("1. Ver Categorias");
        System.out.println("2. Agregar Categoria");
        System.out.println("3. Eliminar Categoria");
        System.out.println("4. Editar Categoria ");
        System.out.println("5. Volver atrás");
    }

    public static void menuEmpleados(Empresa empresa) throws InterruptedException, IOException{
        limpiarPantalla();

        System.out.println(Color.PURPLE_BOLD+"Menu Empleados");
        System.out.println(Color.CYAN_BOLD+"1."+Color.BLANCO_BOLD+" Crear Empleado");
        System.out.println(Color.CYAN_BOLD+"2."+Color.BLANCO_BOLD+" Editar Empleado");
        System.out.println(Color.CYAN_BOLD+"3."+Color.BLANCO_BOLD+" Eliminar Empleado");
        System.out.println(Color.CYAN_BOLD+"4."+Color.BLANCO_BOLD+" Buscar Empleado");
        System.out.println(Color.CYAN_BOLD+"5."+Color.BLANCO_BOLD+" Ver Empleados");
        System.out.println(Color.CYAN_BOLD+"6."+Color.BLANCO_BOLD+" Volver atrás");

        System.out.println();
        Scanner entrada = new Scanner(System.in);
        String select = entrada.nextLine();

        switch (select) {
            case "1":
                System.out.println("Crear Empleado");
                System.out.print("\nNombre completo del empleado: ");
                String nombre = entrada.nextLine();

                System.out.println("Tipo de identificacion: ");
                for(int i = 0; i < TipoID.values().length; i++){
                    System.out.println((i+1)+". "+TipoID.values()[i]);
                }
                String tipoId = entrada.nextLine();
                TipoID tipo;
                if(tipoId.matches("[A-Za-z]+")) {
                     tipo = TipoID.valueOf(tipoId.toUpperCase());
                }else{
                    switch (Integer.parseInt(tipoId)) {
                        case 1:
                            tipo = TipoID.Cedula;
                            break;
                        case 2:
                            tipo = TipoID.NIT;
                            break;
                        case 3:
                            tipo = TipoID.Pasaporte;
                            break;
                        case 4:
                            tipo = TipoID.PermisoDeTrabajo;
                            break;
                        default:
                            tipo = TipoID.Cedula;
                            break;
                    }
                    entrada.close();
                }

                System.out.print("\nIdentificacion: ");
                String identificacion = entrada.nextLine();

                System.out.print("Correo: ");
                String correo = entrada.nextLine();

                System.out.print("Telefono: ");
                String telefono = entrada.nextLine();

                System.out.print("Direccion: ");
                String direccion = entrada.nextLine();

                System.out.println("\nEmpleado creado exitosamente");
                System.out.println("Crear contrato");

                Date fechaInicio = new Date();
                System.out.print("Fecha de inicio: "+fechaInicio.toString());

                System.out.print("Fecha de finalizacion: ");
                System.out.println("1. Indefinido");
                System.out.println("2. Definido");
                String opcion = entrada.nextLine();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicio);
                calendar.add(Calendar.YEAR, 1);
                Date fechaFinal = calendar.getTime();
                switch (opcion) {
                    case "1" :
                        calendar.add(Calendar.YEAR, 99);
                        fechaFinal = calendar.getTime();

                        break;
                    case "2":
                        System.out.print("Años: ");
                        int anios = Integer.parseInt(entrada.nextLine());
                        calendar.add(Calendar.YEAR, anios);
                        fechaFinal = calendar.getTime();
                        break;
                    default:
                        break;
                }
               


                System.out.print("Sueldo Base: ");
                double salario = entrada.nextDouble();

                System.out.print("Elija un Cargo: ");
                for(int i = 0; i < Cargo.values().length; i++){
                    System.out.println((i+1)+". "+Cargo.values()[i]);
                }
                String cargo = entrada.nextLine();
                cargo = cargo.toLowerCase();
                Cargo cargoEmpleado;
                if(cargo.matches("[A-Za-z]+")) {
                     cargoEmpleado = Cargo.valueOf(cargo.substring(0, 1).toUpperCase() + cargo.substring(1).toLowerCase());
                }else{
                    switch (Integer.parseInt(cargo)) {
                        case 1:
                            cargoEmpleado = Cargo.values()[0];
                            break;
                        case 2:
                            cargoEmpleado = Cargo.values()[1];
                            break;
                        case 3:
                            cargoEmpleado = Cargo.values()[2];
                            break;
                        case 4:
                            cargoEmpleado = Cargo.values()[3];
                            break;
                        default:
                            cargoEmpleado = Cargo.General;
                            break;
                    }
                }

                System.out.print("Horario: ");
                String horario = entrada.nextLine();
                

                Contrato contrato = new Contrato();
                Empleado empleado = new Empleado(nombre, tipo, identificacion, correo, telefono, direccion, contrato);
                empresa.agregarEmpleado(empleado);

                ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                System.out.println("Empleado creado exitosamente");
                System.out.println("\n Presiona Enter para volver al menu");
                select = entrada.nextLine();
                menuEmpleados(empresa);
                break;
            case "2":
                System.out.println("Editar Empleado");
                break;
            case "3":
                System.out.println("Eliminar Empleado");
                break;
            case "4":
                System.out.println("Buscar Empleado");
                break;
            case "5":
                System.out.println("Ver Empleados");
                break;
            case "6":
                menuEmpresa(empresa);
                break;
            default:
                break;
        }
    }

    public static void menuClientes(Empresa empresa) throws InterruptedException, IOException{
        limpiarPantalla();

        System.out.println(Color.PURPLE_BOLD+"Menu Clientes");
        System.out.println(Color.CYAN_BOLD+"1."+Color.BLANCO_BOLD+" Ver clientes");
        System.out.println(Color.CYAN_BOLD+"2."+Color.BLANCO_BOLD+" Buscar Cliente");
        System.out.println(Color.CYAN_BOLD+"3."+Color.BLANCO_BOLD+" Agregar Cliente");
        System.out.println(Color.CYAN_BOLD+"4."+Color.BLANCO_BOLD+" Editar Cliente");
        System.out.println(Color.CYAN_BOLD+"5."+Color.BLANCO_BOLD+" Eliminar Cliente");
        System.out.println(Color.CYAN_BOLD+"6."+Color.BLANCO_BOLD+" Volver atrás"+Color.RESET);

        System.out.print("Seleccione una opcion: ");
        Scanner sc = new Scanner(System.in);
        String select = sc.nextLine();
        switch (select) {
            case "1":
                for(Cliente cliente: empresa.getClientes()){
                    System.out.println("\nNombre: "+cliente.getNombre());
                    System.out.println("\nId: "+cliente.getIdentificacion());
                }
                System.out.println("\n Presiona Enter para volver al menu");
                select = sc.nextLine();
                menuClientes(empresa);
                break;
            case "2":
                System.out.println("Buscar Cliente");
                System.out.print("Ingrese la identificacion del cliente: ");
                String identificacion = sc.nextLine();
                empresa.buscarCliente(identificacion);
                System.out.println("\n Presiona Enter para volver al menu");
                select = sc.nextLine();
                menuClientes(empresa);
                break;
            case "3":
                System.out.println("Agregar Cliente");

                System.out.print("Ingrese el nombre del cliente: ");
                String nombre = sc.nextLine();
                System.out.print("Ingrese la identificacion del cliente: ");
                identificacion = sc.nextLine();
                System.out.println("Elija el tipo de identificacion: ");
                for(int i = 0; i < TipoID.values().length; i++){
                    System.out.println((i+1)+". "+TipoID.values()[i]);
                }
                String opcion = sc.nextLine();
                TipoID tipoIdentificacion;
                if(opcion.matches("[A-Za-z]+")) {
                     tipoIdentificacion = TipoID.valueOf(opcion.substring(0, 1).toUpperCase() + opcion.substring(1).toLowerCase());
                }else{
                   int opcionInt = Integer.parseInt(opcion);
                   tipoIdentificacion = TipoID.values()[opcionInt-1];
                }

                System.out.print("Ingrese el correo del cliente: ");
                String correo = sc.nextLine();
                System.out.print("Ingrese la direccion del cliente: ");
                String direccion = sc.nextLine();
                System.out.print("Ingrese el telefono del cliente: ");
                String telefono = sc.nextLine();
                Cliente cliente = new Cliente(nombre,tipoIdentificacion,identificacion,correo,direccion,telefono);

                empresa.agregarCliente(cliente);
                ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                System.out.println("Cliente creado exitosamente");
                System.out.println("\n Presiona Enter para volver al menu");
                select = sc.nextLine();
                menuClientes(empresa);
                break;
            case "4":
                limpiarPantalla();
                System.out.println("Editar Cliente");
                System.out.println("1. Nombre");
                System.out.println("2. tipo de identificacion");
                System.out.println("3. Correo");
                System.out.println("4. Direccion");
                System.out.println("5. Telefono");
                System.out.println("6. Volver atrás");
    
                select = sc.nextLine();
                switch (select) {
                    case "1":
                        System.out.println("Editar Nombre");
                        System.out.println("Ingrese el numero de identificacion del cliente: ");
                        identificacion = sc.nextLine();
                        Boolean encontrado = false;
                        for(Cliente client: empresa.getClientes()){
                            if(client.getIdentificacion().equals(identificacion)){
                                encontrado = true;
                                System.out.println("Nombre actual: "+client.getNombre());
                                System.out.print("Ingrese el nuevo nombre: ");
                                nombre = sc.nextLine();
                                client.setNombre(nombre);
                                System.out.println("Cliente modificado exitosamente");
                                break;
                            }else{
                                continue;
                            }
                        }
                        if(!encontrado){
                            System.out.println("No se encontro el cliente");
                            break;
                        }else{
                            ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                            System.out.println("Cliente modificado exitosamente");
                            System.out.println("\n Presiona Enter para volver al menu");
                            select = sc.nextLine();
                            menuClientes(empresa);
                        }

                        break;
                    case "2":
                        System.out.println("Editar Tipo de Identificacion");
                        System.out.println("Ingrese el numero de identificacion del cliente: ");
                        identificacion = sc.nextLine();
                        encontrado = false;
                        for(Cliente client: empresa.getClientes()){
                            if(client.getIdentificacion().equals(identificacion)){
                                encontrado = true;
                                System.out.println("Tipo de identificacion actual: "+client.getIdentificacion());
                                System.out.println("Elija el tipo de identificacion: ");
                                for(int i = 0; i < TipoID.values().length; i++){
                                    System.out.println((i+1)+". "+TipoID.values()[i]);
                                }
                                 opcion = sc.nextLine();
                                
                                if(opcion.matches("[A-Za-z]+")) {
                                    if(TipoID.valueOf(opcion.substring(0, 1).toUpperCase() + opcion.substring(1).toLowerCase()) == null){
                                        System.out.println("Opcion no valida");
                                        break;
                                    }else{
                                        tipoIdentificacion = TipoID.valueOf(opcion.substring(0, 1).toUpperCase() + opcion.substring(1).toLowerCase());
                                    }
                                }else{
                                   int opcionInt = Integer.parseInt(opcion);
                                   if(opcionInt > TipoID.values().length){
                                       System.out.println("Opcion no valida");
                                       break;
                                   }
                                   tipoIdentificacion = TipoID.values()[opcionInt-1];
                                }
                                client.setTipoID(tipoIdentificacion);
                                ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                                System.out.println("Cliente modificado exitosamente");
                                break;
                            }else{
                                continue;
                            }
                        }
                        if(!encontrado){
                            System.out.println("No se encontro el cliente");
                            break;
                        }
                        break;
                    case "3":
                        System.out.println("Editar Correo");
                        System.out.println("Ingrese el numero de identificacion del cliente: ");
                        identificacion = sc.nextLine();
                        encontrado = false;
                        for(Cliente client: empresa.getClientes()){
                            if(client.getIdentificacion().equals(identificacion)){
                                encontrado = true;
                                System.out.println("Correo actual: "+client.getCorreo());
                                System.out.print("Ingrese el nuevo correo: ");
                                correo = sc.nextLine();
                                client.setCorreo(correo);
                                ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                                System.out.println("Cliente modificado exitosamente");
                                break;
                            }else{
                                continue;
                            }
                        }
                        if(!encontrado){
                            System.out.println("No se encontro el cliente");
                            break;
                        }
                        break;
                    case "4":
                        System.out.println("Editar Direccion");
                        System.out.println("Ingrese el numero de identificacion del cliente: ");
                        identificacion = sc.nextLine();
                        encontrado = false;
                        for(Cliente client: empresa.getClientes()){
                            if(client.getIdentificacion().equals(identificacion)){
                                encontrado = true;
                                System.out.println("Direccion actual: "+client.getDireccion());
                                System.out.print("Ingrese la nueva direccion: ");
                                direccion = sc.nextLine();
                                client.setDireccion(direccion);
                                ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                                System.out.println("Cliente modificado exitosamente");
                                break;
                            }else{
                                continue;
                            }
                        }
                        if(!encontrado){
                            System.out.println("No se encontro el cliente");
                            break;
                        }
                        break;
                    case "5":
                        System.out.println("Editar Telefono");
                        System.out.println("Ingrese el numero de identificacion del cliente: ");
                        identificacion = sc.nextLine();
                        encontrado = false;
                        for(Cliente client: empresa.getClientes()){
                            if(client.getIdentificacion().equals(identificacion)){
                                encontrado = true;
                                System.out.println("Telefono actual: "+client.getTelefono());
                                System.out.print("Ingrese el nuevo telefono: ");
                                telefono = sc.nextLine();
                                client.setTelefono(telefono);
                                ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
                                System.out.println("Cliente modificado exitosamente");
                                break;
                            }else{
                                continue;
                            }
                        }
                        if(!encontrado){
                            System.out.println("No se encontro el cliente");
                            break;
                        }
                        break;
                    case "6":
                        menuEmpresa(empresa);
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        menuEmpresa(empresa);
                        break;
                }
            }
            sc.close();
    }

    public static void crearEmpresa() throws InterruptedException, IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de la empresa: ");
        String nombre = sc.nextLine();
        System.out.println("Codigo de la empresa: ");
        String codigo = sc.nextLine();
        Empresa empresa = new Empresa(nombre, codigo);

        ManejoArchivos.crearArchivo("DB/Empresas/"+empresa.getNombre()+".json");
        ManejoArchivos.escribirArchivo("DB/Empresas/"+empresa.getNombre()+".json", new Gson().toJson(empresa));
        sc.close();
    }

    public static void listarEmpresas() throws InterruptedException, IOException{
        System.out.println("Empresas: ");
        ManejoArchivos.listarArchivos("DB/Empresas/");
    }

    public static void listarProveedores(Empresa empresa){
        for(Provedor provedor : empresa.getProveedores()){
            System.out.println(provedor.getNombre());
        }
    }
    
    public static void listarProveedoresDeProductosAgotados(Empresa empresa){
        empresa.getProveedoresDeProductosAgotados();
    }

    public static Empresa cargarEmpresa(String empresa) throws InterruptedException, IOException{
        Gson gson = new Gson();
        FileReader reader = new FileReader("DB/Empresas/"+empresa+".json");
        Empresa e = gson.fromJson(reader, Empresa.class);
        return e; 
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String convertirDivisa(double monto){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.forLanguageTag("es-CO"));
        dfs.setCurrencySymbol(Currency.getInstance("COP").getSymbol());
        DecimalFormat df = new DecimalFormat("¤ #,##0", dfs);
        return df.format(monto)+" "+dfs.getCurrency().getCurrencyCode();
    }
}