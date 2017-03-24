package umm3601;

import spark.utils.IOUtils;
import umm3601.garden.GardenController;

import java.io.FileInputStream;
import java.io.InputStream;

import java.io.IOException;

import static spark.Spark.*;


public class Server {
    public static void main(String[] args) throws IOException {

        GardenController gardenController = new GardenController();

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
 
            return "OK";
        });



        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        // Simple example route
        get("/hello", (req, res) -> "Hello World");

        // Redirects for the "home" page
        redirect.get("", "/");
//        redirect.get("/", "http://localhost:9000");

        get("/", (req, res) -> {
            InputStream stream = new FileInputStream("/home/hoff0899/iteration1_server/server/client/index.html");
            return IOUtils.toString(stream);
        });

        /*
        this isn't actually being used, so why not?
         */
        // Handle "404" file not found requests:
        notFound((req, res) -> {
            res.type("text");
            res.status(404);
            return "Sorry, we couldn't find that!";
        });

        get("api/beds", (req, res) -> {
            res.type("application/json");
            return gardenController.listBeds();
        });

        get("api/garden/bed/:gardenLocation", (req, res) -> {
            res.type("application/json");
            String gardenLocation = req.params("gardenLocation");
            System.out.println(gardenLocation);
            return gardenController.listPlantsInBed(req.queryMap().toMap(), gardenLocation);
        });
//        get("api/garden/bed/", (req, res) -> {
//            res.type("application/json");
//            return gardenController.listPlantsInBed(req.queryMap().toMap(), gardenLocation);
//        });

        get("api/garden/plant/:plantID", (req, res) -> {
            res.type("application/json");
            String plantID = req.params("plantID");
            System.out.println("be cool");
            return gardenController.displayPlant(req.queryMap().toMap(), plantID);
        });

        get("api/garden/comments/:plantID", (req, res) -> {
            res.type("application/json");
            String plantID = req.params("plantID");
            System.out.println("find comments");
            return gardenController.getCommentsByID(plantID);
        });

        get("api/garden/insert/:plantID/:comment", (req, res) -> {
            res.type("application/json");
            String plantID = req.params("plantID");
            String comment = req.params("comment");
            System.out.println("insert comment");
            return gardenController.insertComment(plantID,comment);
        });

        get("/*", (req, rest) -> {
            InputStream stream = new FileInputStream("/home/hoff0899/iteration1_server/server/client/index.html");
            return IOUtils.toString(stream);
        });

    }

}
