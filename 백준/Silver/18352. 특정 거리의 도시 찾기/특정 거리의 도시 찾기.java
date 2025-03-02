import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Integer>[] load;
	static boolean[] visited;
	static int N, M, K, X;
	static BufferedWriter bw;
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		X = Integer.parseInt(str[3]);
		
		load = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i=0; i<N+1; i++) {
			load[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			load[a].add(b);
		}
		
	
		bfs(X);
		
		if(result.isEmpty()) {
			bw.write(-1 + " ");
		} else {
			Collections.sort(result);
			for(int i=0; i<result.size(); i++) {
				int re = result.get(i);
				bw.write(re + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void bfs(int start) throws IOException {
		Queue<int[]> queue = new LinkedList<>();
		result = new ArrayList<>();
		
		visited[start] = true;
		queue.add(new int[] {start, 0});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			int ns = poll[0];
			int nd = poll[1];
			
			if(nd == K) {
				result.add(ns);
			} 
			if(nd > K) {
				return;
			}
			
			for(int child: load[ns]) {
				if(!visited[child]) {

					visited[child] = true;
					queue.add(new int[] {child, (nd+1)});
				}
			}
		}
		
	}
}