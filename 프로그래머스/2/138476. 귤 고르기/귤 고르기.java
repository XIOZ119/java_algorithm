import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        int[] list = new int[10000001];
        
        for(int i=0; i<tangerine.length; i++){
            int num = tangerine[i];
            list[num]++;
        }
        
        Arrays.sort(list);
        
        int size = 0;
        
        for(int i=list.length-1; i>=0; i--){
            size += list[i];
            answer++;
            
            if(size >= k){
                return answer;
            }
        }
        return 0;
    }
}