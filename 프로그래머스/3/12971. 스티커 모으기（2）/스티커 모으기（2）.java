import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int l = sticker.length;
        
        // o: 0, x: 1
        int[][] dp1 = new int[l][2];
        int[][] dp2 = new int[l][2];
        
        if(l == 1) return sticker[0];

        for(int i=0; i<l; i++) {
            if(i == 0) {
                dp1[i][0] = sticker[i];
                continue;
            }
            
            dp1[i][0] = dp1[i-1][1] + sticker[i];
            dp1[i][1] = Math.max(dp1[i-1][0], dp1[i-1][1]);
            
            dp2[i][0] = dp2[i-1][1] + sticker[i];
            dp2[i][1] = Math.max(dp2[i-1][0], dp2[i-1][1]);
        }

        return Math.max(dp1[l-1][1], dp2[l-1][0]);
    }
}