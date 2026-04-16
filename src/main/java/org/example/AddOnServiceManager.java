package org.example;

import java.util.*;
public class AddOnServiceManager {
    private Map<String, List<Service>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }
    public void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());

        serviceMap.get(reservationId).add(service);

        System.out.println(service.getServiceName() + " added to Reservation " + reservationId);
    }

    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
    public void displayServices(String reservationId) {

        System.out.println("\nAdd-On Services for Reservation: " + reservationId);

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (Service s : services) {
            System.out.println("- " + s.getServiceName() + " (₹" + s.getCost() + ")");
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}