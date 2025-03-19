import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[][] arr;
	static int[][] result;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		result = new int[n][m];
		
		int startX = 0;
		int startY = 0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				result[i][j] = Integer.MAX_VALUE;
				if(arr[i][j] == 2) {
					startX = i;
					startY = j;
				}
			}
		}
		
		bfs(startX, startY);
		
		result[startX][startY] = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void bfs(int x, int y) {
		Queue<int []> queue = new LinkedList<>();

		queue.add(new int[] {x, y, 0});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int cx = cur[0]; 
			int cy = cur[1];
			int cd = cur[2];
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				int nd = cd + 1;
				
				if(!isValid(nx, ny) || arr[nx][ny] == 0) continue;
				
				
				if(result[nx][ny] > nd) {
					result[nx][ny] = nd;
					queue.add(new int[] {nx, ny, nd});
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(arr[i][j] == 1 && result[i][j] == Integer.MAX_VALUE) {
					result[i][j] = -1;
				}
				if(arr[i][j] == 0) result[i][j] = 0;
			}
		}
	}
	
	private static boolean isValid(int x, int y) {
		return x > -1 && y > -1 && x < n && y < m;
	}
} 