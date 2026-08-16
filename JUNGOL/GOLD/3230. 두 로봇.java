import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static ArrayList<int[]>[] list;
    static int[] robot1_arr;
    static int[] robot2_arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int robot1 = Integer.parseInt(st.nextToken());
        int robot2 = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); 

            list[a].add(new int[] {b, c});
            list[b].add(new int[] {a, c});
        }

        robot1_arr = new int[N+1];
        move(robot1, robot1_arr);
        robot2_arr = new int[N+1];
        move(robot2, robot2_arr);

        int answer = Integer.MAX_VALUE;

        for(int r1=1; r1<=N; r1++) {
            for(int[] r2: list[r1]) {
                answer = Math.min(answer, robot1_arr[r1] + robot2_arr[r2[0]]);
            }
        }

        if(answer == Integer.MAX_VALUE || robot1 == robot2) answer = 0;
        System.out.println(answer);
    }

    static void move(int start, int[] arr) {
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[start] = 0;

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {start, 0});

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int cx = cur[0];
            int cd = cur[1];

            for(int[] next: list[cx]) {
                int nx = next[0]; 
                int nd = next[1] + cd;

                if(arr[nx] <= nd) continue;

                que.add(new int[] {nx, nd});
                arr[nx] = nd;
            }
        }
    }
}
