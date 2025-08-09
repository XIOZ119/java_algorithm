import java.lang.*;
import java.util.*;
import java.io.*;

class Solution {
    
    static class Words {
        String word;
        int count;
        
        Words(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    
    static boolean[] visited;
    static int l;
    static int answer;
    
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        
        l = words.length;
        visited = new boolean[l];
        
        bfs(begin, target, words);
        
        if(answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
    
    static void bfs(String begin, String target, String[] words) {
        Queue<Words> queue = new LinkedList<>();
        queue.add(new Words(begin, 0));
        
        while(!queue.isEmpty()){
            Words curWords = queue.poll();
            
            String curWord = curWords.word;
            int curCount = curWords.count;
            
            if(curWord.equals(target)) {
                answer = Math.min(answer, curCount);
            }
            
            for(int i=0; i<l; i++){
                if(visited[i]) continue;
                
                String s = words[i];
                int c = 0; 
                for(int j=0; j<s.length(); j++){
                    if(curWord.charAt(j) != s.charAt(j)) c++;
                }
                
                if(c == 1) {
                    visited[i] = true;
                    queue.add(new Words(s, curCount+1));
                }
            }
        }
    }
}