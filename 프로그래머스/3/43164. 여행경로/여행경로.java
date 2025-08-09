import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    static boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        Arrays.sort(tickets, (a, b) -> {
            if (!a[0].equals(b[0])) return a[0].compareTo(b[0]);
            return a[1].compareTo(b[1]);
        });
        
        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs("ICN", tickets, path, 0);

        return path.toArray(new String[0]);
    }
    
    static boolean dfs(String cur, String[][] tickets, List<String> path, int used){
        if(used == tickets.length) return true;
        
        for(int i=0; i<tickets.length; i++) {
            if(visited[i]) continue;
            if(!tickets[i][0].equals(cur)) continue;
            
            path.add(tickets[i][1]);
            visited[i] = true;
            
            if(dfs(tickets[i][1], tickets, path, used+1)){
                return true;
            }
            
            visited[i] = false;
            path.remove(path.size() - 1);
        }
        return false;
    }
}