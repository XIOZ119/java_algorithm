import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
       StringTokenizer st = new StringTokenizer(br.readLine());
       
       int N = Integer.parseInt(st.nextToken());
       long M = Long.parseLong(st.nextToken());
        
       long[] arr = new long[N];
       
       st = new StringTokenizer(br.readLine());
       
       long min = 1;
       long max = Long.MIN_VALUE;
       
       for(int i=0; i<N; i++) {
    	   arr[i] = Long.parseLong(st.nextToken());
    	   max = Math.max(max, arr[i]);
       }
       
       long result = 0;
       
       while(true) {
    	   long cuttedTree = 0;
    	   
    	   long cuttingLength = (min+max)/2;
    	   
    	   for(int i=0; i<N; i++) {
    		   if(arr[i] > cuttingLength) cuttedTree += arr[i] - cuttingLength;
    	   }
    	   
    	   if(cuttedTree >= M) {
    		   min = cuttingLength+1;
    		   result = cuttingLength; 
    	   }
    	   else max = cuttingLength;

    	   if(min == max) {
    		   break;
    	   }
    	   
       }
       
       bw.write(result + "");
       bw.flush();
       bw.close();
    }
}