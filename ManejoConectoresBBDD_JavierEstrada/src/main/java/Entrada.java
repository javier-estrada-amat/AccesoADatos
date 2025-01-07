import model.Coche;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Coche coche = new Coche();
        Scanner scanner = new Scanner(System.in);

        String menu = """
                ¿Qué desea hacer? 
                1. Añadir un vehículo
                2. Borrar coche por ID
                3. Consultar coche por ID
                4. Modificar coche por ID
                5. Listar los coches
                6. Salir del programa
                """;

        int opcion = 0;
        do {
            System.out.println(menu);
            System.out.print("Elija una opción: ");

            // Verificar que la entrada sea válida
            // Este if lo busque en google un ejemplo, relamente no se muy bien como funciona, se lo que hace.
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Por favor, introduzca un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("<------------------------------->");
                    System.out.println("Añadir un nuevo coche");
                    System.out.print("Dime la marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Dime el modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Dime la matrícula: ");
                    String matricula = scanner.nextLine();
                    coche.anadirNuevoCoche(marca, modelo, matricula);
                    System.out.println("Coche añadido con éxito.");
                    System.out.println("<------------------------------->");
                }
                case 2 -> {
                    System.out.println("<------------------------------->");
                    System.out.println("Función para borrar coche por ID.");
                    System.out.println("Dime la id del coche a borrar");
                    int id = scanner.nextInt();
                    coche.borrarCocheID(id);
                    System.out.println("El coche ha sido eliminado correctamente");
                    System.out.println("<------------------------------->");
                }
                case 3 -> {
                    System.out.println("<------------------------------->");
                    System.out.println("Función para consultar coche por ID.");
                    System.out.println("Dime la id del coche a consultar");
                    int id = scanner.nextInt();
                    coche.consultarPorID(id);
                    System.out.println("<------------------------------->");
                }
                case 4 -> {
                    System.out.println("<------------------------------->");
                    System.out.println("Función para modificar coche por ID.");
                    System.out.println("Dime el id del coche que quieres modificar");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Dime la marca");
                    String marca = scanner.nextLine();
                    System.out.println("Dime el modelo");
                    String modelo = scanner.nextLine();
                    System.out.println("Dime la matricula");
                    String matricula = scanner.nextLine();
                    coche.modificarPorID(id,marca,modelo,matricula);
                    System.out.println("<------------------------------->");

                }
                case 5 -> {
                    System.out.println("<------------------------------->");
                    System.out.println("Función para listar todos los coches.");
                    System.out.println("Aqui tienes todos los coches");
                    coche.listarTodos();
                    System.out.println("<------------------------------->");
                }
                case 6 -> System.out.println("Cerrando programa ....");
                default -> System.out.println("Opción no válida, inténtelo de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
