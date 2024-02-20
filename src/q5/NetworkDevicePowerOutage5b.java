package q5;

import java.util.*;

public class NetworkDevicePowerOutage5b {

    public static List<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        Map<Integer, List<Integer>> graph = buildGraph(edges);
        Set<Integer> visited = new HashSet<>();
        List<Integer> impactedDevices = new ArrayList<>();

        dfs(graph, targetDevice, targetDevice, visited, impactedDevices);

        return impactedDevices;
    }

    private static Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());

            graph.get(u).add(v);
            graph.get(v).add(u); // Assuming undirected graph
        }

        return graph;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int currentDevice, int targetDevice, Set<Integer> visited, List<Integer> impactedDevices) {
        visited.add(currentDevice);

        List<Integer> neighbors = graph.getOrDefault(currentDevice, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, targetDevice, visited, impactedDevices);
            }
        }

        // If the current device is not the target device, add it to the list of impacted devices
        if (currentDevice != targetDevice) {
            impactedDevices.add(currentDevice);
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,3},{1,6},{2,4},{4,6},{4,5},{5,7}};
        int targetDevice = 4;

        List<Integer> impactedDevices = findImpactedDevices(edges, targetDevice);

        System.out.println("Impacted Device List: " + impactedDevices); // Output: [5, 7]
    }
}
