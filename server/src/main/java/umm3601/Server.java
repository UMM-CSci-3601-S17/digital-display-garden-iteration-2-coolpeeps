package umm3601;
import com.oreilly.servlet.MultipartRequest;

import java.io.*;
import java.util.Enumeration;

import static spark.Spark.*;
import umm3601.flower.ExcelParser;
import umm3601.flower.FlowerController;



public class Server {

    public static File upload;
    public static String name;
    public static String fileName;
    public static boolean status = false;
    public static void main(String[] args) throws IOException {

        ExcelParser parser = new ExcelParser(false);

        // This users looks in the folder `public` for the static web artifacts,
        // which includes all the HTML, CSS, and JS files generated by the Angular
        // build. This `public` directory _must_ be somwhere in the classpath;
        // a problem which is resolved in `server/build.gradle`.
        staticFiles.location("/public");

        FlowerController flowerController = new FlowerController("test");

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



        post("/api/flowers/upload", (req, res)->{
            System.out.println("file should be here");
            upload = new File("server/src/main/java/umm3601/flower");
            if (!upload.exists() && !upload.mkdirs()) {
                throw new RuntimeException("Failed to create directory " + upload.getAbsolutePath());
            }
            // this dumps all files contained in the multipart request to target directory.
            System.out.println("file should be here");
            MultipartRequest request = new MultipartRequest(req.raw(), upload.getAbsolutePath());
            Enumeration files = request.getFileNames();
            name = (String)files.nextElement();
            fileName = request.getFilesystemName(name);
            parser.parseExcel(upload, fileName);
            status = true;
            halt(200);
            return null;
        });


//        // List beds
        get("api/beds", (req, res) -> {
            res.type("application/json");
            return flowerController.listBeds(req.queryMap().toMap());
        });

        // List flowers
        get("api/flowers", (req, res) -> {
            res.type("application/json");
            return flowerController.listFlowers(req.queryMap().toMap());
        });

        // Get a flower
        get("api/flowers/:id", (req, res) -> {
            res.type("application/json");
            String id = req.params(":id");
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

        post("/api/flowers/:id/like", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            System.out.println("hello");
            return flowerController.incrementLikes(id, "Likes");
        });

        post("/api/flowers/:id/dislike", (req, res) -> {
            res.type("application/json");
            String id = req.params("id");
            return flowerController.incrementLikes(id, "Dislikes");
        });
    }




}
