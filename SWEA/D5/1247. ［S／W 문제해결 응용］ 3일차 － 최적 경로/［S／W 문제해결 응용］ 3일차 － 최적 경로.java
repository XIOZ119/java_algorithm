import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static ArrayList<int []> list;
    static boolean[] visited;
    static int minD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=testCase; tc++){
            N = Integer.parseInt(br.readLine()) + 2; // 회사, 집, 고객의 수

            st = new StringTokenizer(br.readLine());
            list = new ArrayList<>();
            
            for(int i=0; i<N; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new int[] {x, y});
            }

            visited = new boolean[N];
            minD = Integer.MAX_VALUE;
            // 순열 
            perm(new ArrayList<>());

            sb.append("#").append(tc).append(" ").append(minD).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    } 
    
    static void perm(ArrayList<int[]> selected) {

        if(selected.size() == N-2){

            selected.add(list.get(1));
            int distance = 0;
            int[] company = list.get(0);
            int[] guest = selected.get(0);

            distance += Math.abs(company[0] - guest[0]) + Math.abs(company[1] - guest[1]);

            for(int i=1; i<selected.size(); i++) {
                int[] past = selected.get(i-1);
                int[] cur = selected.get(i);

                distance += Math.abs(past[0] - cur[0]) + Math.abs(past[1] - cur[1]);
            }

            selected.remove(selected.size() - 1);
            minD = Math.min(distance, minD);
        }

        for(int i=2; i<N; i++){
            if(!visited[i]) {
                visited[i] = true;
                selected.add(list.get(i));
                perm(selected);
                selected.remove(selected.size() - 1);
                visited[i] = false;
            }
        }
    }
}
