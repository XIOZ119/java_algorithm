import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int num = 0;
        
        int point = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        
        while(num < order.length) {
            if(point == order[num]) {
                answer++;
                point++;
            }
            
            else if(point < order[num]) {
                while(point < order[num]) {
                    stack.addLast(point);
                    point++;
                }
                
                answer++;
                point++;
            }
            
            else if(point > order[num]) {
                if(order[num] != stack.peekLast()) break;

                stack.removeLast();
                answer++;
            }
            
            num++;
        }
        
        return answer;
    }
}