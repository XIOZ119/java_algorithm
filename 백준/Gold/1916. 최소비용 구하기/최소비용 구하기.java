import java.util.*;
import java.io.*;

public class Main {
	private static ArrayList<int[]>[] graph;
	private static boolean[] visited;
	private static int[] shortD;
	// 단지 가장 짧은 거리 갱신해주면서 하려고 했는데, 모든 경우의 수이기에 메모리 초과남
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 도시 개수 
		int M = Integer.parseInt(br.readLine()); // 버스 개수 
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		shortD = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
			shortD[i] = Integer.MAX_VALUE;
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, d});
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int startNode = Integer.parseInt(st.nextToken());
		int endNode = Integer.parseInt(st.nextToken());

		shortD[startNode] = 0;
		Dijkstra(startNode, endNode);
		
		bw.write(shortD[endNode] + "");
		bw.flush();
		bw.close();
	}
	
	private static void Dijkstra(int sn, int en) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		
		pq.add(new int[] {sn, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int cn = cur[0];
			int cd = cur[1]; 

			// 현재 꺼낸 거리보다 기존 저장된 최단 거리가 짧을 경우, 무시
			if (cd > shortD[cn]) continue;
			
			for(int[] child: graph[cn]) {
				int childN = child[0];
				int childD = child[1];
				int sumD = cd + childD;
				
				if(sumD < shortD[childN]) {
					shortD[childN] = sumD;
					pq.add(new int[] {childN, sumD});
				}
			}
		}
	}
}
