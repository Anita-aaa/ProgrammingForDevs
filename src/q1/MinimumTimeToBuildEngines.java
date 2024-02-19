package q1;//1b
//
//import java.util.Arrays;
//
//public class MinimumTimeToBuildEngines {
//    public static int minTimeToBuildEngines(int[] engines, int splitCost) {
//        // Initialize lower bound and upper bound
//        int minTime = Arrays.stream(engines).max().getAsInt(); // Minimum time to build a single engine
//        int maxTime = Arrays.stream(engines).sum() + splitCost * (engines.length - 1); // Maximum time if each engine is built sequentially
//
//        // Binary search to find the minimum time
//        while (minTime <= maxTime) { // Fixed termination condition
//            int midTime = minTime + (maxTime - minTime) / 2;
//
//            // Simulate the process of building engines within mid time
//            int engineers = 1;
//            int time = 0;
//            for (int engine : engines) {
//                if (engine > midTime) {
//                    // Need to split an engineer
//                    engineers++;
//                    time = 0;
//                }
//                time += engine;
//                if (time > midTime) {
//                    // Need to split an engineer
//                    engineers++;
//                    time = engine;
//                }
//            }
//
//            if (engineers <= 2) {
//                // All engines can be built within mid time
//                maxTime = midTime - 1;
//            } else {
//                // Not all engines can be built within mid time
//                minTime = midTime + 1;
//            }
//        }
//
//        return minTime;
//    }
//
//    public static void main(String[] args) {
//        int[] engines = {3, 4, 5, 2};
//        int splitCost = 2;
//        System.out.println(minTimeToBuildEngines(engines, splitCost)); // Output: 4
//    }
//}

import java.util.Arrays;

public class MinimumTimeToBuildEngines {
    public int minTimeToBuildEngines(int[] engines, int splitCost) {
        Arrays.sort(engines);
        int time = 0;
        int engineers = 1;

        for (int i = 0; i < engines.length; i++) {
            if (engineers < engines.length - i) {
                time += splitCost; // Splitting one engineer into two
                engineers *= 2; // Each engineer can build one engine
            }
            time += engines[i];
        }

        return time;
    }

    public static void main(String[] args) {
        MinimumTimeToBuildEngines solution = new MinimumTimeToBuildEngines();
        int[] engines = {1, 2, 3};
        int splitCost = 1;
        int minTime = solution.minTimeToBuildEngines(engines, splitCost);
        System.out.println("Minimum time to build all engines: " + minTime);
    }
}


