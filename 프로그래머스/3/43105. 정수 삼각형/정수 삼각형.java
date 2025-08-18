import java.io.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int triangleHeight = triangle.length;
        int triangleSize = triangle[triangleHeight - 1].length;
        
        int[][] dp = new int[triangleHeight][triangleSize];
        
        dp[0][0] = triangle[0][0];
        
        for(int i=1; i<triangleHeight; i++) {
            for(int j=0; j<triangle[i].length; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }
                else if(j == triangle[i].length - 1){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j] + triangle[i][j], dp[i-1][j-1] + triangle[i][j]);
                                        
                }
            }
        }
        
        for(int i=0; i<dp[triangleHeight-1].length; i++) {
            answer = Math.max(answer, dp[triangleHeight-1][i]);
        }
        
        return answer;
    }
}