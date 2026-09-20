import java.util.*; 

class Solution {
    static ArrayList<Integer>[] graph; 
    static boolean[] visited; 
    
    public int solution(int n, int[][] computers) {
        graph = new ArrayList[n]; 
        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers[i].length; j++) {
                if(i == j) continue; 
                if(computers[i][j] != 1) continue;
                
                graph[i].add(j);
                graph[j].add(i);
            }
        }
        
        int answer = 0;
        
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(visited[i]) continue; 
            
            link(i);
            answer++;
        }
        
        return answer;
    }
    
    private static void link(int a) {
        Queue<Integer> que = new LinkedList<>();
        que.add(a);
        visited[a] = true; 
        
        while(!que.isEmpty()) {
            int cur = que.poll();
            
            for(int next: graph[cur]) {
                if(visited[next]) continue;
                
                que.add(next);
                visited[next] = true;
            }
        }
        
    }
}