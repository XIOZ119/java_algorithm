import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] arr;
    private static boolean[] robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 길이
        K = Integer.parseInt(st.nextToken()); // 0 개수

        arr = new int[2*N]; // 내구성 배열
        robots = new boolean[2*N]; // 로봇 위치 배열

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0; // 시작 위치
        int end = N-1; // 끝 위치

        int round = 1;

        while(true) {
            // 1. 회전
            start = (start - 1 + 2*N) % (2*N);
            end = (end - 1 + 2*N) % (2*N);

            // 1-1. 끝 지점에 있는 로봇 사라지기
            if(robots[end]) robots[end] = false;

            // 2. 로봇 이동
            int point = end;
            while(true) {
                if(robots[point]) {
                    int nextPos = (point + 1) % (2*N);

                    if(!robots[nextPos] && arr[nextPos] != 0) {
                        robots[point] = false;
                        arr[nextPos]--;
                        robots[nextPos] = true;
                    }
                }

                if(point == start) break;

                point = (point - 1 + 2*N) % (2*N);
            }

            // 2-1. 끝 지점에 있는 로봇 사라지기
            if(robots[end]) robots[end] = false;

            // 3. 로봇 올리기
            if(!robots[start] && arr[start] != 0) {
                robots[start] = true;
                arr[start]--;
            }

            // 4. 0 개수 확인
            int cnt = 0;
            for(int i=0; i<2*N; i++) {
                if(arr[i] == 0) cnt++;
            }

            if(cnt >= K) break;

            round++;
        }


        bw.write(round + "");
        bw.flush();
        bw.close();
    }
}
