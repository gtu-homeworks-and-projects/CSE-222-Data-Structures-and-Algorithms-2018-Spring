package hotel;

import java.io.*;
import java.util.Scanner;

/**
 * Main management system class. Customers interacts with this layer of classes.
 */
public class ManagementSystem {
    private User loggedInUser;
    private boolean isLogged = false;
    private Hotel gtuHotel;
    private Receptionist masterUser;

    /**
     * Our management system manages one hotel by one receptionist.
     */
    ManagementSystem(){
        gtuHotel = new Hotel();
        masterUser = new Receptionist("Halil Onur", "Çeçen",1);
        try {
            loadDB();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /* MAIN TEST METHODS */



    public static void main(String[] args) {
        ManagementSystem sys1 = new ManagementSystem();
        sys1.startSystemTest();
    }


    /**
     * Main management system flow controlled within this method. All other methods called from here. (Console input)
     */
    public void startSystem(){
        System.out.println("Welcome to the GTU Hotel Management System!");
        boolean resetOperation;
        do{
            boolean loggedSuccessfully;
            do{
                System.out.println("Please provide your Last Name and your User ID correctly. (Do not share your id with anyone other than hotel receptionists.");
                Scanner reader = new Scanner(System.in);
                System.out.print("Last Name: ");
                String lastName = reader.nextLine();
                System.out.print("User ID: ");
                int userID = reader.nextInt();
                loggedSuccessfully = isLoggedSuccessfully(lastName, userID);
            }while (!loggedSuccessfully);
            System.out.println(loggedInUser + " logged in successfully.");

            resetOperation = false;
            boolean validInput;
            do {
                System.out.println("Please select one of operations listed below.");
                if (loggedInUser.isGuest())
                    System.out.println("1. Book a Room\n2. Cancel Reservation For Your Reservation");
                else
                    System.out.println("1. Book a Room for a Guest\n2. Cancel Reservation\n3. Check-in a Guest\n4. Check-out a Guest");
                Scanner operationReader = new Scanner(System.in);
                int operationChoice = operationReader.nextInt();
                validInput = isValidInput(operationChoice);
            } while (!validInput);
            Scanner resetReader = new Scanner(System.in);
            System.out.print("Do you want to choose another operation? (y/n)");
            String resetChoice = resetReader.nextLine();
            if(resetChoice.equals("y") || resetChoice.equals("Y")) {
                resetOperation = true;
                logout();
            }
        } while(resetOperation);
        saveDB();
        System.out.println("Have a nice day!");
    }

    /**
     * Main management system flow controlled within this method. All other methods called from here. (File input)
     */
    public void startSystemTest(){
        System.out.println("Welcome to the GTU Hotel Management System!");
        boolean resetOperation;
        do{
            boolean loggedSuccessfully = false;
            do{
                System.out.println("Please provide your Last Name and your User ID correctly. (Do not share your id with anyone other than hotel receptionists.");
                System.out.print("Last Name: ");
                String lastName = StaticScanner.getScanner().nextLine();
                System.out.print("User ID: ");
                int userID = Integer.parseInt(StaticScanner.getScanner().nextLine());
                loggedSuccessfully = isLoggedSuccessfully(lastName, userID);
            }while (!loggedSuccessfully);
            System.out.println(loggedInUser + " logged in successfully.");

            resetOperation = false;
            boolean validInput;
            do {
                System.out.println("Please select one of operations listed below.");
                if (loggedInUser.isGuest())
                    System.out.println("1. Book a Room\n2. Cancel Reservation For Your Reservation");
                else
                    System.out.println("1. Book a Room for a Guest\n2. Cancel Reservation\n3. Check-in a Guest\n4. Check-out a Guest");
                int operationChoice = Integer.parseInt(StaticScanner.getScanner().nextLine());
                validInput = isValidInput(operationChoice);

            } while (!validInput);
            System.out.print("Do you want to choose another operation? (y/n)");
            String resetChoice = StaticScanner.getScanner().nextLine();
            if(resetChoice.equals("y") || resetChoice.equals("Y")) {
                resetOperation = true;
                logout();
            }
        } while(resetOperation);
        saveDB();
        System.out.println("Have a nice day!");
    }

    /* */

    /**
     * Checks input validation for operations
     * @param operationChoice operation input
     * @return true or false depending on success
     */
    private boolean isValidInput(int operationChoice){
        boolean validInput = true;
        switch (operationChoice){
            case 1:
                validInput = book();
                break;
            case 2:
                validInput = cancel();
                break;
            case 3:
                if(!loggedInUser.isGuest()){
                    checkIn();
                    break;
                }
            case 4:
                if(!loggedInUser.isGuest()) {
                    checkOut();
                    break;
                }
            default:
                validInput = false;
                System.err.println("You haven't choose a valid operation. Please try again.");
        }
        return validInput;
    }

    /**
     * Handles login operation and its success
     * @param lastName User last name
     * @param userID User ID
     * @return true or false depending on success
     */
    private boolean isLoggedSuccessfully(String lastName, int userID) {
        boolean loggedSuccessfully;
        try {
            User loginnable = checkIdentity(lastName,userID);
            loggedSuccessfully = true;
            login(loginnable);
        } catch (UserException e){
            System.err.println(e.getMessage());
            loggedSuccessfully = false;
        }
        return loggedSuccessfully;
    }

    /**
     * Checks validity of input for login.
     * @param lastName Lastname of user
     * @param ID ID of uer
     * @return logged in user
     * @throws UserException Invalid lastname and id combination
     */
    private User checkIdentity(String lastName, int ID) throws UserException {
        if(masterUser.getUserID() == ID && masterUser.getSurname().equals(lastName)){
            return masterUser;
        }
        int index = gtuHotel.findGuestIndexByID(ID);
        if(index == Hotel.maxNumOfRooms * Room.roomSize || !gtuHotel.guests[index].getSurname().equals(lastName) ){
            throw new UserException("Invalid Credentials, Please try Again");
        }
        return gtuHotel.guests[index];
    }

    /**
     * If receptionist logs in, he can check in any guest by asking id of guest.
     */
    private void checkIn(){
        gtuHotel.guestFinderByID("Enter Guest ID which you want to check-in: ").checkIn();
    }

    /**
     * If receptionist logs in, he can check out any guest by asking id of guest.
     */
    private void checkOut(){
        int roomID = gtuHotel.guestFinderByID("Enter Guest ID which you want to check-out: ").checkOut();
        if (roomID != 0) gtuHotel.roomFinderByID(roomID).cancelAssign();
    }

    /**
     * Logs a user in
     * @param user user to be logged in
     */
    private void login(User user){
        loggedInUser = user;
        isLogged = true;
    }

    /**
     * Logsout current logged in user.
     */
    private void logout(){
        if(isLogged){
            loggedInUser = null;
            isLogged = false;
        }
        else System.err.println("User not logged in!");
    }

    /**
     * Books new room for a guest
     * @return if successful returns true.
     */
    private boolean book(){
        if(isLogged){
            if(loggedInUser.isGuest())
                gtuHotel.newBooking((Guest)loggedInUser);
            else {
                gtuHotel.newBooking(gtuHotel.guestFinderByID("Enter Guest ID which you want to book: "));
            }
            return true;
        }
        else System.err.println("First login please!");
        return false;
    }

    /**
     * Cancels reservation for a guests booked room.
     * @return True if successful
     */
    private boolean cancel(){
        if(isLogged){
            if(loggedInUser.isGuest())
                gtuHotel.cancelReservation((Guest)loggedInUser);
            else {
                gtuHotel.cancelReservation(gtuHotel.guestFinderByID("Enter Guest ID which you want to cancel their reservation: "));
            }
            return true;
        }
        else System.err.println("First login please!");
        return false;
    }

    /**
     * Loads offline database of system.
     * @throws FileNotFoundException Wrong file names
     */
    private void loadDB() throws FileNotFoundException{
        gtuHotel.loadDB("rooms.csv", "guests.csv");
    }

    /**
     * Saves offline database of system.
     */
    private void saveDB() {
        try {
            gtuHotel.saveCurrentState("rooms.csv", "guests.csv");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
