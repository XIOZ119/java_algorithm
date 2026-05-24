import java.util.*;

class Solution {
    
    static class Plan {
        String task;
        int start;
        int time;
        
        Plan(String task, int start, int time) {
            this.task = task;
            this.start = start;
            this.time = time;
        }
    }
    
    public String[] solution(String[][] plans) {
        PriorityQueue<Plan> pq = new PriorityQueue<>((a, b) -> {
            return a.start - b.start;
        });
        
        for(int i=0; i<plans.length; i++) {
            String t = plans[i][0];
            String s = plans[i][1];
            String time = plans[i][2];

            int hour = Integer.parseInt(s.split(":")[0]);
            int min = Integer.parseInt(s.split(":")[1]);
            int hm = 60 * hour + min;
            
            Plan p = new Plan(t, hm, Integer.parseInt(time));
            pq.add(p);
        }
        
        Stack<Plan> stack = new Stack<>();
        Queue<String> q = new LinkedList<>();
        
        while(!pq.isEmpty()) {
            Plan cur = pq.poll(); 
            
            if(!pq.isEmpty()) {
                Plan next = pq.poll();
                
                int dis = cur.time - (next.start - cur.start);
                if(dis > 0) {
                    cur.time = dis;
                    stack.add(cur);
                } else {
                    q.add(cur.task);
                    
                    while(!stack.isEmpty()) {
                        Plan p = stack.pop();
                        
                        dis += p.time;
                        if(dis > 0) {
                            p.time = dis;
                            stack.push(p);
                            break;
                        } else {
                            q.add(p.task);
                        }
                    }
                }
                
                pq.add(next);
            } else {
                q.add(cur.task);
                
                while(!stack.isEmpty()) {
                    Plan p = stack.pop();
                    q.add(p.task);
                }
            }
        }
        
        String[] answer = new String[plans.length];
        for(int i=0; i<answer.length; i++) {
            answer[i] = q.poll();
        }
        
        return answer;
    }
}