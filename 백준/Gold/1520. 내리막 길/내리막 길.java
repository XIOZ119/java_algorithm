import java.util.*;
import java.io.*;

public class Main {
    static int M, N;
    static int[][] arr;
    static int[][] movePossible;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    
    // 왼쪽 위 칸 -> 오른쪽 아래 칸 이동
    // 항상 높이가 현재 있는 칸보다 더 낮은 지점으로만 이동(내리막길), 상하좌우 이동        
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[M][N];
        movePossible = new int[M][N];
        visited = new boolean[M][N];
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=M-1; i>-1; i--) {
        	for(int j=N-1; j>-1; j--) {
        		if(!visited[i][j]) {
        			dfs(i, j);
        		}
        	}
        }
        
        bw.write(movePossible[0][0] + "");
        bw.flush();
        bw.close();
    }
    
    private static void dfs(int x, int y) {
    	int count = 0;
    	
    	if(x == M-1 && y == N-1) count++;
    	
    	for(int i=0; i<4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		
    		if(isValid(nx, ny)) {
    			if(arr[nx][ny] < arr[x][y]) {
    				if(!visited[nx][ny]) {
    					dfs(nx, ny);
    					count += movePossible[nx][ny];
    				} else {
    					count += movePossible[nx][ny];
    				}
    				
    			}
    		}
    	}
    	
    	movePossible[x][y] = count;
    	visited[x][y] = true;
    		
    }

    private static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < M && y <N;
    }
}  