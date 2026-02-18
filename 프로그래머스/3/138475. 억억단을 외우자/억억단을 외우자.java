import java.util.*;
/* 
 * 1...e 전체에 대해 약수 개수 테이블을 한 번에 만들어두기 
 */

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] divCnt = new int[e+1];
        
        for(int i=1; i<=e; i++) {
            for(int j=i; j<=e; j+=i) {
                divCnt[j]++;
            }
        }
        
        // best[x] = 구간 [x...e] 에서 약수 개수 최대인 수 (동률이면 더 작은 수) 미리 만들어놓기
        int[] best = new int[e+1];
        best[e] = e;
        for(int i=e-1; i>0; i--) {
            if(divCnt[best[i+1]] > divCnt[i]) {
                best[i] = best[i+1];
            }
            else if(divCnt[best[i+1]] <= divCnt[i]) {
                best[i] = i;
            }
        }
        
        int[] answer = new int[starts.length];
        for(int i=0; i<starts.length; i++) {
            int s = starts[i];
            answer[i] = best[s];
        }
        
        return answer;
    }
}