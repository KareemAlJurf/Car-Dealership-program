package dealership;

import java.util.List;

/**
 * The Dealership class represents a dealership that contains users and cars.
 */
public class Dealership {
    private List<User> users;
    private List<Car> inventory;

    /**
     * Constructs a Dealership object with a list of users and a list of cars.
     *
     * @param users    The list of users in the dealership.
     * @param inventory The list of cars available in inventory.
     */
    public Dealership(List<User> users, List<Car> inventory) {
        this.users = users;
        this.inventory = inventory;
    }

    /**
     * Retrieves the list of users in the dealership.
     *
     * @return A list of users (managers and salespersons).
     */
    public List<User> getUsers() { return users; }

    /**
     * Retrieves the list of cars in the dealership inventory.
     *
     * @return A list of cars available in inventory.
     */
    public List<Car> getInventory() { return inventory; }
}
