import java.io.*;
import java.util.*;

public class Main {
    static long max = 10_000_000_000_000_001L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken()) - 1;
        long M = Long.parseLong(st.nextToken());

        bw.write(countOnes(M) - countOnes(N) + "");
        bw.flush();
        bw.close();
    }

    static long countOnes(long n) {
        if(n <= 0) return 0;

        /*
            << 연산자 : 비트 왼쪽 이동
            1 << 1 의 경우, 1 = 0001 -> 왼쪽 한 칸 이동 -> 0010 -> 결과 2
            1 << 2 의 경우, 1 = 0001 -> 왼쪽 두 칸 이동 -> 0100 -> 결과 4
            즉, 1 << k = 2^k => k에 2 곱해지는 것
         */
        /*
            핵심 아이디어
            1의 자리: 0 1 0 1 0 1 0 1 반복 (패턴 길이 2)
            2의 자리: 0 0 1 1 0 0 1 1 반복 (패턴 길이 4)
            4의 자리: 0 0 0 0 1 1 1 1 반복 (패턴 길이 8)
         */

        long total = 0;
        long bit = 1;
        while (bit <= n) {
            long cycle = bit * 2; // 패턴 길이 ( bit << 1 / 2의 거듭제곱 만들 때 많이 씀 )

            long fullCycles = (n+1) / cycle; // 0~n까지 숫자 개수 - 실제로는 (n+1)개
            long remainder = (n+1) % cycle; // 패턴이 끝나고 남은 부분

            // 각 패턴마다 bit개의 1이 있음
            total += fullCycles * bit; // 완전한 패턴에서 등장한 1의 개수

            // remainder가 bit보다 크면
            // remainder-bit 만큼 1이 추가됨
            total += Math.max(0L, remainder - bit); // 남은 부분에서 추가로 등장하는 1

            // overflow 방지
            if(bit > Long.MAX_VALUE / 2) break;

            // 다음 비트 자리
            // 1 -> 2 -> 4 -> 8 -> 16
            bit <<= 1;
        }

        return total;
    }
}