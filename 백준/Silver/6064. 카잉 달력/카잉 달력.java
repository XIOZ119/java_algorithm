import java.io.*;
import java.util.*;

public class Main {

    /* 
     * 3
        10 12 3 9
        10 12 7 2
        13 11 5 6
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int i=0; i<testCase; i++) {
            st = new StringTokenizer(br.readLine());
            Set<Integer> visited = new HashSet<>();

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int result = -1;

            // for(int j=0; j<=N; j++){
            //     int a = N * j + x;
            //     visited.add(a);
            // }

            // for(int j=0; j<=M; j++){
            //     int b = M * j + y;

            //     if(visited.contains(b)) {
            //         result = b;
            //         break;
            //     }
            //     visited.add(b);
            // }

            // N * j + x 와 M * j + y 가 같을 때를 찾는 최소 공배수 문제 
            // (Nj + x) = (Mj + y) -> (Nj + x) - y = Mj -> (Nj + x - y) % M == 0 나오면 같은 값

            // x = N * j + x 형태로 N의 배수씩 증가시켜가며 확인
            for (int j = 0; j < M; j++) {
                int a = N * j + x;  // x 값이 M과 맞는지를 찾는 과정
                if ((a - y) % M == 0) {  // a와 y가 맞는 나머지 값을 가지는지 확인
                    result = a;
                    break;
                }
            }

            sb.append(result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}  