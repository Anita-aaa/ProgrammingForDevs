package q1;//1b

import java.util.Arrays;

public class MinimumTimeToBuildEngines1b {

    public static int minTimeToBuildEngines(int[] engines, int k) {
        Arrays.sort(engines);
        reverseArray(engines);

        int totalTime = 0;
        int engineers = 1;

        for (int i = 0; i < engines.length; i++) {
            if (engines.length - i <= engineers) {
                totalTime += engines[i];
            } else {
                // If splitting the engineer is cheaper than the time to build the current engine alone
                if (k < engines[i]) {
                    // Split an engineer
                    engineers *= 2;
                    // Add the split cost
                    totalTime += k;
                } else {
                    // Otherwise, just add the time to build the current engine
                    totalTime += engines[i];
                }
            }
        }
        return totalTime;
    }

    // Helper method to reverse an array
    private static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] engines = {1,2,3};
        int k = 1;
        int minTime = minTimeToBuildEngines(engines, k);
        System.out.println("Minimum time to build all engines: " + minTime);
    }
}