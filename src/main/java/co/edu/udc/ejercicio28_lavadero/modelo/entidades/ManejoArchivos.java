package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.io.*;


/**
 *
 * @author Kevin Gómez
 */
public class ManejoArchivos {
    
    public static void main(String[] args){
        crearArchivo("Hola.txt");
    }
    
    public static void crearArchivo(String nombre){
        try {
            File archivo = new File(nombre);
            PrintWriter salida = new PrintWriter(archivo);
            
            salida.close();
            System.console().flush();
            System.out.println("Se creo el archivo "+nombre);   
        } catch (FileNotFoundException ex) {
            System.out.println("Error: "+ex);
        }
    }

    public static void escribirArchivo(String nombre, String texto) throws InterruptedException, IOException{
        try {
            File archivo = new File(nombre);
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(texto);
            salida.close();
            System.out.println("Se escribio en el archivo "+nombre);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: "+ex);
        }
    }

    public static void leerArchivo(String nombre){
        try {
            File archivo = new File(nombre);
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = entrada.readLine();
            while(linea != null){
                System.out.println(linea);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: "+ex);
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
    }

    public static String getContenido(String nombre){
        try {
            File archivo = new File(nombre);
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = entrada.readLine();
            String contenido = "";
            while(linea != null){
                contenido += linea+"\n";
                linea = entrada.readLine();
            }
            entrada.close();
            return contenido;
        } catch (FileNotFoundException ex) {
            System.out.println("Error: "+ex);
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
        return "";  
    }


    public static void borrarArchivo(String nombre){
        File archivo = new File(nombre);
        archivo.delete();
        System.out.println("Se borro el archivo "+nombre);
    }
    
    public static void listarArchivos(String carpeta){
        File archivo = new File(carpeta);
        File[] archivos = archivo.listFiles();
        for (File file : archivos) {
            System.out.println(" - "+file.getName().replace(".json", ""));
        }
    }
    
}
