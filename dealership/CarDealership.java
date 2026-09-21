package dealership;

import dealership.utils.DealershipDB;
import java.io.IOException;

/**
 * The CarDealership class is the main entry point for the dealership system.
 * Usage:
 *       java dealership.CarDealership <database directory path>
 */
public class CarDealership {
    /**
     * The main method starts the dealership system.
     * It expects a command-line argument specifying the database directory path.
     * If the argument is missing, an error message is displayed, and the program exits.
     *
     * @param args Command-line arguments, where args[0] should be the database directory path.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java dealership.CarDealership <database directory path>");
            System.exit(1); // Exit with an error code
        }

        try {
            // Initialize the dealership database with the provided path
            DealershipDB db = new DealershipDB(args[0]);

            // Load users and inventory from the database into a Dealership object
            Dealership dealership = new Dealership(db.loadUsers(), db.loadInventory());

            // Start the interactive menu system
            Menu menu = new Menu(dealership);
            menu.showLoginMenu();
        } catch (IOException e) {
            // Handle errors related to loading the database
            System.err.println("Error loading database: " + e.getMessage());
        }
    }
}
