package BBDD;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
    private static final String DB_NAME = "centro_estudios";
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        mongoClient = MongoClients.create("mongodb+srv://soyeladmin:4252@cluster0.0lmn1.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
        database = mongoClient.getDatabase(DB_NAME);
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
