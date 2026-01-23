import java.util.*;

class Solution {
    int[][] target;

    int[][] flip1; // 0번째 열 뒤집지 않음
    int[][] flip2; // 0번째 열 뒤집음
    int fn1 = 0, fn2 = 0;

    public int solution(int[][] beginning, int[][] target) {
        this.target = target;

        this.flip1 = copy(beginning);
        this.flip2 = copy(beginning);

        int R = beginning.length;
        int C = beginning[0].length;

        // 0번째 열 먼저 뒤집기
        fn2++;
        flipCol(flip2, 0);

        // 0번째 열 맞추기: 행 뒤집기 결정
        fn1 += flipRowByCol0(flip1);
        fn2 += flipRowByCol0(flip2);

        // i번째 '열' 맞추기
        boolean ok1 = true, ok2 = true;

        for (int col = 1; col < C; col++) {
            if (ok1) {
                if (!isColMatch(flip1, col)) {
                    fn1++;
                    flipCol(flip1, col);
                    if (!isColMatch(flip1, col)) ok1 = false; // 실패
                }
            }
            if (ok2) {
                if (!isColMatch(flip2, col)) {
                    fn2++;
                    flipCol(flip2, col);
                    if (!isColMatch(flip2, col)) ok2 = false; // 실패
                }
            }
        }

        // 둘 다 실패면 -1
        if (!ok1 && !ok2) return -1;
        if (ok1 && ok2) return Math.min(fn1, fn2);
        return ok1 ? fn1 : fn2;
    }

    // 0번째 열 기준으로 행을 뒤집어 0열을 target과 맞춤
    int flipRowByCol0(int[][] arr) {
        int num = 0;
        int R = arr.length;
        int C = arr[0].length;

        for (int r = 0; r < R; r++) {
            if (arr[r][0] != target[r][0]) {
                num++;
                for (int c = 0; c < C; c++) {
                    arr[r][c] ^= 1;
                }
            }
        }
        return num;
    }

    void flipCol(int[][] arr, int col) {
        for (int r = 0; r < arr.length; r++) {
            arr[r][col] ^= 1; // 0과 1을 서로 바꾸는 코드 (XOR)
        }
    }

    boolean isColMatch(int[][] arr, int col) {
        for (int r = 0; r < arr.length; r++) {
            if (arr[r][col] != target[r][col]) return false;
        }
        return true;
    }

    int[][] copy(int[][] a) {
        int[][] b = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) b[i] = Arrays.copyOf(a[i], a[i].length);
        return b;
    }
}