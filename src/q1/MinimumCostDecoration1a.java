package q1;

public class MinimumCostDecoration1a {
    public static int minCostToDecorate(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;

        int n = costs.length;
        int k = costs[0].length;

        // Initialize the DP table to store the minimum cost
        int[][] dp = new int[n][k];

        // Base case: Initialize the first row with the costs of decorating the first venue
        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }

        // Iterate through each venue
        for (int i = 1; i < n; i++) {
            // Iterate through each theme for the current venue
            for (int j = 0; j < k; j++) {
                // Calculate the minimum cost of decorating the current venue with the current theme
                dp[i][j] = Integer.MAX_VALUE;
                for (int prevTheme = 0; prevTheme < k; prevTheme++) {
                    if (prevTheme != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][prevTheme] + costs[i][j]);
                    }
                }
            }
        }

        // Find the minimum cost from the last row
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            minCost = Math.min(minCost, dp[n - 1][j]);
        }

        return minCost;
    }

    public static void main(String[] args) {
        int[][] costs = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}};
        System.out.println(minCostToDecorate(costs)); // Output: 7
    }
}
