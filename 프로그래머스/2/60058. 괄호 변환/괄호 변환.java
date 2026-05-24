import java.util.*;

class Solution {
    public String solution(String p) {
        boolean flag = true;
        
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<p.length(); i++) {
            char c = p.charAt(i);
            
            if(c == ')') {
                if(stack.isEmpty()) {
                    flag = false;
                    break;
                }
                
                stack.pop();
            } else stack.push(c);
        }

        if(flag) return p; 
        
        return convert(p);
    }
    
    String convert(String w) {
        if (w.equals("")) return "";

        int count = 0;
        String u = "";
        String v = "";

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') count++;
            else count--;

            if (count == 0) {
                u = w.substring(0, i + 1);
                v = w.substring(i + 1);
                break;
            }
        }

        if (isCorrect(u)) {
            return u + convert(v);
        } else {
            String temp = "(";
            temp += convert(v);
            temp += ")";
            temp += reverse(u.substring(1, u.length() - 1));
            return temp;
        }
    }
    
    boolean isCorrect(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
    
    String reverse(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') sb.append(')');
            else sb.append('(');
        }

        return sb.toString();
    }
}