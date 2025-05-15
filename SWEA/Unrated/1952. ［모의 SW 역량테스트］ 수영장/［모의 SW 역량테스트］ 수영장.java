import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {

	static int day, monthPrice, threeMonth, year;
	static int[] plan;
	static int[] minPrice;
	static int result;
	static boolean[] tMonth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=testCase; tc++) {
        	st = new StringTokenizer(br.readLine());
        	
        	day = Integer.parseInt(st.nextToken());
        	monthPrice = Integer.parseInt(st.nextToken());
        	threeMonth = Integer.parseInt(st.nextToken());
        	year = Integer.parseInt(st.nextToken());
        	
        	plan = new int[13];
        	
        	st = new StringTokenizer(br.readLine());
        	for(int i=1; i<13; i++) {
        		plan[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	result = year;
        	
        	dp(1, 0);
        	
        	sb.append("#" + tc + " " + result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static void dp(int month, int sum) {
    	if(sum > result) return;
    	
    	if(month > 12) {
    		result = sum;
    	} else {
    		dp(month+1, sum+(day*plan[month]));
    		dp(month+1, sum+monthPrice);
    		dp(month+3, sum+threeMonth);
    	}
    }
}
