import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>(9);
        
        for(int i=0; i<9; i++) {
            dp.add(new HashSet<>());
        }
        
        for(int i=1; i<9; i++) {
            Set<Integer> cur = dp.get(i);
            
            int concat = 0;
            for(int j=0; j<i; j++) {
                concat = concat * 10 + N;
            }
            
            cur.add(concat);
            
            for(int j=1; j<i; j++) {
                Set<Integer> A = dp.get(j);
                Set<Integer> B = dp.get(i-j);
                
                for(int a: A){
                    for(int b: B){
                        cur.add(a - b);
                        cur.add(a + b);
                        cur.add(a * b);
                        if(b != 0) cur.add(a / b);
                    }
                }
            }
            
            if(cur.contains(number)) return i;
        }
        
        return -1;
    }
}