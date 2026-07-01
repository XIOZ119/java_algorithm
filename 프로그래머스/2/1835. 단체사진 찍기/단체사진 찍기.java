import java.util.*;

class Solution {
    static boolean[] visited;
    static char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int answer;
    static String[] data;
    
    public int solution(int n, String[] data) {
        answer = 0;
        this.data = data; 
        visited = new boolean[8];
        
        dfs(new ArrayList<Integer>());
        
        return answer;
    }
    
    private static boolean check(ArrayList<Integer> list) {
        for(int i=0; i<data.length; i++) {
            char c1 = data[i].charAt(0);
            char c2 = data[i].charAt(2);
            char bs = data[i].charAt(3);
            int size = data[i].charAt(4) - '0';
            
            int indexC1 = -1; 
            int indexC2 = -1;
            
            for(int j=0; j<list.size(); j++) {
                if(arr[list.get(j)] == c1) indexC1 = j;
                if(arr[list.get(j)] == c2) indexC2 = j;
            }
            
            int dis = Math.abs(indexC1 - indexC2) - 1;
            
            if(bs == '=' && dis != size) return false;
            if(bs == '>' && dis <= size) return false;
            if(bs == '<' && dis >= size) return false;
        }
        
        return true;
    }
    
    private static void dfs(ArrayList<Integer> list) {
        if(list.size() == arr.length) {
            if(check(list)) answer++;
            
            return;
        }
        
        for(int i=0; i<arr.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            list.add(i);
            dfs(list);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}