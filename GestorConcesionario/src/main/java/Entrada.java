import controller.Concesionario;
import database.DBConnection;
import model.Empleado;

import java.sql.Connection;
import java.sql.SQLException;

public class Entrada {

    public static void main(String[] args) {

       // DBConnection dbConnection = new DBConnection();
       // Connection connection= dbConnection.getConnection();
       // DBConnection dbConnection2 = new DBConnection();
      //  Connection connection2= dbConnection2.getConnection();

        Concesionario concesionario = new Concesionario();
        //concesionario.insertarTrabajador(new Empleado("Javier","Estrada","Javier@amat.com",2333));
        concesionario.borrarUsuario(1);


    }
}
