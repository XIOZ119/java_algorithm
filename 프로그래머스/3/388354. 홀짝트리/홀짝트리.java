import java.util.*;

class Solution {
    int[] answer = new int[2];
    int[] nodes;
    
    public int[] solution(int[] nodes, int[][] edges) {
        this.nodes = nodes;
        ArrayList<Integer>[] list = new ArrayList[1_000_001];
        
        for(int i=0; i<1_000_001; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            
            list[a].add(b);
            list[b].add(a);
        }
        
        boolean[] visited = new boolean[1_000_001];
        
        for(int i=0; i<nodes.length; i++) {
            if(visited[nodes[i]]) continue;
            
            Queue<Integer> que = new LinkedList<>();
            que.add(nodes[i]);
            visited[nodes[i]] = true;
            
            int same = 0;
            int diff = 0;
            
            while(!que.isEmpty()) {
                int cur = que.poll();
                
                int cnt = list[cur].size();
                
                if(cur % 2 == cnt % 2) same++; 
                else diff++;
                
                for(int next: list[cur]) {
                    if(!visited[next]) {
                        visited[next] = true;
                        que.add(next);
                    } 
                }
            }
            
            if(same == 1) answer[0]++;
            if(diff == 1) answer[1]++;
        }
        
        return answer;
    }
}