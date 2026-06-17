import java.util.*;

// 벽에 튕기는 경로를 직접 계산하지 말고, 벽 너머로 공을 대칭시켜 "직선 거리" 구하는 문제
class Solution {
    private static class Point{
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        Point border = new Point(m, n);
        Point start = new Point(startX, startY);
        
        for (int i=0; i<balls.length; i++) {
            Point ball = new Point(balls[i][0], balls[i][1]);
            List<Point> list = new ArrayList<>();
            
            if(!(start.x == ball.x && start.y > ball.y)) list.add(new Point(ball.x, ball.y * - 1)); // 아래 벽 반사
            if(!(start.x == ball.x && start.y < ball.y)) list.add(new Point(ball.x, border.y + (border.y - ball.y))); // 위 벽 반사
            if(!(start.y == ball.y && start.x > ball.x)) list.add(new Point(ball.x * - 1, ball.y)); // 왼쪽 벽 반사 (-x, y)
            if(!(start.y == ball.y && start.x < ball.x)) list.add(new Point(border.x + (border.x - ball.x), ball.y)); // 오른쪽 벽 반사(2*m - x, y)
            
            int min = Integer.MAX_VALUE;
            for(Point p: list){
                int dis = (int) Math.pow(Math.abs(p.x - start.x), 2) + (int) Math.pow(Math.abs(p.y - start.y), 2);
                
                min = Math.min(min, dis);
            }
            
            answer[i] = min;
        }
        
        return answer;
    }
}