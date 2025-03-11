import java.util.*;
import java.io.*;

public class Main {
	private static ArrayList<int[]>[] graph;
	private static boolean[] visited;
	private static int[] shortD;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
	
		String[] input = br.readLine().split(" ");
		
		int V = Integer.parseInt(input[0]); // 정점 개수
		int E = Integer.parseInt(input[1]); // 간선 개수
		
		graph = new ArrayList[V+1];
		visited = new boolean[V+1];
		shortD = new int[V+1];
		
		for(int i=0; i<V+1; i++) {
			graph[i] = new ArrayList<>();
			shortD[i] = Integer.MAX_VALUE;
		}
		
		int startNode = Integer.parseInt(br.readLine()); // 시작 정점 
		
		for(int i=0; i<E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, d});
		}
		
		shortD[startNode] = 0; // 시작점 노드
		Dijkstra(startNode, 0);
		
		for(int i=1; i<V+1; i++) {
			if(shortD[i] == Integer.MAX_VALUE) {
				sb.append("INF" + "\n");
			}
			else sb.append(shortD[i] + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void Dijkstra(int startNode, int distance) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		
		pq.add(new int[] {startNode, 0});
		visited[startNode] = true;
		
		while(!pq.isEmpty()) {
			
			int[] cur = pq.poll(); 
			int curNode = cur[0];
			int curDistance = cur[1];
			visited[curNode] = true;
			
			for(int[] child: graph[curNode]) {
				int childNode = child[0];
				int childDistance = child[1];
				
				if(!visited[childNode]) {
					
					int sumDistance = curDistance + childDistance;
					if(shortD[childNode] > sumDistance) {
						shortD[childNode] = sumDistance;
						pq.add(new int[] {childNode, sumDistance});
					}
				}
			}
			
		}
	
	}
}
