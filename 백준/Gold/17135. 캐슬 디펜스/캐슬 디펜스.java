import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, D;
	static boolean[] archer;
	static List<int []> targetOrigin;
	static int maxCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 공격 거리 제한 거리 
		
		int[][] arr = new int[N][M]; 
		targetOrigin = new ArrayList<>(); // 적 위치
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) targetOrigin.add(new int[] {i, j});
			}
		}
		
		// comb 궁수 위치 조합 [N][0~M-1] 중 3개 (순서 상관X) 
		archer = new boolean[M];
		maxCnt = 0;
		
		comb(0, 0);
		
		bw.write(maxCnt + "");
		bw.flush();
		bw.close();
	}
	
	private static void comb(int start, int select) {
		List<int []> target = new ArrayList<>();
		
		// 처음에는 input 받은 것 그대로 사용했다가, 값을 삭제하는 행위로 인해 계속 복제...로 변경함...
		for(int[] t: targetOrigin) {
			target.add(t);
		}
		
		if(select == 3) {
			attack(target);
		}
		
		for(int i=start; i<M; i++) {
			if(!archer[i]) {
				archer[i] = true;
				comb(i+1, select+1);
				archer[i] = false;
			}
		}
	}
	
	private static void attack(List<int []> target) {
		// 적과의 거리 D 이하일 경우
		// 적과의 거리, x, y, index 저장
		List<int []> list = new ArrayList<>();
		List<int []> remove = new ArrayList<>();
		int count = 0;
		
		while(!target.isEmpty()) {
			for(int i=0; i<M; i++) {
				// 방문 처리한 궁수와
				if(archer[i]) {
					// 적과의 거리 계산
					for(int j=0; j<target.size(); j++) {
						int distance = Math.abs(target.get(j)[0] - N) + Math.abs(target.get(j)[1] - i);
						if(distance <= D) {
							list.add(new int[] {distance, target.get(j)[0], target.get(j)[1], j});
						}
					}
					
					// 정렬 : 거리 짧은 순 -> y 좌표 작은 순
					Collections.sort(list, new Comparator<int []>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[0] == o2[0]) {
								return o1[2] - o2[2];
							}
							return o1[0] - o2[0];
						}
					});
					
					// 가장 가까운 적 삭제 (list 맨 앞에 있는 것)
					if(!list.isEmpty()) {
						remove.add(list.get(0));
					}

					list.clear();
				}
			}
			
			// 적 삭제
			for(int[] r: remove) {
				int[] targetLoc = {r[1], r[2]};
				
				int index = -1;
				int size = target.size();
				
				for(int i=0; i<size; i++) {
					int[] t = target.get(i);
					
					
					if(t[0] == targetLoc[0] && t[1] == targetLoc[1]) {
						index = i;
						break;
					}
				}
				
				if(index != -1) {
					target.remove(index);
					count++;
				}
			}
			
			remove.clear();
			
			int size = target.size();
			
			for(int i=0; i<size; i++) {
				int[] t = target.get(0);
				
				target.remove(0);
				
				if(t[0]+1 < N) {
					target.add(new int[] {(t[0]+1), t[1]});
				}
			}
		}
		maxCnt = Math.max(maxCnt, count);
		
		return;
	}
	
}  