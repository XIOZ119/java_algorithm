import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> answer = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        
        for(int i=0; i<arr.length; i++) {
            queue.add(arr[i]);
        }
        
        int past = -1;
        while(!queue.isEmpty()) {
            int poll = queue.poll();
        
            if(past == poll) continue;
            
            past = poll;
            answer.add(poll);
        }
        
        int[] result = new int[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }
}