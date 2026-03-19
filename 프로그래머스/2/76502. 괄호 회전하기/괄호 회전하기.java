import java.util.*;

class Solution {
    public int solution(String s) {
        // ( ) [ ] { }
        ArrayList<Integer> arr = new ArrayList<>();
        
        if(s.length() % 2 == 1) return 0;
        
        for(int i=0; i<s.length(); i++) {
            arr.add(toInt(s.charAt(i)));
        }
        
        int answer = 0;
        for(int i=0; i<s.length(); i++){
            Deque<Integer> deq = new LinkedList<>();
            
            for(int j=0; j<s.length(); j++){
                int cur = arr.get(j);
                if(cur % 2 == 0) {
                    deq.add(cur);
                    continue;
                }
                
                if(deq.isEmpty()) break;
                if(deq.peekLast() != cur-1) break;
                
                deq.pollLast();
                if(j == s.length() - 1) answer++;
            }
            
            Integer next = arr.remove(0); 
            arr.add(next);
        }
        
        return answer;
    }
    
    static int toInt(char c) {
        if(c == '(') return 0;
        else if(c == ')') return 1;
        else if(c == '[') return 2;
        else if(c == ']') return 3;
        else if(c == '{') return 4;
        
        return 5;
    }
}