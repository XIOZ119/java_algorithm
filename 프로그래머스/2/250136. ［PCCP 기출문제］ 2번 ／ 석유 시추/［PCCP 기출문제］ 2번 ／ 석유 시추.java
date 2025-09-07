import java.util.*;

class Solution {
    static int[][] lands;
    static int[][] groups;
    static int n, m;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static HashMap<Integer, Integer> map;

    public int solution(int[][] land) {
        lands = land;
        n = land.length;
        m = land[0].length;
        groups = new int[n][m];
        map = new HashMap<>();

        visited = new boolean[n][m];

        int answer = 0;
        int groupSize = 1;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(visited[i][j] || land[i][j] == 0) continue;
                group(i, j, groupSize);
                groupSize++;
            }
        }
        
        for(int i=0; i<m; i++) {
            boolean[] v = new boolean[map.size()+1];
            int sum = 0;
            for(int j=0; j<n; j++){
                if(groups[j][i] > 0 && !v[groups[j][i]]) {
                    v[groups[j][i]] = true;
                    sum += map.get(groups[j][i]);
                }
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    static void group(int x, int y, int num){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        int visit = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            groups[cx][cy] = num;
            visit++;

            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isValid(nx, ny) || visited[nx][ny]  || lands[nx][ny] == 0) continue;
                queue.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }

        map.put(num, visit);
    }

    static boolean isValid(int x, int y){
        return x > -1 && y > -1 && x < n && y < m;
    }
}