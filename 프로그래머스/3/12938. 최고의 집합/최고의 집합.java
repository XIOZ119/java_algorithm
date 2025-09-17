import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] numbers = new int[n];
        
        if(s < n) return new int[] {-1};
        
        int sameNumber = s / n; 
        int remain = s % n; 
        
        Arrays.fill(numbers, sameNumber);
        
        if(remain == 0) return numbers;
        
        for(int i=0; i<n; i++) {
            numbers[i]++;
            remain--;
            
            if(remain==0) break;
        }
        
        Arrays.sort(numbers);
        
        return numbers;
    }
}