import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int l = numbers.length;
        int[] answer = new int[l];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i=0; i<l; i++) {
            if(stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            
            while(true) {
                if(stack.isEmpty()) {
                    stack.push(i);
                    break;
                }
                
                int top = stack.peek(); 
                if(numbers[top] < numbers[i]) {
                    stack.pop();
                    answer[top] = numbers[i];
                } else {
                    stack.push(i);
                    break;
                }
            }
        }
        
        return answer;
    }
}