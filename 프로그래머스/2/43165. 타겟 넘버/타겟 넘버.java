class Solution {
    static boolean[][] visited;
    static int l;
    static int t;
    static int answer;
    static int[] n;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        l = numbers.length;
        t = target;
        
        n = new int[l];
        
        for(int i=0; i<l; i++) {
            n[i] = numbers[i];
        }
        
        visited = new boolean[l][2];
        
        dfs(0, 0); 
        
        return answer;
    }
    
    static void dfs(int start, int sum) {
        if(start == l && sum == t) {
            answer++;
            return;
        }
        
        if(start >= l) return;
        
        for(int i=0; i<2; i++) {
            if(visited[start][i]) continue;

            visited[start][i] = true;
            
            if(i % 2 == 0) {
                dfs(start + 1, sum + n[start]);
            } else {
                dfs(start + 1, sum - n[start]);
            }
            
            visited[start][i] = false;
        }
    }
}