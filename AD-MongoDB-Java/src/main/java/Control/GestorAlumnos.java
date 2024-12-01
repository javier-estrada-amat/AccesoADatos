package Control;

import BBDD.ConexionMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Scanner;

public class GestorAlumnos {
    private final MongoCollection<Document> alumnosCollection;
    private final Scanner scanner;

    public GestorAlumnos() {
        alumnosCollection = ConexionMongo.getDatabase().getCollection("alumnos");
        scanner = new Scanner(System.in);
    }

    public void insertarAlumno() {
        Document alumno = new Document();
        System.out.print("Nombre: ");
        alumno.append("name", scanner.nextLine());
        System.out.print("Edad: ");
        alumno.append("age", scanner.nextInt());
        scanner.nextLine();
        System.out.print("Género: ");
        alumno.append("gender", scanner.nextLine());
        System.out.print("Email: ");
        alumno.append("email", scanner.nextLine());
        System.out.print("Teléfono: ");
        alumno.append("phone", scanner.nextLine());
        System.out.print("Calificación: ");
        alumno.append("calification", scanner.nextInt());
        scanner.nextLine();
        System.out.print("Nota superior: ");
        alumno.append("higher_grade", scanner.nextLine());
        System.out.print("Rating: ");
        alumno.append("rating", scanner.nextDouble());

        alumnosCollection.insertOne(alumno);
        System.out.println("Alumno insertado con éxito.");
    }

    public void mostrarAlumnos() {
        for (Document alumno : alumnosCollection.find()) {
            System.out.println(alumno.toJson());
        }
    }

    public void buscarAlumno() {
        System.out.print("Introduce el email del alumno: ");
        String email = scanner.nextLine();
        Document alumno = alumnosCollection.find(Filters.eq("email", email)).first();
        if (alumno != null) {
            System.out.println(alumno.toJson());
        } else {
            System.out.println("No se encontró un alumno con ese email.");
        }
    }

    public void darDeBajaAlumnos() {
        alumnosCollection.deleteMany(Filters.gte("calification", 5));
        System.out.println("Alumnos con calificación >= 5 eliminados.");
    }
}
