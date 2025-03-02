import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Integer>[] arr;
	static int[] colors; // 0초기화, 1 or -1
	static boolean isBinaryGraph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			arr = new ArrayList[V+1];
			colors = new int[V+1];
			
			for(int i=0; i<V+1; i++) {
				arr[i] = new ArrayList<>();
				colors[i] = 0;
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr[a].add(b);
				arr[b].add(a);
			}
			
			isBinaryGraph = true;
			
			for(int i=0; i<V+1; i++) {
				if(!isBinaryGraph) break;
				if(colors[i] == 0) {
					bfs(i, 1);
				}
			}
			
			if(isBinaryGraph) bw.write("YES" + "\n");
			else bw.write("NO" + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void bfs(int start, int color) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(start);
		colors[start] = color;
		
		while(!queue.isEmpty()) {
			int ns = queue.poll();
			
			for(int child: arr[ns]) {
				if(colors[child] == 0) {
					colors[child] = colors[ns] * -1;
					queue.add(child);
				} else {
					if(colors[child] + colors[ns] != 0) {
						isBinaryGraph = false;
						return;
					}
				}
			}
		}
	}
}