import java.util.*;
import java.io.*;

public class Main {
    static int N, P, s;
    static int[] arr;
    static PriorityQueue<int[]> pq;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        s = 0;

        pq = new PriorityQueue<int[]>((a,b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int[] change = new int[] {-1, -1};

        for(int i=s+1; i<N-1; i++){
            if(arr[i-1] > arr[i] && arr[i+1] > arr[i]) {
                pq.add(new int[] {arr[i], i});
            }
        }

        while(P > 0) {
            if(change[0] != -1) {
                addCandidate(change[1] - 1);
                addCandidate(change[1]);
                addCandidate(change[1] + 1);
            }

            while(!pq.isEmpty()) {
                change = pq.poll();

                if(change[1] <= s || change[1] >= N-1) continue;
                if(change[0] != arr[change[1]]) continue;
                if(!(arr[change[1] - 1] > arr[change[1]] && arr[change[1] + 1] > arr[change[1]])) continue;

                int ab = arr[s];
                arr[s] = change[0];
                arr[change[1]] = ab;

                break;
            }

            s++;
            P--;
        }        

        for(int i=0; i<N; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void addCandidate(int idx) {
        if(idx <= s || idx >= N-1) return;

        if(arr[idx - 1] > arr[idx] && arr[idx + 1] > arr[idx]) {
            pq.add(new int[] {arr[idx], idx});
        }
    }
}