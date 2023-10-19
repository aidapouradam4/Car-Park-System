/**
* This program will indentify cars by their registration number
* @author Aida Pouradam <103847608>
* @version JDK version 20.0.2; Program version 1.0
* @date Created on 22 October 2023
*/

import javafx.beans.property.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {
    private StringProperty registrationNumber;
    private StringProperty make;
    private StringProperty model;
    private StringProperty year;
    private BooleanProperty isParked; // New property to track whether the car is parked
    private String parkingTime; // String to store the parking time

    /**
     * Initializes a new Car object with the provided registration number, make, model, and year.
     *
     * @param registrationNumber The unique registration number of the car.
     * @param make               The make (manufacturer) of the car.
     * @param model              The model of the car.
     * @param year               The manufacturing year of the car.
     */
    public Car(String registrationNumber, String make, String model, String year) {
        this.registrationNumber = new SimpleStringProperty(registrationNumber);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleStringProperty(year);
        this.isParked = new SimpleBooleanProperty(false);
    }

    /**
     * Checks if the car is currently parked.
     *
     * @return True if the car is parked; otherwise, false.
     */
    // Getter and setter methods for isParked
    public boolean isParked() {
        return isParked.get();
    }

    public BooleanProperty isParkedProperty() {
        return isParked;
    }

    public void setParked(boolean parked) {
        this.isParked.set(parked);
    }

    /**
     * Marks the car as parked and records the current parking time.
     */
    public void park() {
        isParked.set(true);
        // Record the current time when parking
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        parkingTime = dateFormat.format(new Date());
    }

    /**
     * Marks the car as not parked (i.e., unparked).
     */
    public void unpark() {
        isParked.set(false);
    }

    /**
     * Gets the registration number of the car.
     *
     * @return The registration number.
     */
    public String getRegistrationNumber() {
        return registrationNumber.get();
    }

    public StringProperty registrationNumberProperty() {
        return registrationNumber;
    }

    /**
     * Gets the make (manufacturer) of the car.
     *
     * @return The make of the car.
     */
    public String getMake() {
        return make.get();
    }

    public StringProperty makeProperty() {
        return make;
    }

    /**
     * Gets the model of the car.
     *
     * @return The model of the car.
     */
    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    /**
     * Gets the manufacturing year of the car.
     *
     * @return The manufacturing year of the car.
     */
    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    /**
     * Gets the parking time timestamp for the car.
     *
     * @return The parking time timestamp in the format "yyyy-MM-dd HH:mm:ss".
     */
    public String getParkingTime() {
        return parkingTime;
    }
    
    /**
     * Converts a StringProperty to a regular String.
     *
     * @param stringProperty The StringProperty to convert.
     * @return The String value.
     */
    private String convertStringPropertyToString(StringProperty stringProperty) {
        return stringProperty.get();
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
