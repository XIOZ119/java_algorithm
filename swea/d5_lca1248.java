package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class d5_lca1248 {
	private static ArrayList<Integer>[] tree;
	private static int[] depthArray;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			int node = Integer.parseInt(st.nextToken());
			int line = Integer.parseInt(st.nextToken());
			int F1 = Integer.parseInt(st.nextToken());
			int F2 = Integer.parseInt(st.nextToken());
			
			tree = new ArrayList[node+1];
			depthArray = new int[node+1];
			visited = new boolean[node+1];
			
			for(int i=1; i<=node; i++) {
				tree[i] = new ArrayList<>();
			}

			String[] strNode = br.readLine().split(" ");
			for(int i=0; i<line; i++) {
				int a = Integer.parseInt(strNode[2*i]);
				int b = Integer.parseInt(strNode[2*i+1]);
				
				tree[a].add(b);
				tree[b].add(a);
			}
			
			dfs(1, 0);
			
			int parent = find(F1, F2);
			
			visited = new boolean[node+1];
			int count = count(parent, depthArray[parent], 1);
			
			System.out.println("#" + test + " " + parent + " " + count);
		}

	}
	
	private static void dfs(int node, int depth) {
		depthArray[node] = depth;
		
		visited[node] = true;
		 
		for(int child: tree[node]) {
			if(visited[child] == false) {
				dfs(child, depth+1);
			}
		}
	}
	
	private static int count(int node, int depth, int count) {
		visited[node] = true;
		 
		for(int child: tree[node]) {
			if(visited[child] == false && depthArray[child] == depth + 1) {
				count = count(child, depth+1, count+1);
			}
		}
		
		return count;
	}
	
	private static int find(int a, int b) {
		int depthA = depthArray[a];
		int depthB = depthArray[b];

		int parentA = -1;
		int parentB = -1;
		
		// 깊이 같다면 
		if(depthA == depthB) {
			for(int child: tree[a]) {
				if(depthArray[child] == depthA - 1) {
					parentA = child;
				}
			}
			
			for(int child: tree[b]) {
				if(depthArray[child] == depthB - 1) {
					parentB = child;
				}
			}
			
			if(parentA == parentB) {
				return parentA;
			} else {
				return find(parentA, parentB);
			}
			
		} else if(depthA > depthB) {
			int parent = 0;
			for(int child: tree[a]) {
				if(depthArray[child] == depthA - 1) {
					parent = child;
				}
			}
			return find(parent, b);
		} else if(depthA < depthB) {
			int parent = 0;
			for(int child: tree[b]) {
				if(depthArray[child] == depthB - 1) {
					parent = child;
				}
			}
			return find(a, parent);
		}

		return parentA;
	}
 	
	

}
