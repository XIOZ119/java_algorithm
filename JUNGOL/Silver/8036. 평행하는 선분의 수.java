import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Integer, Integer> mapX = new HashMap<>(1 << 19);
        HashMap<Integer, Integer> mapY = new HashMap<>(1 << 19);
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            mapX.put(a, mapX.getOrDefault(a, 0) + 1);
            mapY.put(b, mapY.getOrDefault(b, 0) + 1);
        }

        long answer = 0;
        for(int value: mapX.values()) {
            answer += (long) value * (value - 1) / 2;
        }

        for(int value: mapY.values()) {
            answer += (long) value * (value - 1) / 2;
        }

        System.out.println(answer);
    }
}
