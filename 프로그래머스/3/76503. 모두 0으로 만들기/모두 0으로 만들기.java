import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        long answer = 0;
        long[] longA = new long[a.length];
        for(int i=0; i<a.length; i++) {
            longA[i] = (long) a[i];
        }
        int[] degree = new int[a.length];
        
        ArrayList<Integer>[] list = new ArrayList[a.length];
        for(int i=0; i<a.length; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] edge: edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
            
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[a.length];
        
        for(int i=0; i<a.length; i++) {
            if(degree[i] != 1) continue;
            
            que.add(i);
        }
        
        while(!que.isEmpty()) {
            int cur = que.poll();
            visited[cur] = true;
            
            for(int next: list[cur]) {
                if(visited[next]) continue;
                
                degree[next]--;
                if(degree[next] == 1) {
                    que.add(next);
                }
                 
                answer += Math.abs(longA[cur]);
                longA[next] += longA[cur];
                longA[cur] = 0;
            }
        }
        
        for(int i=0; i<a.length; i++) {
            if(longA[i] != 0) return -1;
        }
        
        return answer;
    }
}