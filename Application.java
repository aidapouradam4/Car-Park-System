
/**
* This class is for idenetifying if a car is parkes in a slot or not
* and also will let you add or remove a car form the slot.
* @author Aida Pouradam <103847608>
* @version JDK version 20.0.2; Program version 1.0
* @date Created on 22 October 2023
*/

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Application {
    private String slotID;
    private BooleanProperty occupied;
    private ObjectProperty<Car> parkedCar;
    
    /**
     * Initializes a new parking slot with the provided slot identifier.
     *
     * @param slotID The unique identifier for the parking slot.
     */
    // Constructor to initialize the parking slot
    public Application(String slotID) {
        this.slotID = slotID;// Initialize the slot ID
        this.occupied = new SimpleBooleanProperty(false);// Initialize as unoccupied
        this.parkedCar = new SimpleObjectProperty<>(null);// Initialize with no parked car
    }

    /**
     * Gets the unique identifier for this parking slot.
     *
     * @return The slot identifier.
     */    
    // Getter method to retrieve the slot ID
    public String getSlotID() {
        return slotID;
    }
    
    /**
     * Checks if the parking slot is currently occupied by a car.
     *
     * @return True if the parking slot is occupied; otherwise, false.
     */
    // Getter method to check if the parking slot is occupied
    public BooleanProperty occupiedProperty() {
        return occupied;
    }
    
    public boolean isOccupied() {
        return occupied.get();
    }

    public void setOccupied(boolean occupied) {
        this.occupied.set(occupied);
    }
    
    /**
     * Gets the car currently parked in this parking slot.
     *
     * @return The parked car or null if the slot is empty.
     */
    public ObjectProperty<Car> parkedCarProperty() {
        return parkedCar;
    }
    
    // Getter method to retrieve the parked car
    public Car getParkedCar() {
        return parkedCar.get();
    }

    /**
     * Parks a car in the slot if it's unoccupied.
     *
     * @param car The car to park in the slot.
     */
    // Method to park a car in the slot
    public void parkCar(Car car) {
        if (!occupied.get()) {
            parkedCar.set(car);// Set the parked car
            occupied.set(true);
        } else {
            System.out.println("Parking slot is already occupied.");
        }
    }
    
    /**
     * Removes a car from the parking slot if it's occupied.
     */
    // Method to remove a car from the slot
    public void removeCar() {
        if (occupied.get()) {
            parkedCar.set(null); // Remove the parked car reference
            occupied.set(false); // Mark the slot as unoccupied
        } else {
            System.out.println("Parking slot is already empty.");
        }
    }

    // Override toString() to provide a better representation of the ParkingSlot
    @Override
    public String toString() {
        String status = occupied.get() ? "Occupied" : "Empty";
        String carInfo = occupied.get() ? parkedCar.get().toString() : "No car parked";

        return "ParkingSlot{" +
                "slotID='" + slotID + '\'' +
                ", status='" + status + '\'' +
                ", parkedCar=" + carInfo +
                '}';
    }
}