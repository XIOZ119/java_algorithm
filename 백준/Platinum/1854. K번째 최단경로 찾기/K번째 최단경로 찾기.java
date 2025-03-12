import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {
	private static ArrayList<int[]>[] graph;
	private static PriorityQueue<Integer>[] distance; 
	private static int n, m, k;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// n(도시 개수), m(도시 간 도로 수), k(k번째 최단경로 찾기)
		// a b c ( a -> b 갈 때 c 시간이 걸림 ) 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 도시 개수 
		m = Integer.parseInt(st.nextToken()); // 도시 간 도로 수 
		k = Integer.parseInt(st.nextToken()); // K번째 최단 경로 찾기 
		
		graph = new ArrayList[n+1]; 
		distance = new PriorityQueue[n+1];
		
		for(int i=0; i<n+1; i++) {
			graph[i] = new ArrayList<>();
			distance[i] = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, d});
		}
		
		Dijkstra(1);
		
		for(int i=1; i<n+1; i++) {
			if(distance[i].size() == k) {
				sb.append(distance[i].peek() + "\n");				
			} else {
				sb.append("-1" + "\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void Dijkstra(int s) {
		
		// 오름차순 정렬 - 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		
//		visited[s] = true;
		pq.add(new int[] {s, 0});
		distance[s].add(0);
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int cn = cur[0]; 
			int cd = cur[1];
			
			for(int[] child: graph[cn]) {
				int nn = child[0];
				int nd = child[1] + cd;
				
				if(distance[nn].size() < k) {
					distance[nn].add(nd);
					pq.add(new int[] {nn, nd});
				} else if(distance[nn].peek() > nd) {
					distance[nn].poll();
					distance[nn].add(nd);
					pq.add(new int[] {nn, nd});
				}

			}
		}
	}
}


