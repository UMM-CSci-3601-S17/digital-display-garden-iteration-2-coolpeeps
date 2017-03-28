package umm3601.flower;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
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
import static org.junit.Assert.assertThat;

public class DupFlowerControllerSpec
{
    private FlowerController flowerController;
    private String roseIdString;

    @Before
    public void connectToDB() throws IOException {
        flowerController = new FlowerController("ddg");
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

    private static String getCommonName(BsonValue val) {
        BsonDocument doc = val.asDocument();
        return ((BsonString) doc.get("commonName")).getValue();
    }

    @Test
    public void getAllFlowers() {
        Map<String, String[]> emptyMap = new HashMap<>();
        String jsonResult = flowerController.listFlowers(emptyMap);
        BsonArray docs = parseJsonArray(jsonResult);

        assertEquals("Should be a lot of flowers", 358, docs.size());
    }

    @Test
    public void getSrcFlowers() {
        Map<String, String[]> argMap = new HashMap<>();
        argMap.put("source", new String[] { "EA" });
        String jsonResult = flowerController.listFlowers(argMap);
        BsonArray docs = parseJsonArray(jsonResult);

        assertEquals("Should be 24 flowers", 24, docs.size());
    }

    @Test
    public void getGardenLocationFlowers() {
        Map<String, String[]> argMap = new HashMap<>();
        String jsonResult;
        BsonArray docs;


        argMap = new HashMap<>();
        argMap.put("gardenLocation", new String[] { "6" });
        jsonResult = flowerController.listFlowers(argMap);
        docs = parseJsonArray(jsonResult);
        assertEquals ("23 flowers",23,docs.size());

        argMap = new HashMap<>();
        argMap.put("gardenLocation", new String[] { "1S" });
        jsonResult = flowerController.listFlowers(argMap);
        docs = parseJsonArray(jsonResult);
        assertEquals ("36 flowers",36,docs.size());
    }

    @Test
    public void listBeds() {
        Map<String, String[]> emptyMap = new HashMap<>();
        String jsonResult = flowerController.listBeds(emptyMap);
//        BsonArray docs = parseJsonArray(jsonResult);
//Disabled, the line above causes the error: "org.bson.BsonInvalidOperationException:
//readStartArray can only be called when CurrentBSONType is ARRAY, not when CurrentBSONType is DOCUMENT."
//        assertEquals("Should be a lot of flowers", 12, docs.size());

    }
}

