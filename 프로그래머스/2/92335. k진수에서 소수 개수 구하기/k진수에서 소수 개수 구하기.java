import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String a = Integer.toString(n, k);
        
        boolean flag = false;
        String num = "";
        for(int i=0; i<a.length(); i++) {
            if(a.charAt(i) != '0') num += a.charAt(i);
            
            if(flag && a.charAt(i) == '0') {
                if(isPrime(Long.parseLong(num))) answer++;
                flag = false;
                num = "";
            }
            else if(!flag && a.charAt(i) != '0') {
                flag = true;
            }
        }
        if(flag == true && isPrime(Long.parseLong(num))) answer++; 
        
        return answer;
    }
    
    static private boolean isPrime(long n) {
        if (n <= 1) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }   
}