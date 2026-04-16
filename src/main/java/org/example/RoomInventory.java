package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();

        // default rooms
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 5);
        roomAvailability.put("Suite", 5);
    }

    // ================= EXISTING METHODS =================

    public void addRoomType(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        roomAvailability.put(roomType, newCount);
    }

    // ================= FIX FOR YOUR ERROR =================
    // This is what RoomSearchService is calling

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    // ================= DISPLAY =================

    public void displayInventory() {
        System.out.println("\n===== ROOM INVENTORY =====");

        for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}