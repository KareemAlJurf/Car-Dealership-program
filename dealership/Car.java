package dealership;

/**
 * The Car class represents a single car in the dealership inventory.
 */
public class Car {
    private String vin;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private String color;
    private double price;
    private String status;

    /**
     * Constructs a Car object with all attributes.
     *
     * @param vin     Vehicle Identification Number
     * @param make    Car make (e.g., Toyota)
     * @param model   Car model (e.g., Camry)
     * @param year    Manufacture year
     * @param mileage Mileage in miles
     * @param color   Car color
     * @param price   Car price in USD
     * @param status  Availability status (Available/Sold/In-Service)
     */
    public Car(String vin, String make, String model, int year, int mileage, String color, double price, String status) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.price = price;
        this.status = status;
    }

    /** Returns a formatted string representation of the car.
     */
    @Override
    public String toString() {
        return vin + ", " + make + ", " + model + ", " + year + ", " + mileage + " miles, " + color + ", $" + price + ", Status: " + status;
    }

    // Add getter methods so other classes can access car details
    public String getVin() { return vin; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
}
