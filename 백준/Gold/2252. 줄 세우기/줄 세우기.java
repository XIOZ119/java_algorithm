import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		int[] indegree = new int[N+1]; 
		
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			String[] arr = br.readLine().split(" ");
			
			int a = Integer.parseInt(arr[0]);
			int b = Integer.parseInt(arr[1]);
			
			graph[a].add(b);
			indegree[b] ++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<N+1; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			result.add(poll);
			
			for(int child: graph[poll]) {
				indegree[child]--;
				
				if(indegree[child] == 0) {
					queue.add(child);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			int re = result.get(i);
			bw.write(re + " ");
		}
		
		bw.flush();
		bw.close();
		
	}
	
}
