# Car Dealership Program

A Java console application for a car dealership, built with object-oriented classes and CSV file storage. It loads user accounts and vehicle inventory, supports login, and lets users view an inventory report through the manager menu.

## Run

Install a Java JDK (8 or newer), then run these commands from the project root:

```sh
javac -encoding UTF-8 dealership/*.java dealership/utils/*.java
java dealership.CarDealership db
```

Choose **Manager** and sign in using an account from `db/users.csv`. Select **Generate Reports → Inventory** to view the cars.

## Project structure

- `dealership/`: application entry point, menus, and car/user classes.
- `dealership/utils/`: CSV loading and console helpers.
- `db/`: CSV data files, including users, inventory, and sales.

## Current status

Login and inventory viewing are implemented. Adding/deleting cars, sales reports, salesperson features, and saving changes are not implemented yet.
