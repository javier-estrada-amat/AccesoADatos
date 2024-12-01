package Control;

import BBDD.ConexionMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.List;
import java.util.Scanner;

public class GestorProfesores {
    private final MongoCollection<Document> profesoresCollection;
    private final Scanner scanner;

    public GestorProfesores() {
        profesoresCollection = ConexionMongo.getDatabase().getCollection("profesores");
        scanner = new Scanner(System.in);
    }

    public void insertarProfesor() {
        Document profesor = new Document();
        System.out.print("Nombre: ");
        profesor.append("name", scanner.nextLine());
        System.out.print("Edad: ");
        profesor.append("age", scanner.nextInt());
        scanner.nextLine();
        System.out.print("Género: ");
        profesor.append("gender", scanner.nextLine());
        System.out.print("Email: ");
        profesor.append("email", scanner.nextLine());
        System.out.print("Teléfono: ");
        profesor.append("phone", scanner.nextLine());
        System.out.print("Título: ");
        profesor.append("title", scanner.nextLine());
        System.out.print("Rating: ");
        profesor.append("rating", scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Introduce las materias (separadas por comas):");
        String[] materias = scanner.nextLine().split(",");
        profesor.append("subjects", List.of(materias));

        profesoresCollection.insertOne(profesor);
        System.out.println("Profesor insertado con éxito.");
    }

    public void mostrarProfesores() {
        for (Document profesor : profesoresCollection.find()) {
            System.out.println(profesor.toJson());
        }
    }

    public void buscarProfesor() {
        System.out.print("Introduce la edad mínima: ");
        int minEdad = scanner.nextInt();
        System.out.print("Introduce la edad máxima: ");
        int maxEdad = scanner.nextInt();
        scanner.nextLine();

        for (Document profesor : profesoresCollection.find(Filters.and(
                Filters.gte("age", minEdad),
                Filters.lte("age", maxEdad)
        ))) {
            System.out.println(profesor.toJson());
        }
    }

    public void actualizarProfesor() {
        System.out.print("Introduce el email del profesor: ");
        String email = scanner.nextLine();
        System.out.print("Introduce la nueva calificación: ");
        double nuevoRating = scanner.nextDouble();

        profesoresCollection.updateOne(Filters.eq("email", email),
                new Document("$set", new Document("rating", nuevoRating)));
        System.out.println("Profesor actualizado.");
    }
}
