import java.util.*;
import java.io.*;

public class Main {
    private static int m;
    private static int answer = Integer.MAX_VALUE;

    private static ArrayList<int[]> persons;
    private static ArrayList<int[]> hospitals;
    private static int[][] distance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); 
        m = Integer.parseInt(st.nextToken()); 

        persons = new ArrayList<>();
        hospitals = new ArrayList<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                String str = st.nextToken();
                if(str.equals("1")) persons.add(new int[]{i, j});
                if(str.equals("2")) hospitals.add(new int[]{i, j});
            }
        }

        distance = new int[persons.size()][hospitals.size()];
        getDistance();

        ArrayList<Integer> candi = new ArrayList<>();
        comb(0, candi);

        System.out.println(answer + "");
    }

    private static void getDistance(){
        for(int i=0; i<persons.size(); i++) {
            int[] person = persons.get(i);

            for(int j=0; j<hospitals.size(); j++) {
                int[] hospital = hospitals.get(j);

                distance[i][j] = Math.abs(person[0] - hospital[0]) + Math.abs(person[1] - hospital[1]);
            }
        }
    }

    private static void calDistance(ArrayList<Integer> candi) {
        int ans = 0;
        for(int i=0; i<persons.size(); i++) {
            int min = Integer.MAX_VALUE;
            for(int hospital: candi) {
                int d = distance[i][hospital];

                min = Math.min(min, d);
            }
            ans += min;
        }

        answer = Math.min(answer, ans);
    }

    private static void comb(int start, ArrayList<Integer> candi) {
        if(candi.size() == m) {
            calDistance(candi);
            return;
        }

        for(int i=start; i<hospitals.size(); i++) {
            candi.add(i);
            comb(i+1, candi);
            candi.remove(candi.size()-1);
        }
    }
}
