package org.example;

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v11.0 (UC11 Concurrent System) =====");

        // ================= ROOM TYPES =================
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // ================= INVENTORY =================
        RoomInventory inventory = new RoomInventory();

        // ================= SEARCH (UC4) =================
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);

        // ================= SHARED QUEUE =================
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("R201", "Alice", "Single"));
        queue.addRequest(new Reservation("R202", "Bob", "Double"));
        queue.addRequest(new Reservation("R203", "Charlie", "Suite"));
        queue.addRequest(new Reservation("R204", "David", "Single"));
        queue.addRequest(new Reservation("R205", "Eve", "Double"));
        queue.addRequest(new Reservation("R206", "Frank", "Suite"));

        queue.displayQueue();

        // ================= SHARED SERVICES =================
        BookingService bookingService = new BookingService();
        CancellationService cancelService = new CancellationService();
        BookingHistory history = new BookingHistory();

        // ================= UC11 THREAD PROCESSING =================

        System.out.println("\n===== UC11: Concurrent Booking Execution =====");

        Runnable processor = new ConcurrentBookingProcessor(
                queue,
                bookingService,
                inventory,
                cancelService,
                history
        );

        Thread t1 = new Thread(processor, "Guest-Thread-1");
        Thread t2 = new Thread(processor, "Guest-Thread-2");
        Thread t3 = new Thread(processor, "Guest-Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ================= FINAL SYSTEM STATE =================
        System.out.println("\n===== FINAL INVENTORY =====");
        inventory.displayInventory();

        System.out.println("\n===== BOOKING HISTORY =====");
        history.displayHistory();

        System.out.println("\n===== ROLLBACK STACK (UC10 + UC11 SAFE LOG) =====");
        cancelService.displayRollbackStack();
    }
}