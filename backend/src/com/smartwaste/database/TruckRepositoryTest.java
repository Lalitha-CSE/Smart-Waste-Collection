package com.smartwaste.database;

import com.smartwaste.model.Truck;
import java.util.List;

public class TruckRepositoryTest {

    public static void main(String[] args) {

        System.out.println("Reading trucks from database...");

        TruckRepository repository = new TruckRepository();

        List<Truck> trucks = repository.getAllTrucks();

        for (Truck truck : trucks) {
            System.out.println(
                truck.getTruckId() + " | " +
                truck.getDriver() + " | " +
                truck.getCapacity() + " kg | " +
                truck.getStatus()
            );
        }

        System.out.println("Total trucks: " + trucks.size());
    }
}