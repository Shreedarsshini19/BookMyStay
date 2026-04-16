package org.example;

public class BookingService {

    public void allocateRoom(RoomInventory inventory,
                             Reservation reservation,
                             CancellationService cancelService) {

        try {
            BookingValidator.validate(reservation, inventory);

            String roomType = reservation.getRoomType();
            int current = inventory.getAvailability(roomType);

            String roomId = roomType.substring(0, 1) + current;

            System.out.println("Booking confirmed for " + reservation.getGuestName()
                    + " | Room ID: " + roomId);

            cancelService.recordAllocation(reservation.getReservationId(), roomId);

            inventory.updateAvailability(roomType, current - 1);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}