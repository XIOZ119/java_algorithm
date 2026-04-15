import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        
        int[] answer = new int[prices.length];
        for(int i=0; i<prices.length; i++) {
            if(stack.isEmpty()) {
                stack.push(i);
                continue;
            }    
            
            int index = stack.peek();
            while(prices[index] > prices[i]){
                index = stack.pop();
                answer[index] = i - index;
                
                if(stack.isEmpty()) break;
                index = stack.peek();
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = prices.length - 1 - index;
        }
        
        return answer;
    }
}