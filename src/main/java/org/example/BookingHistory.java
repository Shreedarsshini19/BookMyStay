package org.example;

import java.util.ArrayList;
import java.util.List;
public class BookingHistory {

    private List<Reservation> bookingList;

    public BookingHistory() {
        bookingList = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        bookingList.add(reservation);
    }
    public List<Reservation> getAllBookings() {
        return bookingList;
    }
    public void displayHistory() {
        System.out.println("\n===== BOOKING HISTORY =====");

        for (Reservation r : bookingList) {
            r.displayReservation();
        }
    }
}