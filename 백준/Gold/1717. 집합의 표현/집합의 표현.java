import java.util.*;
import java.io.*;

public class Main {
	static int[] parent;
	static int[] rank; // 트리 높이 정보 저장 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		parent = new int[N+1];
		rank = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int what = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(what == 0) union(a, b);
			if(what == 1) {
				int parentA = find(a);
				int parentB = find(b);
				
				if(parentA == parentB) bw.write("YES" + "\n");
				else bw.write("NO" + "\n");
			}
		}
		bw.flush();
		bw.close();
		
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		// 이미 같은 집합일 경우 
		if(a == b) return;
		
		// union by rank
		if(rank[a] < rank[b]) parent[a] = b;
		if(rank[a] > rank[b]) parent[b] = a;
		else {
			parent[b] = a;
			rank[a]++;
		}
	}
	
	private static int find(int x) {
		while (x != parent[x]) {
			parent[x] = parent[parent[x]]; // 두 단계 위의 부모를 가리켜 경로 단축 
			x = parent[x];
		}
		return x;
	}
}