import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        int size = targets.length;
        
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        
        int shoot = targets[0][1];
        
        for(int i=1; i<size; i++) {
            int start = targets[i][0];
            int end = targets[i][1];
            
            if(shoot <= start || shoot > end) {
                answer++;
                shoot = targets[i][1];
            }      
        }
        
        return answer;
    }
}