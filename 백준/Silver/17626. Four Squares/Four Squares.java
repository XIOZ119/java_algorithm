import java.util.*;
import java.io.*;

public class Main {
	static int n, num;
	static int[] arr;
	static int minCnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        
        num = 0;
        
        for(int i=0; i<251; i++) {
        	if(i * i > n) {
        		num = i;
        		break;
        	} 
        }
        
        arr = new int[num];
        
        for(int i=0; i<num; i++) {
        	arr[i] = i*i;
        }
        
        minCnt = Integer.MAX_VALUE;
        
        for(int i=num-1; i>-1; i--) {
        	recur(n-arr[i], 1, i);
        	if (minCnt == 1) break;
        }
        
        bw.write(minCnt + "");
        bw.flush();
        bw.close();
    }
    
    private static void recur(int i, int sNum, int k) {
    	if(sNum >= minCnt) return;
    	if(i == 0) {
    		minCnt = Math.min(minCnt, sNum);
    		return;
    	}
    	
    	for(int j=k; j>-1; j--) {
    		if(arr[j] <= i) {
    			recur(i-arr[j], sNum+1, j);
    		}
    	}
    	
		return;
    }
}  