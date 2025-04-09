import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] list;
    static int baconMin;
    static int person;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N+1];
        
        for(int i=0; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            list[a].add(b);
            list[b].add(a);
        }
        
        baconMin = Integer.MAX_VALUE;
        person = -1;
        
        for(int i=1; i<N+1; i++) {
            bacon(i);
        }
        
        bw.write(person + "");
        bw.flush();
        bw.close();
    }    
    
    private static void bacon(int start) {
        int[] arr = new int[N+1];
        boolean[] visited = new boolean[N+1];
        
        for(int i=0; i<N+1; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<int []> pq = new PriorityQueue<int[]>(new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        pq.add(new int[] {start, 0});
        arr[start] = 0;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cx = cur[0];
            int cd = cur[1] + 1;
//            System.out.println(cx + " " + (cd-1));
            
            for(int l: list[cx]) {
                if(arr[l] > cd && !visited[l]) {
                	visited[l] = true;
                	arr[l] = cd;
                	pq.add(new int[] {l, cd});
                }
            }
        }
        
        int sum = 0;
        for(int i=1; i<N+1; i++) {
        	sum += arr[i];
        }
        
//        System.out.println("---" + start + " " + sum);
        
        if(baconMin >= sum) {
        	if(baconMin == sum) {
        		if(person > start) {
                	person = start;
        		}
        	}else {
        		baconMin = sum;
            	person = start;
        	}
        }
    }
}