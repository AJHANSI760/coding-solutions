import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();

            ArrayList<Integer>[] graph = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < N - 1; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();

                graph[u].add(v);
                graph[v].add(u);
            }

            // Total ways to choose the 3 excluded vertices
            long total = (long) N * (N - 1) * (N - 2) / 6;

            // Count leaf vertices
            int leaves = 0;

            // Number of leaf neighbours of every vertex
            int[] leafNeighbours = new int[N + 1];

            for (int v = 1; v <= N; v++) {
                if (graph[v].size() == 1) {
                    leaves++;
                    int parent = graph[v].get(0);
                    leafNeighbours[parent]++;
                }
            }

            /*
             * Bad sets caused by leaves.
             *
             * For every leaf:
             *   leaf + its neighbour must be excluded.
             *   The third excluded vertex can be chosen in N-2 ways.
             */
            long bad = (long) leaves * (N - 2);

            /*
             * If a vertex has k leaf neighbours,
             * a triple containing the vertex and two of its leaves
             * was counted twice (once for each leaf).
             *
             * Remove these duplicate counts.
             */
            for (int v = 1; v <= N; v++) {
                long k = leafNeighbours[v];
                bad -= k * (k - 1) / 2;
            }

            /*
             * Now handle degree-2 vertices.
             *
             * Their closed neighbourhood contains exactly 3 vertices.
             * If either neighbour is a leaf, that triple was already
             * counted in the leaf cases.
             *
             * So only count degree-2 vertices whose two neighbours
             * are not leaves.
             */
            for (int v = 1; v <= N; v++) {
                if (graph[v].size() == 2) {
                    int a = graph[v].get(0);
                    int b = graph[v].get(1);

                    if (graph[a].size() != 1 && graph[b].size() != 1) {
                        bad++;
                    }
                }
            }

            long answer = total - bad;

            System.out.println(answer);
        }

        sc.close();
    }
}