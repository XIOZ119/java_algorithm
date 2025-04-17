import java.util.*;
import java.io.*;

public class Solution {

  static int N;
  static boolean[] visited;
  static HashMap<Integer, int[]> map;
  static long result;

  // 1494. 사랑의 카운슬러 D4
  // 지렁이 두 마리 매칭.
  // 가능한 지렁이들이 움직인 벡터 합의 크기 작기
  // 점 A -> 점 B
  // 벡터 : | (x, y) = x * x + y * y
  // 모든 지렁이 매칭, 지렁이들이 움직인 벡터 합해 그 크기가 최소가 되도록

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    int testCase = Integer.parseInt(br.readLine());

    for (int tc = 1; tc < testCase + 1; tc++) {
      N = Integer.parseInt(br.readLine());
      map = new HashMap<>();
      visited = new boolean[N];

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        map.put(i, new int[] { a, b });
      }

      result = Long.MAX_VALUE;
      comb(new boolean[N], 0, 0);

      sb.append("#" + tc + " " + result + "\n");
    }

    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

  // ✨ 참고 아이디어 (힌트)
  // 전체 지렁이의 위치를 저장

  // N/2 마리만 선택하는 조합을 만든다

  // 선택된 지렁이는 +방향, 나머지는 -방향

  // 벡터 합=(전체+방향 좌표 합-전체-방향 좌표 합)
  // 벡터 합의 크기=x*x+y*y

  // 그 중 가장 작은 값을 result에 저장

  private static void comb(boolean[] visited, int start, int count) {

    if (count == N / 2) {
      int sumX = 0;
      int sumY = 0;

      for (int i = 0; i < N; i++) {
        if (visited[i]) {
          int m[] = map.get(i);
          sumX += m[0];
          sumY += m[1];
        } else {
          int m[] = map.get(i);
          sumX -= m[0];
          sumY -= m[1];
        }
      }

      long vectorLength = (long) sumX * sumX + (long) sumY * sumY;

      result = Math.min(result, vectorLength);

      return;
    }

    for (int i = start; i < N; i++) {
      if (!visited[i]) {
        visited[i] = true;
        comb(visited, i + 1, count + 1);
        visited[i] = false;
      }
    }
  }

}