package hotel;

/**
 * Room class has all room related methods. Also keeps hotels data for one room.
 */
class Room {
    static final int roomSize = 2;
    private int roomID;
    private int guestID = 0;

    /**
     * Simple room constructor
     * @param roomNumber Room ID / number
     */
    Room(int roomNumber){
        roomID = roomNumber;
    }

    /**
     * Getter for which guest is staying in that room
     * @return guest id
     */
    int getGuestID(){
        return guestID;
    }

    /**
     * Getter for current rooms ID
     * @return room ID
     */
    int getRoomID(){
        return roomID;
    }

    /**
     * Assigns a guest to the current room
     * @param guestID which guest to be assigned
     * @return true or false depending on success
     */
    boolean assignGuest(int guestID){
        if(this.guestID == 0) {
            this.guestID = guestID;
            return true;
        } else {
            System.err.println("Room is already assigned to another Guest. Please checkout or cancel reservation first!");
            return false;
        }
    }

    /**
     * Cancels an assignment on the room
     * @return true or false depending on success
     */
    boolean cancelAssign(){
        if(this.guestID != 0) {
            this.guestID = 0;
            return true;
        } else {
            System.err.println("Room isn't assigned to anybody!");
            return false;
        }
    }

}
