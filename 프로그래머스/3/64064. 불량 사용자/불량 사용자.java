import java.util.*;

class Solution {
    private static String[] user_id;
    private static String[] banned_id;
    private static HashSet<String> set;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        this.user_id = user_id;
        this.banned_id = banned_id;
        set = new HashSet<String>(); 
        
        boolean[] visited = new boolean[user_id.length];
        dfs(0, visited);
        
        answer = set.size();
        
        return answer;
    }
    
    private static void dfs(int index, boolean[] visited) {
        if(index >= banned_id.length) {
            String userList = ""; 
            for(int i=0; i<visited.length; i++) {
                if(!visited[i]) continue;
                String user = user_id[i];
                
                userList += (user + " ");
            }
            
            set.add(userList);
            return;
        }
        
        for(int i=0; i<user_id.length; i++) {
            if(visited[i]) continue;
            if(!match(i, index)) continue; 
            
            visited[i] = true;
            dfs(index + 1, visited);
            visited[i] = false;
        }
    }
    
    private static boolean match(int user, int ban) {
        String users = user_id[user];
        String banned = banned_id[ban];
        
        if(users.length() != banned.length()) return false;
        
        for(int i=0; i<users.length(); i++) {
            if(banned.charAt(i) == '*') continue;
            if(users.charAt(i) != banned.charAt(i)) return false;
        }
        
        return true;
    }
}