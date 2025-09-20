import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight; // 가중치 기준 오름차순
    }
}

public class Main {
    static int[] parent;

    // Find : 부모 노드 찾기 + 경로 압축
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // Union (두 집합 합치기)
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false; // 이미 같은 집합이면 사이클 발생

        parent[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        // 간선 리스트
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(u, v, weight));
        }

        // 부모 배열 초기화
        parent = new int[V+1];
        for(int i=0; i<=V; i++) parent[i] = i;

        // 간선 정렬
        Collections.sort(edges);

        int mstWeight = 0;
        List<Edge> mstEdges = new ArrayList<>();

        for(Edge edge : edges) {
            if (union(edge.u, edge.v)) {
                mstWeight += edge.weight;
                mstEdges.add(edge);
            }
        }

        // 결과 출력
        System.out.println(mstWeight);
    }
}
