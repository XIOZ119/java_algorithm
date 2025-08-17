import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        List<Integer>[] arr = new ArrayList[n+1];
        List<Integer>[] reverseArr = new ArrayList[n+1];
        
        for(int i=0; i<n+1; i++) {
            arr[i] = new ArrayList<>();
            reverseArr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];
            
            arr[a].add(b);
            reverseArr[b].add(a);
        }
        
        for(int i=1; i<n+1; i++){
            if(check(arr, reverseArr, n, i)) answer++;
        }
        
        return answer;
    }
    
    static boolean check(List<Integer>[] arr, List<Integer>[] reverseArr, int n, int start){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start);
        visited[start] = true;
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            
            for(int next: arr[cur]){
                if(visited[next]) continue;
                
                visited[next] = true;
                queue.add(next);
            } 
        }
        
        queue.add(start);
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            
            for(int next: reverseArr[cur]){
                if(visited[next]) continue;
                
                visited[next] = true;
                queue.add(next);
            } 
        }
        
        for(int i=1; i<n+1; i++) {
            if(!visited[i]) return false;
        }
        
        return true;
    }
}