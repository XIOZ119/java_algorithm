import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for(int i=0; i<N; i++){
            set.add(br.readLine());
        }

        for(int i=0; i<M; i++){
            String[] inputs = br.readLine().split(",");

            for(String s: inputs) {
                if(!set.contains(s)) continue;

                set.remove(s);
            }
            sb.append(set.size()).append("\n");
        }

        System.out.println(sb.toString());
    }
}