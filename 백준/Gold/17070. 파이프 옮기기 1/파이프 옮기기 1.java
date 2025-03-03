import java.util.*;
import java.io.*;

public class Main {
	static int[][] state;
	static boolean[][] visited;
	static int[] dx = {0, 1, 1}; // 세로 , 가로 , 대각선 이동
	static int[] dy = {1, 0, 1};
	static int count, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		state = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				state[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0: 빈칸, 1: 벽, 가로: 2, 세로: 3, 대각선: 4
		state[1][1] = 2;
		state[1][2] = 2;
		
		count = 0;
		dfs(state, 1, 2);
		
		bw.write(count + "");
		bw.flush();
		bw.close();
		
	}
	
	private static void dfs(int[][] recentState, int nodeX, int nodeY) {
		visited[nodeX][nodeY] = true;
		
		if(nodeX == N && nodeY == N) {
			count++; 
			return;
		}
		
		
		if(recentState[nodeX][nodeY] == 2) { // 가로 
			for(int i=0; i<3; i+=2) {
				int nx = nodeX + dx[i];
				int ny = nodeY + dy[i]; 
				
				if(isPossible(nx, ny) && state[nx][ny] == 0) { 
					if(i == 2) {
						if(isPossible(nx-1, ny) && isPossible(nx, ny-1)) {
							if(recentState[nx-1][ny] != 0 || recentState[nx][ny-1] != 0) continue;
						}
					}
					recentState[nx][ny] = i+2;
					visited[nx][ny] = true;
					dfs(recentState, nx, ny);
					visited[nx][ny] = false;
					recentState[nx][ny] = 0;						
				}
			}
		} else if(recentState[nodeX][nodeY] == 3) { // 세로 
			for(int i=1; i<3; i++) {
				int nx = nodeX + dx[i];
				int ny = nodeY + dy[i]; 
				
				if(isPossible(nx, ny) && state[nx][ny] == 0) { 
					if(i == 2) { 
						if(isPossible(nx-1, ny) && isPossible(nx, ny-1)) {
							if(recentState[nx-1][ny] != 0 || recentState[nx][ny-1] != 0) continue;
						}
					}
					recentState[nx][ny] = i+2;
					visited[nx][ny] = true;
					dfs(recentState, nx, ny);
					visited[nx][ny] = false;
					recentState[nx][ny] = 0;						
				}
			}
		} else if(recentState[nodeX][nodeY] == 4) { // 대각선  
			for(int i=0; i<3; i++) {
				int nx = nodeX + dx[i];
				int ny = nodeY + dy[i]; 
				
				if(isPossible(nx, ny) && state[nx][ny] == 0) { 
					if(i == 2) { 
						if(isPossible(nx-1, ny) && isPossible(nx, ny-1)) {
							if(recentState[nx-1][ny] != 0 || recentState[nx][ny-1] != 0) continue;
						}
					}
					recentState[nx][ny] = i+2;
					visited[nx][ny] = true;
					dfs(recentState, nx, ny);
					visited[nx][ny] = false;
					recentState[nx][ny] = 0;						
				}
			}
		}
	}
	
	private static boolean isPossible(int nodeX, int nodeY) {
		return nodeX > 0 && nodeY > 0 && nodeX < N+1 && nodeY < N+1;
	}
}