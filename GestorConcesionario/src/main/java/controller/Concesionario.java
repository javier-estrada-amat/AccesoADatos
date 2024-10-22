package controller;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Concesionario  {
    // Statement --> "Query directa"--> INSERT INTO empleados(nombre, apellido, correo, telefono) VALUES
    // ('Javier','Estrada','amat@amat.com',123132)
            // true o false -> create
            // Nº Filas afectadas -> UPDATE DELETE
    // Recomendable --> PrepareStatement --> "Query con template"
    // Statement --> "Query directa"--> INSERT INTO empleados(nombre, apellido, correo, telefono) VALUES
    // (?,?,?,?)
    // setInt(4,123)
    //setString(1,"Javier")
    //SetString(3,"javier@gmail.com")
    //SetString(2,"Estrada")

    // Create update Delete --> Se ejecuta de una forma --> Modifica la tabla
    // Read --> y esto de otra --> obtiene vector de valores


    //insertar trabajador
    public void insertarTrabajador(Empleado empleado){

        // Connection -> Statement (Query) -> execute
        Connection connection = new DBConnection().getConnection();
        //ya puedo acceder a la DB

        try {
            Statement statement = connection.createStatement();
            String query2 = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_PHO

            );
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1,"JavierPS");
            preparedStatement.setString(2,"Estrada");
            preparedStatement.setString(3,"estrada@amat.com");
            preparedStatement.setInt(4,123123);
            preparedStatement.execute();


            //String query = "INSERT INTO"+SchemaDB.TAB_EMP+"("+SchemaDB.COL_EMP_NAME+","+"")"; --> NO, MUY LIOSO
            // %s -> String
            // %d -> int
            // %f -> Double float
            // %b -> Boolean
            // %c -> char

          /*  String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s','%s',%d)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_PHO,
                    empleado.getNombre(),empleado.getApellido(),empleado.getCorreo(),empleado.getTelefono()
            ); */


            //statement.execute(query); // hay o no fallo
            //statement.executeUpdate(); //filas afectadas cuantas


        } catch (SQLException e) {
            System.out.println("Error en la conexión de la DB");
        }

    }

    public void borrarUsuario(int id){
        Connection connection = new DBConnection().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "+SchemaDB.TAB_EMP+" WHERE id=?");
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            System.out.println("El numero de registros borrados es de "+row);
        } catch (SQLException e) {
            System.out.println("Error en la creacion de la query");
            System.out.println(e);
        }
    }

}
