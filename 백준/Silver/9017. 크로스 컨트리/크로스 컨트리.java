import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] team = new int[N+1];
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                team[arr[i]]++;
            }

            int index = 1;
            HashMap<Integer, Integer> map = new HashMap<>();
            int[] countTeam = new int[N+1];
            int[] fiveScore = new int[N+1];
            for(int i=0; i<N; i++) {
                int teamIndex = arr[i];
                if(team[teamIndex] < 6) continue;
                else if(countTeam[teamIndex] >= 5) {
                    index++;
                    continue;
                }
                else if(countTeam[teamIndex] >= 4) {
                    fiveScore[teamIndex] = index;
                    countTeam[teamIndex]++;
                    index++;
                    continue;
                }

                map.put(teamIndex, map.getOrDefault(teamIndex, 0) + index);
                countTeam[teamIndex]++;
                index++;
            }

            int answerTeam = 0;
            int answerScore = Integer.MAX_VALUE;

            for(int i=1; i<N; i++) {
                if(!map.containsKey(i)) continue;

                int score = map.get(i);
                if(answerScore < score) continue;
                else if(answerScore == score && (fiveScore[answerTeam] < fiveScore[i])) continue;

                answerTeam = i;
                answerScore = score;
            }

            sb.append(answerTeam + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
