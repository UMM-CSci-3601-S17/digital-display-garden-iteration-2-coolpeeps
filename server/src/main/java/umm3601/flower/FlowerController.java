package umm3601.flower;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.bson.BsonInvalidOperationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bson.conversions.Bson;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import com.mongodb.DBCollection;

public class FlowerController {

    private final MongoCollection<Document> flowerCollection;
    private final DBCollection foo;

    public FlowerController(String dbName) throws IOException {
        // Set up our server address
        // (Default host: 'localhost', default port: 27017)
        // ServerAddress testAddress = new ServerAddress();

        // Try connecting to the server
        //MongoClient mongoClient = new MongoClient(testAddress, credentials);
        MongoClient mongoClient = new MongoClient(); // Defaults!

        // Try connecting to a database
        MongoDatabase db = mongoClient.getDatabase(dbName);
        DB dab = mongoClient.getDB(dbName);

        flowerCollection = db.getCollection("flowers");
        foo = dab.getCollection("flowers");

    }

    // List flowers
    public String listFlowers(Map<String, String[]> queryParams) {
        System.out.println("hello");
        Document filterDoc = new Document();

        if (queryParams.containsKey("cultivar")) {
            String targetCultivar = queryParams.get("cultivar")[0];
            filterDoc = filterDoc.append("cultivar", targetCultivar);
        }

        if (queryParams.containsKey("_id")) {
            String targetSource = queryParams.get("_id")[0];
            filterDoc = filterDoc.append("_id", targetSource);
        }

        if (queryParams.containsKey("gardenLocation")) {
            String targetLocation = queryParams.get("gardenLocation")[0];
            filterDoc = filterDoc.append("gardenLocation", targetLocation);
        }

        if (queryParams.containsKey("commonName")) {
            String targetName= queryParams.get("commonName")[0];
            filterDoc = filterDoc.append("commonName", targetName);
        }

        if (queryParams.containsKey("Likes")) {
            try {
                int targetYear = Integer.parseInt(queryParams.get("year")[0]);
                filterDoc = filterDoc.append("year", targetYear);
            }
            catch (Exception e){
            }
        }

        FindIterable<Document> matchingFlowers = flowerCollection.find(filterDoc);

        return JSON.serialize(matchingFlowers);
    }

    // Get a single flower
    public String getFlower(String id) {
        FindIterable<Document> jsonFlowers
                = flowerCollection
                .find(eq("_id", new ObjectId(id)));

        Iterator<Document> iterator = jsonFlowers.iterator();

        Document flower = iterator.next();

        return flower.toJson();
    }

    // Get all the names of the beds in the DB
    public String listBeds(Map<String, String[]> queryParams) {
        Document output = new Document();
//        System.out.println(foo.distinct("gardenLocation"));
        List magic = foo.distinct("gardenLocation");//sort list?

        for (Object x: magic){
            if (x == null || x.equals("")) {}
            else {
                output.append(x.toString(), x);
            }
        }

        return output.toJson();
    }

    public boolean incrementMetadata(String id, String field) {

        ObjectId objectId;

        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return false;
        }


        Document searchDocument = new Document();
        searchDocument.append("_id", objectId);

        Bson updateDocument = inc("metadata." + field, 1);

        return null != flowerCollection.findOneAndUpdate(searchDocument, updateDocument);
    }

    public static boolean storeFlowerComment(String json) {

        try {

            Document toInsert = new Document();
            Document parsedDocument = Document.parse(json);

            if (parsedDocument.containsKey("plantId") && parsedDocument.get("plantId") instanceof String) {
                toInsert.put("commentOnObjectOfId", new ObjectId(parsedDocument.getString("plantId")));
            } else {
                return false;
            }

            if (parsedDocument.containsKey("comment") && parsedDocument.get("comment") instanceof String) {
                toInsert.put("comment", parsedDocument.getString("comment"));
            } else {
                return false;
            }

        } catch (BsonInvalidOperationException e){
            e.printStackTrace();
            return false;
        } catch (org.bson.json.JsonParseException e){
            return false;
        }

        return true;
    }

}
