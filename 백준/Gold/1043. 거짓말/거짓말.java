import java.util.*;
import java.io.*;

public class Main {
    static int[] parent;
    static List<int[]> party;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티 수

        parent = new int[N + 1];
        party = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            parent[i] = i; // 자기 자신을 부모로 설정
        }

        st = new StringTokenizer(br.readLine());
        int tn = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수

        Set<Integer> truthSet = new HashSet<>();
        for (int i = 0; i < tn; i++) {
            int truthedPerson = Integer.parseInt(st.nextToken());
            truthSet.add(truthedPerson);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pn = Integer.parseInt(st.nextToken()); // 파티 참석자 수
            int[] members = new int[pn];

            for (int j = 0; j < pn; j++) {
                members[j] = Integer.parseInt(st.nextToken());
            }
            
            party.add(members);

            for (int j = 1; j < pn; j++) {
                union(members[0], members[j]); // 같은 파티 참석자들을 하나의 집합으로 결합
            }
        }

        // 모든 진실을 아는 사람들을 같은 집합으로 연결
        for (int person : truthSet) {
            parent[find(person)] = 0; // 0번 노드가 진실을 아는 그룹의 대표 노드 역할
        }

        count = 0;
        for (int[] members : party) {
            boolean canLie = true;
            for (int person : members) {
                if (find(person) == 0) { // 진실을 아는 그룹과 연결된 경우
                    canLie = false;
                    break;
                }
            }
            if (canLie) count++;
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA; // 대표 노드 통합
        }
    }
}