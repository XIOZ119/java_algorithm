package D5;

import java.io.*;
import java.util.StringTokenizer;

public class 10507. 영어공부 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken()); // 영어 공부 한 날 수
			int p = Integer.parseInt(st.nextToken()); // 추가로 체크할 수 있는 날 수

			boolean[] visited = new boolean[1_000_001]; // 날짜 최대값은 1_000_000
			int maxDay = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int day = Integer.parseInt(st.nextToken());
				visited[day] = true;
				maxDay = Math.max(maxDay, day);
			}

			int left = 0;
			int right = 0;
			int result = 0;
			int fake = 0;

			while (right <= 1_000_000) {
				if (visited[right]) {
					right++;
				} else if (fake < p) {
					fake++;
					right++;
				} else {
					if (!visited[left]) fake--;
					left++;
				}
				result = Math.max(result, right - left);
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
