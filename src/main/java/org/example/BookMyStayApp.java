package org.example;

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v7.0 =====");

        // Room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // UC4 - Search
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );

        // UC5 - Queue
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("R101", "Alice", "Single"));
        queue.addRequest(new Reservation("R102", "Bob", "Double"));
        queue.addRequest(new Reservation("R103", "Charlie", "Suite"));

        queue.displayQueue();

        // UC6 - Allocation
        BookingService service = new BookingService();

        Reservation r;
        Reservation firstReservation = null;

        while ((r = queue.processRequest()) != null) {
            service.allocateRoom(inventory, r);

            if (firstReservation == null) {
                firstReservation = r;
            }
        }

        inventory.displayInventory();

        // ================= UC7 START =================

        AddOnServiceManager addOnManager = new AddOnServiceManager();

        if (firstReservation != null) {
            String resId = firstReservation.getReservationId();

            addOnManager.addService(resId, new Service("Breakfast", 500));
            addOnManager.addService(resId, new Service("WiFi", 200));
            addOnManager.addService(resId, new Service("Spa", 1000));

            addOnManager.displayServices(resId);
        }

        // ================= UC7 END =================
    }
}