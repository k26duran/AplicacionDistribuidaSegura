import static spark.Spark.*;
import spark.Request;
import spark.Response;

public class SslExample {

    // View example at https://localhost:4567/

    public static void main(String[] args) {
        secure("deploy/keystore.jks", "password", null, null);
        get("/", (req, res) -> inputDataPage(req, res));
    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\"" 
                + "integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">" 
                + "</head>"
                + "<body>"
                + "<center><h2>Welcome!!</h2></center>"
                + "<form action=\"/results\">"
                + "  <br>"
                + "  <center>Username: <input type=\"text\" name=\"username\"></input></center>"
                + "  <br>"
                + "  <center>Password: <input name=\"password\" type=\"password\"></input></center>"
                + "  <br>"
                + "  <center><button class= \"btn btn-outline-primary\" type=\"submit\">Login</button></center>"
                + "</form>"
                + " <br>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

}
