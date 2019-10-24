package edu.eci.SecureApp.Persistence;

import edu.eci.SecureApp.Model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private Map<Integer, User> users;

    /**
     * Constructor
     */
    public UserRepository() {
        this.users = new HashMap<>();
    }

    /**
     * Almacena los usuarios que se han registrado
     *
     * @param user, usuario que se registra
     */
    public void saveUser(User user) {
        int userId = users.size() + 1;
        user.setId(userId);
        users.put(userId, user);
    }

    /**
     * Obtiene un usuario por el correo
     *
     * @param email del usuario.
     * @return User usuario.
     */
    public User getUserByEmail(String email) {
        User user = null;
        for (Integer userId : users.keySet()) {
            if (email.equals(users.get(userId).getEmail())) {
                user = users.get(userId);
            }
        }
        return user;
    }
}
