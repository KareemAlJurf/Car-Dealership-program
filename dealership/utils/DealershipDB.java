package dealership.utils;

import dealership.Car;
import dealership.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The DealershipDB class handles database operations such as loading users and inventory.
 */
public class DealershipDB {
    public static final boolean DROP_COMMENT_LINES = true;
    public static final boolean DROP_EMPTY_LINES = true;

    public static final String INVENTORY_FILE_NAME = "inventory.csv";
    public static final String USERS_FILE_NAME = "users.csv";
    public static final String SALES_FILE_NAME = "sales.csv";

    private final String dataDirPath;
    private final IOHelper ioHelper;

    /**
     * Constructs a DealershipDB object.
     *
     * @param dataDirPath The file path to the database directory.
     */
    public DealershipDB(String dataDirPath) {
        if (dataDirPath == null || dataDirPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Data directory cannot be null or empty.");
        }
        this.dataDirPath = dataDirPath;
        this.ioHelper = new IOHelper(dataDirPath);
    }

    /**
     * Loads the inventory from `inventory.csv` and converts it into a list of Car objects.
     *
     * @return A list of Car objects.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Car> loadInventory() throws IOException {
        List<Car> cars = new ArrayList<>();
        List<String> lines = ioHelper.readFileContent(INVENTORY_FILE_NAME, DROP_COMMENT_LINES, DROP_EMPTY_LINES);

        for (String line : lines) {
            String[] data = line.split(",");

            // Ensure we have enough fields (extra columns exist)
            if (data.length >= 12) { // Your CSV has extra date + x1, y1, z1
                data = new String[]{data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[11]};
            }

            if (data.length >= 8) {
                try {
                    Car car = new Car(
                            data[0].trim(),                        // VIN
                            data[1].trim(),                        // Make
                            data[2].trim(),                        // Model
                            Integer.parseInt(data[3].trim()),      // Year
                            Integer.parseInt(data[4].trim()),      // Mileage
                            data[5].trim(),                        // Color
                            Double.parseDouble(data[6].trim()),    // Price
                            data[7].trim()                         // Status
                    );
                    cars.add(car);
                } catch (NumberFormatException e) {
                    System.err.println("ERROR: Invalid number format in inventory.csv -> " + Arrays.toString(data));
                }
            } else {
                System.err.println("ERROR: Malformed CSV line -> " + line);
            }
        }
        return cars;
    }

    /**
     * Loads users from `users.csv` and converts them into a list of User objects.
     *
     * @return A list of User objects.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        List<String> lines = ioHelper.readFileContent(USERS_FILE_NAME, DROP_COMMENT_LINES, DROP_EMPTY_LINES);

        for (String line : lines) {
            String[] data = line.split(",");

            // Ensure that the row has the correct number of columns
            if (data.length < 8) {
                System.err.println("ERROR: Malformed user data -> " + Arrays.toString(data));
                continue; // Skip this row
            }

            // Trim all fields to remove any extra spaces
            String username = data[6].trim();
            String password = data[7].trim();
            String role = data[5].trim().replaceAll("[^a-zA-Z]", ""); // Remove unexpected numbers

            // Add only valid users
            users.add(new User(username, password, role));
        }
        return users;
    }

    /**
     * Loads raw sales data from `sales.csv`. (No modification yet.)
     *
     * @return A list of raw sales records as strings.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<String> loadSales() throws IOException {
        return ioHelper.readFileContent(SALES_FILE_NAME, DROP_COMMENT_LINES, DROP_EMPTY_LINES);
    }

    /** Placeholder method to save inventory (To be implemented later). */
    public void saveInventory() {}

    /** Placeholder method to save users (To be implemented later). */
    public void saveUsers() {}

    /** Placeholder method to write sales data (To be implemented later). */
    public void writeSalesGrades(List<Integer> sales) {}
}
