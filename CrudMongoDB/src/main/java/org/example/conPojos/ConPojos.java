package org.example.conPojos;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ConPojos {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/"
                )
        );
        MongoDatabase database = mongoClient.getDatabase("Profesores");
        MongoCollection<Profesor> collection = database.getCollection("coches_profesores", Profesor.class);

       // insertaDocumentos(collection);
      //  borraDocumentos(collection);
        actualizaDocumentos(collection);
        actualizaDocumentoBiologia(collection);
        listaDocumentos(collection);
        ordenarPorVehiculo(collection);
        listaPrimero(collection);
    }
    private static void listaPrimero(MongoCollection<Profesor> collection) {
        FindIterable<Profesor> documents = collection.find().limit(5);
        Iterator iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void ordenarPorVehiculo(MongoCollection<Profesor> collection) {
        Bson query = Sorts.ascending("vehiculo");
        List<Profesor> lista = new ArrayList<>();
        collection.find().sort(query).into(lista);

        for (Profesor f : lista) {
            System.out.println(f.toString());
        }


    }
    private static void listaDocumentos(MongoCollection<Profesor> collection) {
        FindIterable<Profesor> documents = collection.find();
        Iterator iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void actualizaDocumentoBiologia(MongoCollection<Profesor> collection) {
        Bson query = Filters.eq("especialidad", "biologia");
        Bson query1 = Updates.set("edad", 30);
        UpdateResult result = collection.updateMany(query, query1);
        System.out.println("Exitoso se cambiaron " + result.getModifiedCount() + "documentos");
    }


    private static void actualizaDocumentos(MongoCollection<Profesor> collection) {
        Bson query = Filters.eq("esAsociado", false);
        Bson query2 = Updates.set("esAsociad", true);
        UpdateResult result = collection.updateMany(query, query2);
        System.out.println("Exitoso se cambiaron " + result.getModifiedCount() + "documentos");

    }

    private static void borraDocumentos(MongoCollection<Profesor> collection) {
        Bson query = eq("nombre", "Antonio");
        collection.deleteOne(query);
        System.out.println("Eliminado correctamente");
    }

    private static void insertaDocumentos(MongoCollection<Profesor> collection) {

        List<Profesor> profesores = new ArrayList<>();

        profesores.add(new Profesor("Maria", "Suarez Manrique", new String[]{"biologia"}, true, false, "Lexus"));
        profesores.add(new Profesor("Jose Luis", "Lopez Perez", new String[]{"matematicas", "fisica"}, true, false, "Toyota"));
        profesores.add(new Profesor("Antonio", "Manguia Arteche", new String[]{"fisica", "quimica"}, true, false, "volvo"));
        profesores.add(new Profesor("Fernando", "Delgado De La Fuente", new String[]{"fisica"}, false, true, "Yamaha"));
        profesores.add(new Profesor("Elena", "Hernandez Serafin", new String[]{"matematicas", "fisica"}, true, false, "Seat"));
        profesores.add(new Profesor("Miguel", "Jimenes Ochoa", new String[]{"biologia"}, true, true, "Seat"));
        profesores.add(new Profesor("Paco", "Fernandez Garcia", new String[]{"matematicas", "geologia"}, true, false, "Renault"));
        profesores.add(new Profesor("Blanca", "Gutierrez Ochoa", new String[]{"fisica", "quimica"}, true, false, "Renault"));
        profesores.add(new Profesor("Laura", "Garcia Ochoa", new String[]{"biologia"}, true, false, "Peugeot"));

        collection.insertMany(profesores);

        System.out.println("Insertados correctamente");

    }
}
