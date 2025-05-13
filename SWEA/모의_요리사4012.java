import java.io.*;
import java.lang.*;
import java.util.*;

public class 모의_요리사4012 {

    static int N;
    static int[][] arr;
    static boolean[] selected;
    static int result;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=testCase; tc++) {
            
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            selected = new boolean[N];
            result = Integer.MAX_VALUE;
            comb(0, selected, 0);
            
            sb.append("#" + tc + " " + result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    // 조합
    static void comb(int start, boolean[] selected, int count) {
        
        if(count == N/2) {
            check(selected);
            return;
        }
        
        for(int i=start; i<N; i++) {
            if(!selected[i]) {
                selected[i] = true;
                comb(i+1, selected, count+1);
                selected[i] = false;
            }
        }
        
    }
    
    static void check(boolean[] selected) {
        
        int select = 0;
        int notSelect = 0;
        
        for(int i=0; i<N; i++) {
            if(selected[i]){
                for(int j=0; j<N; j++) {
                	if(selected[j] && i != j) {
//                		System.out.println("select " + i + " " + j);
                		select += arr[i][j];
                	}
                }
            }
            if(!selected[i]){
                for(int j=0; j<N; j++) {
                	if(!selected[j] && i != j) {
//                		System.out.println("notSelect " + i + " " + j);
                		notSelect += arr[i][j];
                	}
                }
            }
        }
        
//        System.out.println(select + " " + notSelect);

        result = Math.min(Math.abs(select - notSelect), result);
    }
}