import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        
        int answer = 0;
        int sum = 0;
        for(int i=0; i<enemy.length; i++) {
            pq.add(enemy[i]);
            sum += enemy[i];
            
            if(sum > n) {
                if(k == 0) break;
                
                k--; 
                sum -= pq.poll();
            }
            
            answer++;
        }
        
        return answer;
    }
}