package resources;
import java.io.File;

public class Entrada {
    public static void main(String[] args) {
        File actividad2 = new File("C:/");
        System.out.println("Actividad 1 Acceso a datos");
        listarTodo(actividad2);
    }
    public static void listarTodo(File dir) {
        File[] listaDeArchivos = dir.listFiles();
        if (listaDeArchivos != null) {
            for (File file : listaDeArchivos) {
                if (file.isFile()) {
                    System.out.println("Archivo: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directorio: " + file.getName());
                    listarTodo(file);
                }
            }
        } else {
            System.out.println("No se pudo acceder al directorio o está vacío: " + dir.getAbsolutePath());
        }
    }
}