import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long left = 1; 
        Arrays.sort(times);
        long right = (long) times[times.length-1] * n;
        long answer = right;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            
            long cnt = 0;
            for(int i=0; i<times.length; i++) {
                cnt += mid / times[i];
                if(cnt >= n) break; // 오버플로우 방지 
            }
            
            if(cnt >= n) { // 예상 결과보다 클 경우 범위 낮추기
                answer = mid;
                right = mid - 1;
            } else { // 예상 결과보다 작을 경우 범위 높이기
                left = mid + 1;
            }
        }
        
        return answer;
    }
}


// 1 ~ 60 -> 30 : 4 + 3 => 7 
// 1 ~ 30 -> 15 : 2 + 3 => 5 
// 15 ~ 30 -> 22 : 3 + 2 => 5
// 22 ~ 30 -> 28 : 4 + 2 => 6