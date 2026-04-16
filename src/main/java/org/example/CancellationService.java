package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CancellationService {

    private Map<String, String> reservationToRoomMap;
    private Stack<String> releasedRooms;

    public CancellationService() {
        reservationToRoomMap = new HashMap<>();
        releasedRooms = new Stack<>();
    }

    public void recordAllocation(String reservationId, String roomId) {
        reservationToRoomMap.put(reservationId, roomId);
    }
    public boolean hasReservation(String reservationId) {
        return reservationToRoomMap.containsKey(reservationId);
    }

    public void cancelBooking(Reservation reservation, RoomInventory inventory) {

        String resId = reservation.getReservationId();

        if (!reservationToRoomMap.containsKey(resId)) {
            System.out.println("Cancellation failed: Reservation not found");
            return;
        }

        String roomId = reservationToRoomMap.get(resId);

        releasedRooms.push(roomId);

        String roomType = reservation.getRoomType();
        int current = inventory.getAvailability(roomType);

        inventory.updateAvailability(roomType, current + 1);

        reservationToRoomMap.remove(resId);

        System.out.println("Booking cancelled for " + reservation.getGuestName()
                + " | Released Room ID: " + roomId);
    }

    public void displayRollbackStack() {
        System.out.println("\nRollback Stack (Released Rooms): " + releasedRooms);
    }
}