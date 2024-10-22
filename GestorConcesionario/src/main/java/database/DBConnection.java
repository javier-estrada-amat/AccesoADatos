package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    // si alguien pide una conexion,
    // si esta -> se la doy
    // si no esta -> la creo

    public Connection getConnection(){
        if (connection==null){
            newConnection();
        }

        return connection;
    }

    public Connection getCloseconnection(){
       return closeConnection();
    }

    private void newConnection() {
        // en realidad URI
        // url de conexion jdbc:mysql://localhost:3306/concesionario

        String url = "jdbc:mysql://localhost:3306/concesionario";
        try {
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            System.out.println("Error en la conexion de la base de datos");
        }

        System.out.println("Conexion establecida");

    }

    private Connection closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println("Error al cerrar la conexion");
        }finally {
            connection = null;
        }
        return null;
    }

}
