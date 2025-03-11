import java.util.*;
import java.io.*;

public class Main {
	private static ArrayList<Integer>[] tree;
	
	private static int[] parent;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		tree = new ArrayList[N+1];
		visited = new boolean[N+1];
		parent = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int child: tree[cur]) {
				if(!visited[child]) {
					visited[child] = true;
					parent[child] = cur;
					queue.add(child);
				}
			}
		}
		
		for(int i=2; i<N+1; i++) {
			bw.write(parent[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
