import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    static List<Integer>[] network;
    static int min;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        min = Integer.MAX_VALUE;
        network = new ArrayList[n+1];
        
        for(int i=0; i<wires.length; i++) {
            visited = new boolean[n+1];
            
            for(int j=0; j<n+1; j++) {
                network[j] = new ArrayList<>();
            }
            
            for(int j=0; j<wires.length; j++) {
                if(i==j) continue;
                network[wires[j][0]].add(wires[j][1]);
                network[wires[j][1]].add(wires[j][0]);
            }
            
            backtrack();
        }

        return min;
    }
    
    static void backtrack(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for(int n: network[cur]){
                if(visited[n]) continue;
                
                visited[n] = true;
                queue.add(n);
            }
        }
        
        int v = 0, nv = 0;
        for(int i=1; i<network.length; i++) {
            if(visited[i]) v++;
            else nv++;
        }
        
        min = Math.min(min, Math.abs(v - nv));
    }
    
}