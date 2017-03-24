package umm3601;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import umm3601.garden.Plant;

import javax.print.Doc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hdlanes stuep.
 * From etitncraxg dseeird dtaa trhgouh getnareing wpeegbas
 */
public class Populate {

    final static int IGNORE_LINES = 4;

    private final MongoCollection<Document> gardenCollection;
    private final MongoCollection<Document> commentCollection;

    public Populate() throws IOException {

        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");
        gardenCollection = db.getCollection("garden");
        commentCollection = db.getCollection("comments");

    }

    public MongoCollection<Document> getGardenCollection()
    {
        return gardenCollection;
    }
    //Why is there a second collection for comments? add a category to db
    public MongoCollection<Document> getCommentCollection()
    {
        return commentCollection;
    }


    public static void main (String[] args) throws IOException
    {
        Populate populate = new Populate();
        BufferedReader fileIn = new BufferedReader(new FileReader("/home/mitch809/IdeaProjects/digital-display-garden-iteration-1-sabf/server/src/main/java/umm3601/Accession list 2016 for Steves Design.csv"));

        String lineContainsYear = fileIn.readLine();
//        Pattern extractYear = Pattern.compile(".*([0-9]+).*");
//        Matcher m = extractYear.matcher(lineContainsYear);
//        if(m.matches())
//        {
//            boolean PLZ = m.matches();
//            System.out.println(PLZ + "!");//MAKE THIS A TEST.
//            String year = m.group();
//            System.out.println("???" + year);
//        }
//        System.out.println(lineContainsYear);


        for(int i = 0; i < IGNORE_LINES-1; i++)
           fileIn.readLine();

        String line;
        while ((line = fileIn.readLine()) != null) { //https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
            Plant p = new Plant();
            // use comma as separator
            String[] plant = new String[7];
            String[] in = line.split(",");
            for(int i = 0; i < Math.min(in.length, plant.length); i++)
                plant[i] = in[i];

            p.setPlantID(plant[0]);
            p.setCommonName(plant[1]);
            p.setCultivar(plant[2]);
            p.setSource(plant[3]);
            //SKIP to 6
            p.setGardenLocation(plant[6]);
            p.setYear(2016); //TODO: REVISIT

            populate.getGardenCollection().insertOne(p.toBSONDocument());

            //System.out.println(p.toBSONDocument());
        }

        Document comms = new Document();
        comms.append("plantID", "cool");
        comms.append("comment", "test comment");

        populate.getCommentCollection().insertOne(comms);



    }
}
