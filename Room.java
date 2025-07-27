// Room.java
public class Room {
    private int roomNumber;
    private boolean occupied;
    private String guestName;

    public Room(int num) { roomNumber = num; }

    public boolean isOccupied() { return occupied; }
    public int getRoomNumber() { return roomNumber; }
    public String getGuestName() { return guestName; }

    public boolean book(String guest) {
        if (occupied) return false;
        occupied = true;
        guestName = guest;
        return true;
    }

    public boolean checkout() {
        if (!occupied) return false;
        occupied = false;
        guestName = null;
        return true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + (occupied ? "Occupied by " + guestName : "Available") + "]";
    }
}
