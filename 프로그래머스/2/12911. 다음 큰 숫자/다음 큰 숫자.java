import java.util.*;

class Solution {
    public int solution(int n) {
        
        int cnt = Integer.bitCount(n);
        int nCnt = -1;
        int answer = 0;
        
        while(cnt != nCnt) {
            n++;
            answer = n;
            nCnt = Integer.bitCount(n);
        }
        
        return answer;
    }
}