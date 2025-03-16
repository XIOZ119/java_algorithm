import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static boolean[] visited;
	private static int N;
	private static int max;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        max = 0;
        perm(new ArrayList<Integer>());
        
        bw.write(max + "");
        bw.flush();
        bw.close();
    }
    
    private static void perm(List<Integer> list) {
    	
    	if(list.size() == N) {
    		int sum = 0;
    		for(int i=0; i<N-1; i++) {
    			sum += Math.abs(list.get(i) - list.get(i+1));
    		}
    		max = Math.max(sum, max);
    	}
    	
    	for(int i=0; i<N; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			list.add(arr[i]);
    			perm(list);
    			
    			list.remove(list.size() - 1);
    			visited[i] = false;
    		}
    	}
    }
}