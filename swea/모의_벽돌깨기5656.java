import java.io.*;
import java.util.*;

/*  [SWEA] 5656. 벽돌 깨기
    W x H 배열, 구슬은 N번만 쏠 수 있음.
    ① 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
    ② 벽돌은 숫자 1 ~ 9 로 표현되며, 구술이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 - 1 ) 칸 만큼 같이 제거된다.
    ③ 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.

    문제 전략
    1. 구현 (벽돌 제거-십자가, 연쇄 제거, 중력 처리)
    2. 완전 탐색 -> 순열 (구슬 위치 결정)
    ** 순열, BFS, 열 우선 순회 **
*/

public class 모의_벽돌깨기5656 {
    static int N, W, H;
    static int[][] arr;
    static int answer;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int tc=1; tc <= testCase; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken()); // y
            H = Integer.parseInt(st.nextToken()); // x

            arr = new int[H][W];
            int leftBlock = 0;
            // H * W

            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j] != 0) leftBlock++; 
                }
            }

            answer = H*W;

            // 순열
            perm(arr, 0, leftBlock);

            sb.append("#" + tc + " " + answer + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void perm(int[][] arr, int count, int leftBlock){

        if(count == N) {
            if(answer > leftBlock) answer = leftBlock;
            return;
        }

        // 열 선택
        for(int c=0; c<W; c++) {
            int[][] newArr = new int[H][W];

            // 배열 깊은 복사 
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    newArr[i][j] = arr[i][j];
                }
            }

            int shottedBlock = shoot(newArr, c);
            fall(newArr);
            perm(newArr, count + 1, leftBlock - shottedBlock);
        }

    }

    private static int shoot(int[][] newArr, int c) {
        int r = 0;
        while ( r < H && newArr[r][c] == 0 ) {
            r++;
        }

        if(r == H) return 0;

        int brokenBlock = 0;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {newArr[r][c] - 1, r, c});
        newArr[r][c] = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cr = cur[1];
            int cc = cur[2];
            brokenBlock++;
            
            for(int i=0; i<4; i++) {
                int count = cur[0];
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                while(count != 0){
                    if(!isValid(nr, nc) || newArr[nr][nc] == 0) {
                        nr += dr[i];
                        nc += dc[i];
                        count--;

                        continue;
                    }
                    
                    queue.add(new int[] {newArr[nr][nc] - 1, nr, nc});
                    newArr[nr][nc] = 0;

                    nr += dr[i];
                    nc += dc[i];
                    count--;
                }
            }
        }

        return brokenBlock;
    }

    private static void fall(int[][] newArr){
        for(int i=0; i<W; i++) {
            Stack<Integer> stack = new Stack<>();

            for(int j=0; j<H; j++) {
                if(newArr[j][i] != 0){
                    stack.push(newArr[j][i]);
                    newArr[j][i] = 0;
                }
            }

            for(int j=H-1; j>-1; j--) {
                if(stack.isEmpty()) break;
                newArr[j][i] = stack.pop();
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x > -1 && y > -1 && x < H && y < W;
    }
}
