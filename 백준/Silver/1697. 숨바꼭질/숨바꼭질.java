import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	
	static char[] operation = {'+', '-', '*'};
	static int N, K;
	static boolean[] visited = new boolean[200001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		
		int depth = bfs(N);
		
		bw.write(depth + " ");
		bw.flush();
		bw.close();
	}
	
	private static int bfs(int start) {
		Queue<int[]> queue = new LinkedList<>();
		 
		queue.add(new int[] {start, 0});
		visited[start] = true; 
		while(!queue.isEmpty()) {
					
			int[] poll = queue.poll();
			
			int a = poll[0];
			int depth = poll[1]; 
			
			
			if(a == K) return depth;
			
			if(a <= 200000 && a >= 0) {
				
				for(int i=0; i<3; i++) {
					int b = a;
					if(operation[i] == '+') b += 1; 
					if(operation[i] == '-') b -= 1;
					if(operation[i] == '*') b *= 2;
					
					if(b < 200000 && b>=0 && !visited[b]) {
						visited[b] = true;
						queue.add(new int[] {b, depth+1});						
					}
				}
			}
		}
		
		return -1;
	}
}