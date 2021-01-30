import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean reachable(ArrayList<int[]> graph, int n, int start, int end){
        boolean[] visited = new boolean[n];
        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(start);
        visited[start] = true;
        while(queue.size()>0){
            int temp = queue.get(0);
            queue.remove(0);
            if (temp == end) {
                return true;
            }
                for (int j=0; j<graph.size(); j++){
                    if (graph.get(j)[0]==temp){
                        if (!visited[graph.get(j)[1]]){
                            queue.add(graph.get(j)[1]);
                            visited[graph.get(j)[1]] = true;
                        }
                    }
                }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        ArrayList<int[]> graph = new ArrayList<int[]>();
        for (int i=0; i<k; i++){
            int[] temp = new int[2];
            temp[0] = scanner.nextInt()-1;
            temp[1] = scanner.nextInt()-1;
            graph.add(temp);
        }
        if (reachable(graph, n, 0, n-1)){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
