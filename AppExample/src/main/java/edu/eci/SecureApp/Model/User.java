package edu.eci.SecureApp.Model;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;

    /**
     * Constructor vacío
     */
    public User() {
    }

    /**
     * Constructor
     *
     * @param id       id del usuario.
     * @param name     nombre del usuario.
     * @param email    correo del usuario, que deberia ser único.
     * @param password contraseña del usuario.
     */
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}