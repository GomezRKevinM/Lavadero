package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Servicio {
    String codigo;
    String nombre;
    String descripcion;
    double precioDeVenta;
    List<Empleado> funcionarios;
    String imagen;
    boolean disponibilidad;
    Categoria categoria;

    public Servicio(String codigo, String nombre, String descripcion, double precioDeVenta, String imagen, boolean disponibilidad, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioDeVenta = precioDeVenta;
        this.imagen = imagen;
        this.disponibilidad = disponibilidad;
        this.categoria = categoria;
    }

    public String getFuncionariosJSON(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(funcionarios);
    }

    public ArrayList<Empleado> getFuncionariosArray(String funcionariosJSON){
        Gson gson = new Gson();
        Type token = new TypeToken<ArrayList<Empleado>>(){}.getType();
        return gson.fromJson(funcionariosJSON, token);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public List<Empleado> getFuncionario() {
        return funcionarios;
    }

    public void setFuncionario(List<Empleado> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void addFuncionario(Empleado empleado) {
        this.funcionarios.add(empleado);
    }

    public void removeFuncionario(Empleado empleado) {
        this.funcionarios.remove(empleado);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    
}
