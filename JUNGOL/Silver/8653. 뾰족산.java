import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        long[] H = new long[N]; 

        st = new StringTokenizer(br.readLine());
        int max = 0; 
        for(int i=0; i<N; i++) {
            H[i] = Long.parseLong(st.nextToken());

            if(H[i] > H[max]) max = i;
        }

        for(int i=0; i<Q; i++) {
            long num = Long.parseLong(br.readLine());
            
            if(H[max] == num) {
                System.out.println("T");
                continue;
            }

            boolean flag = false; 

            // 왼쪽 탐색 
            int left = 0;
            int right = max; 
            while(left < right) {
                int mid = (left + right) / 2;
                
                if(num == H[mid]) {
                    flag = true; 
                    break;  
                }

                if(num < H[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if(flag) {
                System.out.println("L");
                continue;
            }

            // 오른쪽 탐색 
            flag = false; 
            left = max;
            right = N;
            while(left < right) {
                int mid = (left + right) / 2;
                
                if(num == H[mid]) {
                    flag = true; 
                    break;  
                }

                if(num < H[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if(flag) System.out.println("R");
            else System.out.println("N");
        }
    }
}
