import BBDD.ConexionMongo;
import Control.GestorAlumnos;
import Control.GestorProfesores;

import java.util.Scanner;

public class CentroEstudios {
    private final GestorAlumnos gestorAlumnos;
    private final GestorProfesores gestorProfesores;
    private final Scanner scanner;

    public CentroEstudios() {
        gestorAlumnos = new GestorAlumnos();
        gestorProfesores = new GestorProfesores();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("Menú de opciones:");
            System.out.println("1- Insertar un profesor");
            System.out.println("2- Insertar un alumno");
            System.out.println("3- Mostrar todos los datos");
            System.out.println("4- Mostrar profesores");
            System.out.println("5- Mostrar alumnos");
            System.out.println("6- Buscar alumno por email");
            System.out.println("7- Buscar profesor por rango de edad");
            System.out.println("8- Actualizar profesor");
            System.out.println("9- Dar de baja alumnos con calificación >= 5");
            System.out.println("10- Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> gestorProfesores.insertarProfesor();
                case 2 -> gestorAlumnos.insertarAlumno();
                case 3 -> {
                    gestorAlumnos.mostrarAlumnos();
                    gestorProfesores.mostrarProfesores();
                }
                case 4 -> gestorProfesores.mostrarProfesores();
                case 5 -> gestorAlumnos.mostrarAlumnos();
                case 6 -> gestorAlumnos.buscarAlumno();
                case 7 -> gestorProfesores.buscarProfesor();
                case 8 -> gestorProfesores.actualizarProfesor();
                case 9 -> gestorAlumnos.darDeBajaAlumnos();
                case 10 -> {
                    ConexionMongo.close();
                    System.out.println("¡Adiós!");
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    public static void main(String[] args) {
        new CentroEstudios().iniciar();
    }
}
