import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st; 

        int num = Integer.parseInt(br.readLine());
        String arr[][] = new String[num][4];
        
        for(int i=0; i<num; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = st.nextToken();
            arr[i][1] = st.nextToken();  
            arr[i][2] = st.nextToken();
            arr[i][3] = st.nextToken();
        }

        Arrays.sort(arr, (a, b) -> {
            int k1 = Integer.parseInt(a[1]);
            int k2 = Integer.parseInt(b[1]);
            int e1 = Integer.parseInt(a[2]);
            int e2 = Integer.parseInt(b[2]);
            int m1 = Integer.parseInt(a[3]);
            int m2 = Integer.parseInt(b[3]);
            
            if(k1 == k2) {
                if(e1 == e2) {
                    if(m1 == m2) {
                        return a[0].compareTo(b[0]);
                    }
                    return m2 - m1;
                }
                return e1 - e2;
            }
            return k2 - k1;
        });

        for (int i=0; i<num; i++) {
            bw.write(arr[i][0] + "\n");
        }

        bw.flush();
        bw.close();
    }
}