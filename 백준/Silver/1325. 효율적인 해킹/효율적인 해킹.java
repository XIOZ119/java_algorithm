import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static List<Integer>[] trust;
	static boolean[] visited;
	static int[] hack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trust = new ArrayList[N+1];
		hack = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			trust[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			trust[a].add(b);
		}
		
		for(int i=1; i<N+1; i++) {
			bfs(i);
		}
		
		int maxHack = Arrays.stream(hack).max().getAsInt();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<hack.length; i++) {
			if(hack[i] == maxHack) {
				sb.append(i).append(" ");
			}
		}
		
		bw.write(sb.toString().trim());
		
		bw.flush();
		bw.close();
		
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int ns = queue.poll();

            for (int child : trust[ns]) {
                if (!visited[child]) {
                    visited[child] = true;
                    hack[child]++;
                    queue.add(child);
                }
            }
        }
		
	}
	
}