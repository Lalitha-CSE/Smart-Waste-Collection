package com.smartwaste.database;

import com.smartwaste.model.Bin;
import java.util.List;

public class BinRepositoryTest {

    public static void main(String[] args) {

        System.out.println("Reading bins from database...");

        BinRepository repository = new BinRepository();

        List<Bin> bins = repository.getAllBins();

        for (Bin bin : bins) {
            System.out.println(
                bin.getBinId() + " | " +
                bin.getLocation() + " | " +
                bin.getCurrentFill() + "% | " +
                bin.getStatus()
            );
        }

        System.out.println("Total bins: " + bins.size());
    }
}