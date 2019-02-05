package hotel;

/**
 * Receptionist class for keeping data of receptionist.
 */
class Receptionist implements User {
    private String name,surname;
    private int ID;
    private int processingGuestRoomID = 0;

    /**
     * Minimum required information for Receptionist object
     * @param fname name of the receptionist
     * @param sname surname of the receptionist
     * @param userID Receptionist ID
     */
    Receptionist(String fname, String sname, int userID){
        name = fname;
        surname = sname;
        ID = userID;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public boolean isGuest(){
        return false;
    }

    @Override
    public int getUserID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setRoomID(int roomID){
        processingGuestRoomID = roomID;
    }

    /**
     * Getter for receptionists current roomID. For use of last processed id of room.
     * @return Room ID.
     */
    @Override
    public int getRoomID(){
        return processingGuestRoomID;
    }

    @Override
    public String toString() {
        return "Receptionist " + name + ' ' + surname;
    }


}
