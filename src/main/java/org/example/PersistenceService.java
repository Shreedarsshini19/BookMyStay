package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PersistenceService {

    private static final String FILE_NAME = "bookmystay_data.dat";
    public void saveState(RoomInventory inventory,
                          CancellationService cancelService) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            Map<String, Object> state = new HashMap<>();

            state.put("inventory", inventory);
            state.put("bookings", cancelService);

            oos.writeObject(state);

            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }
    public Map<String, Object> loadState() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            Object obj = ois.readObject();

            System.out.println("System state restored from file.");

            return (Map<String, Object>) obj;

        } catch (Exception e) {

            System.out.println("No previous state found or corrupted file.");
            return null;
        }
    }
}