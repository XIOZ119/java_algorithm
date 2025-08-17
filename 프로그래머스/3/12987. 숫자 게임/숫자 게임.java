import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int length = A.length;
        
        int now = length-1;
        for(int i=length-1; i>=0; i--) {
            if(A[i] < B[now]) {
                answer++;
                now--;
            }
        }
        
        return answer;
    }
}