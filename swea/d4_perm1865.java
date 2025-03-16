package swea;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class d4_perm1865 {
	private static int N;
	private static double maxPercent;
	private static int[][] arr;
	private static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
     
        for(int tc=1; tc<=testCase; tc++) {
        	N = Integer.parseInt(br.readLine());
        	
        	arr = new int[N][N];
        	visited = new boolean[N];
        	
        	for(int i=0; i<N; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			arr[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	maxPercent = 0;
        	perm(0, 1.0);
        	
        	BigDecimal bd = new BigDecimal(maxPercent * 100);
        	bd = bd.setScale(6, RoundingMode.HALF_UP);
        	
        	sb.append("#" + tc + " " + bd.toPlainString() + "\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    private static void perm(int depth, double prob) {
    	
    	if(prob <= maxPercent) return;
    	
    	if(depth == N) {
    		maxPercent = Math.max(maxPercent, prob);
    		return;
    	}
    	
    	for(int i=0; i<N; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			
    			perm(depth+1, prob * (arr[depth][i] / 100.0));
    			
    			visited[i] = false;
    		}
    	}
    }
}