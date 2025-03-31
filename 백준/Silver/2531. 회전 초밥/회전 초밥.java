import java.util.*;
import java.io.*;

public class Main {
    static int N, d, k, c, max;
    static int[] belt, sushiCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시 수
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        belt = new int[N];
        sushiCount = new int[d + 1]; // 초밥 가짓수만큼 배열 생성
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getMaxSushi());
    }

    private static int getMaxSushi() {
        int uniqueCount = 0; // 현재 윈도우에서 고유한 초밥 개수

        // 초기 k개의 초밥 세팅
        for (int i = 0; i < k; i++) {
            if (sushiCount[belt[i]] == 0) uniqueCount++; // 새로운 초밥이면 증가
            sushiCount[belt[i]]++;
        }

        max = uniqueCount;
        if (sushiCount[c] == 0) max++; // 쿠폰 초밥 추가 가능하면 +1

        // 슬라이딩 윈도우 적용
        for (int i = 1; i < N; i++) {
            int removeIdx = belt[i - 1]; // 제거되는 초밥
            int addIdx = belt[(i + k - 1) % N]; // 추가되는 초밥

            // 초밥 제거
            sushiCount[removeIdx]--;
            if (sushiCount[removeIdx] == 0) uniqueCount--; // 0이 되면 고유한 초밥 개수 감소

            // 초밥 추가
            if (sushiCount[addIdx] == 0) uniqueCount++; // 새 초밥이면 증가
            sushiCount[addIdx]++;

            // 쿠폰 초밥 포함 여부 확인
            int currentMax = (sushiCount[c] == 0) ? uniqueCount + 1 : uniqueCount;
            max = Math.max(max, currentMax);
        }

        return max;
    }
}