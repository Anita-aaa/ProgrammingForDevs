package q1;//1b

import java.util.Arrays;

public class SpaceMission {

    public static int calculateMinimumTime(int[] engines, int splitCost) {
        // Sort the engines by build time
        Arrays.sort(engines);

        int totalTime = 0;
        int numEngineers = 1;

        // Iterate through each engine
        for (int i = 0; i < engines.length; i++) {
            // Check if splitting engineers would be beneficial
            if (i > 0 && engines[i] > splitCost) {
                // If beneficial, split engineers and update total time and engineer count
                totalTime += splitCost;
                numEngineers++;
            }
            // Build the current engine
            totalTime += engines[i];
        }
        // Adjust the total time by subtracting the split cost for each additional engineer
        return totalTime - splitCost * (numEngineers - 1);
    }

    public static void main(String[] args) {
        int[] engines = {3, 4, 5, 2};
        int splitCost = 2;

        // Calculate the minimum time needed to build all engines
        int minimumTime = calculateMinimumTime(engines, splitCost);

        // Print the result
        System.out.println("Minimum time needed to build all engines: " + minimumTime);
    }
}
