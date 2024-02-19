package q3;

import java.util.*;

class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalAlgorithm3b {
    private int V; // Number of vertices
    private List<Edge> edges;

    public KruskalAlgorithm3b(int vertices) {
        V = vertices;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
    }

    public List<Edge> minimumSpanningTree() {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges); // Sort edges by weight

        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i; // Initialize each vertex as its own parent
        }

        int edgeCount = 0;
        int index = 0;

        while (edgeCount < V - 1 && index < edges.size()) {
            Edge nextEdge = edges.get(index);
            index++;

            int sourceParent = find(parent, nextEdge.source);
            int destinationParent = find(parent, nextEdge.destination);

            if (sourceParent != destinationParent) {
                result.add(nextEdge);
                union(parent, sourceParent, destinationParent);
                edgeCount++;
            }
        }

        return result;
    }

    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]); // Path compression
        }
        return parent[vertex];
    }

    private void union(int[] parent, int x, int y) {
        int xParent = find(parent, x);
        int yParent = find(parent, y);
        parent[yParent] = xParent;
    }

    public static void main(String[] args) {
        int V = 4;
        KruskalAlgorithm3b graph = new KruskalAlgorithm3b(V);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        List<Edge> result = graph.minimumSpanningTree();

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
