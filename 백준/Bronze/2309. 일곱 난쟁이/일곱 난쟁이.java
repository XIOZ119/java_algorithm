import java.util.*;
import java.io.*;

public class Main {
	private static int[] arr;
	private static int[] result;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        arr = new int[9];
        result = new int[7];
        
        for(int i=0; i<9; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        comb(0, new ArrayList<Integer>());
        
        Arrays.sort(result);
        
        for(int i=0; i<7; i++) {
        	bw.write(result[i] + "\n");
        }
        
        bw.flush();
        bw.close();
    }
    
    private static void comb(int depth, List<Integer> list) {
    	
    	if(list.size() == 7) {
    		int sum = 0;
    		
    		for(int l: list) {
    			sum += arr[l];
    		}

    		if(sum == 100) {
    			for(int i=0; i<7; i++) {
    				result[i] = arr[list.get(i)];
    			}
    		}
    		return;
    	}
    	
    	for(int i=depth; i<9; i++) {
    		list.add(i);
    		comb(i+1, list);
    		list.remove(list.size() - 1);
    	}
    }

}