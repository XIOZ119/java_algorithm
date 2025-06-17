import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        
        HashSet<String> set = new HashSet<>(); 
        
        set.add(words[0]);

        for(int i=1; i<words.length; i++){
            
            if(words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)) {
                int num = i % n + 1;
                int cnt = i / n + 1;
                
                answer = new int[] {num, cnt};
                
                break;
            }
            
            // 같은 단어 중복 체크 
            if(!set.contains(words[i])) {
                set.add(words[i]);
            } else {
                int num = i % n + 1;
                int cnt = i / n + 1;
                answer = new int[] {num, cnt};
                
                System.out.println(i);
                
                break;
            }
            
            if(i == words.length-1) {
                answer = new int[] {0, 0};
            }
        }

        return answer;
    }
}