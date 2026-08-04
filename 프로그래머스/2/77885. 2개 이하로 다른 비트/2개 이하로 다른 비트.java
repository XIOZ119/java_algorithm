import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i=0; i<numbers.length; i++) {
            long num = numbers[i];
            
            if(num % 2 == 0) {
                answer[i] = num + 1;
                continue;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            sb.append(Long.toBinaryString(num));
            
            for(int j=sb.length() - 1; j>=0; j--) {
                if(sb.charAt(j) != '0') continue;
                
                sb.setCharAt(j, '1');
                sb.setCharAt(j+1, '0');
                break;
            }
            
            answer[i] = Long.parseLong(sb.toString(), 2);
        }
        
        return answer;
    }
}