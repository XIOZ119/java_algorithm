import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int h = triangle.length; 
        int w = triangle[h-1].length;
        
        int[][] dp = new int[h][w];
        dp[0][0] = triangle[0][0];
        
        for(int i=1; i<h; i++) {
            for(int j=0; j<i+1; j++) {
                int cnt = triangle[i][j];
                
                if(j == 0) {
                    dp[i][j] = cnt + dp[i-1][j];
                } else if(j == i) {
                    dp[i][j] = cnt + dp[i-1][j-1];
                } else {
                    dp[i][j] = cnt + Math.max(dp[i-1][j], dp[i-1][j-1]);
                }
            }
        }

        int answer = 0;
        for(int i=0; i<w; i++) {
            answer = Math.max(dp[h-1][i], answer);            
        }
        
        return answer;
    }
}