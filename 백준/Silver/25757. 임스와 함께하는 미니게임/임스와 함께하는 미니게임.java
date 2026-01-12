import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static String type;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        type = st.nextToken();

        int count = 0;
        int appliedPerson = 0;

        if ("Y".equals(type)) count = 1;
        else if ("F".equals(type)) count = 2;
        else if ("O".equals(type)) count = 3;

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String name = br.readLine();

            if (!set.contains(name)) {
                set.add(name);
                appliedPerson++;
            }
        }

        bw.write(appliedPerson / count + "");
        bw.flush();
        bw.close();
    }
}
