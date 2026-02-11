import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<skill.length(); i++) {
            char c = skill.charAt(i);
            
            map.put(c, i);
        }
        
        int answer = 0;
            
        for(int i=0; i<skill_trees.length; i++){
            boolean[] checked = new boolean[skill.length()];
            String str = skill_trees[i];
            boolean flag = true;
            
            for(int j=0; j<str.length(); j++) {
                char cc = str.charAt(j);
                
                if(map.containsKey(cc)) {
                    int v = map.get(cc);
                    
                    checked[v] = true;
                    
                    for(int k=v-1; k>=0; k--) {
                        if(!checked[k]) {
                            flag = false;
                            break;
                        }
                    }
                }
                if(!flag) break;
            }

            if(flag) answer++;
        }
        
        return answer;
    }
}