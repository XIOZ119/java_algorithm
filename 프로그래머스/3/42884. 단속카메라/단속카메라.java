import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        int size = routes.length;
        
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        int loc = routes[0][1];
        for(int i=1; i<size; i++) {
            int start = routes[i][0];
            int end = routes[i][1];
            
            if(start > loc || end < loc) {
                answer++;
                loc = end;
            }
        }      
        
        return answer;
    }
}