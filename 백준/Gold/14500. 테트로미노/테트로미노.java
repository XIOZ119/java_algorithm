import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, arr[i][j], 1);
				exep(i, j);
				visited[i][j] = false;
			}
		}
		
		bw.write(max + "");
		bw.flush(); 
		bw.close();
	}

    private static void dfs(int x, int y, int sum, int count) {
    	
    	if(count == 4) {
    		max = Math.max(sum, max);
    		return;
    	}
    	
    	for(int i=0; i<4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		
    		if(isValid(nx, ny) && !visited[nx][ny]) {
    			visited[nx][ny] = true;
    			dfs(nx, ny, sum + arr[nx][ny], count+1);
    			visited[nx][ny] = false;
    		}
    	}
    }
    
    private static void exep(int x, int y) {
    	if(x+2 < N && y-1 > -1) {
    		int m = arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y-1];
    		max = Math.max(m, max);
    	}
    	if(x+2 < N && y+1 < M) {
    		int m = arr[x][y] + arr[x+1][y] + arr[x+2][y] + arr[x+1][y+1];
    		max = Math.max(m, max);
    	}
    	if(y-1 > -1 && y+1 < M && x-1 > -1) {
    		int m = arr[x][y] + arr[x][y-1] + arr[x][y+1] + arr[x-1][y];
    		max = Math.max(m, max);
    	}
    	if(y-1 > -1 && y+1 < M && x+1 < N) {
    		int m = arr[x][y] + arr[x][y-1] + arr[x][y+1] + arr[x+1][y];
    		max = Math.max(m, max);
    	}
    }
    
    private static boolean isValid(int x, int y) {
    	return x > -1 && y > -1 && x < N && y < M;
    }
}