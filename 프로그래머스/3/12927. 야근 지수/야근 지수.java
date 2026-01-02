import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        Arrays.sort(works);
        
        int cnt = 1;
        int max = -1;
        
        for(int i=works.length-1; i > 0; i--) {
            int cur = works[i];
            int next = works[i-1];
            int minus = cur - next;
            
            if(n < minus * cnt) break;
            
            n -= minus * cnt;
            cnt++;
            max = next;
        }
        
        if(max != -1) {
            for(int i=0; i<works.length; i++) {
                if(works[i] <= max) continue;
                works[i] = max;
            }
        } 

        if(n != 0) {
            int more = n / cnt;
            int left = n % cnt;

            for(int i=works.length-1; i > works.length-1-cnt; i--) {
                works[i] -= more;
                if(works[i] < 0) works[i] = 0;

                if(left > 0 && works[i] > 0) {
                    works[i]--;
                    left--;
                }
            }
        } 

        long answer = 0;
        for(int i=0; i<works.length; i++) {
            answer += (long)works[i] * (long)works[i];
        }
        
        return answer;
    }
}