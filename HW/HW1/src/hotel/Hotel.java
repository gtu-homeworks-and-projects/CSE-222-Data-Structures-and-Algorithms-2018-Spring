package hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Hotel class keeps all of its guests and rooms data. Also most of the operations happens in this class.
 */
class Hotel {
    static final int maxNumOfRooms = 200;
    Room[] rooms = new Room[maxNumOfRooms];
    Guest[] guests = new Guest[maxNumOfRooms*Room.roomSize];

    /**
     * No parameter constructor for Hotel constructs room and guest arrays with different ID's.
     */
    Hotel(){
        for (int i = 0; i < maxNumOfRooms; i++)   rooms[i] = new Room(i+1);
        for (int i = 0; i < maxNumOfRooms * Room.roomSize; i++)   guests[i] = new Guest("", "", i+1);
    }

    /* OPERATIONS RELATED BOTH GUEST AND ROOMS */

    /**
     * Books a new Room if current Guest has no Room. And assigns guest to the related room.
     * @param bookingPerson Which guest to be booked in.
     */
    void newBooking(Guest bookingPerson){
        if(bookingPerson.getRoomID() == 0) {
            int roomIndex = findEmptyRoom();
            boolean success = rooms[roomIndex].assignGuest(bookingPerson.getUserID());
            bookingPerson.setRoomID(rooms[roomIndex].getRoomID());

            if(success) System.out.println(bookingPerson + " successfully booked. \nGuest ID: " + bookingPerson.getUserID() + "\nRoom ID was: " + bookingPerson.getRoomID());
        }
    }

    /**
     * Cancels reservation of guest by resetting room and guest data of ID's. If there's room registered for him.
     * @param cancelledPerson Which guest to be cancelled.
     */
    void cancelReservation(Guest cancelledPerson){
        if (cancelledPerson.getRoomID() != 0) {
            int roomIndex = findRoomIndexByID(cancelledPerson.getRoomID());
            boolean success = rooms[roomIndex].cancelAssign();
            int guestIndex = findGuestIndexByID(cancelledPerson.getUserID());
            guests[guestIndex].setRoomID(0);

            if(success) System.out.println(cancelledPerson + " successfully cancelled reservation. \nGuest ID: " + cancelledPerson.getUserID() + "\nRoom ID was: " + rooms[roomIndex].getRoomID());
        }
    }

    /* FINDER METHODS */

    /**
     * Helper function for finding first empty Room in a Room Array
     * @return index for the first empty Room
     */
    private int findEmptyRoom(){
        int i;
        for (i = 0; i < maxNumOfRooms && rooms[i].getGuestID() != 0; i++);
        return i;
    }

    /**
     * Helper function for finding Room of a Guest
     * @param roomID ID of Room which you want to find
     * @return index of corresponding room
     */
    int findRoomIndexByID(int roomID){
        int i;
        for (i = 0; i < maxNumOfRooms && rooms[i].getRoomID() != roomID; i++);
        return i;
    }

    /**
     * Helper method for finding Room of a Guest
     * @param roomID ID of Room which you want to find
     * @return Found room reference
     */
    Room roomFinderByID(int roomID){
        return rooms[findRoomIndexByID(roomID)];
    }

    /**
     * Helper function for finding Guest of a Room
     * @param guestID ID of Guest which you want to find
     * @return index of corresponding guest
     */
    int findGuestIndexByID(int guestID){
        int i;
        for (i = 0; i < maxNumOfRooms * Room.roomSize && guests[i].getUserID() != guestID; i++);
        return i;
    }

    /**
     * Helper method for finding Guest for Receptionists. It asks Which ID to be searched.
     * @param inputMessage Displayed message before ID input.
     * @return Found Guest reference
     */
    Guest guestFinderByID(String inputMessage){
        System.out.print(inputMessage);
        int tempGuestID;
        do{
            tempGuestID = Integer.parseInt(StaticScanner.getScanner().nextLine());
        } while (tempGuestID <= 0 || tempGuestID > Hotel.maxNumOfRooms*Room.roomSize);
        return guests[findGuestIndexByID(tempGuestID)];
    }

    /**
     * Helper function for finding first empty Guest in a Guest Array in case of new guest
     * @return index for the first empty Guest
     */
    private int findEmptyGuestSlot(){
        int i;
        for (i = 0; i < maxNumOfRooms * Room.roomSize && guests[i].getRoomID() != 0; i++);
        return i;
    }

    /* DATABASE FILE READING/WRITING METHODS */

    /**
     * Loads offline database of hotel.
     * @param roomDB Room Database file name
     * @param guestDB Guest Database file name
     * @throws FileNotFoundException Wrong file names
     */
    void loadDB(String roomDB, String guestDB) throws FileNotFoundException{
        Scanner csvReader = new Scanner(new File(roomDB));
        csvReader.nextLine();
        for (int i = 0; csvReader.hasNext() && i < maxNumOfRooms; i++){
            String[] line = csvReader.nextLine().split(",");
            rooms[i] = new Room(Integer.parseInt(line[0]));
            rooms[i].assignGuest(Integer.parseInt(line[1]));
        }
        csvReader.close();

        csvReader = new Scanner(new File(guestDB));
        csvReader.nextLine();
        for (int i = 0; i < maxNumOfRooms*Room.roomSize && csvReader.hasNext(); i++) {
            String[] line = csvReader.nextLine().split(",");
            guests[i] = new Guest(line[0],line[1],Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]) == 1);
        }
        csvReader.close();
    }
    /**
     * Saves offline database of hotel.
     * @param roomDB Room Database file name
     * @param guestDB Guest Database file name
     * @throws FileNotFoundException Wrong file names
     */
    void saveCurrentState(String roomDB, String guestDB) throws FileNotFoundException{
        PrintWriter csvWriter = new PrintWriter(new File(roomDB));
        csvWriter.append("roomID,guestID\n");
        for (int i = 0; i < maxNumOfRooms; i++) {
            csvWriter.append(rooms[i].getRoomID() + "," + rooms[i].getGuestID() + "\n");
        }
        csvWriter.close();

        csvWriter = new PrintWriter(new File(guestDB));
        csvWriter.append("name,surname,ID,roomID,checkIn\n");
        for (int i = 0; i < maxNumOfRooms * Room.roomSize; i++) {
            csvWriter.append(guests[i].getName() + "," + guests[i].getSurname() + "," + guests[i].getUserID() + "," + guests[i].getRoomID() + "," + (guests[i].getChecked() ? 1:0) + "\n");
        }
        csvWriter.close();

    }

}
