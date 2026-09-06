import java.util.*;

class Solution {
    static int[] info;
    static ArrayList<Integer>[] tree; 
    static int answer = Integer.MIN_VALUE;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        tree = new ArrayList[info.length];
        for(int i=0; i<info.length; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edges.length; i++) {
            int a = edges[i][0]; 
            int b = edges[i][1]; 
            
            tree[a].add(b);
        }
        
        ArrayList<Integer> candidates = new ArrayList<>();
        candidates.add(0);
        
        dfs(0, 0, candidates);
        
        return answer;
    }
    
    static void dfs(int sheep, int wolf, ArrayList<Integer> candidates) {
        answer = Math.max(answer, sheep);

        for(int i=0; i<candidates.size(); i++) { 
            int parent = candidates.get(i);
            
            if(info[parent] == 1 && wolf + 1 >= sheep) continue; 
            
            ArrayList<Integer> next = new ArrayList<>(candidates);

            next.remove(i);

            for(int child: tree[parent]) {
                next.add(child);
            }
            
            if(info[parent] == 0) dfs(sheep+1, wolf, next);
            else dfs(sheep, wolf + 1, next);
        }
    }
}