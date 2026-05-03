class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        
        for(int i=0; i<puddles.length; i++) {
            int x = puddles[i][0] - 1;
            int y = puddles[i][1] - 1;
            
            dp[y][x] = -1;
        }
        
        for(int i=1; i<m; i++) {
            if(dp[0][i] == -1 || dp[0][i-1] == -1) {
                dp[0][i] = -1;
                continue;
            }
            
            dp[0][i] = dp[0][i-1];
        }
        
        for(int i=1; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(dp[i][j] == -1) continue;
                if(j == 0){
                    if(dp[i-1][j] == -1) {
                        dp[i][j] = -1;
                        continue;
                    }
                    
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    if(dp[i-1][j] == -1 && dp[i][j-1] == -1) {
                        dp[i][j] = -1; 
                        continue;
                    } 
                    else if(dp[i-1][j] == -1) {
                        dp[i][j] = dp[i][j-1];
                    } 
                    else if(dp[i][j-1] == -1) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
                    }
                }
            }
        }
        
        if(dp[n-1][m-1] == -1) dp[n-1][m-1] = 0; 
        
        return dp[n-1][m-1] % 1_000_000_007;
    }
}