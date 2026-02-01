import java.util.*;

class Solution {
    int[][] dp;
    ArrayList<Integer>[] graph;
    boolean[] visited;
        
    public int solution(int n, int[][] lighthouse) {
        this.graph = new ArrayList[n+1];
        this.dp = new int[n+1][2];
        this.visited = new boolean[n+1];
        
        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int i=0; i<lighthouse.length; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            
            // 양방향 그래프
            graph[a].add(b);
            graph[b].add(a);
        }
        
        visited[1] = true;
        dfs(1);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    void dfs(int n) {
        for(int c: graph[n]){
            if(!visited[c]){
                visited[c] = true;
                dfs(c);
            }
        }
        
        int sum = 0;
        int cSum = 0;
        for(int c: graph[n]){
            cSum += dp[c][1];
            sum += Math.min(dp[c][0], dp[c][1]);
        }
        
        dp[n][0] = cSum;
        dp[n][1] = 1 + sum;
        
        return;
    }
    
}