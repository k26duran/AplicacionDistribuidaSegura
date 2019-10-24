package edu.eci.DateApp;

import java.util.Date;

import static spark.Spark.*;

public class DateApp {

    public static void main(String[] args) {
        port(getPort());
        secure("deploy/keyStoreDateServer.jks",
                "password",
                null,
                null);
        get("/", (req, res) -> "Date Server");
        get("/date", (req, res) -> getDate());
    }
    /**
     * Método para obtener la fecha actual
     * @return fecha
     */
    private static String getDate() {
        java.util.Date n = new Date();
        return n.toString();
    }

    private static int getPort() {
        int port = 4568; //default port if heroku-port isn't set
        if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        }
        return port;
    }
}
