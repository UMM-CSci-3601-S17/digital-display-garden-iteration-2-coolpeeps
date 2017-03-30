//package umm3601.plant;
//
//import com.google.gson.Gson;
//import org.junit.Before;
//import org.junit.Test;
//import umm3601.flower.Flower;
//import umm3601.flower.FlowerController;
//import umm3601.plant.PopulateMockDatabase;
//
//import java.io.IOException;
//
//import static junit.framework.TestCase.assertEquals;
//
//public class FilterByUniqueGardenLocations {
//    @Before
//    public void setUpDB() throws IOException{
//        PopulateMockDatabase mockDatabase = new PopulateMockDatabase();
//        mockDatabase.clearAndPopulateDBAgain();
//    }
//
//    @Test
//    public void findByGardenLocation() throws IOException {
//        FlowerController flowerController = new FlowerController();
//        GardenLocation[] filteredPlants;
//        Gson gson = new Gson();
//        //System.out.println();
///*        String rawPlants = flowerController.getGardenLocations();
//        filteredPlants = gson.fromJson(rawPlants, GardenLocation[].class);
//        assertEquals("Incorrect number of unique garden locations", 2, filteredPlants.length);
//        assertEquals("Incorrect zero index", "10.0", filteredPlants[0]._id);
//        assertEquals("Incorrect value for index 1", "7.0", filteredPlants[1]._id);*/
//    }
//}
