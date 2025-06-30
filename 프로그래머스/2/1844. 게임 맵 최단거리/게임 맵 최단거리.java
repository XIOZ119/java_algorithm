// 5x5 맵 (1~5)
// 내 캐릭터 (1, 1)
// 동서남북 한칸씩 이동
// n x m 크기
// 0 : 벽, 1: 이동 가능
// 상대 진영: n, m

import java.lang.*;
import java.io.*;
import java.util.*;

class Solution {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int m;
    private static int n;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        // System.out.println(m + " " + n + "\n");
        
        int answer = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        queue.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];
            
            // System.out.println(cx + " " + cy + " " + cd + "\n");
            
            if(cx == n-1 && cy == m-1) {
                answer = Math.min(answer, cd);
            }
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(!valid(nx, ny) || maps[nx][ny] == 0 || visited[nx][ny])
                    continue;
                
                queue.add(new int[] {nx, ny, cd+1});
                visited[nx][ny] = true;
            }
        }
        if(answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        
        return answer;
    }
    
    private static boolean valid(int x, int y) {
        return x>=0 &&  y >= 0 && x < n && y < m;
    }
}