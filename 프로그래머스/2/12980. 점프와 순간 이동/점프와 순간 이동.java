import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        
        // 그리디 ( Top down, 역방향 )
        // n 부터 시작해서 n/2, n-1 로 줄여나감
        while(n > 0) {
            
            // 홀수
            if( n % 2 == 1) {
                n--;
                ans++;
            }
            
            // 짝수
            if( n % 2 == 0) {
                n /= 2;
            }
            
        }

        return ans;
    }
}