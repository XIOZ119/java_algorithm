import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        boolean flag = false;
        for(String str: words) {
            if(target.equals(str)) flag = true;
        }
        if(!flag) return 0;
        
        dfs(words, begin, new boolean[words.length], 0, target);
        
        return answer;
    }
    
    public void dfs(String[] words, String begin, boolean[] visited, int cnt, String target) {
        for(int i=0; i<words.length; i++) {
            if(begin.equals(target)) {
                answer = Math.min(answer, cnt);
                return;
            }
            
            if(visited[i] || begin.equals(words[i])) continue;
            
            int wrongCnt = 0; 
            for(int j=0; j<begin.length(); j++) {
                if(begin.charAt(j) != words[i].charAt(j)) wrongCnt++;
            }
            
            if(wrongCnt > 1) continue;
            
            visited[i] = true;
            dfs(words, words[i], visited, cnt+1, target);
            visited[i] = false; 
        }
    }
}