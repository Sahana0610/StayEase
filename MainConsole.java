// MainConsole.java
import java.util.*;

public class MainConsole {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Hotel hotel = new Hotel(10);
        while (true) {
            System.out.println("\n1: View, 2: Book, 3: Checkout, 0: Exit");
            switch (in.nextInt()) {
                case 1 -> hotel.displayAvailableRooms();
                case 2 -> {
                    System.out.print("Room #: ");
                    int r = in.nextInt();
                    in.nextLine();
                    System.out.print("Name: ");
                    String n = in.nextLine();
                    System.out.println(hotel.bookRoom(r, n) ? "Booked!" : "Failed!");
                }
                case 3 -> {
                    System.out.print("Room #: ");
                    System.out.println(hotel.checkoutRoom(in.nextInt()) ? "Checked out!" : "Failed!");
                }
                case 0 -> { in.close(); return; }
                default -> System.out.println("Invalid.");
            }
        }
    }
}
