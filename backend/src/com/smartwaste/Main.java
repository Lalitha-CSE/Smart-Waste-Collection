package com.smartwaste;

import com.smartwaste.service.RouteService;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RouteService routeService = new RouteService();

        String start = "Main Gate";
        String destination = "Block B";

        List<String> route =
                routeService.findRoute(start, destination);

        System.out.println("Optimal Route:");
        System.out.println(String.join(" -> ", route));
    }
}