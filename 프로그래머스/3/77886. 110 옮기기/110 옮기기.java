import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for(int i=0; i<s.length; i++) {
            String str = s[i];
            char[] temp = new char[str.length()];
	        int idx = 0;
            int count = 0;
                
            for(int j=0; j<str.length(); j++) {
                char c = str.charAt(j);
                temp[idx++] = c;
                if(idx >= 3 && (temp[idx-3] == '1' && temp[idx-2] == '1' && temp[idx-1] == '0')) {
                    idx -= 3;
                    count++;
                }
            }
            
            String remain = new String(temp, 0, idx);
            int lastZero = -1;
            for(int j=remain.length()-1; j>=0; j--){
                if(remain.charAt(j) == '0') {
                    lastZero = j;
                    break;
                }
            }
            
            StringBuilder sb = new StringBuilder();
            if(lastZero != -1) {
                sb.append(remain.substring(0, lastZero + 1));
                for(int j=0; j<count; j++){
                    sb.append("110");
                }
                sb.append(remain.substring(lastZero + 1));
            } else {
                for(int j=0; j<count; j++){
                    sb.append("110");
                }
                sb.append(remain);
            }

            answer[i] = sb.toString();
        }
        
        return answer;
    }
}