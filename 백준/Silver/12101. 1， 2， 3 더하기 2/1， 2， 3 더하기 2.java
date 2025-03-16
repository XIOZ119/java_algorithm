import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int n;
	private static ArrayList<int []> result;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        arr = new int[] {1, 2, 3};
        result = new ArrayList<>();
        
        perm(0, new ArrayList<Integer>());
        
        if(result.size() > k-1) {
        	int[] r = result.get(k-1);
        	
        	for(int i=0; i<r.length; i++) {
        		if(i == r.length-1) bw.write(r[i] + "");
        		else bw.write(r[i] + "+");
        	}
        } else {
        	bw.write("-1");
        }
        
        bw.flush();
        bw.close();
    }
    
    private static void perm(int sum, List<Integer> list) {
    	
    	if(sum == n) {
    		int[] r = new int[list.size()];
    		
    		for(int i=0; i<list.size(); i++) {
    			r[i] = list.get(i);
    		}
    		result.add(r);
    		return;
    	}
    	
    	if(sum > n) return;
    	
    	for(int i=0; i<3; i++) {
    		list.add(arr[i]);
    		perm(sum + arr[i], list);
    		list.remove(list.size() - 1);
    	}
    }

}