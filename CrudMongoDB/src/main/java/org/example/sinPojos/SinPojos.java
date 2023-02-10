package org.example.sinPojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

import static com.mongodb.client.model.Filters.eq;

public class SinPojos {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/"
                )
        );
        MongoDatabase database = mongoClient.getDatabase("Profesores");
        MongoCollection<Document> collection = database.getCollection("coches_profesores");

        //  insertaDocumentos(collection);
        //borraUnDocumento(collection);
        // actualizaDocumento(collection);

        // actualizaDocumentoBiologia(collection);
        // listaDocumentos(collection);
        //ordenarPorVehiculo(collection);
        // listaPrimero(collection);


    }

    private static void listaPrimero(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find().limit(5);
        Iterator iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void ordenarPorVehiculo(MongoCollection<Document> collection) {
        Bson query = Sorts.ascending("vehiculo");
        List<Document> lista = new ArrayList<>();
        collection.find().sort(query).into(lista);

        for (Document d : lista) {
            System.out.println(d.toJson());
        }


    }


    private static void listaDocumentos(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find();
        Iterator iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void actualizaDocumentoBiologia(MongoCollection<Document> collection) {
        Bson query = Filters.eq("especialidad", "biologia");
        Bson query1 = Updates.set("edad", 30);
        UpdateResult result = collection.updateMany(query, query1);
        System.out.println("Exitoso se cambiaron " + result.getModifiedCount() + "documentos");
    }


    private static void actualizaDocumento(MongoCollection<Document> collection) {
        Bson query = Filters.eq("esAsociado", false);
        Bson query2 = Updates.set("esAsociad", true);
        UpdateResult result = collection.updateMany(query, query2);
        System.out.println("Exitoso se cambiaron " + result.getModifiedCount() + "documentos");

    }

    private static void borraUnDocumento(MongoCollection<Document> collection) {
        Bson query = eq("nombre", "Antonio");
        collection.deleteOne(query);
        System.out.println("Eliminado correctamente");
    }

    private static void insertaDocumentos(MongoCollection<Document> collection) {
        ArrayList<Document> lista = new ArrayList<>();
        Document document = new Document()
                .append("nombre", "María")
                .append("apellidos", "Suárez Manrique")
                .append("especialidad", Arrays.asList("biología"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Lexus");
        lista.add(document);
        Document documentDos = new Document()
                .append("nombre", "Jose Luis")
                .append("apellidos", "López Pérez")
                .append("especialidad", Arrays.asList("matemáticas", "física"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Toyota");
        lista.add(documentDos);
        Document documentTres = new Document()
                .append("nombre", "Antonio")
                .append("apellidos", "Munguía Arteche")
                .append("especialidad", Arrays.asList("física", "química"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Volvo");
        lista.add(documentTres);
        Document documentCuatro = new Document()
                .append("nombre", "Fernando")
                .append("apellidos", "Delgado De La Fuente")
                .append("especialidad", Arrays.asList("física"))
                .append("esTitular", false)
                .append("esAsociado", true)
                .append("vehiculo", "Yamaha");
        lista.add(documentCuatro);
        Document documentCinco = new Document()
                .append("nombre", "Elena")
                .append("apellidos", "Hérnandez Serafín")
                .append("especialidad", Arrays.asList("matemáticas", "física"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Seat");
        lista.add(documentCinco);
        Document documentSeis = new Document()
                .append("nombre", "Miguel")
                .append("apellidos", "Jimenes Ochoa")
                .append("especialidad", Arrays.asList("biología"))
                .append("esTitular", true)
                .append("esAsociado", true)
                .append("vehiculo", "Seat");
        lista.add(documentSeis);
        Document documentSiete = new Document()
                .append("nombre", "Paco")
                .append("apellidos", "Fernandez Garcia")
                .append("especialidad", Arrays.asList("matemáticas", "geología"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Renault");
        lista.add(documentSiete);
        Document documentOcho = new Document()
                .append("nombre", "Blanca")
                .append("apellidos", "Gutierrez Ochoa")
                .append("especialidad", Arrays.asList("física", "química"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Renault");
        lista.add(documentOcho);
        Document documentNueve = new Document()
                .append("nombre", "Laura")
                .append("apellidos", "Garcia Ochoa")
                .append("especialidad", Arrays.asList("biología"))
                .append("esTitular", true)
                .append("esAsociado", false)
                .append("vehiculo", "Peugeot");
        lista.add(documentNueve);

        collection.insertMany(lista);
        System.out.println("insertados correctamente");
    }
}