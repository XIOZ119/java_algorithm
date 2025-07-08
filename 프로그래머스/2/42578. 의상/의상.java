import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        int size = clothes.length;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<size; i++){
            String type = clothes[i][1];
            
            if(map.containsKey(type)) {
                map.put(type, map.get(type) + 1);
            } else {
                map.put(type, 1);
            }
        }
        
        for(int key: map.values()) {
            answer *= ++key;
        }
        
        answer -= 1;
        
        return answer;
    }
}
// 3 3 3 
// 16*4 = 64-1 = 63
// (a+1)×(b+1)×(c+1)×…−1