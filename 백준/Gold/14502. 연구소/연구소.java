import java.util.*;
import java.io.*;

public class Main {
	static int[][] arr;
	static ArrayList<int[]> zeros;
	static List<List<int[]>> combination;
	static boolean[][] visited;
	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		zeros = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				if(arr[i][j] == 0) zeros.add(new int[] {i, j});
			}
		}
		combination = new ArrayList<>();
		
		findCombination(new ArrayList<>(), 0);
		
		int max = Integer.MIN_VALUE;
		
		for(List<int[]> child: combination) {
			int count = 0;
			for(int[] cc: child) {
				int x = cc[0];
				int y = cc[1];
				arr[x][y] = 1;
			}

            newVisited();
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == 2) {
						bfs(i, j);
					}
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == 0 && !visited[i][j]) count++;
				}
			}

			max = Math.max(max, count);
			
			for(int[] cc: child) {
				int x = cc[0];
				int y = cc[1];
				arr[x][y] = 0;
			}
		}
		
		bw.write(max + " ");
		bw.flush();
		bw.close();
	}
	
	private static void findCombination(List<int[]> selected, int start) {
		if(selected.size() == 3) {
			combination.add(new ArrayList<>(selected));
			return;
		}
		
		for(int i=start; i<zeros.size(); i++) {
			selected.add(zeros.get(i));
			findCombination(selected, i+1);
			selected.remove(selected.size()-1);
		}
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = poll[0] + dx[i];
				int ny = poll[1] + dy[i];
				
				if(isPossible(nx, ny)) {
					if(arr[nx][ny] == 0 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}					
				}
			}
			
		}
	}
	
	private static void newVisited() {
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 2) visited[i][j] = true;
			}
		}
	}
	
	private static boolean isPossible(int x, int y) {
		return x > -1 && y > -1 && x < N && y < M;
	}
}