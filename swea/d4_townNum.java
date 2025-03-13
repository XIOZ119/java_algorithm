import java.util.*;
import java.io.*;
import java.lang.*;

public class d4_townNum {
	private static ArrayList<Integer>[] graph;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();

	   int testCase = Integer.parseInt(br.readLine());
	   
	   for(int tc=1; tc <= testCase; tc++) {
		   StringTokenizer st = new StringTokenizer(br.readLine());
		   
		   int N = Integer.parseInt(st.nextToken());
		   int M = Integer.parseInt(st.nextToken());
		   
		   graph = new ArrayList[N+1];
		   visited = new boolean[N+1];
		   
		   for(int i=0; i<N+1; i++) {
			   graph[i] = new ArrayList<>();
		   }
		   
		   for(int i=0; i<M; i++) {
			  st = new StringTokenizer(br.readLine());
			  
			  int a = Integer.parseInt(st.nextToken());
			  int b = Integer.parseInt(st.nextToken());
			  
			  graph[a].add(b);
			  graph[b].add(a);
		   }
		   
		   int count = 0;
		   
		   for(int i=1; i<N+1; i++) {
			   if(!visited[i]) {
				   bfs(i);
				   count++;
			   }
		   }
		   
		   sb.append("#" + tc + " " + count + "\n");
	   }
	   
	   bw.write(sb.toString());
	   bw.flush();
	   bw.close();
	   
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int cn = queue.poll();
			
			for(int nn: graph[cn]) {
				if(!visited[nn]) {
					visited[nn] = true;
					queue.add(nn);
				}
			}
		}
	}
}
