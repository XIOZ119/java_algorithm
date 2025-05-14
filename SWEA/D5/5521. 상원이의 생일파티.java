import java.io.*;
import java.lang.*;
import java.util.*;

public class 5521. 상원이의 생일파티 {
	static int N, M;
	static ArrayList<Integer>[] graph;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=testCase; tc++) {
        	st = new StringTokenizer(br.readLine());
        	
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	
        	if(M == 0) {
        		sb.append("#" + tc + " " + 0 + "\n");
        		continue;
        	}
        	
        	graph = new ArrayList[N];
        	
        	for(int i=0; i<N; i++) {
        		graph[i] = new ArrayList<>();
        	}
        	
        	for(int i=0; i<M; i++){
        		st = new StringTokenizer(br.readLine());
        		
        		int a = Integer.parseInt(st.nextToken()) - 1;
        		int b = Integer.parseInt(st.nextToken()) - 1;
        		
        		graph[a].add(b);
        		graph[b].add(a);
        	}
        	
        	int result = giveCard();
            sb.append("#" + tc + " " + result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    private static int giveCard() {
    	boolean[] visited = new boolean[N];
    	Queue<int []> queue = new LinkedList<>();
    	
    	queue.add(new int[] {0, 1});
    	visited[0] = true;
    	
    	int count = 0;
    	
    	while(!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		int cn = cur[0];
    		int cc = cur[1];
    		
    		if(cc > 2) break;
    		
			for(int friend: graph[cn]) {
				if(!visited[friend]) {
					count++;
					visited[friend] = true;
					queue.add(new int[] {friend, cc+1});
				}
			}
    	}
    	
    	return count;
    }
}
