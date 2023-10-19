
/**
* This class provides graphical user interface for car park system
* @author Aida Pouradam <103847608>
* @version JDK version 20.0.2; Program version 1.0
* @date Created on 22 October 2023
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Map;
import java.util.List;



public class GUI implements ActionListener {
    private JFrame mainFrame; // Create a JFrame for the main application window
    private JPanel headerPanel; // Create a JPanel for the header section
    private JPanel mainPanel; // Create a JPanel for the main content
    private CarPark carPark; // Create an instance of the CarPark class to manage parking data
    private JTextArea outputTextArea;
    private JScrollPane scrollPane; // For scrolling the text area
    private JPanel actionPanel; // Declare actionPanel as an instance variable
    
    /**
     * Constructs a new GUI for the Swinburne Car Park System.
     *
     * @param carPark The CarPark instance to be associated with this GUI.
     */
    public GUI(CarPark carPark) {
        this.carPark = carPark; // Initialize the CarPark instance
        prepareGUI(); // Call the prepareGUI method to set up the user interface
    }

    
    /**
     * Prepares the graphical user interface (GUI) for the Swinburne Car Park System.
     * Initializes the main frame, sets up window events, adds header and main content panels,
     * and displays the GUI.
     */
    private void prepareGUI() {
        mainFrame = new JFrame("Swinburne Car Park System"); // Create a new JFrame with a title
        mainFrame.setSize(800, 600); // Set the size of the main window
        mainFrame.setLayout(new GridLayout(3, 1)); // JFrame is 3 rows 1 column
        mainFrame.setLayout(new BorderLayout()); // Use BorderLayout as the layout manager

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0); // Exit the application when the window is closed
            }
        });

        addHeaderPanel(); // Call a method to set up the header section
        addMainPanel(); // Call a method to set up the main content section
        
        // Initialize the output text area
        outputTextArea = new JTextArea(10, 50); // Rows and columns
        outputTextArea.setEditable(false); // Make it read-only
        scrollPane = new JScrollPane(outputTextArea); // Add it to a scroll pane
             
        // Add the scroll pane (containing the text area) to the main content
        mainFrame.add(scrollPane, BorderLayout.SOUTH); // Add it to the bottom of the main window

        mainFrame.setVisible(true); // Make the main window visible
    }
    
    
    /**
     * Appends text to the output text area, followed by a newline, and scrolls to the bottom.
     *
     * @param text The text to be appended.
     */
    // Append text to the text area
    public void appendToOutput(String text) {
        outputTextArea.append(text + "\n"); // Append the text with a newline
        outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength()); // Scroll to the bottom
    }

    
    /**
     * Adds the header panel to the GUI, consisting of a text label and an image.
     */
    private void addHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 1)); // Create a JPanel with a 2x1 grid layout
        headerPanel.setBackground(Color.WHITE); // Set the background color to white
        Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY); // Create a dark gray border
        headerPanel.setBorder(blackline); // Set the border for the header panel

        JLabel textLabel = new JLabel("SWINBURNE CAR PARK SYSTEM"); // Create a label with text
        textLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text horizontally
        textLabel.setVerticalAlignment(SwingConstants.CENTER); // Center-align the text vertically
        textLabel.setFont(new Font("Serif", Font.BOLD, 40)); // Set the font and style
        headerPanel.add(textLabel); // Add the text label to the header panel

        JLabel picLabel = new JLabel(); // Create a label for an image
        // Load and set an image as an icon, scaled to 200x200 pixels
        picLabel.setIcon(new ImageIcon(new ImageIcon("carparksymbol.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        picLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the image horizontally
        picLabel.setVerticalAlignment(SwingConstants.CENTER); // Center-align the image vertically
        headerPanel.add(picLabel); // Add the image label to the header panel

        mainFrame.add(headerPanel, BorderLayout.NORTH); // Add the header panel to the top of the main window
    }
    
    
    /**
     * Refreshes the entire panel by removing all components from the main panel,
     * re-adding action buttons and slot buttons, and updating the GUI.
     */
    // A method to refresh the entire panel
    private void refreshPanel() {
        mainPanel.removeAll(); // Remove all components from the main panel
        addMainPanel(); // Re-add action buttons and slot buttons
        mainFrame.revalidate(); // Revalidate the main frame to reflect the changes
        mainFrame.repaint(); // Repaint the main frame
    }
    
    
    /**
     * Adds the main panel to the GUI, including action buttons on the left and slot buttons on the right.
     */
    private void addMainPanel() {
        mainPanel = new JPanel(new GridLayout(1, 2)); // Create a JPanel with a 4x2 grid layout
        mainFrame.add(mainPanel, BorderLayout.CENTER); // Add the main panel to the center of the main window
            
        // Create a panel for action buttons (left side)
        actionPanel = new JPanel(new GridLayout(4, 1));
        addActionButton("Show All Parking Spots");// Call a method to add a button
        addActionButton("Find Car");// Call a method to add a button
        addActionButton("Park Car");// Call a method to add a button
        addActionButton("Delete Spot");// Call a method to add a button
        addActionButton("Remove Car");// Call a method to add a button
        addActionButton("Add Parking Spot");// Call a method to add a button
        addActionButton("Exit Application");// Call a method to add a button
        addActionButton("Find Cars by Make");// Call a method to add a button
        mainPanel.add(actionPanel); // Add the action panel to the left side of the main panel
    
        // Create parking slots A001 and B001
        ParkingSlot parkingSlotA001 = new ParkingSlot("A001");
        ParkingSlot parkingSlotB001 = new ParkingSlot("B001");
            
    
        // Add the parking slots to the car park
        carPark.addParkingSlot(parkingSlotA001);
        carPark.addParkingSlot(parkingSlotB001);
            
        // Create a panel for slot buttons (right side)
        JPanel slotPanel = new JPanel(new GridLayout(2, 2)); // Create a JPanel for slot buttons with a 2x2 grid layout
                  
        // Check if the list of parking slots is not empty after adding
        if (!carPark.getParkingSlots().isEmpty()) {
            List<ParkingSlot> parkingSlots = carPark.getParkingSlots();
    
            // Iterate through the parking slots and create buttons to represent them
            for (ParkingSlot parkingSlot : parkingSlots) {
                JButton slotButton = new JButton("Slot ID: " + parkingSlot.getSlotID());
   
                // Set the icon based on whether the slot is occupied or available
                if (parkingSlot.isOccupied()) {
                    slotButton.setIcon(new ImageIcon("occupied.jpg"));
                    slotButton.setBackground(Color.RED); // Set the background color to red for occupied slots
                } else {
                    slotButton.setIcon(new ImageIcon("available.png"));
                    slotButton.setBackground(Color.GREEN); // Set the background color to green for unoccupied slots
                }
    
                // Customize the button appearance if needed
                slotButton.setBorder(BorderFactory.createEmptyBorder()); // Remove button border
                slotButton.setContentAreaFilled(false); // Make the button transparent
                slotButton.setVerticalTextPosition(SwingConstants.BOTTOM); // Set text below the icon
    
                // Add an ActionListener to handle button clicks for further actions
                slotButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             JButton clickedButton = (JButton) e.getSource(); // Get the clicked button
                        
                            // Extract the slot ID from the button's text (assuming the text is in the format "Slot ID: XXX")
                            String buttonText = clickedButton.getText();
                            String slotID = buttonText.substring(buttonText.lastIndexOf(" ") + 1);
                        
                            ParkingSlot parkingSlot = carPark.getParkingSlot(slotID); // Get the corresponding parking slot
                        
                            if (parkingSlot == null) {
                                // Handle the case where the parking slot does not exist (optional)
                                System.out.println("Parking slot " + slotID + " does not exist.");
                                return; // Exit the method
                            }
                        
                            if (parkingSlot.isOccupied()) {
                                // Slot is occupied, so implement the logic to remove the car
                                removeCar(); // Call the removeCar method from  GUI class
                            } else {
                                // Slot is empty, so implement the logic to park a car
                                parkCar(); // Call the parkCar method from GUI class
                            }
                            // Refresh the panel to display both action buttons and slot buttons
                            refreshPanel();
                        }
                    });
    
                    mainPanel.add(slotPanel); // Add the slot panel to the right side of the main panel
                    mainPanel.add(slotButton); // Add the button to the main panel
                }
            }
        }
    
    /**
     * Handles the click event of a parking slot button. If the slot is occupied, it removes the car;
     * otherwise, it parks a car in the slot.
     *
     * @param parkingSlot The parking slot associated with the clicked button.
     */
    // New method to handle slot button clicks
    private void handleSlotButtonClick(ParkingSlot parkingSlot) {
        if (parkingSlot.isOccupied()) {
            // Slot is occupied, so remove the car
            removeCarFromSlot(parkingSlot);
        } else {
            // Slot is empty, so park a car
            parkCarInSlot(parkingSlot);
        }
    }
    
    
    /**
     * Adds an action button to the action panel.
     *
     * @param buttonText The text to display on the button.
     */
    private void addActionButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setBackground(Color.LIGHT_GRAY);
        button.setPreferredSize(new Dimension(60, 40));
        button.addActionListener(this);
        actionPanel.add(button);
    }
      
    /**
     * Handles actions triggered by buttons.
     *
     * @param e The ActionEvent associated with the button action.
     */
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand(); // Get the action command of the clicked button

        // Handle button actions based on actionCommand
        if ("Show All Parking Spots".equals(actionCommand)) {
            handleButtonClick(actionCommand);
        } else if ("Delete Spot".equals(actionCommand)) { // Handle the "Delete Spot" button
            handleButtonClick(actionCommand);
        } else if ("Add Parking Spot".equals(actionCommand)) { // Handle the "Add Parking Spot" button
            handleButtonClick(actionCommand);
        } else if ("Park Car".equals(actionCommand)) { // Handle the "Park Car" button
            handleButtonClick(actionCommand);
        } else if ("Find Car".equals(actionCommand)) { // Handle the "Find Car" button
            handleButtonClick(actionCommand);
        } else if ("Remove Car".equals(actionCommand)) { // Handle the "Remove Car" button
            handleButtonClick(actionCommand);
        } else if ("Find Cars by Make".equals(actionCommand)) { // Handle the "Find Cars by Make" button
            handleButtonClick(actionCommand);
        } else if ("Exit Application".equals(actionCommand)) {
            handleButtonClick(actionCommand);
        }
        
        // Refresh the panel to display both action buttons and slot buttons
        refreshPanel();
    }
    
    /**
     * Change the background color of a button when it's clicked.
     *
     * @param buttonName The name of the clicked button.
     */
    private void handleButtonClick(String buttonName) {
        // Iterate through all buttons in the actionPanel
        for (Component component : actionPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                // Check if the button's text matches the buttonName
                if (button.getText().equals(buttonName)) {
                    // Change the button's background color when clicked
                    button.setBackground(Color.GREEN); 
                } else {
                    // Reset the background color for other buttons
                    button.setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    
        // Handle button actions based on buttonName
        if ("Show All Parking Spots".equals(buttonName)) {
            headerPanel.removeAll();
            showAllParkings(); // Call a method to show all parking spots
        } else if ("Delete Spot".equals(buttonName)) { // Handle the "Delete Spot" button
            deleteParking(); // Call a method to delete a parking spot
        } else if ("Add Parking Spot".equals(buttonName)) { // Handle the "Add Parking Spot" button
            addParkingSlot(); // Call the method to add a parking spot
        } else if ("Park Car".equals(buttonName)) { // Handle the "Park Car" button
            parkCar(); // Call the method to park a car
        } else if ("Find Car".equals(buttonName)) { // Handle the "Find Car" button
            findCar(); // Call the method to Find a Car
        } else if ("Remove Car".equals(buttonName)) { // Handle the "Remove Car" button
            removeCar(); // Call the method to Remove a Car
        } else if ("Find Cars by Make".equals(buttonName)) { // Handle the "Find Cars by Make" button
            findCarsByMake(); // Call the method to find cars by make
        } else if ("Exit Application".equals(buttonName)) {
            System.exit(0); // Exit the application
        }
    }

    
    /**
     * Displays the home screen of the application.
     */
    private void showHomeScreen() {
        headerPanel.removeAll();
        JLabel headerLabel = new JLabel("Welcome to Swinburne Car Park"); // Create a label
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text horizontally
        headerLabel.setVerticalAlignment(SwingConstants.CENTER); // Center-align the text vertically
        headerPanel.add(headerLabel); // Add the label to the header panel
        headerPanel.revalidate(); // Revalidate the header panel
        headerPanel.repaint(); // Repaint the header panel
        mainFrame.setVisible(true); // Make the main window visible
    }
    
    /**
     * Removes a car from the specified parking slot.
     *
     * @param parkingSlot The parking slot from which the car will be removed.
     */
    // New method to remove a car from a slot
    private void removeCarFromSlot(ParkingSlot parkingSlot) {
        if (parkingSlot.isOccupied()) {
            Car car = parkingSlot.getParkedCar();
            String registrationNumber = car.getRegistrationNumber();

            // Call the CarPark method to remove the car by registration number
            carPark.removeCarByRegistration(registrationNumber);

            // Append a message to the output area
            appendToOutput("Car with registration number '" + registrationNumber + "' removed from slot " + parkingSlot.getSlotID());
        }
    }

    /**
     * Parks a car in the specified parking slot.
     *
     * @param parkingSlot The parking slot where the car will be parked.
     */
    // New method to park a car in a slot
    private void parkCarInSlot(ParkingSlot parkingSlot) {
        if (!parkingSlot.isOccupied()) {
            // Create a dialog to input car details
            String registrationNumber = JOptionPane.showInputDialog("Enter Car Registration Number:");
            String make = JOptionPane.showInputDialog("Enter Car Make:");
            String model = JOptionPane.showInputDialog("Enter Car Model:");
            String year = JOptionPane.showInputDialog("Enter Car Year:");

            // Check if any input is canceled or empty
            if (registrationNumber != null && !registrationNumber.isEmpty()
                && make != null && !make.isEmpty()
                && model != null && !model.isEmpty()
                && year != null && !year.isEmpty()) {

                // Create a new car object
                Car newCar = new Car(registrationNumber, make, model, year);

                // Call the CarPark method to park the car in the selected slot
                if (carPark.parkCar(parkingSlot.getSlotID(), newCar)) {

                    // Append a message to the output area
                    appendToOutput("Car with registration number '" + registrationNumber + "' parked in slot " + parkingSlot.getSlotID());
                } else {
                    // Car could not be parked (slot occupied or duplicate registration)
                    JOptionPane.showMessageDialog(null, "Car could not be parked in this slot.", "Parking Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // User canceled or left a field empty
                JOptionPane.showMessageDialog(null, "Please fill in all car details.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Adds a parking slot to the car park.
     */
    // Method to add a parking spot
    private void addParkingSlot() {
        String slotID = JOptionPane.showInputDialog("Enter Slot ID (e.g., V001):");

        if (slotID != null && !slotID.isEmpty()) {
            ParkingSlot newParkingSlot = new ParkingSlot(slotID);

            if (carPark.addParkingSlot(newParkingSlot)) {
                JOptionPane.showMessageDialog(null, "Success: Parking slot added!", "Slot Added Message", JOptionPane.INFORMATION_MESSAGE);
                appendToOutput("Success: Parking slot added!");
            } else {
                JOptionPane.showMessageDialog(null, "Alert: Slot number is not unique", "Slot Adding Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Alert: Please enter a valid slot ID", "Invalid Slot ID", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Displays a list of all parking slots and their statuses.
     */
    // Method to show all parking spots
    private void showAllParkings() {
        headerPanel.removeAll();
    
        // Call the CarPark method to retrieve all parking slots and statuses
        Map<String, String> parkingSlotsAndStatus = carPark.getAllParkingSlotsAndStatus();
    
        // Display the parking slots and statuses
        appendToOutput("List of All Slots:");
        for (String slotID : parkingSlotsAndStatus.keySet()) {
            String status = parkingSlotsAndStatus.get(slotID);
            String output = "Slot ID: " + slotID + ", Status: " + status;
            appendToOutput(output);
        }
    
        headerPanel.revalidate();
        headerPanel.repaint();
        mainFrame.setVisible(true);
    }
    
    /**
     * Deletes a parking slot based on the provided slot ID.
     */
    // Method to delete a parking spot
    private void deleteParking() {
        String slotID = JOptionPane.showInputDialog("Enter Slot ID to delete (e.g., V001):");

        if (slotID != null && !slotID.isEmpty()) {
            carPark.deleteParkingSlot(slotID); // Call the method to delete a parking slot

            // Provide feedback to the user
            JOptionPane.showMessageDialog(null, "Parking slot " + slotID + " deleted.", "Slot Deleted Message", JOptionPane.INFORMATION_MESSAGE);
            appendToOutput("Parking slot " + slotID + " deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Alert: Please enter a valid slot ID", "Invalid Slot ID", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Parks a car in a specified parking slot.
     */
    // Method to park a car
        private void parkCar() {
        String slotID = JOptionPane.showInputDialog("Enter Slot ID to park the car (e.g., V001)");
        if (slotID != null && !slotID.isEmpty()) {
            // Check if the slot exists
            ParkingSlot parkingSlot = carPark.getParkingSlot(slotID);
            if (parkingSlot != null) {
                // Check if the slot is unoccupied
                if (!parkingSlot.isOccupied()) {
                    String registrationNumber = JOptionPane.showInputDialog("Enter Car Registration Number:");
                    String make = JOptionPane.showInputDialog("Enter Car Make:");
                    String model = JOptionPane.showInputDialog("Enter Car Model:");
                    String year = JOptionPane.showInputDialog("Enter Car Year:");

                    if (registrationNumber != null && !registrationNumber.isEmpty()
                            && make != null && !make.isEmpty()
                            && model != null && !model.isEmpty()
                            && year != null && !year.isEmpty()) {

                        Car car = new Car(registrationNumber, make, model, year); // Create a new Car object with the updated constructor
                        boolean parked = carPark.parkCar(slotID, car); // Call the method to park the car

                        if (parked) {
                            // Provide feedback to the user
                            JOptionPane.showMessageDialog(null, "Car parked successfully.", "Car Parked", JOptionPane.INFORMATION_MESSAGE);
                            appendToOutput("Car parked successfully: " + registrationNumber);
                        } else {
                            JOptionPane.showMessageDialog(null, "Car could not be parked. Please check if the slot is occupied or the car is already parked.", "Parking Error", JOptionPane.ERROR_MESSAGE);
                            appendToOutput("Failed to park car: " + registrationNumber);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid car information.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Parking slot " + slotID + " is already occupied.", "Occupied Slot", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Parking slot " + slotID + " does not exist.", "Invalid Slot", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Finds a car by its registration number and displays information about the car and its parking slot.
     */
    // Method to find a car by registration number
    private void findCar() {
        String registrationNumber = JOptionPane.showInputDialog("Enter Car Registration Number:");
    
        if (registrationNumber != null && !registrationNumber.isEmpty()) {
            // Call the CarPark method to find the car by registration number
            Map.Entry<Car, ParkingSlot> carEntry = carPark.findCarByRegistration(registrationNumber);
        
            if (carEntry != null) {
                Car foundCar = carEntry.getKey();
                ParkingSlot parkingSlot = carEntry.getValue();
            
                // Display information about the found car and its parking slot
                String message = "Car Found:\n"
                    + "Registration Number: " + foundCar.getRegistrationNumber() + "\n"
                    + "Make: " + foundCar.getMake() + "\n"
                    + "Model: " + foundCar.getModel() + "\n"
                    + "Year: " + foundCar.getYear() + "\n"
                    + "Parked in Slot ID: " + parkingSlot.getSlotID() + "\n";
            
                JOptionPane.showMessageDialog(null, message, "Car Found", JOptionPane.INFORMATION_MESSAGE);
                appendToOutput("Car Found");
            } else {
                JOptionPane.showMessageDialog(null, "Car with registration number '" + registrationNumber + "' not found.", "Car Not Found", JOptionPane.ERROR_MESSAGE);
                appendToOutput("Car with registration number '" + registrationNumber+ "' not found.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Alert: Please enter a valid car registration number.", "Invalid Registration Number", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Removes a car from the car park by its registration number.
     */
    // Method to remove a car
    private void removeCar() {
        String registrationNumber = JOptionPane.showInputDialog("Enter Car Registration Number to remove:");
    
        if (registrationNumber != null && !registrationNumber.isEmpty()) {
            carPark.removeCarByRegistration(registrationNumber);
            appendToOutput("Car with registration number '" + registrationNumber+ "' has been removed");
        } else {
            JOptionPane.showMessageDialog(null, "Alert: Please enter a valid car registration number", "Invalid Registration Number", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Finds cars in the car park by their make and displays the corresponding parking slots.
     */
    // Method to find a car by its make
    private void findCarsByMake() {
        String make = JOptionPane.showInputDialog("Enter Car Make to find:");
    
        if (make != null && !make.isEmpty()) {
            // Call the CarPark method to find cars by make
            carPark.findCarsByMake(make);
    
            // Iterate through parking slots and append the slot IDs to the output
            StringBuilder output = new StringBuilder("Car with Make: " + make + " found in the following slot: ");
            List<ParkingSlot> parkingSlots = carPark.getParkingSlots();
            for (ParkingSlot parkingSlot : parkingSlots) {
                if (parkingSlot.isOccupied()) {
                    Car parkedCar = parkingSlot.getParkedCar();
                    if (parkedCar.getMake().equalsIgnoreCase(make)) {
                        output.append(parkingSlot.getSlotID()).append(", ");
                    }
                }
            }
    
            // Remove the trailing ", " from the output
            String finalOutput = output.toString().replaceAll(", $", "");
            appendToOutput(finalOutput);
        } else {
            JOptionPane.showMessageDialog(null, "Alert: Please enter a valid car make", "Invalid Car Make", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        CarPark carPark = new CarPark(); // Create an instance of the CarPark class
        GUI newGUI = new GUI(carPark); // Create an instance of the GUI class
        newGUI.showHomeScreen(); // Call a method to show the home screen
    }
}
