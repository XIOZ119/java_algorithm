import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        ArrayList<int[]>[] list = new ArrayList[n];
        for(int i=0; i<n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] cost: costs) {
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];
            
            list[a].add(new int[]{b, c});
            list[b].add(new int[]{a, c});
        }
        
        boolean[] visited = new boolean[n];
                
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        
        pq.add(new int[] {0, 0});
        
        int answer = 0;
        int count = 0;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            
            if(visited[node]) continue;
            
            visited[node] = true;
            answer += cost;
            count++;
            
            if(count == n) break;
            
            for(int[] next: list[node]) {
                int nx = next[0];
                int nd = next[1];
                
                if(visited[nx]) continue;
                
                pq.add(next);
            }
        }
        
        return answer;
    }
}