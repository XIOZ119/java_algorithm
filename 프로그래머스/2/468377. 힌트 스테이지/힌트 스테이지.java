import java.util.*;

class Solution {
    static int stage;
    static int[][] cost;
    static int[][] hint;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] cost, int[][] hint) {
        this.cost = cost;
        this.hint = hint;
        stage = cost.length;
        
        dfs(0, new boolean[stage-1]);
        
        return answer;
    }
    
    private static void dfs(int start, boolean[] buy) {
        if(start == hint.length) {
            int ans = 0;
            int[] bh = new int[stage];
            
            for(int i=0; i<stage; i++) {
                if(i < stage - 1 && buy[i]) {
                    ans += hint[i][0];
                    
                    int[] bundle = hint[i];
                    for(int j=1; j<bundle.length; j++) {
                        int b = bundle[j] - 1;
                        bh[b] += 1;
                    }
                }
                if(bh[i] > stage - 1) bh[i] = stage - 1;
                ans += cost[i][bh[i]];
            }
            
            answer = Math.min(ans, answer);
            
            return;
        }
        
        for(int i=start; i<hint.length; i++) {
            buy[i] = true; 
            dfs(i+1, buy);
            
            buy[i] = false;
            dfs(i+1, buy);
        }
    }
}