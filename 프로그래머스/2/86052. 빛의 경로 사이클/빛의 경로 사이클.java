import java.util.*;

class Solution {
    static int row, col; 
    static char[][] arr;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> list;
    
    public int[] solution(String[] grid) {
        row = grid.length; 
        col = grid[0].length();
        
        arr = new char[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                char c = grid[i].charAt(j);
                
                arr[i][j] = c;
            }
        } 
        
        visited = new boolean[row][col][4];
        list = new ArrayList<>();
        
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                for(int k=0; k<4; k++) {
                    if(visited[i][j][k]) continue;
                    bfs(i, j, k);
                }
            }
        }
        
        int[] answer = new int[list.size()];
        
        Collections.sort(list, (a, b) -> {
            return a - b;
        });
        
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    private static void bfs(int x, int y, int d) {
        Queue<int[]> que = new LinkedList<>();
        
        que.add(new int[] {x, y, d, 0});
        visited[x][y][d] = true;
        
        while(!que.isEmpty()) {
            int[] cur = que.poll(); 
            int cx = cur[0]; int cy = cur[1];
            int cd = cur[2]; 
            
            if(cx == x && cy == y && cd == d && cur[3] > 0) {
                list.add(cur[3]);
                break;
            }
            
            int nx = cx; int ny = cy; int nd = cd;

            if(arr[cx][cy] == 'L') {
                if(cd == 0) nd = 2;
                if(cd == 1) nd = 3;
                if(cd == 2) nd = 1;
                if(cd == 3) nd = 0;
            }
            if(arr[cx][cy] == 'R') {
                if(cd == 0) nd = 3;
                if(cd == 1) nd = 2;
                if(cd == 2) nd = 0;
                if(cd == 3) nd = 1;
            }
            
            nx = validX(nx + dx[nd]);
            ny = validY(ny + dy[nd]);
            
            que.add(new int[]{nx, ny, nd, cur[3] + 1});
            visited[nx][ny][nd] = true;
        }
    }
    
    private static int validX(int x) {
        if(x < 0) return row - 1;
        if(x >= row) return 0;
        
        return x;
    }
    
    private static int validY(int y) {
        if(y < 0) return col - 1;
        if(y >= col) return 0;
        
        return y;
    }
}