package swea;

import java.util.*;
import java.io.*;

public class d4_contact {
	class Main {
		private static ArrayList<Integer>[] tree;
	    private static boolean[] visited;
	    private static int[] depthArr;
	    
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        
	        for(int testCase = 1; testCase <= 2; testCase++) {
	            String[] input = br.readLine().split(" ");
	            int length = 101;
	            int start = Integer.parseInt(input[1]);
	            
	            tree = new ArrayList[length];
	            visited = new boolean[length];
	            depthArr = new int[length];
	            
	            for(int i=0; i<length; i++) {
	                tree[i] = new ArrayList<>();
	            }
	            
	            String[] inputTree = br.readLine().split(" ");
	            for(int i=0; i<inputTree.length/2; i++) {
	                int from = Integer.parseInt(inputTree[2*i]);
	                int to = Integer.parseInt(inputTree[2*i+1]);
	                
	                tree[from].add(to);
	            }
	            
	            bfs(start);

	            int maxDepth = 0;
	            int maxNode = 0;
	            
	            for(int i=0; i<depthArr.length; i++) {
	                if(depthArr[i] > maxDepth) {
	                    maxDepth = depthArr[i]; 
	                    maxNode = i;
	                } else if (depthArr[i] == maxDepth) {
	                    maxNode = Math.max(maxNode, i);
	                }
	            }
	            
	            bw.write("#" + testCase + " " + maxNode + "\n");
	        }
	        bw.flush();
	        bw.close();
	        
	    }
	    
	    private static void bfs(int i) {
	        Queue<Integer> queue = new LinkedList<>();
	        queue.add(i);
	        visited[i] = true;
	        depthArr[i] = 0;
	        
	        while ( !queue.isEmpty()) {
	            int node = queue.poll();
	            for(int child: tree[node]) {
	                if(!visited[child]) {
	                    visited[child] = true;
	                    depthArr[child] = depthArr[node] + 1; // 부모 노드 깊이보다 + 1
	                    queue.add(child);
	                }
	            }
	        }
	    }
	}
}
