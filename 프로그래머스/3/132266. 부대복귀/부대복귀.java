import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<Integer>[] arr = new ArrayList[n+1];
        
        for(int i=0; i<n+1; i++) {
            arr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            
            arr[a].add(b);
            arr[b].add(a);
        }
        
        for(int i=0; i<sources.length; i++) {
            int s = sources[i];
            
            answer[i] = bfs(arr, s, destination);
        }
        
        
        return answer;
    }
    
    static int bfs(List<Integer>[] arr, int s, int destination){
        Queue<int []> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        
        queue.add(new int[] {s, 0});
        visited[s] = true;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            
            int cs = cur[0];
            int count = cur[1];
            
            if(cs == destination) return count;
            
            for(int next: arr[cs]){
                if(visited[next]) continue;
                
                visited[next] = true;
                queue.add(new int[] {next, count+1});
            }
        }
        
        return -1;
    }
}