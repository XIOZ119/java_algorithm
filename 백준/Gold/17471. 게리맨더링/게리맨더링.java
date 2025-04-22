import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static List<Integer>[] graph;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				int node = Integer.parseInt(st.nextToken());

				graph[i].add(node - 1);
				graph[node - 1].add(i);
			}
		}

		// 조합 -> 연결되었는지 판단하기
		result = Integer.MAX_VALUE;
		comb(new boolean[N], 0, 0);

		if (result == Integer.MAX_VALUE) {
			bw.write("-1");
		} else {
			bw.write(result + "");
		}
		bw.flush();
		bw.close();
	}

	private static void comb(boolean[] color, int count, int start) {

		if (count != 0 && count != N) {
			// 연결 확인
			boolean[] red = new boolean[N];
			int r = 0;
			boolean[] blue = new boolean[N];
			int b = 0;

			int sumRed = 0;
			int sumBlue = 0;

			for (int i = 0; i < N; i++) {
				if (color[i]) {
					red[i] = true;
					r = i;
					sumRed += arr[i];
				} else {
					blue[i] = true;
					b = i;
					sumBlue += arr[i];
				}
			}

			if (isConnected(red, r) && isConnected(blue, b)) {
				result = Math.min(result, Math.abs(sumRed - sumBlue));
			}
		}

		if (count == N)
			return;

		for (int i = start; i < N; i++) {
			if (!color[i]) {
				color[i] = true;
				comb(color, count + 1, i + 1);
				color[i] = false;
			}
		}
	}

	private static boolean isConnected(boolean[] selected, int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];

		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int c : graph[cur]) {
				if (!visited[c] && selected[c]) {
					visited[c] = true;
					queue.add(c);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				if (!visited[i])
					return false;
			}
		}

		return true;
	}
}