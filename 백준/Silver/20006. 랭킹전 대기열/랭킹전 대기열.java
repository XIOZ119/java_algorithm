import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken()); // 플레이어 수
        int m = Integer.parseInt(st.nextToken()); // 방 정원

        int[] pL = new int[p];
        String[] pN = new String[p];
        for(int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine());
            pL[i] = Integer.parseInt(st.nextToken());
            pN[i] = st.nextToken();
        }

        ArrayList<Integer>[] room = new ArrayList[p];
        for(int i=0; i<p; i++) {
            room[i] = new ArrayList<>();
        }

        for(int i=0; i<p; i++) {
            int r = 0;
            while(r < p) {
                int roomSize = room[r].size();
                if(roomSize == m) {
                    r++;
                    continue;
                }

                if(roomSize == 0) {
                    room[r].add(i);
                    break;
                }

                int index = room[r].get(0);
                if(pL[i] > pL[index] + 10 || pL[i] < pL[index] - 10){
                    r++;
                    continue;
                }

                room[r].add(i);
                break;
            }
        }

        for(int i=0; i<p; i++) {
            if(room[i].isEmpty()) break;
            Collections.sort(room[i], (a, b) -> pN[a].compareTo(pN[b]));
            ArrayList<Integer> arr = room[i];

            if(room[i].size() == m) sb.append("Started! \n");
            else sb.append("Waiting! \n");

            for(int j=0; j<arr.size(); j++) {
                int index = arr.get(j);
                sb.append(pL[index] + " " + pN[index] + "\n");
            }
        }

        System.out.print(sb);
    }
}