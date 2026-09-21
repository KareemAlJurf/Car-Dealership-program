package dealership;

import dealership.utils.Utils;

import java.util.List;
import java.util.Scanner;

/**
 * The Menu class handles user interactions, including:
 * - Login authentication for managers and salespersons.
 * - Displaying and handling the Manager Menu.
 * - Displaying and handling the Report Menu.
 *
 * This class interacts with the `Dealership` object to access users and inventory.
 */
public class Menu {
    private Scanner scanner;
    private Dealership dealership; // Stores dealership data (users & inventory)

    /**
     * Constructs a Menu object with a given Dealership instance.
     *
     * @param dealership The Dealership object containing users and inventory.
     */
    public Menu(Dealership dealership) {
        this.scanner = new Scanner(System.in);
        this.dealership = dealership;
    }

    /**
     * Displays the login menu and prompts the user to log in.
     * Users can log in as either a Manager or a Salesperson.
     * If authentication is successful, the corresponding menu is displayed.
     */
    public void showLoginMenu() {
        while (true) {
            System.out.println("Welcome to Best Cars Dealership!");
            System.out.println("================================");
            System.out.println("1. Manager");
            System.out.println("2. Salesperson");
            System.out.println("3. Exit");
            System.out.print("Please make a choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1") || choice.equals("2")) {
                handleLogin(choice.equals("1") ? "Manager" : "Salesperson");
            } else if (choice.equals("3")) {
                System.out.println("Exiting the system...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles user login authentication based on role (Manager or Salesperson).
     * Checks credentials against the list of `User` objects stored in `Dealership`.
     *
     * @param role The role of the user attempting to log in ("Manager" or "Salesperson").
     */
    private void handleLogin(String role) {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        String password;
        try {
            password = Utils.readPasswordFromConsole("Enter password: "); // Securely read password
        } catch (RuntimeException e) {
            System.out.print("Enter password: ");
            password = scanner.nextLine().trim();  // Fallback if console is unavailable
        }
        for (User user : dealership.getUsers()) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Successfully logged in as " + role + ".");
                if (role.equals("Manager")) {
                    showManagerMenu();
                } else {
                    System.out.println("Salesperson functionality not implemented yet.");
                }
                return;
            }
        }

        System.out.println("Login failed. No matching username and password found.");
    }

    /**
     * Displays the Manager Menu and handles menu choices.
     * Available options:
     * 1. Add a Car (Not Implemented)
     * 2. Delete a Car (Not Implemented)
     * 3. Generate Reports
     * 4. Log Out
     */
    private void showManagerMenu() {
        while (true) {
            System.out.println("================================");
            System.out.println("=       Manager Main Menu      =");
            System.out.println("================================");
            System.out.println("1. Add a Car");
            System.out.println("2. Delete a Car");
            System.out.println("3. Generate Reports");
            System.out.println("4. Log out");
            System.out.print("Please make a choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "3":
                    showReportMenu();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("I’m sorry, but option " + choice + " has not been implemented yet. Please pick option 3 or 4.");
            }
        }
    }

    /**
     * Displays the Report Menu and handles user choices.
     * Available options:
     * 1. View Inventory
     * 2. View Sales (Not Implemented)
     * 3. Return to Manager Menu
     */
    private void showReportMenu() {
        while (true) {
            System.out.println("================================");
            System.out.println("=          Report Menu         =");
            System.out.println("================================");
            System.out.println("1. Inventory");
            System.out.println("2. Sales");
            System.out.println("3. Main Menu");
            System.out.print("Please make a choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                displayInventory();
            } else if (choice.equals("3")) {
                return;
            } else {
                System.out.println("I’m sorry, but option " + choice + " has not been implemented yet. Please pick option 1 or 3.");
            }
        }
    }

    /**
     * Displays the dealership's car inventory.
     * This data is now stored as `Car` objects instead of raw strings.
     */
    private void displayInventory() {
        List<Car> inventory = dealership.getInventory();

        if (inventory.isEmpty()) {
            System.out.println("No cars available in inventory.");
            return;
        }

        System.out.println("================================");
        System.out.println("=          Inventory           =");
        System.out.println("================================");
        System.out.printf("%-10s %-10s %-10s %-6s %-10s %-10s %-10s %-12s%n",
                "VIN", "Make", "Model", "Year", "Mileage", "Color", "Price", "Status");
        System.out.println("---------------------------------------------------------------------------");

        for (Car car : inventory) {
            System.out.printf("%-10s %-10s %-10s %-6d %-10d %-10s $%-9.2f %-12s%n",
                    car.getVin(), car.getMake(), car.getModel(),
                    car.getYear(), car.getMileage(), car.getColor(),
                    car.getPrice(), car.getStatus());
        }
        System.out.println("Press the return key to continue...");
        scanner.nextLine();
    }
}
