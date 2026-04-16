package org.example;

import java.util.LinkedList;
import java.util.Queue;
public class BookingRequestQueue {

    private Queue<Reservation> bookingQueue;

    public BookingRequestQueue() {
        bookingQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        bookingQueue.add(reservation);
        System.out.println("Added booking request for " + reservation.getGuestName());
    }
    public void displayQueue() {
        System.out.println("\n===== BOOKING REQUEST QUEUE =====");

        for (Reservation r : bookingQueue) {
            r.displayReservation();
        }
    }
}