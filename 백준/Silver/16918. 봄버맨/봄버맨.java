import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int R, C, N;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken()); // 몇 초 ?
		
		int[][] arr = new int[R][C];
		
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				char a = str.charAt(j);
				if(a == '.') {
					arr[i][j] = -2; 
				} else {
					arr[i][j] = 0;
				}
			}
		}
		
		int time = 0;
		
		while(true) {
			time++;
			visited = new boolean[R][C];
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					arr[i][j]++;
				}
			}
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(arr[i][j] == 3) {
						visited[i][j] = true;
						for(int k=0; k<4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							
							if(isValid(nx, ny)) visited[nx][ny] = true;
						}
					}
				}
			}
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(visited[i][j]) arr[i][j] = -1;
				}
			}
			
			if(time == N) break;
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j] >= 0) {
					sb.append("O");
				}
				else sb.append(".");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static boolean isValid(int x, int y) {
		return x > -1 && y > -1 && x < R && y < C;
	}
	
}