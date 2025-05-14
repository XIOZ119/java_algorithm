

import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {
	
	/* 
	 * 
	1
	3 3 // 정점의 개수 V, 간선의 개수 E 
	1 2 1
	2 3 2
	1 3 3
	 * */
	
	static int V, E;
	static ArrayList<int []>[] graph;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=testCase; tc++) {
        	st = new StringTokenizer(br.readLine());
        	
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	
        	graph = new ArrayList[V];
        	
        	for(int i=0; i<V; i++) {
        		graph[i] = new ArrayList<>();
        	}
        	
        	for(int i=0; i<E; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		int a = Integer.parseInt(st.nextToken()) - 1;
        		int b = Integer.parseInt(st.nextToken()) - 1;
        		int c = Integer.parseInt(st.nextToken()); // 가중치
        		
        		graph[a].add(new int[] {b, c});
        		graph[b].add(new int[] {a, c});
        	}
        	
//        	if(isConnect()) {
        		long result = mst();
        		sb.append("#" + tc + " " + result + "\n");
//        	} else {
//        		sb.append("#" + tc + " " + 0 + "\n");
//        	}
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static long mst() {
    	boolean[] visited = new boolean[V];
//    	int[] d = new int[V];
//    	Arrays.fill(d, Integer.MAX_VALUE);
    	PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
    	});
    	
    	pq.add(new int[]{0, 0});
    	
    	long sum = 0;
    	int visitedCount = 0;
    	
    	while(!pq.isEmpty() && visitedCount < V) {
    		int[] cur = pq.poll();
    		
    		int point = cur[0];
    		int cost = cur[1];
    		
    		if (visited[point]) continue; // 이미 MST에 포함된 정점은 skip
            visited[point] = true;
            sum += cost;
            visitedCount++;
    		
    		for(int[] next: graph[point]) {
    			int np = next[0];
    			int nd = next[1];
    			
    			if(!visited[np]) {
    				pq.add(new int[] {np, nd});
    			}
    		}
    	}
    	
    	
    	return sum;
    }
        
    static boolean isConnect() {
    	boolean[] visited = new boolean[V];
    	Queue<Integer> queue = new LinkedList<>();
    	
    	queue.add(0);
    	visited[0] = true;
    	int count = 1;
    	
    	while(!queue.isEmpty()) {
    		int cur = queue.poll();
    		
    		for(int[] next: graph[cur]) {
    			int ni = next[0];
    			if(!visited[ni]) {
    				count++;
    				visited[ni] = true;
    				queue.add(ni);
    			}
    		}
    	}
    	
    	if(count == V) return true;
    	else return false;
    }
}
