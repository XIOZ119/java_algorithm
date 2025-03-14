import java.util.*;
import java.io.*;
import java.lang.*;

public class d4_oneIslandLoad1251 {
	private static int N;
	private static int[] x, y;
	private static boolean[] visited;
	private static double[] primD;
	private static double tax;
	
	static class Island {
		int index;
		double distance;
		
		public Island(int index, double distance) {
			this.index = index;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			
			x = new int[N];
			y = new int[N];
			visited = new boolean[N];
			primD = new double[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int a = Integer.parseInt(st.nextToken());
				
				x[i] = a;
			}
		
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int a = Integer.parseInt(st.nextToken());
				
				y[i] = a;
			}
			
			tax = Double.parseDouble(br.readLine());
			
			Arrays.fill(primD, Double.MAX_VALUE);
			primD[0] = 0;
			
			prim(0, 0.0);
			
			double sum = 0;
			
			for(int i=0; i<N; i++ ) {
				sum += primD[i];
			}
			
			sb.append("#" + tc + " " + Math.round(sum) + "\n");
		
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void prim(int index, double distance) {
		
		PriorityQueue<Island> pq = new PriorityQueue<>(new Comparator<Island>() {
			public int compare(Island a, Island b) {
				return Double.compare(a.distance, b.distance);
			}
		});
		
		pq.add(new Island(index, distance));

		while(!pq.isEmpty()) {
			Island curIsland = pq.poll();
			
			if(visited[curIsland.index]) continue;
			visited[curIsland.index] = true;
			
			for(int i=0; i<N; i++) {
				if(i != curIsland.index && !visited[i]) {
					double d = tax * (Math.pow(x[curIsland.index] - x[i], 2) + Math.pow(y[curIsland.index] - y[i], 2));
					
					if(primD[i] > d) {
						primD[i] = d;
					}
					
					pq.add(new Island(i, d));
				}
			}
		}
	}
}