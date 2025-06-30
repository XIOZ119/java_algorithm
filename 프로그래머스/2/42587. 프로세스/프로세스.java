import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        
        int length = priorities.length - 1;
        
        for(int i=0; i<=length; i++){
            queue.add(new int[]{priorities[i], i});
        }
        
        Arrays.sort(priorities);
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            int cp = cur[0];
            int ci = cur[1];
            
            if(cp != priorities[length-answer]) 
                queue.add(new int[]{cp, ci});
            
            if(cp == priorities[length-answer]){
                answer++;
                
                if(ci == location) return answer;
            }
        }
       
        return answer;
    }
}

