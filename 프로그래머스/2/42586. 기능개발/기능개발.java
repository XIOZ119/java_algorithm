import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>(); 
        
        int max = 0;
        int cnt = 0; 
        for(int i=0; i<progresses.length; i++) {
            int p = progresses[i];
            int s = speeds[i];
            
            int quotient = (100 - p) % s == 0 ? (100 - p) / s : (100 - p) / s + 1;
            
            if(max < quotient) {
                if(cnt != 0) list.add(cnt);
                
                max = quotient;
                cnt = 1;
                continue;
            } 
            
            cnt++;
        }
        list.add(cnt);
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}