package com.ebac.practica60MongoDB.Model;

import com.ebac.practica60HibernateJPA.DTO.Telefono;
import com.ebac.practica60MongoDB.DTO.OperacionesCRUD;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class TelefonoModel implements OperacionesCRUD<Document> {
    public final MongoCollection<Document> collection;
    
    public TelefonoModel(MongoDatabase mongoDatabase){
        collection = mongoDatabase.getCollection("telefonos");
    }
    
    @Override
    public Optional<Document> save(Document document) {
        try {
            collection.insertOne(document);
            System.out.println("Registro guardado con exito: " + document);
            Optional.of(document);  
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar el insert: ",e);
        }
        return Optional.empty();
    }

    @Override
    public FindIterable<Document> getAll() {
        FindIterable<Document> telefonos = null;
        try {
            telefonos = collection.find();
            for (Document telefono : telefonos){
                System.out.println(telefono);
            }
            
        } catch (Exception e) {
            System.out.println("Error al ejecutar el Select : " + e.getMessage());
        }
        return telefonos;
    }

    @Override
    public Optional<Document> getByID(Document document) {
        Document registroEncontrado = collection.find(document).first();

        if(!Objects.isNull(registroEncontrado)) {
            System.out.println(registroEncontrado);
            return  Optional.of(registroEncontrado);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Document> update(Document documentActual, Document documentAActualizar) {
        UpdateResult updateResult = collection.updateOne(documentActual, documentAActualizar);

        if(updateResult.getModifiedCount()>0){
            System.out.println("Exito al actualizar");
            return Optional.of(documentAActualizar);
        } else {
            System.out.println("Registro no encontrado");
        }

        return Optional.empty();
    }

    @Override
    public void delete(Document document) {
        try {
            DeleteResult deleteResult = collection.deleteOne(document);
            if(deleteResult.getDeletedCount() > 0){
                System.out.println("Registro eliminado con exito");
            } else {
                System.out.println("No se encontro el registro");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar el Delete : ",e);
        }
    }
}
