import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		
		int cnt = bfs(N, 0);

		bw.write(cnt + "");
		bw.flush();
		bw.close();
	}
	
	private static int bfs(int node, int count) {
		Queue<int[]> queue = new LinkedList<>();
		
		visited[node] = true;
		queue.add(new int[] {node, count});
		
		while(!queue.isEmpty()){
			int[] poll = queue.poll();
			
			int cn = poll[0];
			int cc = poll[1];
			
			if(cn == 1) return cc;
			
			if(cn % 3 == 0) {
				queue.add(new int[] {cn/3, cc+1});
			}
			if(cn % 2 == 0) {
				queue.add(new int[] {cn/2, cc+1});
			}
			queue.add(new int[] {cn-1, cc+1});
		}
		return -1;
	}
}