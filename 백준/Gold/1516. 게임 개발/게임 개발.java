import java.util.*;
import java.io.*;

public class Main {
	static int[] time;
	static ArrayList<Integer>[] graph;
	static int[] indegree;
	static int[] resultTime;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		time = new int[N+1];
		graph = new ArrayList[N+1];
		indegree = new int[N+1];
		resultTime = new int[N+1];

		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N+1; i++) {
			String[] str = br.readLine().split(" ");
			time[i] = Integer.parseInt(str[0]);
			
			for(int j=1; j<str.length; j++) { 

				if(str[j].equals("-1")) {
					break;
				}

				int a = Integer.parseInt(str[j]);
				indegree[i]++;
				graph[a].add(i);
			}
		}
		
		bfs();

		for(int i=1; i<=N; i++) {
			bw.write(resultTime[i] + "\n");
		}
			
		bw.flush();
		bw.close();
	}
	
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
				resultTime[i] = time[i];
			}
		}
		
		while(!queue.isEmpty()) {
			int poll = queue.poll(); 
			
			for(int child: graph[poll]) {
				resultTime[child] = Math.max(resultTime[child], resultTime[poll] + time[child]);
				indegree[child]--;
				
				if(indegree[child] == 0) {
					queue.add(child);
				}
			}
		}
	}
}
