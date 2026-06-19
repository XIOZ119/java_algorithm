import java.util.*;

class Solution {
    
    static class Song {
        String genre;
        int play;
        int index;
        
        Song(String genre, int play, int index){
            this.genre = genre;
            this.play = play;
            this.index = index;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {

        ArrayList<int[]>[] list = new ArrayList[100];
        for(int i=0; i<100; i++) {
            list[i] = new ArrayList<>();
        }
        
        HashMap<String, Song> map = new HashMap<>();
        int index = 0;
        for(int i=0; i<genres.length; i++) {
            String g = genres[i];
            int p = plays[i];
            
            if(map.containsKey(g)) {
                Song s = map.get(g);
                s.play += p;
                map.put(g, s);
                
                list[s.index].add(new int[] {p, i});
            } else{
                Song s = new Song(g, p, index);
                map.put(g, s);
                index++;
                
                list[s.index].add(new int[]{p, i});
            }
        }
        
        ArrayList<Song> arr = new ArrayList<>();
        for(Song value: map.values()) {
            arr.add(value);
        }
        
        Collections.sort(arr, (a, b) -> {
            return b.play - a.play;
        });
        
        ArrayList<Integer> ans = new ArrayList<>();        
        for(int i=0; i<arr.size(); i++) {
            Song song = arr.get(i);
            ArrayList<int[]> l = list[song.index];
            
            Collections.sort(l, (a, b) -> {
                if(a[0] == b[0]) return a[1] - b[1];
                return b[0] - a[0];
            });
            
            ans.add(l.get(0)[1]);
            if(l.size() > 1) {
                ans.add(l.get(1)[1]);
            }
        }
        
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);            
        }
        
        return answer;
    }
}