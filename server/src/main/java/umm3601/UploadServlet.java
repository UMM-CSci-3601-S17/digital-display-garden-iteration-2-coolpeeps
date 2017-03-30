package umm3601;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import umm3601.Server;
@WebServlet(urlPatterns = { "api/flowers/upload" })
@MultipartConfig(location = "api/flowers/upload", maxFileSize = 10485760L) // 10MB.
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/upload.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        MultipartMap map = new MultipartMap(request, this);
        String text = map.getParameter("text");
        File file = map.getFile("file");
        String[] check = map.getParameterValues("check");

        // Now do your thing with the obtained input.
        System.out.println("Text: " + text);
        System.out.println("File: " + file);
        System.out.println("Check: " + Arrays.toString(check));

        request.getRequestDispatcher("/WEB-INF/upload.jsp").forward(request, response);
    }

}