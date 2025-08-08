import java.util.*;
import java.io.*;

class Solution {
    static List<Integer>[] graph;
    static int answer;
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        answer = 0;
        
        graph = new ArrayList[n];
        
        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i != j && computers[i][j] == 1) {
                    graph[i].add(j);
                }
            }
        }
        
        visited = new boolean[n];
        
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            
            visited[i] = true;
            
            dfs(i);
            answer++;
        }
        
        return answer;
    }
    
    static void dfs(int start){
        for(int g: graph[start]){
            if(visited[g]) continue;
            
            visited[g] = true;
            dfs(g);
        }
    }
    
}