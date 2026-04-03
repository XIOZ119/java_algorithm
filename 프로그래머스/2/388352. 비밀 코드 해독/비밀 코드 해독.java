class Solution {
    static int n;
    static int[][] q;
    static int[] ans;
    static boolean[] visited;
    static int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        visited = new boolean[n+1];
        
        comb(1, 0);
        return answer;
    }
    
    static void comb(int start, int depth) {
        if(depth == 5) {
            if(isPossible()) answer++;
            return;
        }
        
        for(int i=start; i<=n; i++) {
            visited[i] = true;
            comb(i+1, depth+1);
            visited[i] = false;
        }
        
    }
    
    static boolean isPossible() {
        for(int i=0; i<q.length; i++) {
            int count = 0;
            for(int j=0; j<5; j++) {
                int n = q[i][j];
                if(visited[n]) count++;
            }
            
            if(count != ans[i]) return false;
        }
        
        return true;
    }
}