import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        int[] sum = new int[scores.length];
        boolean[] visited = new boolean[scores.length];
        
        int wanho1 = scores[0][0];
        int wanho2 = scores[0][1];
        
        // 태도 점수 - 내림차순, 동료평가 점수 - 오름차순
        Arrays.sort(scores, (o1, o2) -> {
            if(o1[0] == o2[0]) 
                return o1[1] - o2[1];
            return o2[0] - o1[0];
        });
        
        int maxB = 0; 
        
        for(int i=0; i<scores.length; i++){
            if(maxB < scores[i][1]) maxB = scores[i][1];
            else if(maxB > scores[i][1]) {
                visited[i] = true;
                if(scores[i][0] == wanho1 && scores[i][1] == wanho2) return -1;
            }
        }
        
        for(int i=0; i<scores.length; i++){
            if(visited[i]) continue;
            
            if(scores[i][0] + scores[i][1] > wanho1 + wanho2) answer++;
        }
        
        return answer+1;
    }
}