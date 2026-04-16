package org.example;

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v5.0 =====");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Double"));
        queue.addRequest(new Reservation("Charlie", "Suite"));

        queue.displayQueue();

        BookingService service = new BookingService();
        Reservation r;
        while((r=queue.processRequest())!=null) {
            service.allocateRoom(inventory, r);
        }
    inventory.displayInventory();
    }
}