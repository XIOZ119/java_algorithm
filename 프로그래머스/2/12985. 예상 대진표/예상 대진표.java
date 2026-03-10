class Solution
{
    public int solution(int n, int a, int b)
    {
        int r = 0;
        
        int[] index = new int[n+1];
        for(int i=1; i<=n; i++){
            index[i] = i;
        }
        
        while(true){
            if(index[a] == index[b]) break;
            
            for(int i=1; i<=n; i++) {
                index[i] = index[i]/2 + index[i]%2; 
            }
            
            r++;
        }

        return r;
    }
}
