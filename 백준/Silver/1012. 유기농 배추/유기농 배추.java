import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	private static int M, N, K;
	private static int[][] arr;
	private static boolean[][] visited;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 배추 1, 없으면 0
			arr = new int[M][N];
			visited = new boolean[M][N];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				arr[x][y] = 1;
			}
			
			int count = 0;
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && arr[i][j] == 1) {
						bfs(i, j);
						count++;
					}
				}
			}
			
			sb.append(count + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		
		visited[x][y] = true;
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int cx = cur[0]; 
			int cy = cur[1];
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i]; 
				int ny = cy + dy[i];
				
				if(isPossible(nx, ny) && !visited[nx][ny] && arr[nx][ny] == 1) {
					visited[nx][ny] = true;
					queue.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	private static boolean isPossible(int x, int y) {
		return x > -1 && y > -1 && x < M && y < N;
	}

}