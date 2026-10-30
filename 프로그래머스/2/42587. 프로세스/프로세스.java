import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> que = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            que.add(new int[] {priorities[i], i});
        }
        
        Arrays.sort(priorities);
        
        int index = priorities.length - 1;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            
            int priority = cur[0];
            int loc = cur[1]; 
            
            if(priorities[index] == priority) {
                if(loc == location) {
                    return priorities.length - index;
                }
                index--;
            } else {
                que.add(cur);
            }
        }
        
        return priorities.length - 1;
    }
}