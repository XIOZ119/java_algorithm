import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int l = numbers.length;
        String[] stringN = new String[l];
        for(int i=0; i<l; i++) {
            stringN[i] = numbers[i] + "";
        }
        
        Arrays.sort(stringN, (a, b) -> (b + a).compareTo(a + b));
        
        StringBuilder sb = new StringBuilder();
        for(String s : stringN) {
            sb.append(s);
        }
        
        if (stringN[0].equals("0")) return "0";
        
        return sb.toString();
    }
}