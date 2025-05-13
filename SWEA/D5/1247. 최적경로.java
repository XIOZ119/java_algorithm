import java.io.*;
import java.lang.*;
import java.util.*;

public class 1247. 최적경로{

    static int N;
    static int homeX, homeY;
    static int companyX, companyY;
    static boolean[] visited;
    static Guest[] guest;
    static int result;

    static class Guest {
        int x;
        int y;

        public Guest(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=testCase; tc++) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            companyX = Integer.parseInt(st.nextToken());
            companyY = Integer.parseInt(st.nextToken());
            homeX = Integer.parseInt(st.nextToken());
            homeY = Integer.parseInt(st.nextToken());
            
            guest = new Guest[N];

            for(int i=0; i<N; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                guest[i] = new Guest(x, y);
            }

            result = Integer.MAX_VALUE;
            visited = new boolean[N];
            comb(new ArrayList<Integer>());

            sb.append("#" + tc + " " + result + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void comb(ArrayList<Integer> select){

        if(select.size() == N) {
            cal(select);
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                select.add(i);
                comb(select);
                select.remove(select.size() - 1);
                visited[i] = false;
            }
        }
    }

    static void cal(ArrayList<Integer> select){

        int index = select.get(0);
        
        int sum = Math.abs(companyX - guest[index].x) + Math.abs(companyY - guest[index].y);

        for(int i=1; i<select.size(); i++){
            int past = select.get(i-1);
            int cur = select.get(i);

            sum += Math.abs(guest[past].x - guest[cur].x) + Math.abs(guest[past].y - guest[cur].y);
        }
        
        sum += Math.abs(guest[select.get(N-1)].x - homeX) + Math.abs(guest[select.get(N-1)].y - homeY);
        
        result = Math.min(result, sum);
    }
    
}
