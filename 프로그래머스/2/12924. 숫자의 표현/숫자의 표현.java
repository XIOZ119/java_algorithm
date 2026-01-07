import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 1;
        
        int[] arr = new int[n+1];
        for(int i=0; i<n+1; i++) {
            arr[i] = i;
        }
        
        int left = 1; 
        int right = 2; 
        int sum = 3;
        
        if(n == 1) return 1;
        
        while(left != n) {
            if(sum < n) {
                right++;
                sum += right;
            } 
            else if(sum > n) {
                sum -= left;
                left++;
            }
            else {
                answer++;
                sum -= left;
                left++;
            }
        }
        
        return answer;
    }
}