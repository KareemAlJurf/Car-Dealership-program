package dealership;

/**
 * The User class represents a user (Manager or Salesperson) in the dealership system.
 */
public class User {
    private String username;
    private String password;
    private String role; // "Manager" or "Salesperson"

    /**
     * Constructs a User object.
     *
     * @param username Username of the user.
     * @param password Password of the user.
     * @param role     Role of the user (Manager/Salesperson).
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() { return username; }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() { return password; }

    /**
     * Retrieves the role of the user (e.g., Manager or Salesperson).
     *
     * @return The role assigned to the user.
     */
    public String getRole() { return role; }

    /**
     * Authenticates the user by verifying the provided password.
     *
     * @param password The password to be checked.
     * @return {true} if the provided password matches the stored password, otherwise {false}.
     */
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
