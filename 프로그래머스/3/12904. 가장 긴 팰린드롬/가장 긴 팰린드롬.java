import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        int length = s.length();
        
        for(int i=0; i<length; i++) {
            int left = i-1;
            int right = i+1; 
            int cnt = 1;
            
            int left2 = i-1;
            int right2 = i+2;
            int cnt2 = 0;
            if(i < length-1 && s.charAt(i) == s.charAt(i+1)) cnt2 = 2;
            
            while(left2 >= 0 && right2 < length) {
                if(s.charAt(left2) != s.charAt(right2)) break;
                
                left2--;
                right2++;
                cnt2 += 2;
            }
            
            while(left >= 0 && right < length) {
                if(s.charAt(left) != s.charAt(right)) break;
                
                cnt += 2;
                left--;
                right++;
            }
            
            answer = Math.max(answer, cnt);
            answer = Math.max(answer, cnt2);
        }

        return answer;
    }
}