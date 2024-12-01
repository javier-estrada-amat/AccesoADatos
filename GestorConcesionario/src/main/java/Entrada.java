import controller.Concesionario;
import database.DBConnection;
import model.Empleado;
import model.Tipo;

import java.sql.Connection;
import java.sql.SQLException;

public class Entrada {

    public static void main(String[] args) {

        //TIPO 1, EXT
        //TIPO 2, IND
        //TIPO 3, BEC


       // DBConnection dbConnection = new DBConnection();
       // Connection connection= dbConnection.getConnection();
       // DBConnection dbConnection2 = new DBConnection();
      //  Connection connection2= dbConnection2.getConnection();

        Concesionario concesionario = new Concesionario();
       // concesionario.insertarTrabajador(new Empleado("Antonio","necrotico","necrotico@antonio.com",2333, Tipo.INDEFINIDO));
       // concesionario.insertarTrabajador(new Empleado("Buafa","Porros","porros@buafa.com",2333, Tipo.BECARIO));
        //concesionario.insertarTrabajador(new Empleado("Andres","Esquizofrenia","Esquizofrenia@andres.com",2333, Tipo.EXTERNO));
        //concesionario.borrarUsuario(1);
        // concesionario.leerUsuarios();
        concesionario.agregarCoche();

    }
}
