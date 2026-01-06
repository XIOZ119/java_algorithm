import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        int index = 0;
        
        while(sb.length() < t) {
            String str = Integer.toString(num, n).toUpperCase();
            
            for(int i=0; i<str.length(); i++) {
                if(index % m == (p-1)) sb.append(str.charAt(i));
                if(sb.length() >= t) break;
                
                index++;
            }
            num++;
        }

        return sb.toString();
    }
}