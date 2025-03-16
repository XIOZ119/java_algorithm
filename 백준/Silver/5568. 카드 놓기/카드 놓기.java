import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static boolean[] visited;
	private static int n, k;
	private static HashSet<Integer> set;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        arr = new int[n];
        visited = new boolean[n];
        set = new HashSet<Integer>();
        
        for(int i=0; i<n; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        perm(0, new ArrayList<Integer>());
        
        bw.write(set.size() + "");
        bw.flush();
        bw.close();
    }
    
    private static void perm(int depth, List<Integer> list) {
    	
    	if(depth == k) {
    		String str = "";
    		for(int l: list) {
    			str += l + "";
    		}
    		
    		if(!set.contains(Integer.parseInt(str))) {
    			set.add(Integer.parseInt(str));
    		} else return;
    	}
    	
    	for(int i=0; i<n; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			list.add(arr[i]);
    			perm(depth+1, list);
    			
    			visited[i] = false;
    			list.remove(list.size() -1);
    		}
    	}
    }
}