import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        /* =============== 초기 세팅 =============== */
        int n = Integer.parseInt(br.readLine()); // 도시 개수
        int m = Integer.parseInt(br.readLine()); // 버스 개수

        List<int[]>[] list = new List[n+1];
        for(int i=0; i<n+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()); // 도착 도시
            int c = Integer.parseInt(st.nextToken()); // 시간

            list[a].add(new int[]{b,c});
        }

        int[][] dijak = new int[n+1][n+1];
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(i == j) dijak[i][j] = 0;
                else dijak[i][j] = Integer.MAX_VALUE;
            }
        }

        /* =============== 다익스트라 =============== */
        for(int i=1; i<n+1; i++){
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);

            while(!queue.isEmpty()){
                int cur = queue.poll();

                for(int[] l: list[cur]){
                    int endCity = l[0];
                    int distance = l[1];

                    if(dijak[i][endCity] <= dijak[i][cur] + distance) continue;

                    dijak[i][endCity] = dijak[i][cur] + distance;
                    queue.add(endCity);
                }
            }
        }

        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(dijak[i][j] == Integer.MAX_VALUE) sb.append("0").append(" ");
                else sb.append(dijak[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
