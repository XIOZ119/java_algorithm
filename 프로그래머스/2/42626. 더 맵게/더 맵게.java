import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        for(int s: scoville) {
            pq.add((long) s);
        }
        
        while(pq.peek() < K) {
            if(pq.size() < 2) return -1;
            
            answer++;
            long one = pq.poll();
            long two = pq.poll();
            
            pq.add(one + (two * 2));
        }
        
        return answer;
    }
}