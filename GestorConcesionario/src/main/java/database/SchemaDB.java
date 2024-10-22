package database;

public interface SchemaDB {

    //1ª Conectar dos clases que no tienen nada que ver
    // con los metodos abs que tiene dicha interfaz
    // 2ª Almacen de constantes variables --> son finales(No de metodos)

    String DB_NAME = "concesionario";
    String TAB_EMP = "empleados";
    String COL_ID = "id";
    String COL_EMP_NAME = "nombre";
    String COL_EMP_SURNAME="apellido";
    String COL_EMP_MAIL="correo";
    String COL_EMP_PHO="telefono";



}
