import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static char[][] arr;
	static int startX, startY, endX, endY;
	static int N, M;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		visited = new boolean[N][M];
		
		startX = 0; startY = 0; 
		endX = 0; endY = 0;
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j);
				if(arr[i][j] == 'I') {
					startX = i;
					startY = j;
				}
			}
		}
		
		result = 0;
		bfs();
		
		if(result == 0) bw.write("TT");
		else bw.write(result + "");
		
		bw.flush();
		bw.close();
	}
	
	private static void bfs() {
		Queue<int []> queue = new LinkedList<>();
		
		queue.add(new int[] {startX, startY});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int cx = cur[0];
			int cy = cur[1]; 
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(isValid(nx, ny) && !visited[nx][ny]) {
					if(arr[nx][ny] == 'P') {
						result++;
					}
					if(arr[nx][ny] != 'X') {
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}
				}
			}
			
		}
	}
	
	private static boolean isValid(int x, int y) {
		return x > -1 && y > -1 && x < N && y < M;
	}
}