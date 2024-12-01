package controller;

import dao.CochesDAO;
import dao.EmpleadoDAO;
import database.DBConnection;
import database.SchemaDB;
import model.Coche;
import model.Empleado;

import java.sql.*;
import java.util.Scanner;

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

    private EmpleadoDAO empleadoDAO;
    private CochesDAO cochesDAO;


    public Concesionario(){
        empleadoDAO= new EmpleadoDAO();
        cochesDAO = new CochesDAO();
    }

    public void insertarTrabajadorDAO(Empleado empleado){

        //la logica del negocio
        try {
            empleadoDAO.insertarEmpleado(empleado);
        } catch (SQLException e) {
            System.out.println("Error en la insercion de empleado");
        }

    }

    //insertar trabajador
    public void insertarTrabajador(Empleado empleado){

        // Connection -> Statement (Query) -> execute
        Connection connection = new DBConnection().getConnection();
        //ya puedo acceder a la DB

        try {
            Statement statement = connection.createStatement();
            String query2 = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME,SchemaDB.COL_EMP_SURNAME,SchemaDB.COL_EMP_MAIL,SchemaDB.COL_EMP_PHO, SchemaDB.COL_EMP_KIN

            );
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2,empleado.getApellido());
            preparedStatement.setString(3, empleado.getCorreo());
            preparedStatement.setInt(4,empleado.getTelefono());
            preparedStatement.setInt(5,empleado.getTipo().getId());
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

    public void leerUsuarios(){
        // no se puede mapear de forma directa -> Vector de vectore [nombre, apellido, correo]
        // Connection -> Statement /  PrepareStatement -> executeQuery -> Devuelve un objeto de tipo ResulSet
        Connection connection = new DBConnection().getConnection();
        String query = "SELECT * FROM "+SchemaDB.TAB_EMP;
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            //       v
            // r,r,r,r,r,r, (Resultado, conjunto de datos)
            ; // Esto devuelve un true o false, si hay algo se va moviendo y va devolviendo los datos
            while (resultSet.next()){
                String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
                System.out.println("Nombre del empleado "+nombre);
            }



        } catch (SQLException e) {
            System.out.println("Error al leer usuarios");
        }

    }

    public void agregarCoche(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce marca");
        String marca = scanner.next();


        //Si me dicen una marca y tengo ya 8 coches de esa marca , no lo quiero comprar

        try {
            if (cochesDAO.getModeloCochesMarca(marca).size() < 2){
                System.out.println("Introduce modelo");
                String modelo = scanner.next();
                System.out.println("Introduce cv");
                int cv = scanner.nextInt();
                System.out.println("Dime que precio tiene");
                int precio = scanner.nextInt();

                cochesDAO.addCoche(new Coche(marca,modelo,cv,precio));
                System.out.println("Coche agregado correctamente");
            } else {
                System.out.println("No le interesa el coche al concesionario");
            }
        } catch (SQLException e) {
            System.out.println("La base de datos no esta disponible, quieres guardar el objeto para mas adelante");
            //Guardar el dato en un fichero
        }


    }

    public void filtrarPorPrecio(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Por que precio quieres filtrar");
        int precio = scanner.nextInt();

        try {
           for (Coche coche: cochesDAO.getCochePrecio(precio)){
               //Mostrar los datos de los coches resultantes en la consola
               coche.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("No se puede realizar la consulta, quieres hacer otra cosa?");
        }

    }

    // Tener la funcionalidad de vender un coche -> matricula y el coche lo vende
    // un vendedor ( tengo que decir quien lo vende)
    // tener la fuyncionalidad de cual es el vendedor que mas coches ha vendido

    public void realizarVenta(){
        //PEDIDO POR SCANNER
        int idCoche=0;
        int idEmpleado= 0;

        System.out.println("Dime el coche que vas a vender");
        System.out.println("Dime el vender que hace la venta");
        try {
            // El COCHE QUE ESTA VENDIENDO ESTA DISPONIBLE??
                //SI NO ESTA DISPOBILE HAZ .....
                    //buscar coche con las mismas caracteristicas de cv y precio
                // SI ESTA DISPONIBLE, procede a registrar la venta

            cochesDAO.realizarVenta(1);
            empleadoDAO.realizarVenta(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void empleadoMes(int numero){

        System.out.println("Dime cuantos quieres sacar");
        int numEmpleadosMES = 0;

        try {
            empleadoDAO.obtenerEmpleadoMes(3);
        } catch (SQLException e) {
            System.out.println("Error a la hora de obtenerlos");
            System.out.println("Quieres hacer .....");
        }

    }


}
