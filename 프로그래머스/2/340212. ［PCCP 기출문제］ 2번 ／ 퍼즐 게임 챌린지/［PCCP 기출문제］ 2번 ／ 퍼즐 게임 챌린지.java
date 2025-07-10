import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        
        int l = diffs.length;
        
        int low = 1;
        int high = 0;
        
        for(int i=0; i<diffs.length; i++){
            high = Math.max(high, diffs[i]);
        }
        
        while(low <= high){
            int mid = (low + high) / 2;
            long time = 0;
            
            for(int i=0; i<l; i++) {
                if(diffs[i] <= mid) {
                    time += times[i];
                } else {
                    long diff = diffs[i] - mid;
                    long t = (i == 0) ? times[i] : times[i] + times[i-1];
                    time += (diff*t) + times[i];
                }
            }
            
            if(time > limit) {
                low = mid + 1;
            }
            else {
                answer = Math.min(answer, mid);
                high = mid - 1;
            }
        }
        
        return answer;
    }
}