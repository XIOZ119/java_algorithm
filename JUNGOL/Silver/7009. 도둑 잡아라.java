import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int cnt = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++) {
            int num = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = N-1; 

            boolean flag = true;
            while(left <= right) {
                int mid = (left + right) / 2;
                int midNum = arr[mid];

                if(midNum == num) {
                    flag = false;
                    break;
                }

                if(midNum > num) right = mid - 1;
                else left = mid + 1;
            }

            if(!flag) continue;
            
            sb.append(num).append(' ');
            cnt++;
        }

        if(cnt == 0) bw.write("-1");
        else bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
}
