package org.example;

public class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue queue;
    private BookingService bookingService;
    private RoomInventory inventory;
    private CancellationService cancelService;
    private BookingHistory history;

    public ConcurrentBookingProcessor(BookingRequestQueue queue,
                                      BookingService bookingService,
                                      RoomInventory inventory,
                                      CancellationService cancelService,
                                      BookingHistory history) {

        this.queue = queue;
        this.bookingService = bookingService;
        this.inventory = inventory;
        this.cancelService = cancelService;
        this.history = history;
    }

    @Override
    public void run() {

        Reservation r;

        while ((r = queue.processRequest()) != null) {
            synchronized (inventory) {

                bookingService.allocateRoom(inventory, r, cancelService);

                if (cancelService.hasReservation(r.getReservationId())) {
                    history.addReservation(r);
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}