package org.example;

import java.util.Map;

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("===== UC12: Data Persistence & Recovery =====");

        PersistenceService persistenceService = new PersistenceService();

        // ================= LOAD PREVIOUS STATE =================
        Map<String, Object> state = persistenceService.loadState();

        RoomInventory inventory;
        CancellationService cancelService;

        if (state != null) {

            inventory = (RoomInventory) state.get("inventory");
            cancelService = (CancellationService) state.get("bookings");

            System.out.println("Recovered previous system state successfully.");

        } else {

            inventory = new RoomInventory();
            cancelService = new CancellationService();

            System.out.println("No previous state found. Starting fresh system.");
        }

        // ================= BOOKING SERVICE =================
        BookingService bookingService = new BookingService();

        // ================= SAMPLE REQUESTS =================
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("R301", "Alice", "Single"));
        queue.addRequest(new Reservation("R302", "Bob", "Double"));
        queue.addRequest(new Reservation("R303", "Charlie", "Suite"));

        // ================= PROCESS BOOKINGS =================
        Reservation r;

        while ((r = queue.processRequest()) != null) {

            bookingService.allocateRoom(inventory, r, cancelService);
        }

        // ================= SHOW FINAL STATE =================
        inventory.displayInventory();
        cancelService.displayRollbackStack();

        // ================= SAVE STATE BEFORE EXIT =================
        persistenceService.saveState(inventory, cancelService);

        System.out.println("===== UC12 Execution Completed =====");
    }
}