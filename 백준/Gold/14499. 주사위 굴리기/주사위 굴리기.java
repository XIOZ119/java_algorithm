import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    static int N, M, x, y, count;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1}; // 위, 아래, 왼, 오
    static int[] dice; // 주사위 면에 적힌 수
    static int[] diceLoc; // 주사위 위치 (밑, 위, 앞, 뒤, 왼, 오)
    static int[] command; // 명령어 저장
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[6];
        diceLoc = new int[6];
        command = new int[count];
        result = new int[count];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<count; i++){
            command[i] = Integer.parseInt(st.nextToken());
        }

        diceLoc[0] = 5;
        diceLoc[1] = 0;
        diceLoc[2] = 4;
        diceLoc[3] = 1;
        diceLoc[4] = 3;
        diceLoc[5] = 2;

        Arrays.fill(result, -1);
        
        roll(x, y);

        for(int i=0; i<result.length; i++){
            if(result[i] == -1) continue;
            bw.write(result[i] + "\n");
        }
        
        bw.flush();
        bw.close();
    }

    static void roll(int x, int y){
        int[] temArr = new int[6];
        int nx = x;
        int ny = y;

        // 명령어 수만큼 돌아 
        // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
        for(int i=0; i<command.length; i++){
            int c = command[i];

            // System.out.println(nx + " " + ny + "");

            if(c == 1) {
                nx += dx[3];
                ny += dy[3];

                if(!isValid(nx, ny)) {
                    nx -= dx[3];
                    ny -= dy[3];
                    continue;
                }

                // 주사위 위치 기록
                temArr[0] = diceLoc[5];
                temArr[1] = diceLoc[4];
                temArr[2] = diceLoc[2];
                temArr[3] = diceLoc[3];
                temArr[4] = diceLoc[0];
                temArr[5] = diceLoc[1];
            } 
            else if(c == 2) {
                nx += dx[2];
                ny += dy[2];

                if(!isValid(nx, ny)) {
                    nx -= dx[2];
                    ny -= dy[2];
                    continue;
                }
                // 주사위 위치 기록
                temArr[0] = diceLoc[4];
                temArr[1] = diceLoc[5];
                temArr[2] = diceLoc[2];
                temArr[3] = diceLoc[3];
                temArr[4] = diceLoc[1];
                temArr[5] = diceLoc[0];
            }
            else if(c == 3) {
                nx += dx[0];
                ny += dy[0];

                if(!isValid(nx, ny)) {
                    nx -= dx[0];
                    ny -= dy[0];
                    continue;
                }
                // 주사위 위치 기록
                temArr[0] = diceLoc[3];
                temArr[1] = diceLoc[2];
                temArr[2] = diceLoc[0];
                temArr[3] = diceLoc[1];
                temArr[4] = diceLoc[4];
                temArr[5] = diceLoc[5];
            }
            else if(c == 4) {
                nx += dx[1];
                ny += dy[1];

                if(!isValid(nx, ny)) {
                    nx -= dx[1];
                    ny -= dy[1];
                    continue;
                }
                // 주사위 위치 기록
                temArr[0] = diceLoc[2];
                temArr[1] = diceLoc[3];
                temArr[2] = diceLoc[1];
                temArr[3] = diceLoc[0];
                temArr[4] = diceLoc[4];
                temArr[5] = diceLoc[5];
            }

            if(map[nx][ny] != 0){
                dice[temArr[0]] = map[nx][ny];
                map[nx][ny] = 0;
            } else {
                map[nx][ny] = dice[temArr[0]];
            }

            for(int j=0; j<6; j++){
                diceLoc[j] = temArr[j];
            }

            result[i] = dice[diceLoc[1]];

        }

    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

}  