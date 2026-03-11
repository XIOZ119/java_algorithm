import java.util.*;

class Solution {
    static char[][] arr;
    static int l;
    static int ll;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        l = maps.length;
        ll = maps[0].length();
        arr = new char[l][ll];

        int startX = 0;
        int startY = 0;
        
        for(int i=0; i<l; i++) {
            String str = maps[i];
            for(int j=0; j<ll; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        int result = 0;
        // S -> L 
        // x, y, distance
        int[] SL = find(startX, startY, 'L');
        if(SL[2] == -1) return -1;
        
        int[] LE = find(SL[0], SL[1], 'E');
        if(LE[2] == -1) return -1;
        
        return SL[2] + LE[2];   
    }
    
    static int[] find(int x, int y, char c) {
        int[][] dijak = new int[l][ll];
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i=0; i<l; i++) {
            Arrays.fill(dijak[i], Integer.MAX_VALUE);
        }
        
        dijak[x][y] = 0;
        queue.add(new int[]{x, y, 0});
        
        int[] result = new int[3];
        Arrays.fill(result, -1);
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cd = cur[2];
            
            if(arr[cx][cy] == c) {
                result[0] = cx;
                result[1] = cy; 
                result[2] = cd;
            }
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i]; 
                int nd = cd + 1;
                
                if(!isValid(nx, ny) || dijak[nx][ny] <= nd || arr[nx][ny] == 'X') continue;
                
                dijak[nx][ny] = nd;
                queue.add(new int[] {nx, ny, nd});
            }
        }
        
        return result;
    }
    
    static boolean isValid(int x, int y) {
        return x > -1 && x < l && y > -1 && y < ll;
    }
}