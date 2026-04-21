import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            ArrayList<int[]>[] list = new ArrayList[n+1];
            for(int i=0; i<n+1; i++) {
                list[i] = new ArrayList<>();
            }
            boolean[] canBack = new boolean[n+1];

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                canBack[s] = true;
                canBack[e] = true;

                list[s].add(new int[]{e, t});
                list[e].add(new int[]{s, t});
            }

            for(int i=0; i<w; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                canBack[e] = true;

                list[s].add(new int[] {e, (t * -1)});
            }

            boolean flag = false;
            for(int i=1; i<=n; i++) {
                if(!canBack[i]) continue;

                int[] dikst = new int[n+1];
                Arrays.fill(dikst, Integer.MAX_VALUE);
                Queue<Integer> que = new LinkedList<>();

                boolean isFirst = true;
                dikst[i] = 0;
                que.offer(i);

                while(!que.isEmpty()){
                    int cur = que.poll();
                    if(cur == i && !isFirst) break;
                    isFirst = false;

                    for(int[] l: list[cur]){
                        int nx = l[0];
                        int nd = l[1];

                        if(dikst[cur] + nd >= dikst[nx]) continue;
                        if(dikst[cur] + nd < -10_000 * n) continue;

                        dikst[nx] = dikst[cur] + nd;
                        que.offer(nx);
                    }
                }

                if(dikst[i] < 0) {
                    flag = true;
                    break;
                }
            }

            if(flag) sb.append("YES \n");
            else sb.append("NO \n");
        }

        System.out.println(sb.toString());
    }
}