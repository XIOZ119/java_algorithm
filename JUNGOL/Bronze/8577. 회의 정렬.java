import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = b-a;

            list.add(new int[]{i+1, a, b, d});
        }

        Collections.sort(list, (a, b) -> {
            if(a[3] == b[3]) {
                return a[1] - b[1];
            }
            return a[3] - b[3];
        });

        for(int[] l: list) {
            bw.write(l[0] + "\n");
        }

        bw.flush();
        bw.close(); 
    }
}
