import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static int F, S, G, U, D;
	static boolean[] visited;
	static int[] button = new int[2];
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] arr = br.readLine().split(" ");
		
		F = Integer.parseInt(arr[0]);
		S = Integer.parseInt(arr[1]);
		G = Integer.parseInt(arr[2]);
		U = Integer.parseInt(arr[3]);
		D = Integer.parseInt(arr[4]);
		
		visited = new boolean[F + 1];
		
		button[0] = U;
		button[1] = -D;
		
		int buttonCount = bfs(S, G, 0);
		
		if(buttonCount == -1) bw.write("use the stairs"); 
		else bw.write(buttonCount + " ");
		
		bw.flush();
		bw.close();
	}
	
	private static int bfs(int start, int end, int depth) {
		Queue<int[]> queue = new LinkedList<>();
		
		if(start == end) return 0;
		
		visited[start] = true;
		queue.add(new int[] {start, depth});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			int floor = poll[0];
			int d = poll[1];
			
			if(floor == end) {
				return d;
			}
			
			
			for(int i=0; i<2; i++) {
				int nextF = floor + button[i];
				
				if(nextF > 0 && nextF <= F) {
					if(!visited[nextF]) {
						visited[nextF] = true;
						queue.add(new int[] {nextF, d+1});
					}
				}
			}
		}
		return -1;
	}
}