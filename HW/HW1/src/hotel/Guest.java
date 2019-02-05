package hotel;

/**
 * Guest class have check in state other than Users.
 */
class Guest implements User {
    private String name,surname;
    private int roomID;
    private int ID;
    private boolean checkedIn = false;

    /**
     * For initialization purposes use this constructor.
     * @param firstName Name
     * @param secondName Surname
     * @param guestID Guest ID
     */
    Guest(String firstName, String secondName, int guestID){
        this(firstName,secondName,guestID,0,false);
    }

    /**
     * For recreating existing guest (loading database) use this constructor.
     * @param firstName Name
     * @param secondName Surname
     * @param guestID Guest ID
     * @param roomID Room ID of Guest
     * @param check checked in or not
     */
    Guest(String firstName, String secondName, int guestID, int roomID, boolean check){
        name = firstName;
        surname = secondName;
        ID = guestID;
        this.roomID = roomID;
        checkedIn = check;
    }

    /**
     * This function checks guests in. Usually invoked by Receptionist.
     */
    void checkIn(){
        if(this.ID != 0 && !checkedIn) {
            checkedIn = true;
            System.out.println(this + " successfully checked in. \nGuest ID: " + ID + "\nRoom ID was: " + roomID);
        }
        else System.err.println("Room is not booked or guest already checked in. Please first make a reservation or check out guest.");
    }

    /**
     * This function checks guests out. Usually invoked by Receptionist.
     * @return Checked out room ID
     */
    int checkOut(){
        int tempRoomID = roomID;
        if(this.ID != 0 && checkedIn) {
            System.out.println(this + " successfully checked out. \nGuest ID: " + ID + "\nRoom ID was: " + roomID);
            roomID = 0;
            checkedIn = false;
            return tempRoomID;
        }
        else System.err.println("Room is not booked or guest not checked in. Please first make a reservation and check in guest.");
        return 0;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public boolean isGuest(){
        return true;
    }

    @Override
    public int getUserID() {
        return ID;
    }

    @Override
    public int getRoomID(){
        return roomID;
    }

    @Override
    public String toString() {
        return "Guest " + name + ' ' + surname;
    }

    @Override
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    @Override
    public String getName() {
        return name;
    }
    public boolean getChecked(){
        return checkedIn;
    }
}
