import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        
        for(int w: works) {
            pq.add(w);
        }
        
        for(int i=0; i<n; i++) {
            if(pq.isEmpty()) return 0;
            int num = pq.poll() - 1;
            if(num == 0) continue;
            pq.add(num);
        }
        
        if(pq.isEmpty()) return 0;
        
        long answer = 0;
        while(!pq.isEmpty()) {
            int num = pq.poll(); 
            answer += (long) num * num;
        }
        
        return answer;
    }
}