package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CancellationService implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, String> reservationToRoomMap = new HashMap<>();
    private Stack<String> releasedRooms = new Stack<>();

    public void recordAllocation(String reservationId, String roomId) {
        reservationToRoomMap.put(reservationId, roomId);
    }

    public boolean hasReservation(String reservationId) {
        return reservationToRoomMap.containsKey(reservationId);
    }

    public String getRoomId(String reservationId) {
        return reservationToRoomMap.get(reservationId);
    }

    public void updateAllocation(String reservationId, String newRoomId) {
        reservationToRoomMap.put(reservationId, newRoomId);
    }

    public void cancelBooking(Reservation reservation, RoomInventory inventory) {

        String resId = reservation.getReservationId();

        if (!reservationToRoomMap.containsKey(resId)) {
            System.out.println("Cancellation failed");
            return;
        }

        String roomId = reservationToRoomMap.get(resId);
        releasedRooms.push(roomId);

        String roomType = reservation.getRoomType();
        int current = inventory.getAvailability(roomType);
        inventory.updateAvailability(roomType, current + 1);

        reservationToRoomMap.remove(resId);

        System.out.println("Cancelled: " + roomId);
    }

    public void displayRollbackStack() {
        System.out.println(releasedRooms);
    }
}