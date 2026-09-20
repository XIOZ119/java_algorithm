import java.util.*;

class Solution {
    static ArrayList<Integer>[] tree; 
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        tree = new ArrayList[n+1];
        for(int i=0; i<n+1; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for(int[] wire: wires) {
            int a = wire[0]; 
            int b = wire[1]; 
            
            tree[a].add(b); 
            tree[b].add(a);
        }
        
        for(int[] wire: wires) {
            visited = new boolean[n+1];
            
            int a = wire[0];
            int b = wire[1];
            
            visited[a] = true;
            visited[b] = true;
            
            Queue<Integer> que = new LinkedList<>();
            que.add(a);
            
            int cnt = 1;
            while(!que.isEmpty()) {
                int cur = que.poll();
                
                for(int next: tree[cur]) {
                    if(visited[next]) continue;
                    
                    visited[next] = true;
                    que.add(next);
                    cnt++;
                }   
            }
            
            answer = Math.min(answer, Math.abs(cnt - (n - cnt)));
        }
        
        return answer;
    }
}