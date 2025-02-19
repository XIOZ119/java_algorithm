import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st; 

        int num = Integer.parseInt(br.readLine());
        int arr[][] = new int[num][2];
        
        for(int i=0; i<num; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());    
        }

        Arrays.sort(arr, (x1, x2) -> {
            if(x1[0] == x2[0]) return Integer.compare(x1[1], x2[1]);
            return Integer.compare(x1[0], x2[0]);
        });

        for (int i=0; i<num; i++) {
            bw.write(arr[i][0] + " " + arr[i][1] + "\n");
        }

        bw.flush();
        bw.close();
    }
}