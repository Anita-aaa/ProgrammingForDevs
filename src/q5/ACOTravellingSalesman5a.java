package q5;

import java.util.List;

public class ACOTravellingSalesman5a {
    public static void main(String[] args) {
        int numCities = 5;
        double[][] distanceMatrix = {
                {0, 2, 4, 5, 1},
                {2, 0, 6, 3, 1},
                {4, 6, 0, 7, 3},
                {5, 3, 7, 0, 2},
                {1, 1, 3, 2, 0}
        };

        int numAnts = 10;
        double alpha = 1.0;
        double beta = 5.0;
        double evaporationRate = 0.1;
        double initialPheromone = 1.0;

        AntColony antColony = new AntColony(numAnts, distanceMatrix, alpha, beta, evaporationRate, initialPheromone);

        List<Integer> bestTour = antColony.findBestTour(100);

        System.out.println("Best Tour: " + bestTour);
    }
}
