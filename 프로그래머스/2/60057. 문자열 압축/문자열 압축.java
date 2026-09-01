class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int i=1; i<=s.length() / 2; i++) {
            
            String prev = s.substring(0, i);
            int count = 1; 
            int length = 0;
            
            for(int j=i; j<s.length(); j+=i) {
                
                int end = Math.min(j + i, s.length());
                String cur = s.substring(j, end);
                
                if(prev.equals(cur)) {
                    count++;
                    
                    if(end != s.length()) continue;
                }
                else {
                    if(count == 1) length += i; 
                    else length += (i + String.valueOf(count).length());
                    
                    prev = cur;
                    count = 1;
                    
                    if(end != s.length()) continue;
                }
                
                if(count == 1) length += cur.length(); 
                else length += (cur.length() + String.valueOf(count).length());
            }
            
            answer = Math.min(answer, length);
        }
        
        return answer;
    }

}