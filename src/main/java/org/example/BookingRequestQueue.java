package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }
    public synchronized Reservation processRequest() {

        if (queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }

    public void displayQueue() {
        System.out.println("\nCurrent Booking Queue:");
        for (Reservation r : queue) {
            System.out.println(r.getReservationId() + " | " +
                    r.getGuestName() + " | " +
                    r.getRoomType());
        }
    }
}