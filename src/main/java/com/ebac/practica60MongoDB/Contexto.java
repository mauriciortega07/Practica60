package com.ebac.practica60MongoDB;

import com.ebac.practica60MongoDB.Model.TelefonoModel;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Optional;

public class Contexto {
    public static void main(String[] args) {
        //OBTENEMOS LA CONEXION A LA BD
        MongoDatabase mongoDatabase = MongoConnection.createConnection();
        System.out.println(mongoDatabase);

        //Objeto que contiene los metodos CRUD
        TelefonoModel telefonoModel = new TelefonoModel(mongoDatabase);

        //Creamos documento
        Document document1 = new Document("numero", "7820123012")
                .append("tipoTelefono", "Casa")
                .append("region", "Brasil");

        Document document2 = new Document("numero", "7820327102")
                .append("tipoTelefono", "Oficina")
                .append("region", "Nueva Zelanda");

        Document document3 = new Document("numero", "7382023784")
                .append("tipoTelefono", "Casa")
                .append("region", "Argentina");

        Document document4 = new Document("numero", "6182542719")
                .append("tipoTelefono", "Recinto")
                .append("region", "Suiza");

        Document document5 = new Document("numero", "8174203748")
                .append("tipoTelefono", "Casa")
                .append("region", "Peru");

        //Guardamos Registros
        telefonoModel.save(document1);
        telefonoModel.save(document2);
        telefonoModel.save(document3);
        telefonoModel.save(document4);
        telefonoModel.save(document5);

        //Obtenemos Todos los Registros
        telefonoModel.getAll();

        //Obtenemos por ID
        ObjectId objectId = new ObjectId("684cf323b68e69533a3b96e3");
        Document documentId = new Document("_id", objectId);

        Optional<Document> documentEncontrado = telefonoModel.getByID(documentId);

        //Actualizar usuario
       Optional<Document> documentEncontrado2 = telefonoModel.getByID(documentId);
        documentEncontrado2.ifPresent(registroActual -> {
            Document document = new Document("numero","5529901222")
                    .append("region", "Mexico")
                    .append("usuario", "Mauricio");

            Document registroActualizado = new Document("$set", document);

            telefonoModel.update(registroActual, registroActualizado);
        });

        //Eliminar un registro
        documentEncontrado2.ifPresent(telefonoModel::delete);

        //Corrobora la eliminacion
        telefonoModel.getAll();

    }
}
