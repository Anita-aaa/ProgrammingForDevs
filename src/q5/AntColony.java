package q5;

import java.util.*;

class AntColony {
    private int numAnts;
    private double[][] distanceMatrix;
    private double[][] pheromoneMatrix;
    private double[][] heuristicMatrix;
    private int numCities;
    private double alpha;
    private double beta;
    private double evaporationRate;
    private double initialPheromone;
    private Random random;

    public AntColony(int numAnts, double[][] distanceMatrix, double alpha, double beta, double evaporationRate, double initialPheromone) {
        this.numAnts = numAnts;
        this.distanceMatrix = distanceMatrix;
        this.numCities = distanceMatrix.length;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.initialPheromone = initialPheromone;

        this.random = new Random();

        initializeHeuristicMatrix();
        initializePheromoneMatrix();
    }

    private void initializeHeuristicMatrix() {
        heuristicMatrix = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i != j) {
                    heuristicMatrix[i][j] = 1.0 / distanceMatrix[i][j];
                } else {
                    heuristicMatrix[i][j] = 0.0;
                }
            }
        }
    }

    private void initializePheromoneMatrix() {
        pheromoneMatrix = new double[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i != j) {
                    pheromoneMatrix[i][j] = initialPheromone;
                } else {
                    pheromoneMatrix[i][j] = 0.0;
                }
            }
        }
    }

    public List<Integer> findBestTour(int maxIterations) {
        List<Integer> bestTour = null;
        double bestTourLength = Double.POSITIVE_INFINITY;

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            List<List<Integer>> antTours = new ArrayList<>();
            double[] tourLengths = new double[numAnts];

            for (int ant = 0; ant < numAnts; ant++) {
                List<Integer> antTour = constructTour();
                antTours.add(antTour);
                tourLengths[ant] = calculateTourLength(antTour);
                if (tourLengths[ant] < bestTourLength) {
                    bestTourLength = tourLengths[ant];
                    bestTour = new ArrayList<>(antTour);
                }
            }

            updatePheromones(antTours, tourLengths);
        }

        return bestTour;
    }

    private List<Integer> constructTour() {
        List<Integer> tour = new ArrayList<>();
        boolean[] visited = new boolean[numCities];
        int startCity = random.nextInt(numCities);

        tour.add(startCity);
        visited[startCity] = true;

        for (int i = 0; i < numCities - 1; i++) {
            int currentCity = tour.get(i);
            int nextCity = selectNextCity(currentCity, visited);
            tour.add(nextCity);
            visited[nextCity] = true;
        }

        return tour;
    }

    private int selectNextCity(int currentCity, boolean[] visited) {
        double[] probabilities = new double[numCities];
        double totalProbability = 0.0;

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                probabilities[i] = Math.pow(pheromoneMatrix[currentCity][i], alpha) * Math.pow(heuristicMatrix[currentCity][i], beta);
                totalProbability += probabilities[i];
            }
        }

        double randomValue = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0.0;

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                cumulativeProbability += probabilities[i];
                if (cumulativeProbability >= randomValue) {
                    return i;
                }
            }
        }

        throw new IllegalStateException("No city was selected.");
    }

    private double calculateTourLength(List<Integer> tour) {
        double tourLength = 0.0;
        for (int i = 0; i < numCities - 1; i++) {
            int city1 = tour.get(i);
            int city2 = tour.get(i + 1);
            tourLength += distanceMatrix[city1][city2];
        }
        tourLength += distanceMatrix[tour.get(numCities - 1)][tour.get(0)]; // Return to the starting city
        return tourLength;
    }

    private void updatePheromones(List<List<Integer>> antTours, double[] tourLengths) {
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i != j) {
                    pheromoneMatrix[i][j] *= (1.0 - evaporationRate); // Evaporation
                    for (int ant = 0; ant < numAnts; ant++) {
                        if (antTours.get(ant).contains(j)) {
                            pheromoneMatrix[i][j] += (1.0 / tourLengths[ant]);
                        }
                    }
                }
            }
        }
    }
}