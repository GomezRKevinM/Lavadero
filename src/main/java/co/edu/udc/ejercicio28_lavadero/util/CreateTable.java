package co.edu.udc.ejercicio28_lavadero.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "nombre TEXT NOT NULL,"+
                    "email TEXT NOT NULL UNIQUE)";

        try(Connection conn = DatabaseConexion.getConnection();
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabla creada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: "+e.getMessage());
        }
    }

    public void createProductosTable(){
    String sql = "create table if not exists Productos\n(" +
            "    codigo           integer\n" +
            "        constraint Productos_pk\n" +
            "            primary key autoincrement,\n" +
            "    nombre_producto  TEXT    not null\n" +
            "        constraint Productos_pk_2\n" +
            "            unique,\n" +
            "    categoria        integer not null\n" +
            "        constraint FK_PRODUCTOS_CATEGORIA\n" +
            "            references Categorias,\n" +
            "    precio           integer not null,\n" +
            "    stock            integer,\n" +
            "    marca            TEXT    not null,\n" +
            "    precio_de_compra integer,\n" +
            "    alerta           integer not null,\n" +
            "    codigo_provedor  integer\n" +
            "        constraint FK_PRODUCTOS_PROVEDORES\n" +
            "            references provedores,\n" +
            "    constraint check_precio_compra\n" +
            "        check (precio > precio_de_compra));";
    }

    public void createEmpresaTable(){
        String sql = "create table Empresas\n" +
                "(\n" +
                "    id               integer\n" +
                "        constraint Empresas_pk\n" +
                "            primary key autoincrement,\n" +
                "    nombre           TEXT    not null,\n" +
                "    catalogo         integer not null\n" +
                "        constraint Empresas_pk_2\n" +
                "            unique,\n" +
                "    informacion_pago integer not null,\n" +
                "    unique (catalogo)\n" +
                ");";
    }
}
