import java.util.*;
import java.io.*;
import java.lang.*;

public class d6_workNum {
	private static ArrayList<Integer>[] graph;
	private static int[] degree;
	private static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	   StringBuilder sb = new StringBuilder();

	   for(int tc=1; tc<=10; tc++) {
		   StringTokenizer st = new StringTokenizer(br.readLine());
		   
		   int V = Integer.parseInt(st.nextToken());
		   int E = Integer.parseInt(st.nextToken());
		   
		   graph = new ArrayList[V+1];
		   degree = new int[V+1];
		   result = new ArrayList<>();
		   
		   degree[0] = -1;
		   
		   for(int i=0; i<V+1; i++) {
			   graph[i] = new ArrayList<>();
		   }

		   st = new StringTokenizer(br.readLine());
		   
		   for(int i=0; i<E; i++) {
			   int a = Integer.parseInt(st.nextToken());
			   int b = Integer.parseInt(st.nextToken());
			   
			   graph[a].add(b);
			   degree[b]++;
		   }
		   
		   find();
		   
		   sb.append("#" + tc + " ");
		   
		   for(int r: result) {
			   sb.append(r + " ");
		   }
		   
		   sb.append("\n");
	   }
	   
	   bw.write(sb.toString());
	   bw.flush();
	   bw.close();
	}
	
	private static void find() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<degree.length; i++) {
			if(degree[i] == 0) {
//				System.out.println(i + " " + degree[i]);
				queue.add(i);
				result.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cn = queue.poll();
			
			for(int c: graph[cn]) {
				degree[c]--;
				
				if(degree[c] == 0) {
					queue.add(c);
					result.add(c);
				}
			}
		}
	}
}
