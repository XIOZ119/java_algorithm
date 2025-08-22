import java.util.*;

class Solution {
    public long solution(int[] weights) {
        HashMap<Long, Long> map = new HashMap<>();
        long answer = 0;

        for(int i=0; i<weights.length; i++) {
            long a = weights[i];
            
            // 1:1
            answer += map.getOrDefault(a, 0L);
            
            // 1:2
            if(a % 2 == 0) answer += map.getOrDefault(a / 2, 0L);
            
            // 2:3
            if(a % 3 == 0) answer += map.getOrDefault((a * 2) / 3, 0L);
            
            // 3:4
            if(a % 4 == 0) answer += map.getOrDefault((a * 3) / 4, 0L);
            
            answer += map.getOrDefault(a * 2, 0L);
            if((a*3) % 2 == 0) answer += map.getOrDefault((a * 3) / 2, 0L);
            if((a*4) % 3 == 0) answer += map.getOrDefault((a * 4) / 3, 0L);
            
            map.put(a, map.getOrDefault(a, 0L) + 1);
        }   
        
        return answer;
    }
}