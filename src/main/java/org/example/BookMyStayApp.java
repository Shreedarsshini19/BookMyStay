package org.example;

public class BookMyStayApp {
    public static void main(String[] args) {
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();
        RoomInventory inventory = new RoomInventory();
        singleRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Single"));

        doubleRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Double"));

        suiteRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Suite"));

        inventory.displayInventory();
    }
}