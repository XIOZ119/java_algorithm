import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        int[][] adj = new int[n+1][n+1];
        
        for(int i=1; i<=n; i++){
            Arrays.fill(adj[i], Integer.MAX_VALUE);
            adj[i][i] = 0;
        }
        
        for(int[] f: fares){
            adj[f[0]][f[1]] = f[2];
            adj[f[1]][f[0]] = f[2];
        }
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                for(int k=1; k<=n; k++){
                    if (adj[j][i] != Integer.MAX_VALUE && adj[i][k] != Integer.MAX_VALUE) {
                        adj[j][k] = Math.min(adj[j][k], adj[j][i] + adj[i][k]);
                    }
                }
            }
        }
        
        int min = adj[s][a] + adj[s][b];
        
        for(int i=1; i<=n; i++){
            min = Math.min(min, adj[s][i] + adj[i][a] + adj[i][b]);
        }
        
        return min;
    }
}