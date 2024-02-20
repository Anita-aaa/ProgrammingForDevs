package q2;

import java.util.*;


public class SecretSharing2b {
    public static List<Integer> findKnowsSecret(int n, int[][] intervals, int firstPerson) {
        Set<Integer> knowsSecret = new HashSet<>();
        knowsSecret.add(firstPerson); // Initially, only the first person knows the secret
        for (int[] interval : intervals) {
            for (int i = interval[0]; i <= interval[1]; i++) {
                knowsSecret.add(i); // Add individuals who can receive the secret during each interval
            }
        }
        List<Integer> result = new ArrayList<>(knowsSecret);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 0;
        List<Integer> output = findKnowsSecret(n, intervals, firstPerson);
        System.out.println(output); // Output: [0, 1, 2, 3, 4]
    }
}
