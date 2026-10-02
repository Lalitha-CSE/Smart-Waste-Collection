package com.smartwaste.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import com.smartwaste.database.BinRepository;
import com.smartwaste.database.TruckRepository;
import com.smartwaste.algorithm.PriorityQueue;
import com.smartwaste.model.Bin;
import com.smartwaste.model.Truck;
import com.smartwaste.service.RouteService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Simple HTTP API Server using Java's built-in HttpServer.
 *
 * Exposes REST endpoints for bins, trucks, routes, and health checks.
 */
public class ApiServer {

    private static final RouteService routeService = new RouteService();
    private static final BinRepository binRepository = new BinRepository();
    private static final TruckRepository truckRepository = new TruckRepository();

    public static void main(String[] args) throws IOException {
int port = Integer.parseInt(
        System.getenv().getOrDefault("PORT", "8080")
);

HttpServer server =
        HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/api/health", new HealthHandler());
        server.createContext("/api/bins", new BinsHandler());
        server.createContext("/api/trucks", new TrucksHandler());
        server.createContext("/api/route", new RouteHandler());
        server.createContext("/api/priority", new PriorityHandler());
        server.setExecutor(null);
        server.start();

        System.out.println(
        "Smart Waste API Server started on port " + port
   );
    }
static class PriorityHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            sendJsonResponse(
                exchange,
                405,
                "{\"error\":\"Method not allowed\"}"
            );
            return;
        }

        try {

            List<Bin> bins = binRepository.getAllBins();

            PriorityQueue priorityQueue =
                    new PriorityQueue();

            for (Bin bin : bins) {
                priorityQueue.add(bin);
            }

            StringBuilder json =
                    new StringBuilder("[");

            boolean first = true;

            while (!priorityQueue.isEmpty()) {

                Bin bin = priorityQueue.poll();

                if (!first) {
                    json.append(",");
                }

                json.append("{")
                    .append("\"binId\":\"")
                    .append(bin.getBinId())
                    .append("\",")
                    .append("\"location\":\"")
                    .append(bin.getLocation())
                    .append("\",")
                    .append("\"currentFill\":")
                    .append(bin.getCurrentFill())
                    .append(",")
                    .append("\"predictedFill\":")
                    .append(bin.getPredictedFill())
                    .append(",")
                    .append("\"status\":\"")
                    .append(bin.getStatus())
                    .append("\"")
                    .append("}");

                first = false;
            }

            json.append("]");

            sendJsonResponse(
                exchange,
                200,
                json.toString()
            );

        } catch (Exception e) {

            e.printStackTrace();

            sendJsonResponse(
                exchange,
                500,
                "{\"error\":\"Unable to load priority data\"}"
            );
        }
    }
}
    /**
     * Handler for GET /api/health
     */
    static class HealthHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if (!"GET".equals(exchange.getRequestMethod())) {
                sendResponse(
                        exchange,
                        405,
                        "{\"error\":\"Method not allowed\"}"
                );
                return;
            }

            String json = """
                    {
                      "status": "running",
                      "message": "Smart Waste API is working"
                    }
                    """;

            sendJsonResponse(exchange, 200, json);
        }
    }

    /**
     * Handler for GET /api/bins
     */
    static class BinsHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if (!"GET".equals(exchange.getRequestMethod())) {
                sendResponse(
                        exchange,
                        405,
                        "{\"error\":\"Method not allowed\"}"
                );
                return;
            }

            try {

                List<Bin> bins = binRepository.getAllBins();

                String json = binsToJson(bins);

                sendJsonResponse(exchange, 200, json);

            } catch (Exception e) {

                String error =
                        "{\"error\":\"Server error: "
                        + escapeJson(e.getMessage())
                        + "\"}";

                sendJsonResponse(exchange, 500, error);
            }
        }

        private String binsToJson(List<Bin> bins) {

            StringBuilder sb = new StringBuilder();

            sb.append("[");

            for (int i = 0; i < bins.size(); i++) {

                Bin b = bins.get(i);

                if (i > 0) {
                    sb.append(",");
                }

                sb.append("{");

                sb.append("\"binId\":\"")
                        .append(escapeJson(b.getBinId()))
                        .append("\",");

                sb.append("\"location\":\"")
                        .append(escapeJson(b.getLocation()))
                        .append("\",");

                sb.append("\"currentFill\":")
                        .append(b.getCurrentFill())
                        .append(",");

                sb.append("\"predictedFill\":")
                        .append(b.getPredictedFill())
                        .append(",");

                sb.append("\"status\":\"")
                        .append(escapeJson(b.getStatus()))
                        .append("\",");

                sb.append("\"lastCollected\":\"")
                        .append(escapeJson(b.getLastCollected()))
                        .append("\"");

                sb.append("}");
            }

            sb.append("]");

            return sb.toString();
        }
    }

    /**
     * Handler for GET /api/trucks
     */
    static class TrucksHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if (!"GET".equals(exchange.getRequestMethod())) {
                sendResponse(
                        exchange,
                        405,
                        "{\"error\":\"Method not allowed\"}"
                );
                return;
            }

            try {

                List<Truck> trucks =
                        truckRepository.getAllTrucks();

                String json = trucksToJson(trucks);

                sendJsonResponse(exchange, 200, json);

            } catch (Exception e) {

                String error =
                        "{\"error\":\"Server error: "
                        + escapeJson(e.getMessage())
                        + "\"}";

                sendJsonResponse(exchange, 500, error);
            }
        }

        private String trucksToJson(List<Truck> trucks) {

            StringBuilder sb = new StringBuilder();

            sb.append("[");

            for (int i = 0; i < trucks.size(); i++) {

                Truck t = trucks.get(i);

                if (i > 0) {
                    sb.append(",");
                }

                sb.append("{");

                sb.append("\"truckId\":\"")
                        .append(escapeJson(t.getTruckId()))
                        .append("\",");

                sb.append("\"driver\":\"")
                        .append(escapeJson(t.getDriver()))
                        .append("\",");

                sb.append("\"capacity\":")
                        .append(t.getCapacity())
                        .append(",");

                sb.append("\"status\":\"")
                        .append(escapeJson(t.getStatus()))
                        .append("\",");

                sb.append("\"currentRoute\":\"")
                        .append(
                                escapeJson(
                                        t.getCurrentRoute() != null
                                                ? t.getCurrentRoute()
                                                : ""
                                )
                        )
                        .append("\"");

                sb.append("}");
            }

            sb.append("]");

            return sb.toString();
        }
    }

    /**
     * Handler for GET /api/route
     *
     * Example:
     * /api/route?start=Main%20Gate&destination=Block%20B
     */
    static class RouteHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if (!"GET".equals(exchange.getRequestMethod())) {

                sendJsonResponse(
                        exchange,
                        405,
                        "{\"error\":\"Method not allowed\"}"
                );

                return;
            }

            try {

                String query =
                        exchange.getRequestURI().getQuery();

                String start =
                        getQueryParameter(query, "start");

                String destination =
                        getQueryParameter(query, "destination");

                if (start == null || destination == null) {

                    sendJsonResponse(
                            exchange,
                            400,
                            "{\"error\":\"Start and destination are required\"}"
                    );

                    return;
                }

                List<String> route =
                        routeService.findRoute(
                                start,
                                destination
                        );

                String json = routeToJson(route);

                sendJsonResponse(exchange, 200, json);

            } catch (Exception e) {

                String error =
                        "{\"error\":\""
                        + escapeJson(e.getMessage())
                        + "\"}";

                sendJsonResponse(exchange, 500, error);
            }
        }

        private String getQueryParameter(
                String query,
                String parameter
        ) {

            if (query == null) {
                return null;
            }

            String[] pairs = query.split("&");

            for (String pair : pairs) {

                String[] keyValue =
                        pair.split("=", 2);

                if (keyValue.length == 2
                        && keyValue[0].equals(parameter)) {

                    return URLDecoder.decode(
                            keyValue[1],
                            StandardCharsets.UTF_8
                    );
                }
            }

            return null;
        }

        private String routeToJson(List<String> route) {

            StringBuilder sb = new StringBuilder();

            sb.append("[");

            for (int i = 0; i < route.size(); i++) {

                if (i > 0) {
                    sb.append(",");
                }

                sb.append("\"")
                        .append(
                                escapeJson(route.get(i))
                        )
                        .append("\"");
            }

            sb.append("]");

            return sb.toString();
        }
    }

    /**
     * Send JSON response with CORS headers.
     */
    private static void sendJsonResponse(
            HttpExchange exchange,
            int statusCode,
            String json
    ) throws IOException {

        exchange.getResponseHeaders().set(
                "Content-Type",
                "application/json"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Origin",
                "*"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Methods",
                "GET, POST, OPTIONS"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Headers",
                "Content-Type"
        );

        sendResponse(exchange, statusCode, json);
    }

    /**
     * Send HTTP response.
     */
    private static void sendResponse(
            HttpExchange exchange,
            int statusCode,
            String response
    ) throws IOException {

        byte[] bytes =
                response.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(
                statusCode,
                bytes.length
        );

        try (OutputStream os =
                     exchange.getResponseBody()) {

            os.write(bytes);
        }
    }

    /**
     * Escape special JSON characters.
     */
    private static String escapeJson(String str) {

        if (str == null) {
            return "";
        }

        return str
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}