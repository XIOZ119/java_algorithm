import java.util.*;
import java.io.*;

public class Main {
	static List<Integer> result;
	static int[] city;
	static int[] rank;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 도시의 수  
		int M = Integer.parseInt(br.readLine()); // 여행계획에 속한 도시들의 수 
		
		city = new int[N+1];
		rank = new int[N+1]; 
		
		for(int i=0; i<N+1; i++) {
			city[i] = i;
			rank[i] = 1;
		}
		
		for(int i=1; i<N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) union(i, j);
			}
		}
		
		result = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++) {
			int a = Integer.parseInt(st.nextToken());
			
			int parent = find(a);
			result.add(parent);
		}
		
		int fp = result.get(0);
//		bw.write("fp: " + fp + "\n");
		boolean flag = true; 
		
		for(int i=1; i<result.size(); i++) {
			int b = result.get(i);
//			bw.write(i+ " : " + b + "\n");
			
			if(fp != b) {
				bw.write("NO\n");
				flag = false;
				break;
			}
		}
		
		if(flag) bw.write("YES\n");
		
		bw.flush();
		bw.close();
		
	}
	
	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b); 
		
		if(x == y) return;
		
		if(rank[x] < rank[y]) city[x] = y;
		else if(rank[x] > rank[y]) city[y] = x;
		else {
			city[x] = y;
			rank[y]++;
		}
		
	}
	
	private static int find(int a) {
		while(city[a] != a) {
			city[a] = city[city[a]];
			a = city[a];
		}
		return a;
	}
}