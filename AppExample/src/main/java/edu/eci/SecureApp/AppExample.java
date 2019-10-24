package edu.eci.SecureApp;

import edu.eci.SecureApp.Model.User;
import edu.eci.SecureApp.Persistence.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static spark.Spark.*;


public class AppExample {

    private static UserRepository userRepository = new UserRepository();

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        secure("deploy/keyStoreSecure.jks",
                "Appserver",
                "deploy/truststoreDate.jks",
                "karen123");
        get("/", (req, res) -> {
            res.type("text/html");
            res.redirect("/index.html");
            res.status(200);
            return null;
        });
        //Login
        post("/login", (req, res) -> {
            String email = req.queryParams("email");
            User searchedUser = userRepository.getUserByEmail(email);
            if (searchedUser == null) {
                System.out.println("NO EXISTE EL USUARIO");
                res.status(200);
                res.redirect("/login.html");
                return null;
            }
            String pwd = bytesToHex(getSHA(req.queryParams("pwd")));
            if (!pwd.equals(searchedUser.getPassword())) {
                System.out.println("CONTRASEÑA INCORRECTA");
                res.status(200);
                res.redirect("/login.html");
                return null;
            }
            res.status(201);
            res.redirect("/fecha");
            return null;
        });
        //Fecha
        post("/fecha",(req, res) -> {
        	String date = getDate();
            String profilePage = "<!DOCTYPE html>\n" +
                    "<html lang='es'>\n" +
                    "<head>\n" +
                    "    <meta charset='UTF-8'>\n" +
                    "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                    "\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h5> " + date + "</h5>\n" +
                    "</body>\n" +
                    "</html>";
            return profilePage;
        });
        // Registro
        post("/register", (req, res) -> {
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            User searchedUser = userRepository.getUserByEmail(email);
            if (searchedUser != null) {
                System.out.println("YA EXISTE EL USUARIO CON EMAIL: " + email);
                res.status(200);
                res.redirect("/register.html");
                return null;
            }
            String pwd = bytesToHex(getSHA(req.queryParams("pwd")));
            User newUser = new User(0, name, email, pwd);
            userRepository.saveUser(newUser);
            res.status(201);
            res.redirect("/login.html");
            return null;
        });
    }
    
    /**
     * Sacadp de: https://www.baeldung.com/sha-256-hashing-java
     *
     * @param hash contraseña cifrada.
     * @return hex del hash.
     */
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    /**
     * Sacado de: https://www.baeldung.com/sha-256-hashing-java
     *
     */
    private static byte[] getSHA(String pwd) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
        return encodedhash;
    }

    

    /**
     * Este método llama al otro servidor para obtener la fecha
     *
     * @return Fecha actual como un String.
     */
    private static String getDate() {
        String date = "";
        URL url = null;
        try {
            url = new URL("https://localhost:4568/date");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = br.readLine();
            while (line != null) {
                date = line;
                line = br.readLine();
            }
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    /**
     * 
     * @return port
     */
    private static int getPort() {
        int port = 4567; //default port if heroku-port isn't set
        if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        }
        return port;
    }
    /**
     * Para pruebas
     */
    static {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslSession) -> {
                    if (hostname.equals("localhost")) {
                        return true;
                    }
                    return false;
                });
    }
}
