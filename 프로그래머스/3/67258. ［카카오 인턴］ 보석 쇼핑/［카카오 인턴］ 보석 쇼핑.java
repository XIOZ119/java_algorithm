import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        int size = gems.length;
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<size; i++){
            if(!map.containsKey(gems[i])) {
                map.put(gems[i], 0);
            }
        }
        
        int totalGems = map.size();
        int curGems = 0;
        int length = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        
        while(right < size) {
            String gem = gems[right];
            if(map.get(gem) == 0) curGems++;
            map.put(gem, map.get(gem) + 1);
            
            // 보석 다 모았는지 확인 
            while(curGems == totalGems) {
                int l = right-left+1;

                if(length > l) {
                    length = right-left+1;
                    answer[0] = left;
                    answer[1] = right;
                } 

                map.put(gems[left], map.get(gems[left]) - 1);
                if(map.get(gems[left]) == 0) {
                    curGems--;
                    left++;
                    break;
                }
                left++;
            }
            right++;
        }
        
        answer[0]++;
        answer[1]++;
        
        return answer;
    }
}