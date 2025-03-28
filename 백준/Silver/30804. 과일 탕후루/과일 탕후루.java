import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMaxLength(S, N));
    }

    private static int getMaxLength(int[] S, int N) {
        Map<Integer, Integer> fruitCount = new HashMap<>(); // 과일 개수 저장
        int left = 0, right = 0, maxLength = 0;

        while (right < N) {
            fruitCount.put(S[right], fruitCount.getOrDefault(S[right], 0) + 1);

            // 과일 종류가 2개를 초과하면 왼쪽 포인터 이동
            while (fruitCount.size() > 2) {
                fruitCount.put(S[left], fruitCount.get(S[left]) - 1);
                if (fruitCount.get(S[left]) == 0) {
                    fruitCount.remove(S[left]);
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}