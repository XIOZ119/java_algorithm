import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<int []> list = new ArrayList<>();

        int lastDay = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken()); // 금액
            int d = Integer.parseInt(st.nextToken()); // 일자

            list.add(new int[] {d, p});

            lastDay = Math.max(lastDay, d);
        }

        Collections.sort(list, new Comparator<int []>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o2[0] == o1[0]) {
                    return o2[1] - o1[1]; // 금액 내림차순
                }

                return o2[0] - o1[0]; // 일자 내림차순
            }
        });

        int result = 0;

        for(int i=lastDay; i>0; i--) {
            int index = -1; 
            int maxMoney = 0;

            for(int j=0; j<list.size(); j++) {
                if(list.get(j)[0] >= i && list.get(j)[1] > maxMoney){
                    index = j;
                    maxMoney = list.get(j)[1];
                }
            }

            if(index != -1) {
                result += maxMoney;
                list.remove(index);
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}  