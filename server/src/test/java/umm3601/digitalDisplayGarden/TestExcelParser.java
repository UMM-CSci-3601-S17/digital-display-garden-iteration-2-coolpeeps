package umm3601.digitalDisplayGarden;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.Test;
import umm3601.flower.ExcelParser;
import umm3601.Server;

import java.io.File;
import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestExcelParser {

    public MongoClient mongoClient = new MongoClient();
    public MongoDatabase testDB;
    public ExcelParser parser;
    public static String FILE_NAME = "/IDPH_STD_Illinois_By_County_By_Sex.xlsx";;
    public File file = null;

    @Before
    public void clearAndPopulateDatabase(){
        mongoClient.dropDatabase("test");
        testDB = mongoClient.getDatabase("test");
        parser = new ExcelParser(true);
    }

    @Test
    public void testSpeadsheetToDoubleArray() throws IOException {
        ///home/lauxx265/IdeaProjects/digital-display-garden-iteration-2-coolpeeps/server/build/resources/main/
        String[][] plantArray = parser.extractFromXLSX(file, FILE_NAME);
        //printDoubleArray(plantArray);
        assertEquals(1668, plantArray.length);
        assertEquals(plantArray[40].length, plantArray[1234].length);
        assertEquals("ALEXANDER", plantArray[5][2]);
    }

    @Test
    public void testCollapse() throws IOException {
        String[][] plantArray = parser.extractFromXLSX(file, FILE_NAME);
        //System.out.println(plantArray.length);
        //printDoubleArray(plantArray);

        plantArray = parser.collapseHorizontally(plantArray);
        plantArray = parser.collapseVertically(plantArray);

        //printDoubleArray(plantArray);

        assertEquals(1668, plantArray.length);
        assertEquals(10, plantArray[30].length);
        assertEquals(10, plantArray[0].length);
        assertEquals(10, plantArray[3].length);
    }

    @Test
    public void testReplaceNulls() throws IOException{
        String[][] plantArray = parser.extractFromXLSX(file, FILE_NAME);
        plantArray = parser.collapseHorizontally(plantArray);
        plantArray = parser.collapseVertically(plantArray);
        parser.replaceNulls(plantArray);

        for (String[] row : plantArray){
            for (String cell : row){
                assertNotNull(cell);
            }
        }
    }

    @Test
    public void testPopulateDatabase() throws IOException{
        String[][] plantArray = parser.extractFromXLSX(file, FILE_NAME);
        plantArray = parser.collapseHorizontally(plantArray);
        plantArray = parser.collapseVertically(plantArray);
        parser.replaceNulls(plantArray);

        parser.populateDatabase(plantArray);
        MongoCollection plants = testDB.getCollection("flowers");


        assertEquals(1664, plants.count());
        assertEquals(16, plants.count(eq("Sort", "104")));
    }

}
