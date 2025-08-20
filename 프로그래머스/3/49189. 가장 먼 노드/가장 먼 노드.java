import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] arr = new ArrayList[n+1];
        
        int[] d = new int[n+1];
        for(int i=0; i<n+1; i++) {
            arr[i] = new ArrayList<>();
            d[i] = Integer.MAX_VALUE;
        }
        
        for(int i=0; i<edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            
            arr[a].add(b);
            arr[b].add(a);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[] {1, 0});
        d[1] = 0;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int c = cur[0];
            int cd = cur[1];
            
            for(int a: arr[c]) {
                if(d[a] <= cd + 1) continue;
                
                d[a] = cd + 1;
                queue.add(new int[]{a, cd + 1});
            }
        }
        
        int max = 0;
        int answer = 0;
        
        for(int i = 1; i<n+1; i++) {
            if(max > d[i]) continue;
            else if(max < d[i]) {
                max = d[i];
                answer = 1;
            }
            else answer++;
        }
        
        return answer;
    }
}