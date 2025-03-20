import java.util.*;
import java.io.*;

public class Main {
	static int M, N;
	static int[][] arr;
	static boolean[][] visited;
	static List<int []> ttomato;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		ttomato = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0 : 익지 않은 토마토, 1 : 익은 토마토, -1 : 토마토 없는 칸
		boolean flag = false;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1) {
					ttomato.add(new int [] {i, j});
				}
				if(arr[i][j] == 0) {
					flag = true;
				}
			}
		}
		
		int year = 0;
		
		if(!flag) year = 0; // 저장될 때부터 익어잇는 상태
		else year = bfs() - 1; 
		
		// 토마토가 모두 익지 못한 상황
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0 && !visited[i][j]) {
					year = -1;
				}
			}
		}
		
		bw.write(year + "");
		bw.flush();
		bw.close();
	}
	
	private static int bfs() {
		Queue<int []> queue = new LinkedList<>();
		int year = 1;
		
		for(int[] l: ttomato) {
			queue.add(l);
			visited[l[0]][l[1]] = true;
		}
		
		ttomato.clear();
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int cx = cur[0]; 
			int cy = cur[1];
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(!isValid(nx, ny) || visited[nx][ny]) continue;
			
				if(arr[nx][ny] == 0) {
					visited[nx][ny] = true;
					ttomato.add(new int[] {nx, ny});
				}
			}
			
			if(queue.isEmpty()) {
				if(!ttomato.isEmpty()) {
					year++;
					for(int[] l: ttomato) {
						queue.add(l);
					}
					ttomato.clear();
				}
			}
		}
		return year;
	}
	
	private static boolean isValid(int x, int y) {
		return x > -1 && y > -1 && x < N && y < M;
	}
} 