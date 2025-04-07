import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static int N;
    static List<int []>[] graph;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }
        
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        
        int min = Integer.MAX_VALUE;
        int count = 0;
        
        while(count < 2) {
            count++;
            int a = dijk(1, v1);
            int b = dijk(v1, v2);
            int c = dijk(v2, N);
            
            if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE || c == Integer.MAX_VALUE) {
                continue; // 이번 경로는 유효하지 않으므로 무시
            }
            
            // a + b + c
            int result = a + b + c;
            min = Math.min(min, result);
            
            // v1 이랑 v2 자리 바꾸기
            int t = v1;
            v1 = v2;
            v2 = t;
        }
        
        if(min == Integer.MAX_VALUE) {
        	bw.write(-1 + "");
        }else {
        	bw.write(min + "");
        }
        bw.flush();
        bw.close();
    }
    
    private static int dijk(int start, int end) { 
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        PriorityQueue<int []> queue = new PriorityQueue<int[]>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        queue.add(new int[] {start, 0});
        distance[start] = 0;
        
        while(!queue.isEmpty()) {
            int[] q = queue.poll();
            
            int q1 = q[0]; // 점 
            int q2 = q[1]; // 거리
            
            if(distance[q1] < q2) continue;
            
	    	for(int[] g: graph[q1]) {
	    		int nextN = g[0];
	    		int nextD = q2 + g[1];
	    		if(nextD < distance[nextN]) {
	    			distance[nextN] = nextD;
	    			queue.add(new int[] {nextN, nextD});
	    		}
	    	}
            
        }
        return distance[end];
    }
}

