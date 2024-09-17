package resources;

import java.io.File;

public class Entrada2 {
    public static void main(String[] args) {

        File actividad1 = new File("Archivos");
        File actividad2 = new File("C:/");



        // Obtener la lista de archivos en la carpeta
        File[] lista = actividad1.listFiles();

        // Verificar si la carpeta contiene archivos
        if (lista != null) {
            for (File file : lista) {
                if (file.isFile()) {
                    System.out.println("Archivo: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Carpeta: " + file.getName());
                }
            }
        } else {
            System.out.println("La carpeta está vacía o no existe.");
        }
    }
}
