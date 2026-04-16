package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class BookingService {
    private Map<String, Set<String>> allocatedRooms;

    public BookingService() {
        allocatedRooms = new HashMap<>();
    }

    public void allocateRoom(RoomInventory inventory, Reservation reservation) {

        String roomType = reservation.getRoomType();

        int available = inventory.getAvailability(roomType);

        if (available <= 0) {
            System.out.println("No rooms available for " + roomType);
            return;
        }
        String roomId = generateRoomId(roomType);

        allocatedRooms.putIfAbsent(roomType, new HashSet<>());
        Set<String> roomSet = allocatedRooms.get(roomType);

        while (roomSet.contains(roomId)) {
            roomId = generateRoomId(roomType);
        }

        roomSet.add(roomId);
        inventory.updateAvailability(roomType, available - 1);

        System.out.println("Booking Confirmed!");
        System.out.println("Guest: " + reservation.getGuestName());
        System.out.println("Room Type: " + roomType);
        System.out.println("Room ID: " + roomId);
        System.out.println("-----------------------------");
    }
    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() + (int)(Math.random() * 1000);
    }
}