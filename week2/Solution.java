import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int count = s.uniquePaths(3, 2);
        System.out.println("62 uniquePaths:" + count);
    }

    //leetcode 62
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    // leetcode 64
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    grid[i][j] = grid[i][j] + grid[i][j + 1];
                } else if (i != m - 1 && j == n - 1) {
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                } else if (i != m - 1 && j != n - 1) {
                    grid[i][j] = Math.min(grid[i + 1][j], grid[i][j + 1]) + grid[i][j];
                }

            }
        }
        return grid[0][0];
    }

    // leetcode 300
    // 最长上升子序列
    // 输入: [10,9,2,5,3,7,101,18]
    // 输出: 4
    // 解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int localMax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    localMax = Math.max(localMax, dp[j]);
                } 
            }
            dp[i] = localMax + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // leetcode 403
    public boolean canCross(int[] stones) {
        int[][] memo = new int[stones.length][stones.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return can_Cross(stones, 0, 0, memo) == 1;
    }

    public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
        if (memo[ind][jumpsize] >= 0) {
            return memo[ind][jumpsize];
        }
        for (int i = ind + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[ind];
            if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                if (can_Cross(stones, i, gap, memo) == 1) {
                    memo[ind][gap] = 1;
                    return 1;
                }
            }
        }
        memo[ind][jumpsize] = (ind == stones.length - 1) ? 1 : 0;
        return memo[ind][jumpsize];
    }

}