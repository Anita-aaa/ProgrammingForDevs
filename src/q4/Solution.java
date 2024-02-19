//4a
package q4;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int minMovesToCollectKeys(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int keysCount = 0;
        Set<Character> keys = new HashSet<>();

        // Find the count of keys in the maze
        for (char[] row : grid) {
            for (char cell : row) {
                if (Character.isLowerCase(cell)) {
                    keysCount++;
                    keys.add(cell);
                }
            }
        }

        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0}); // {x, y, keysCollected}
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                int keysCollected = curr[2];

                if (Character.isLowerCase(grid[x][y])) {
                    keysCollected |= (1 << (grid[x][y] - 'a')); // Mark key as collected
                }

                if (keysCollected == ((1 << keysCount) - 1)) {
                    return moves;
                }

                int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != 'W') {
                        if (Character.isUpperCase(grid[nx][ny]) && (keysCollected & (1 << (grid[nx][ny] - 'A'))) == 0) {
                            // Door is locked and key is not collected
                            continue;
                        }
                        String key = nx + "," + ny + "," + keysCollected;
                        if (!visited.contains(key)) {
                            visited.add(key);
                            queue.offer(new int[]{nx, ny, keysCollected});
                        }
                    }
                }
            }
            moves++;
        }

        return -1; // Impossible to collect all keys and reach exit
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = {
                {'S', 'P', 'q', 'P', 'P'},
                {'W', 'W', 'W', 'P', 'W'},
                {'r', 'P', 'Q', 'P', 'R'}
        };
        System.out.println(solution.minMovesToCollectKeys(grid)); // Output: 8
    }
}
