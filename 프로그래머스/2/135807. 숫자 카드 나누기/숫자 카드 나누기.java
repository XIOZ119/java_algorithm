import java.util.*;

class Solution {
    // 최대 공약수
    public int solution(int[] arrayA, int[] arrayB) {
        int a = calculate(arrayA);
        int b = calculate(arrayB);
        
        int answer = Math.max(isDivide(a, arrayB), isDivide(b, arrayA));
        if(answer == -1) answer = 0;
        
        return answer;
    }
    
    static int isDivide(int a, int[] array){
        if(a <= 1) return -1;
        
        boolean flag = false;
        for(int i=0; i<array.length; i++){
            int num = array[i];
            if(num % a == 0) {
                flag = true;
                break;
            }
        }
        
        if(!flag) return a;
        return -1;
    }
    
    static int calculate(int[] array) {
        if(array.length == 1) return array[0];
        
        int a = 0; int b = 0;
        for(int i=0; i<array.length-1; i++) {
            if(i == 0) {
                a = array[i];
                b = array[i+1];
            } else {
                b = array[i+1];
            }
            
            while(b != 0) {
                int c = a % b;
                
                if(c == 0) {
                    a = b;
                    break;
                } else {
                    a = b; 
                    b = c;
                }
            }
        }
        
        return a;
    }
}