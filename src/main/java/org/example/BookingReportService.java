package org.example;

import java.util.List;

public class BookingReportService {

    public void generateReport(BookingHistory history) {

        List<Reservation> bookings = history.getAllBookings();

        System.out.println("\n===== BOOKING REPORT =====");

        System.out.println("Total Bookings: " + bookings.size());

        int single = 0, doubleRoom = 0, suite = 0;

        for (Reservation r : bookings) {
            switch (r.getRoomType()) {
                case "Single":
                    single++;
                    break;
                case "Double":
                    doubleRoom++;
                    break;
                case "Suite":
                    suite++;
                    break;
            }
        }

        System.out.println("Single Rooms Booked: " + single);
        System.out.println("Double Rooms Booked: " + doubleRoom);
        System.out.println("Suite Rooms Booked: " + suite);
    }
}