import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] move = {1, -1, 0};
	static int result, minD;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		minD = Integer.MAX_VALUE;
		result = 0;
		bfs();
		
		bw.write(minD + "\n" + result);
		bw.flush(); 
		bw.close();
	}
	
	static void bfs() {
		int[] visited = new int[100_001];
		
		for(int i=0; i<visited.length; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		pq.add(new int[] {N, 0});
		visited[N] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cx = cur[0];
			int cd = cur[1];
			
			if(cx == K) {
				if(minD > cd) {
					minD = cd;
					result = 1;
				}
				else if(minD == cd) {
					result++;
				}
			}
			
			for(int i=0; i<3; i++) {
				int nx = -1;
				int nd = cd + 1;
				
				if(move[i] == 0) {
					nx = cx + cx;
				} else {
					nx = cx + move[i];
				}
				
				if(nd > minD) continue;
				if(nx > 100_000 || nx < 0) continue;
 				
				if(visited[nx] >= nd) {
					visited[nx] = nd;
					pq.add(new int[] {nx, nd});
				}
			}
		}
	}

}
