import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        Long l = right - left + 1;
        int length = l.intValue();
        
        int[] answer = new int[length];
        
        Long a = left / n + 1;
        long aa = left % n;
        
        int count = 0;
        for(int i=1; i<=n; i++) {
            if(aa > i-1) continue;
            if(count >= length) break;
            
            if(i < a) answer[count] = a.intValue();
            else answer[count] = i;
            
            count++;
        }
    
        while(count < length) {
            a++;
            
            for(int i=1; i<=n; i++) {
                if(count >= length) break;
                if(i < a) answer[count] = a.intValue();
                else answer[count] = i;

                count++;
            }
        }
        
        return answer;
    }
}