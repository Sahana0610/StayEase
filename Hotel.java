// Hotel.java
public class Hotel {
    private Room[] rooms;
    public Hotel(int size) {
        rooms = new Room[size];
        for (int i = 0; i < size; i++) rooms[i] = new Room(i + 1);
    }

    public void displayAvailableRooms() {
        System.out.println("Available rooms:");
        for (Room r : rooms)
            if (!r.isOccupied()) System.out.println(r);
    }

    public boolean bookRoom(int num, String guest) {
        if (num < 1 || num > rooms.length) return false;
        return rooms[num - 1].book(guest);
    }

    public boolean checkoutRoom(int num) {
        if (num < 1 || num > rooms.length) return false;
        return rooms[num - 1].checkout();
    }
}
