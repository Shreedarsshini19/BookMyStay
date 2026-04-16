package org.example;

public class BookMyStayApp {
    public static void main(String[] args) {
System.out.println("=====Book My Stay App v4.0=====");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        RoomSearchService searchService = new RoomSearchService();

        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}