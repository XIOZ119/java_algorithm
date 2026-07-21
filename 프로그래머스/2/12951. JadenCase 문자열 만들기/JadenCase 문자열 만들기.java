class Solution {
    public String solution(String s) {
        String answer = "";
        boolean flag = true;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == ' ') {
                flag = true; 
                answer += " ";
                continue;
            }
            
            if(flag) {
                answer += Character.toString(c).toUpperCase();
            } else {
                answer += Character.toString(c).toLowerCase();
            }
            
            flag = false;
        }
        
        return answer;
    }
}