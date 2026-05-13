import java.util.*;

class Solution {
    static ArrayList<int[]>[] list;
    static int n, k;
    static int answer = 0;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        list = new ArrayList[n+1];
        this.n = n; this.k = k;
        
        for(int i=0; i<n+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            int type = edges[i][2];
            
            list[x].add(new int[] {y, type});
            list[y].add(new int[] {x, type});
        }
        
        ArrayList<Integer> l = new ArrayList<>();
        l.add(infection);
        boolean[] visited = new boolean[n+1];
        visited[infection] = true;
        
        dfs(l, visited, 0, 0); 
        
        return answer;
    }
    
    // 감염된 노드 list, 감염 여부 배열, 개방된 파이프, 열고 닫은 횟수
    static void dfs(ArrayList<Integer> nodes, boolean[] visited, int pipe, int cnt) {
        answer = Math.max(answer, nodes.size());
        if(cnt == k) return;
        
        for(int i=1; i<=3; i++) {
            if(pipe == i) continue;
            
            Queue<Integer> que = new LinkedList<>();
            for(int n: nodes) {
                que.add(n);
            }
            
            ArrayList<Integer> check = new ArrayList<>();
            
            while(!que.isEmpty()) {
                int cur = que.poll();
                
                for(int[] next: list[cur]) {
                    int n = next[0];
                    int p = next[1];
                    
                    if(p == i && !visited[n]) {
                        nodes.add(n);
                        visited[n] = true;
                        que.add(n);
                        check.add(n);
                    }
                }
            }
            
            dfs(nodes, visited, i, cnt+1);
            
            for(int j=0; j<check.size(); j++) {
                nodes.remove(nodes.size() - 1);
            }
            
            for(int ch: check) {
                visited[ch] = false;
            }
            
            
        }
    }
}