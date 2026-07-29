import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int[] arr = new int[number.length()];
        
        for(int i=0; i<number.length(); i++) {
            arr[i] = number.charAt(i) - '0';
        }
        
        int length = number.length(); 
        int totalLength = number.length() - k;
        int count = totalLength;
        int index = 0; 
        
        while(count > 0) {
            int leftCnt = length - count;
            
            int bigNum = Integer.MIN_VALUE;
            int bigIndex = 0;
            for(int i=index; i<=leftCnt; i++) {
                if(bigNum < arr[i]) {
                    bigNum = arr[i];
                    bigIndex = i;
                }
            }
            
            sb.append(bigNum); 
            index = bigIndex + 1;
            count--;
        }
        
        return sb.toString();
    }
}