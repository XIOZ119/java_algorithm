class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int in[] = new int[1_000_001];
        int out[] = new int[1_000_001];
        
        for(int i=0; i<edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            
            out[a]++; 
            in[b]++;
        }
        
        int graph = 0;
        int eightG = 0;
        int stickG = 0;
        
        for(int i=1; i<=1_000_000; i++) {
            if(in[i] == 0 && out[i] == 0) continue;
            
            if(in[i] == 0 && out[i] >= 2) {
                answer[0] = i;
                
                graph = out[i];
            }
            
            if(out[i] == 0) stickG++;
            if(in[i] >= 2 && out[i] == 2) eightG++; 
        }
        
        answer[1] = (graph - eightG - stickG);
        answer[2] = stickG;
        answer[3] = eightG; 
        
        return answer;
    }
}