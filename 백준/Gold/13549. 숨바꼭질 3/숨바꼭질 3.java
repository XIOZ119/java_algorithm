import java.io.*;
import java.util.*;

public class Main {

    /*
     * 0 <= N <= 100_000, 0<= K <= 100_000
     * 걷는다면 1초 후 X + 1 or X - 1
     * 순간이동 한다면 0초 후 2 * X
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) { // 위치, 시간
                return o1[1] - o2[1];
            }
        });

        int[] d = new int[200_001];
        Arrays.fill(d, Integer.MAX_VALUE);

        pq.add(new int[] {N, 0});

        int result = Integer.MAX_VALUE;

        if(N == K) {
            result = 0;
        } else {
            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cp = cur[0];
                int ct = cur[1];
    
                if(cp == K) result = Math.min(ct, result);
    
                if(ct > result) break;
    
                if(isValid(cp+1) && d[cp+1] > ct+1) {
                    d[cp+1] = ct+1;
                    pq.add(new int[] {cp+1, ct+1});
                }
                if(isValid(cp-1) && d[cp-1] > ct+1) {
                    d[cp-1] = ct+1;
                    pq.add(new int[] {cp-1, ct+1});
                }
                if(isValid(cp*2) && d[2*cp] > ct){
                    d[2*cp] = ct;
                    pq.add(new int[] {2*cp, ct});
                }
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    static boolean isValid(int a) {
        return a >= 0 && a <= 200_000;
    }
}  