import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>(); 
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            s1.push(c);
        }
        
        while(!s1.isEmpty()) {
            if(s2.isEmpty()) {
                char c = s1.pop();
                s2.push(c);
                continue;
            }
            
            if(s1.peek() == s2.peek()) {
                s1.pop();
                s2.pop();
            } else {
                char c = s1.pop();
                s2.push(c);
            }
        }
        
        if(s2.isEmpty()) answer = 1; 
        return answer;
    }
}