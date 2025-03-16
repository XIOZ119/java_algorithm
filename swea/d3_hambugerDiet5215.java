package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class d3_hambugerDiet5215 {
	private static int N, L;
	private static int[] happy;
	private static int[] calories;
	private static int maxHappy;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=testCase; tc++) {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	N = Integer.parseInt(st.nextToken()); // 재료 수 
        	L = Integer.parseInt(st.nextToken()); // 칼로리 제한 
        	
        	happy = new int[N]; 
        	calories = new int[N];
        	
        	for (int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		happy[i] = Integer.parseInt(st.nextToken());
        		calories[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	maxHappy = 0;
        	comb(new ArrayList<Integer>(), 0);
        	
        	sb.append("#" + tc + " " + maxHappy + "\n");
        	
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    private static void comb(List<Integer> selected, int start) {
    	
    	int sumCalory = 0; 
    	int sumHappy = 0;
    	
    	for(int index: selected) {
    		sumCalory += calories[index];
    		sumHappy += happy[index];
    	}
    	
    	if(sumCalory <= L) maxHappy = Math.max(sumHappy, maxHappy);
    	else return;
    	
    	for(int i=start; i<N; i++) {
    		selected.add(i);
    		comb(selected, i+1);
    		selected.remove(selected.size()-1);
    	}
    }
}