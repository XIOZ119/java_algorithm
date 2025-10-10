import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            
            int n = storey % 10;
            int next = (storey / 10) % 10;

            if(n > 5) {
                answer += 10 - n;
                storey += 10;
            } else if (n == 5 && next >= 5) {
                answer += 10 - n;
                storey += 10;
            } else {
                answer += n;
            }
            
            storey /= 10;
        }
        
        return answer;
    }
}