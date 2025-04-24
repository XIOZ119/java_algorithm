import java.io.*;
import java.util.*;

public class 개구리의 여행 {
    static int N; // map 크기
    static char[][] arr; // 돌 map 
    static int Q; // 확인할 loop
    static int[] point; // 시작점과 도착점
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int resultTime;
    static int[][][] recordT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new char[N+1][N+1];
        recordT = new int[N+1][N+1][6];

        for(int i=1; i<N+1; i++){
            String str = br.readLine();
            for(int j=1; j<N+1; j++) {
                arr[i][j] = str.charAt(j-1);
            }
        }

        Q = Integer.parseInt(br.readLine());
        
        for(int tc=0; tc<Q; tc++){
            st = new StringTokenizer(br.readLine());
            
            point = new int[4];
            
            for(int i=0; i<N+1; i++) {
            	for(int j=0; j<N+1; j++) {
            		for(int k=0; k<6; k++) {
            			recordT[i][j][k] = Integer.MAX_VALUE;
            		}
            	}
            }
            
            for(int p=0; p<4; p++){
                point[p] = Integer.parseInt(st.nextToken());
            }

            // 출력 쌓아놓고
            resultTime = Integer.MAX_VALUE;
            bfs(point[0], point[1]);
            
            if(resultTime == Integer.MAX_VALUE) {
            	sb.append("-1" + "\n");
            } else {
            	sb.append(resultTime + "\n");
            }
            
        }

        // 여기서 출력 내보내기
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // dfs 개구리 위치, 시간, 방문 배열, 점프 높이 
    // 가지치기 -> 배열에 최소 시간 기록 -> 이상일 경우 return

    /* 1. 4방향 탐색 
            위치 +- 점프 높이 이동
            가는 길에 # 있을 경우 이동 불가
            도착 지점이 # 혹은 S 일 경우 이동 불가
        2. 높이만 증가
            증가된 높이의 제곱 시간 증가 
        3. 높이 감소 ( 1 ~ K-1 시간 중 선택)
            +1 시간 증가 
        
    */
    
    // -> BFS, 다익스트라로 수정
    
    private static void bfs(int x, int y) {
    	// x, y, time, power 저장, time 작은 순
    	PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int []>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
    	
    	pq.add(new int[] {x, y, 0, 1});
    	recordT[x][y][1] = 0;
    	
    	while(!pq.isEmpty()) {
    		int[] cur = pq.poll();
    		int cx = cur[0];
    		int cy = cur[1];
    		int cTime = cur[2];
    		int cPower = cur[3];
    		
    		if(cx == point[2] && cy == point[3]){
                resultTime = cTime;
                return;
            }
    		
    		for(int i=0; i<4; i++){
                int nx = cx;
                int ny = cy;
                int p = 1;
                boolean flag = true;
                
                while(p <= cPower){
                    nx += dx[i];
                    ny += dy[i];

                    if(!isValid(nx, ny) || arr[nx][ny] == '#') {
                        flag = false;
                        break;
                    }

                    p++;
                }
                
                if(!flag) continue;
                // 도착지점이 S인 경우
                if(arr[nx][ny] == 'S' || recordT[nx][ny][cPower] <= cTime+1) continue;
                
                recordT[nx][ny][cPower] = cTime+1;
                pq.add(new int[] {nx, ny, cTime+1, cPower});
    		}
    		
    		if(cPower != 5) {
    			int newPower = cPower+1;
    			int newTime = cTime+(newPower*newPower);
    			if(recordT[cx][cy][newPower] > newTime) {
    				recordT[cx][cy][newPower] = newTime;
    				pq.add(new int[] {cx, cy, newTime, newPower});
    			}
            }
    		
    		for(int i=cPower-1; i>=1; i--) {
    			if(recordT[cx][cy][i] > cTime+1) {
    				recordT[cx][cy][i] = cTime+1;
    				pq.add(new int[] {cx, cy, cTime+1, i});
    			}
            }
    	}
    }

    private static boolean isValid(int x, int y) {
        return x > 0 && y > 0 && x < N+1 && y < N+1;
    }
}