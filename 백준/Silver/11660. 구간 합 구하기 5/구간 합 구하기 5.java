import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        
        int size = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int arr[][] = new int[size+1][size+1];

        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result[][] = new int[size+1][size+1];
    
        for(int i=1; i<size+1; i++){
            for(int j=1; j<size+1; j++) {
                result[i][j] = result[i-1][j] + result[i][j-1] + arr[i-1][j-1] - result[i-1][j-1];  
            }
        }

    
        for(int test=0; test<t; test++){
            st = new StringTokenizer(br.readLine());
            
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
    
            sb.append(result[x2][y2] - result[x1-1][y2] - result[x2][y1-1] + result[x1-1][y1-1]);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}