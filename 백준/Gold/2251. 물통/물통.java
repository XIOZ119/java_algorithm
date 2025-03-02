import java.util.*;
import java.io.*;

public class Main {
	static int[] capacity;
	static Queue<int[]> queue;
	static boolean[][][] visited;
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		capacity = new int[] { a, b, c };
		
		visited = new boolean[201][201][201];
		
		result = new ArrayList<>();
		
		bfs(0, 0, c);
		
		Collections.sort(result);
		
		for(int child: result) {
			bw.write(child + " ");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void bfs(int a, int b, int c) {
		queue = new LinkedList<>();
		
		visited[a][b][c] = true;
		queue.add(new int[] {a, b, c});
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			
			int na = poll[0];
			int nb = poll[1];
			int nc = poll[2];
			
			if(na == 0) {
				result.add(nc);
			}
			
			int[] next;
			
			if(na != 0) {
				next = nextPour(na, nb, capacity[1]);
				addQueue(next[0], next[1], nc);
				next = nextPour(na, nc, capacity[2]);
				addQueue(next[0], nb, next[1]);
			}
			if(nb != 0) {
				next = nextPour(nb, na, capacity[0]);
				addQueue(next[1], next[0], nc);
				next = nextPour(nb, nc, capacity[2]);
				addQueue(na, next[0], next[1]);
			}
			if(nc != 0) {
				next = nextPour(nc, na, capacity[0]);
				addQueue(next[1], nb, next[0]);
				next = nextPour(nc, nb, capacity[1]);
				addQueue(na, next[1], next[0]);
			}
		}
	}
	
	private static int[] nextPour(int from, int to, int to_capacity) {
		int pour = Math.min(from, to_capacity-to);
		from -= pour;
		to += pour;
		
		return new int[] {from, to};
	}
	
	private static void addQueue(int a, int b, int c) {
		if(!visited[a][b][c]) {
			visited[a][b][c] = true;
			queue.add(new int[] {a, b, c});
		}
	}
}