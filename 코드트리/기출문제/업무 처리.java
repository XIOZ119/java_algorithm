import java.util.*;

public class Main {

    static int nodes;
    static List<int[]>[] tasks; 
    static List<Integer>[] tree;
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int K = sc.nextInt();
        int R = sc.nextInt();
        int leafCount = (int) Math.pow(2, H);

        nodes = 1;

        for(int i=0; i<=H; i++) {
            nodes += (int) Math.pow(2, i);
        }

        tasks = new ArrayList[nodes];
        for(int i=0; i<nodes; i++) {
            tasks[i] = new ArrayList<>();
        }

        for (int i = 0; i < leafCount; i++) {
            int leaf = nodes - leafCount - 1 + i;

            for (int j = 0; j < K; j++) {
                int task = sc.nextInt();

                tasks[leaf].add(new int[] {task, -1});
            }
        }

        tree = new ArrayList[nodes];

        for(int i=0; i<nodes; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=1; i<nodes; i++) {
            int parent = (i-1) / 2;
            tree[i].add(parent);
        }
        
        for(int i=1; i<=R; i++) {
            doWork(i);
        }

        System.out.println(answer + "");
    }

    static void doWork(int day) {
        for(int i=0; i<nodes; i++) {
            for(int[] task: tasks[i]){
                if(task[1] == -1 || (task[1] % 2 == 0 && day % 2 == 0) || (task[1] % 2 == 1 && day % 2 == 1)) {
                    tasks[i].remove(task);

                    if(i == 0) {
                        answer += task[0];
                    }
                    
                    for(int parent: tree[i]){
                        tasks[parent].add(new int[] {task[0], i});
                    }

                    break;
                }
            }
        }
    }
}
