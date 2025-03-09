import java.util.*;
import java.io.*;

public class Main {
    static char[][] arr;
    static boolean[][] visited;
    static int M, N; 
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        arr = new char[M][N];
        visited = new boolean[M][N];
        ArrayList<int[]> startPoint = new ArrayList<>();
        
        int minCount = Integer.MAX_VALUE;
        
        for(int i=0; i<M; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        
        // 0,0  0,1  0,2  0,3  ... 0,6
        for(int i=0; i<=M-8; i++) {
            for(int j=0; j<=N-8; j++) {
                startPoint.add(new int[] {i, j});
            }
        }
        
        for(int[] start: startPoint) {
            
            int white = bfs(start[0], start[1], 1);
            minCount = Math.min(white, minCount);
            
            int black = bfs(start[0], start[1], -1); 
            minCount = Math.min(minCount, black);
         
        }
        
        bw.write(minCount + "\n");
        bw.flush();
        bw.close();
    }
    
    private static int bfs(int x, int y, int color) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[M][N];
        
        visited[x][y] = true;
        // black : -1, white : 1
        queue.add(new int[] {x, y, color});
        
        int count = 0;

        if(arr[x][y] == 'B') {
        	if(color == 1) count++;
        }
        else if(arr[x][y] == 'W') {
        	if(color == -1) count++;
        }
        
        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            
            int cx = poll[0];
            int cy = poll[1];
            int cc = poll[2];
            
            for(int i=0; i<4; i++) {
                int nx = cx + dx[i]; 
                int ny = cy + dy[i];
            
                if(isPossible(nx, ny, x, y) && !visited[nx][ny]) {
                    char nc = arr[nx][ny];
                    
                    if(cc == -1) {
                        if(nc != 'W') count++;

                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny, 1});
                    }
                    else if(cc == 1) {
                        if(nc != 'B') count++;

                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny, -1});
                    }
                }
            }
        }
        
        return count;
        
    }
    
    private static boolean isPossible(int x, int y, int endX, int endY) {
        return x >= endX && y >= endY && x < endX+8 && y < endY+8;
    }
    
}