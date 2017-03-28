package umm3601;
import com.oreilly.servlet.MultipartRequest;
import umm3601.MultipartMap;
import java.io.*;
import static spark.Spark.*;
import umm3601.flower.ExcelParser;
import umm3601.flower.FlowerController;



public class Server {
    public static void main(String[] args) throws IOException {

        System.out.println("hello");


        //MultipartRequest mpp = new MultipartRequest(null, null);

        // This users looks in the folder `public` for the static web artifacts,
        // which includes all the HTML, CSS, and JS files generated by the Angular
        // build. This `public` directory _must_ be somwhere in the classpath;
         //a problem which is resolved in `server/build.gradle`.

        staticFiles.location("/public");

        FlowerController flowerController = new FlowerController();

        options("", (request, response) -> {

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
        redirect.get("/", "http://localhost:9000");

        post("api/flowers/upload", (request, response)->{
            final File upload = new File("upload");
            if (!upload.exists() && !upload.mkdirs()) {
                throw new RuntimeException("Failed to create directory " + upload.getAbsolutePath());
            }
            // this dumps all files contained in the multipart request to target directory.
            final MultipartRequest req = new MultipartRequest(request.raw(), upload.getAbsolutePath());
            halt(200);
            return null;
        });


        // List beds
        get("api/beds", (req, res) -> {
            res.type("application/json");
            return flowerController.listBeds();
        });

        // List flowers
        get("api/flowers", (req, res) -> {
            res.type("application/json");
            return flowerController.listFlowers(req.queryMap().toMap());
        });

        // Get a flower
        get("api/flowers/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            return flowerController.getFlower(id);
        });

        // Handle "404" file not found requests:
        notFound((req, res) -> {
            res.type("text");
            res.status(404);
            return "Sorry, we couldn't find that!";
        });

        post("api/plant/leaveComment", (req, res) -> {
            res.type("application/json");
            return flowerController.storeFlowerComment(req.body());
        });

        post("api/plant/:id/like", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            return flowerController.incrementMetadata(id, "likes");
        });

        post("api/plant/:id/dislike", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            return flowerController.incrementMetadata(id, "dislikes");
        });
    }




}
