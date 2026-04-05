import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int[] row = new int[2];
        int[] col = new int[2];
        
        Arrays.fill(row, x);
        Arrays.fill(col, y);
        
        for(int i=queries.length-1; i>=0; i--) {
            int dir = queries[i][0];
            int count = queries[i][1];
            
            if(dir == 0 || dir == 2) {
                if(row[0] != 0) row[0] += (dx[dir]*count);
                row[1] = Math.min(n-1, row[1]+(dx[dir]*count));

                if(col[0] != 0) col[0] += (dy[dir]*count);
                col[1] = Math.min(m-1, col[1]+(dy[dir]*count));
            } else {
                if(row[1] != n-1) row[1] += (dx[dir]*count);
                row[0] = Math.max(0, row[0]+(dx[dir]*count));
                
                if(col[1] != m-1) col[1] += (dy[dir]*count);
                col[0] = Math.max(0, col[0]+(dy[dir]*count));
            }
            
            if(row[0] > row[1] || col[0] > col[1]) return 0;
        }
        
        long rCount = row[1] - row[0] + 1;
        long cCount = col[1] - col[0] + 1;
        
        long answer = rCount * cCount;
        return answer;
    }
}