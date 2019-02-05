package hotel;

/**
 * Following class members can log onto Management System.
 */
interface User {
    /**
     * Checks current User instance is guest or not
     * @return true if its Guest
     */
    boolean isGuest();

    /**
     * Gets the ID of User instance
     * @return User ID
     */
    int getUserID();

    /**
     * Gets surname of User
     * @return surname
     */
    String getSurname();

    /**
     * Gets name of User
     * @return name
     */
    String getName();

    /**
     * Setter for roomID
     * @param roomID RoomID to be set
     */
    void setRoomID(int roomID);

    /**
     * Getter for Room ID of following user
     * @return roomID
     */
    int getRoomID();
}
