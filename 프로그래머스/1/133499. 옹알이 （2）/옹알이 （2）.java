import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        HashSet<String> set = new HashSet<>(); 
        set.add("aya"); set.add("ye"); set.add("woo"); set.add("ma");
        
        for(int i=0; i<babbling.length; i++) {
            String str = babbling[i];
            
            String past = "";
            String cur = "";
            for(int j=0; j<str.length(); j++) {
                cur += str.charAt(j);
                
                if(!past.equals(cur) && set.contains(cur)) {
                    past = cur;
                    cur = "";
                } else if(past.equals(cur)) {
                    cur = "aaa";
                    break;
                }
            }
            
            if(cur.equals("")) answer++;
        }
        return answer;
    }
}