import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class Entrada {

    public static void main(String[] args) {


        String connectionString = "mongodb+srv://%s:%s@cluster0.0lmn1.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(String.format(connectionString,DBScheme.USER,DBScheme.PASS)))
                .serverApi(serverApi)
                .build();

        //MongoClient mongoClient = MongoClients.create(settings);
        MongoClient mongoClient1 = MongoClients.create(String.format(connectionString,DBScheme.USER,DBScheme.PASS));


        MongoDatabase database = mongoClient1.getDatabase("academia");

        MongoCollection collection = database.getCollection("usuarios");

        Document documentInsercion = new Document();
/*
        documentInsercion.append("nombre" ,"Javier");
        documentInsercion.append("apellido" ,"Estrada");
        documentInsercion.append("edad" ,22);
        documentInsercion.append("correo" ,"javier@mail.com");
        collection.insertOne(documentInsercion);

*/
/*
        List<Document> listaInsercion = Arrays.asList(
                new Document().append("nombre","Juan").append("apellido","gomez").append("edad",32).append("correo","juan@mail.com"),
                new Document().append("nombre","Maria").append("apellido","Amat").append("edad",42).append("correo","Maria@mail.com"),
                new Document().append("nombre","Celia").append("apellido","perez").append("edad",82).append("correo","Celia@mail.com")
        );

        collection.insertMany(listaInsercion);

        */

        Document filtroNombre = new Document().append("edad",new Document().append("$gt",50));

        FindIterable resultado = collection.find(filtroNombre);

        MongoCursor<Document> cursor = resultado.iterator();

        while (cursor.hasNext()){
           Document document = cursor.next();
           String nombre = document.getString("nombre");
           String apellido = document.getString("apellido");
           int edad = document.getInteger("edad");
            System.out.println(nombre+ " "+ apellido + " edad: " +edad);

        }

    }
}
