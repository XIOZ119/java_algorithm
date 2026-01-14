import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int length = topping.length;
        
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        
        for(int i=0; i<length; i++) {
            right.put(topping[i],  right.getOrDefault(topping[i], 0)+1);
        }
        
        int answer = 0;
        
        for(int i=0; i<length; i++) {
            int topp = topping[i];
            
            left.put(topp, left.getOrDefault(topp, 0)+1);
            right.put(topp, right.get(topp)-1);
            
            if(right.get(topp) == 0) right.remove(topp);
            
            if(left.size() == right.size()) answer++;
        }
        
        return answer;
    }
}