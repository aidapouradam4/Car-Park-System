/**
* This Class is responsible for mainaining a list of available parking slots
* @author Aida Pouradam <103847608>
* @version JDK version 20.0.2; Program version 1.0
* @date Created on 22 October 2023
*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Map;
import java.util.AbstractMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class CarPark {
    private ObservableMap<String, ParkingSlot> parkingSlots; // ObservableMap to store parking slots by ID
    private ObservableSet<String> usedSlotNumbers; // ObservableSet to track used slot numbers
    private ObservableSet<String> parkedCarRegistrationNumbers; // ObservableSet to track parked cars by registration number

    /**
     * Constructs a new CarPark object. Initializes the data structures for parking slots and tracking.
     */
    public CarPark() {
        parkingSlots = FXCollections.observableHashMap(); // Initialize the parkingSlots map as an ObservableMap
        usedSlotNumbers = FXCollections.observableSet(); // Initialize the usedSlotNumbers set as an ObservableSet
        parkedCarRegistrationNumbers = FXCollections.observableSet(); // Initialize the parkedCarRegistrationNumbers set as an ObservableSet
    }
    
    // Define a method to get a list of parking slots
    public List<ParkingSlot> getParkingSlots() {
        return new ArrayList<>(parkingSlots.values());
    }

    /**
     * Adds a parking slot to the car park.
     *
     * @param parkingSlot The parking slot to add.
     * @return True if the parking slot is added successfully; otherwise, false if the slot number is not unique.
     */
    public boolean addParkingSlot(ParkingSlot parkingSlot) {
        String slotID = parkingSlot.getSlotID();

        // Check if the slot number is already in use
        if (usedSlotNumbers.contains(slotID)) {
            return false; // Slot number is not unique
        }

        parkingSlots.put(slotID, parkingSlot);
        usedSlotNumbers.add(slotID); // Add the slot number to the used set
        return true; // Successfully added
    }

    /**
     * Deletes a parking slot from the car park if it is unoccupied.
     *
     * @param slotID The ID of the parking slot to delete.
     */
    public void deleteParkingSlot(String slotID) {
        ParkingSlot slot = parkingSlots.get(slotID);
        if (slot != null && !slot.isOccupied()) {
            parkingSlots.remove(slotID);// Remove the parking slot
            System.out.println("Parking slot " + slotID + " deleted.");
            System.out.println("===============================");
        } else {
            System.out.println("Parking slot " + slotID + " cannot be deleted as it is occupied or doesn't exist.");
            System.out.println("===============================");
        }
    }

    /**
     * Lists all parking slots in the car park, including their status and occupied car details if applicable.
     */
    public void listAllSlots() {
        System.out.println("List of All Slots:");
        for (String slotID : parkingSlots.keySet()) {
            ParkingSlot parkingSlot = parkingSlots.get(slotID);
            String status = parkingSlot.isOccupied() ? "Occupied" : "Empty";
            
            System.out.print("Slot ID: " + slotID + ", Status: " + status);
            
            if (parkingSlot.isOccupied()) {
                Car parkedCar = parkingSlot.getParkedCar();
                String parkingTime = parkedCar.getParkingTime();

                // Calculate the parking time duration
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date parkingStartTime = dateFormat.parse(parkingTime);
                    Date currentTime = new Date();
                    long durationMillis = currentTime.getTime() - parkingStartTime.getTime();

                    long hours = durationMillis / (60 * 60 * 1000);
                    long minutes = (durationMillis % (60 * 60 * 1000)) / (60 * 1000);
                    long seconds = (durationMillis % (60 * 1000)) / 1000;

                    System.out.println(", Parking Time: " + hours + " hours " + minutes + " minutes " + seconds + " seconds");

                    // Display car's registration and make
                    System.out.println("   Car Registration: " + parkedCar.getRegistrationNumber());
                    System.out.println("   Car Make: " + parkedCar.getMake());
                    System.out.println("===============================");
                } catch (Exception e) {
                    System.out.println(", Parking Time: Error calculating parking time");
                    System.out.println("===============================");
                }
            } else {
                System.out.println();
            }
        }
    }
    
    public Map<String, String> getAllParkingSlotsAndStatus() {
        Map<String, String> parkingSlotsAndStatus = new HashMap<>();
        for (String slotID : parkingSlots.keySet()) {
            ParkingSlot parkingSlot = parkingSlots.get(slotID);
            String status = parkingSlot.isOccupied() ? "Occupied" : "Empty";
            parkingSlotsAndStatus.put(slotID, status);
        }
        return parkingSlotsAndStatus;
    }
    
    /**
     * Get a parking slot by its ID.
     *
     * @param slotID The ID of the parking slot to retrieve.
     * @return The ParkingSlot object associated with the given slot ID, or null if not found.
     */
    public ParkingSlot getParkingSlot(String slotID) {
        return parkingSlots.get(slotID);
    }

    /**
     * Parks a car in a specified parking slot, provided the slot exists and is unoccupied.
     *
     * @param slotID The ID of the parking slot to park the car.
     * @param car    The car to park.
     * @return True if the car is parked successfully; otherwise, false if the slot is occupied or doesn't exist,
     * or the car with the same registration number is already parked.
     */
    public boolean parkCar(String slotID, Car car) {
        ParkingSlot parkingSlot = parkingSlots.get(slotID);

        // Check if the slot exists and is unoccupied
        if (parkingSlot != null && !parkingSlot.isOccupied()) {
            String registrationNumber = car.getRegistrationNumber();
            
            // Check if the car with this registration number is already parked
            if (!parkedCarRegistrationNumbers.contains(registrationNumber)) {
                parkingSlot.parkCar(car);
                car.park(); // Call the park method to record the parking time

                // Display the parking time
                System.out.println("Car parked successfully at " + car.getParkingTime());
                System.out.println("===============================");

                parkedCarRegistrationNumbers.add(registrationNumber); // Add the registration number to the set
                return true; // Car parked successfully
            } else {
                // Car is already parked
                System.out.println("Car with registration number '" + registrationNumber + "' is already parked.");
                return false; // Car could not be parked
            }
        }

        return false; // Car could not be parked
    }


    /**
     * Searches for a car in the parking slots by its registration number.
     *
     * @param registrationNumber The registration number of the car to find.
     * @return A Map.Entry containing the Car and ParkingSlot where the car is parked if found; otherwise, null.
     */
    public Map.Entry<Car, ParkingSlot> findCarByRegistration(String registrationNumber) {
        for (Map.Entry<String, ParkingSlot> entry : parkingSlots.entrySet()) {
            ParkingSlot slot = entry.getValue();
            if (slot.isOccupied() && slot.getParkedCar().getRegistrationNumber().equals(registrationNumber)) {
                return new AbstractMap.SimpleEntry<>(slot.getParkedCar(), slot);
            }
        }
        return null;
    }

    /**
     * Removes a car from a parking slot by its registration number, if found and the slot is unoccupied.
     * Prints relevant messages indicating success or failure.
     *
     * @param registrationNumber The registration number of the car to remove.
     */
    public void removeCarByRegistration(String registrationNumber) {
            for (ParkingSlot slot : parkingSlots.values()) {
                if (slot.isOccupied() && slot.getParkedCar().getRegistrationNumber().equals(registrationNumber)) {
                    slot.removeCar();
                    System.out.println("Car with registration " + registrationNumber + " removed from slot " + slot.getSlotID());
                    System.out.println("===============================");
                    return;
                }
            }
            System.out.println("Car with registration " + registrationNumber + " not found in any slot.");
            System.out.println("===============================");
        }
        
    /**
     * Removes a car from a parking slot by Slot ID, if found and the slot is unoccupied.
     * Prints relevant messages indicating success or failure.
     *
     * @param SlotID .
     */    
    public void removeCarBySlotID(String slotID) {
    if (parkingSlots.containsKey(slotID)) {
        ParkingSlot slot = parkingSlots.get(slotID);
        if (slot.isOccupied()) {
            slot.removeCar();
        } else {
            System.out.println("Slot " + slotID + " is not occupied.");
        }
    } else {
        System.out.println("Slot " + slotID + " does not exist.");
    }
    }
        
    

        /**
     * Finds and displays information about cars with a specified make that are currently parked in the car park.
     * This includes details like the slot ID, registration number, make, model, year, and occupied time.
     * If the calculation of occupied time encounters an error, an error message is displayed.
     *
     * @param make The make of the cars to find.
     */
    public void findCarsByMake(String make) {
        System.out.println("Cars with Make: " + make);
        for (String slotID : parkingSlots.keySet()) {
            ParkingSlot parkingSlot = parkingSlots.get(slotID);
            if (parkingSlot.isOccupied()) {
                Car parkedCar = parkingSlot.getParkedCar();
                if (parkedCar.getMake().equalsIgnoreCase(make)) {
                    String registrationNumber = parkedCar.getRegistrationNumber();
                    
                    // Calculate the parking time duration
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date parkingStartTime = dateFormat.parse(parkedCar.getParkingTime());
                        Date currentTime = new Date();
                        long durationMillis = currentTime.getTime() - parkingStartTime.getTime();

                        long hours = durationMillis / (60 * 60 * 1000);
                        long minutes = (durationMillis % (60 * 60 * 1000)) / (60 * 1000);
                        long seconds = (durationMillis % (60 * 1000)) / 1000;

                        System.out.println("Slot ID: " + slotID);
                        System.out.println("   Registration Number: " + registrationNumber);
                        System.out.println("   Make: " + parkedCar.getMake());
                        System.out.println("   Model: " + parkedCar.getModel());
                        System.out.println("   Year: " + parkedCar.getYear());
                        System.out.println("   Occupied Time: " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
                        System.out.println("===============================");
                    } catch (Exception e) {
                        System.out.println("Slot ID: " + slotID + ", Registration Number: " + registrationNumber +
                                ", Occupied Time: Error calculating parking time");
                        System.out.println("===============================");
                    }
                }
            }
        }
    }
}