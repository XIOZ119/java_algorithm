import java.util.*;
import java.io.*;

public class Main {
	// 적록색약 아닌 사람 : 빨강, 파랑, 초록
	// 적록색약인 사람 : 빨강-초록, 파랑
	// BFS 적록색약인 경우, 아닌 경우 나누어 계산
	// boolean visited 배열 -> 전역변수

	static int N;
	static String[][] arr;
	static boolean[][] visited;
	static boolean[][] visited2;

	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		arr = new String[N][N];
		visited = new boolean[N][N];
		visited2 = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) + "";
			}
		}

		int notRG = 0;
		int RG = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, "", arr[i][j]);
					notRG++;
				}
				if (!visited2[i][j]) {
					bfs(i, j, "적록색약", arr[i][j]);
					RG++;
				}
			}
		}

		bw.write(notRG + " " + RG);
		bw.flush();
		bw.close();
	}

	private static void bfs(int x, int y, String type, String color) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		if (!type.equals("적록색약"))
			visited[x][y] = true;
		else
			visited2[x][y] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cx = cur[0];
			int cy = cur[1];

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (!isValid(nx, ny))
					continue;

				if (!type.equals("적록색약")) {
					if (!visited[nx][ny] && color.equals(arr[nx][ny])) {
						visited[nx][ny] = true;
						queue.add(new int[] { nx, ny });
					}
				} else {
					if (!visited2[nx][ny]) {
						if ((color.equals("R") && (arr[nx][ny].equals("R") || arr[nx][ny].equals("G")))
								|| (color.equals("G") && (arr[nx][ny].equals("R") || arr[nx][ny].equals("G")))) {
							visited2[nx][ny] = true;
							queue.add(new int[] { nx, ny });
						} else {
							if (color.equals(arr[nx][ny])) {
								visited2[nx][ny] = true;
								queue.add(new int[] { nx, ny });
							}
						}
					}
				}
			}
		}
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}