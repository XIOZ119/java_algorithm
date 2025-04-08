import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	
	static boolean[][] visited;
	static int[][] arr;
	static int N, M, F;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 판 
		M = Integer.parseInt(st.nextToken()); // 승객 수
		F = Integer.parseInt(st.nextToken()); // 초기 연료 
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		
		List<int []>[] destination = new ArrayList[M+3];
		
		for(int i=0; i<M+3; i++) {
			destination[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1; 
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			arr[a][b] = i+2; // 2, 3, 4번
			destination[i+2].add(new int[] {c, d});
		}
		
		while(M != 0) {
			// 택시와 승객 간의 최단 거리 구하기
			int[] c = chooseP(startX, startY);
			if(c.length == 0) break;
			
			arr[c[1]][c[2]] = 0;
			F -= c[0];
			int[] d = destination[c[3]].get(0);
			
			int[] drive = driveTexi(c[1], c[2], d[0], d[1]);
			
			if(drive.length == 0 || F < drive[0]) break;
			
			F += drive[0];
			M--;
			
			startX = drive[1];
			startY = drive[2];
		}
		
		if(M != 0) {
			bw.write("-1");
		} else {
			bw.write(F + "");
		}
		bw.flush();
		bw.close();
	}
	
	private static int[] driveTexi(int x, int y, int desx, int desy) {
		visited = new boolean[N][N];
		Queue<int []> queue = new LinkedList<>();
		
		queue.add(new int[] {0, x, y});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cd = cur[0];
			int cx = cur[1];
			int cy = cur[2];
			
			if(cx == desx && cy == desy) {
				return new int[] {cd, cx, cy};
			}
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(!isValid(nx, ny) || arr[nx][ny] == 1 || visited[nx][ny]) continue;
				
				queue.add(new int[] {cd+1, nx, ny});
				visited[nx][ny] = true;
			}
		}
		
		return new int[] {};
	}
	private static int[] chooseP(int x, int y) {
		visited = new boolean[N][N];
		PriorityQueue<int []> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) {
						return o1[2] - o2[2];
					}
					return o1[1] - o2[1];	
				}
				return o1[0] - o2[0];
			}
			
		});
		
		pq.add(new int[] {0, x, y});
		visited[x][y] = true;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cd = cur[0];
			int cx = cur[1];
			int cy = cur[2];
			
			if(arr[cx][cy] > 1) {
				return new int[] {cd, cx, cy, arr[cx][cy]};
			}
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(!isValid(nx, ny) || arr[nx][ny] == 1 || visited[nx][ny]) continue;
                
				pq.add(new int[] {cd+1, nx, ny});
				visited[nx][ny] = true;
			}
		}
		
		return new int[] {};
	}
	
	private static boolean isValid(int x, int y) {
		return x > -1 && y > -1 && x < N && y < N;
	}
}


