import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.io.Serializable;

public class GestionUsuarios {
    private static final String URL_DB = "jdbc:mysql://localhost:3306/examen";
    private static final String USER_DB = "root";
    private static final String PASSWORD_DB = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
            while (true) {
                System.out.println("\nMenú:");
                System.out.println("1. Insertar un usuario");
                System.out.println("2. Listar todos los usuarios");
                System.out.println("3. Comprobar credenciales");
                System.out.println("4. Exportar usuarios a fichero");
                System.out.println("5. Salir");
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        registrarUsuario(connection, scanner);
                        break;
                    case 2:
                        listarUsuarios(connection);
                        break;
                    case 3:
                        comprobarCredenciales(connection, scanner);
                        break;
                    case 4:
                        exportarUsuarios(connection);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
        }
    }

    // Registrar un nuevo usuario
    public static void registrarUsuario(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Vamos a registrar a un nuevo usuario");

        // Pedir datos
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        // Comprobar si el correo ya existe en la base de datos
        String checkQuery = "SELECT * FROM usuarios WHERE correo = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, correo);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("Ya existe alguien con ese correo");
                return;
            }
        }

        // Insertar el nuevo usuario
        String insertQuery = "INSERT INTO usuarios (nombre, apellidos, correo, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setString(1, nombre);
            insertStmt.setString(2, apellidos);
            insertStmt.setString(3, correo);
            insertStmt.setString(4, password);
            insertStmt.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        }
    }

    // Listar todos los usuarios
    public static void listarUsuarios(Connection connection) throws SQLException {
        String query = "SELECT id, nombre FROM usuarios";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
            }
        }
    }

    // Comprobar credenciales de un usuario
    public static void comprobarCredenciales(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, correo);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Has iniciado sesion correctamente.");
            } else {
                System.out.println("Credenciales erroneas.");
            }
        }
    }

    // Exportar usuarios a un fichero
    public static void exportarUsuarios(Connection connection) throws SQLException {
        String query = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("correo"),
                        rs.getString("password")
                );
                usuarios.add(usuario);
            }
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("usuarios.obj"))) {
            out.writeObject(usuarios);
            System.out.println("Usuarios exportados correctamente a 'usuarios.obj'.");
        } catch (IOException e) {
            System.out.println("Error al exportar usuarios.");
            e.printStackTrace();
        }
    }
}