package org.example;

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v10.0 (UC10 Integrated) =====");

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

        // UC9 invalid test
        queue.addRequest(new Reservation("R104", "David", "Luxury"));

        queue.displayQueue();

        // Services
        BookingService bookingService = new BookingService();
        CancellationService cancelService = new CancellationService();
        BookingHistory history = new BookingHistory();

        Reservation r;
        Reservation firstReservation = null;

        // ================= UC6 + UC9 + UC10 Allocation =================
        while ((r = queue.processRequest()) != null) {

            bookingService.allocateRoom(inventory, r, cancelService);

            // store only successful bookings
            if (cancelService.hasReservation(r.getReservationId())) {
                history.addReservation(r);
            }

            if (firstReservation == null && cancelService.hasReservation(r.getReservationId())) {
                firstReservation = r;
            }
        }

        inventory.displayInventory();

        // ================= UC7 Add-ons =================
        AddOnServiceManager addOnManager = new AddOnServiceManager();

        if (firstReservation != null) {
            String resId = firstReservation.getReservationId();

            addOnManager.addService(resId, new Service("Breakfast", 500));
            addOnManager.addService(resId, new Service("WiFi", 200));
            addOnManager.addService(resId, new Service("Spa", 1000));

            addOnManager.displayServices(resId);
        }

        // ================= UC10 Cancellation =================
        System.out.println("\n===== UC10: Cancellation Flow =====");

        if (firstReservation != null) {
            cancelService.cancelBooking(firstReservation, inventory);
        }

        cancelService.displayRollbackStack();

        inventory.displayInventory();

        // ================= UC8 History + Report =================
        history.displayHistory();

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}