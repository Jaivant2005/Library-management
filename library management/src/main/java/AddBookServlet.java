import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/addBook")
@MultipartConfig
public class AddBookServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root"; 
    private static final String PASSWORD = "2005"; 

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL JDBC Driver not found.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/plain");

        String bookCode = request.getParameter("book_code");
        String bookName = request.getParameter("book_name");
        String author1 = request.getParameter("author1");
        String author2 = request.getParameter("author2");
        String subject = request.getParameter("subject"); 
        String tags = request.getParameter("tags");
        //Part ebookPart = request.getPart("myfile");
//System.out.println(subject);
        // File handling (optional)
//        String ebookPath = null; // Specify the directory to save the file
//        if (ebookPart != null && ebookPart.getSize() > 0) {
//            String fileName = ebookPart.getSubmittedFileName();
//            ebookPath = "path_to_your_storage_directory/" + fileName; // Update with actual path
//            ebookPart.write(ebookPath); // Save the file
//        }

        // Ensure mandatory fields are present
        if (bookCode == null || bookName == null || author1 == null) {
            response.getWriter().write("Book code, name, and author1 are required.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO books (book_code, book_name, author1, author2, subject, tags) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(bookCode));
                statement.setString(2, bookName);
                statement.setString(3, author1);
                statement.setString(4, author2);
                statement.setString(5, subject);
                statement.setString(6, tags);
               // statement.setString(7, ebookPath); // Save ebook path if it exists

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    response.getWriter().write("Book added successfully!");
                } else {
                    response.getWriter().write("Failed to add book.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid book code format.");
        } catch (IOException e) {
            response.getWriter().write("File writing error: " + e.getMessage());
        }
    }
}
