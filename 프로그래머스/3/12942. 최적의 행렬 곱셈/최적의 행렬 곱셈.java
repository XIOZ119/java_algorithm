import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length; 
        
        long[][] dp = new long[n][n];
        for(int i=0; i<n; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
        for(int i=0; i<n; i++) dp[i][i] = 0;
        
        // len = 구간 길이 (행렬 길이)
        for(int len=2; len<=n; len++) {
            for(int i=0; i+len-1 < n; i++) {
                int j = i + len - 1;
                
                for(int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k+1][j] + 1L * matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1];
                    if(cost < dp[i][j]) dp[i][j] = cost; 
                }
            } 
        }
        
        return (int) dp[0][n - 1];
    }
}