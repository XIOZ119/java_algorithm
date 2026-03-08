import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[1001];
        int first = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int loc = Integer.parseInt(st.nextToken());
            first = Math.min(first, loc);
            end = Math.max(end, loc);

            int height = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, height);

            arr[loc] = height;
        }

        int maxLeft = -1;
        int maxRight = -1;
        for(int i=first; i<=end; i++) {
            if(arr[i] == maxHeight) {
                if(maxLeft == -1) maxLeft = i;
                maxRight = i;
            }
        }
      
        int remember = 0;
        for(int i=first; i<maxLeft; i++) {
            if(remember > arr[i]) arr[i] = remember;
            else remember = arr[i];
        }

        remember = 0;
        for(int i=end; i>maxRight; i--){
            if(remember > arr[i]) arr[i] = remember;
            else remember = arr[i];
        }

        for (int i = maxLeft; i <= maxRight; i++) {
            arr[i] = maxHeight;
        }

        int sum = 0;
        for(int i=first; i<=end; i++) {
            sum += arr[i];
        }

        System.out.println(sum + "");

    }
}