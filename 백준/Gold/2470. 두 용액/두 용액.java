import java.util.*;
import java.io.*;

public class Main {
	// 산성 용액 1~10억, 알칼리성 용액 -1~-10억
	// 서로 다른 용액을 혼합해 특성값이 0에 가장 가까운 용액 만들어내는 두 용액 찾기
	// 산성 용액 2개일 경우도 있다.
	// 출력 - 오름차순, 특성값이 0에 가장 가까운 용액 만들어내는 경우 두 개 이상일 경우 아무거나 출력

	// 2개 선택 조합 -> 시간초과 N 이 10만... 인 걸 못 봤네 에라이
	// -99 -2 -1 4 98 | -99 -2 -1 1 4 40 | -99 98 4 -2 -1
	// 절대값 내림차순 정렬 -> 연속한 두 개씩 더한 값

	static int N;
	static long[] arr;
	static long result = Long.MAX_VALUE;
	static long[] sol = new long[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new long[N];

		PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
			@Override
			public int compare(long[] arg0, long[] arg1) {
				return (int) (arg1[1] - arg0[1]);
			}
		});

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			pq.add(new long[] { arr[i], Math.abs(arr[i]) });
		}

		long pastValue = pq.poll()[0];
		for (int i = 1; i < N; i++) {
			long[] cur = pq.poll();
			long currentValue = cur[0];

			if (result > Math.abs(pastValue + currentValue)) {
				result = Math.abs(pastValue + currentValue);
				if (pastValue > currentValue) {
					sol[0] = currentValue;
					sol[1] = pastValue;
				} else {
					sol[1] = currentValue;
					sol[0] = pastValue;
				}
			}

			pastValue = currentValue;
		}

		bw.write(sol[0] + " " + sol[1]);
		bw.flush();
		bw.close();
	}
}