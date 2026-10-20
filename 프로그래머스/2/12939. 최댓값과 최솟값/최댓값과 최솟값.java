import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] arr = s.split(" ");
        int[] newArr = new int[arr.length];
        
        for(int i=0; i<arr.length; i++) {
            newArr[i] = Integer.parseInt(arr[i]);
        }
        
        Arrays.sort(newArr);
        
        answer = newArr[0] + " " + newArr[newArr.length - 1];
        
        return answer;
    }
}