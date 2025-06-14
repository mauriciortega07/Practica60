package com.ebac.practica60MongoDB.DTO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.util.List;
import java.util.Optional;

public interface OperacionesCRUD<T> {
    Optional<T> save(Document document);
    FindIterable<T> getAll();
    Optional<T> getByID(Document document);
    Optional<Document> update(Document documentActual, Document documentAActualizar);
    void delete(Document document);

}
