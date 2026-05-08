import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        int[] arr = new int[y+1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[x] = 0;
        
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x, 0}); 
        while(!que.isEmpty()) { 
            int[] cur = que.poll();
            int cx = cur[0]; int cd = cur[1];
            
            if(cx + n < arr.length && arr[cx + n] > cd + 1) {
                arr[cx + n] = cd + 1;
                que.add(new int[] {cx+n, cd+1});
            }
            if(cx * 2 < arr.length && arr[cx * 2] > cd + 1) {
                arr[cx * 2] = cd + 1;
                que.add(new int[] {cx*2, cd+1});
            }
            if(cx * 3 < arr.length && arr[cx * 3] > cd + 1) {
                arr[cx * 3] = cd + 1;
                que.add(new int[] {cx*3, cd+1});
            }
        }
        
        if(arr[y] == Integer.MAX_VALUE) return -1;
        return arr[y];
    }
}