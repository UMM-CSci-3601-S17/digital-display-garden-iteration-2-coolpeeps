package umm3601.flower;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class FlowerControllerSpec
{
    private FlowerController flowerController;
    private String roseIdString;

    @Before
    public void clearAndPopulateDB() throws IOException {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> flowerDocuments = db.getCollection("flowers");
        flowerDocuments.drop();
        List<Document> testFlowers = new ArrayList<>();
        testFlowers.add(Document.parse("{\n" +
                "                    _id: \"id-1\",\n" +
                "                    commonName: \"tulip\",\n" +
                "                    cultivar: \"cltv-tulip\",\n" +
                "                    source: \"src-a\",\n" +
                "                    gardenLocation: \"loc-1\",\n" +
                "                    year: 2016\n" +
                "                }"));
        testFlowers.add(Document.parse("{\n" +
                "                    _id: \"id-2\",\n" +
                "                    commonName: \"lily\",\n" +
                "                    cultivar: \"cltv-lily\",\n" +
                "                    source: \"src-a\",\n" +
                "                    gardenLocation: \"loc-1\",\n" +
                "                    year: 2016\n" +
                "                }"));
        testFlowers.add(Document.parse("{\n" +
                "                    _id: \"id-3\",\n" +
                "                    commonName: \"daisy\",\n" +
                "                    cultivar: \"cltv-daisy\",\n" +
                "                    source: \"src-b\",\n" +
                "                    gardenLocation: \"loc-2\",\n" +
                "                    year: 2016\n" +
                "                }"));
        ObjectId roseId = new ObjectId();
        BasicDBObject rose = new BasicDBObject("_id", roseId);
        rose = rose.append("commonName", "rose")
                .append("cultivar", "cltv-rose")
                .append("source", "src-b")
                .append("gardenLocation", "loc-2")
                .append("year", 2016);
        roseIdString = roseId.toHexString();
        flowerDocuments.insertMany(testFlowers);
        flowerDocuments.insertOne(Document.parse(rose.toJson()));

        // It might be important to construct this _after_ the DB is set up
        // in case there are bits in the constructor that care about the state
        // of the database.
        flowerController = new FlowerController();
    }

    // http://stackoverflow.com/questions/34436952/json-parse-equivalent-in-mongo-driver-3-x-for-java
    private BsonArray parseJsonArray(String json) {
        final CodecRegistry codecRegistry
                = CodecRegistries.fromProviders(Arrays.asList(
                new ValueCodecProvider(),
                new BsonValueCodecProvider(),
                new DocumentCodecProvider()));

        JsonReader reader = new JsonReader(json);
        BsonArrayCodec arrayReader = new BsonArrayCodec(codecRegistry);

        return arrayReader.decode(reader, DecoderContext.builder().build());
    }

    @Test
    public void getAllFlowers() {
        Map<String, String[]> emptyMap = new HashMap<>();
        String jsonResult = flowerController.listFlowers(emptyMap);
        BsonArray docs = parseJsonArray(jsonResult);

        System.out.println(docs.size());
        assertEquals("Should be 358 flowers", 358, docs.size());
    }

    @Test
    public void getBed() {
        Map<String,String[]> emptyMap = new HashMap<>();


    }
}

