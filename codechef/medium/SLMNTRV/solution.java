import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static boolean valid(int a, int b, int K) {
        int p = a * b;

        for (int x = 1; x * x <= p + K; x++) {
            if (Math.abs(p - x * x) <= K) {
                return true;
            }
        }

        return false;
    }

    static boolean solve(int start, int N, int K, boolean[] used, int[] ans, int pos) {
        if (pos == N) {
            return true;
        }

        // Find the first unused number
        int a = -1;

        for (int i = start; i <= N; i++) {
            if (!used[i]) {
                a = i;
                break;
            }
        }

        if (a == -1) {
            return true;
        }

        // Try every possible partner
        for (int b = a + 1; b <= N; b++) {
            if (!used[b] && valid(a, b, K)) {
                used[a] = true;
                used[b] = true;

                ans[pos] = a;
                ans[pos + 1] = b;

                if (solve(a + 1, N, K, used, ans, pos + 2)) {
                    return true;
                }

                used[a] = false;
                used[b] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            int[] ans = new int[N];
            boolean[] used = new boolean[N + 1];

            /*
             * If N is odd, one city can be placed at the end.
             * Only N-1 cities need to form valid pairs.
             */
            int pairCount = (N / 2) * 2;

            boolean possible = false;

            // Try every possible city as the leftover when N is odd.
            if (N % 2 == 1) {
                for (int leftover = 1; leftover <= N && !possible; leftover++) {

                    used[leftover] = true;
                    ans[N - 1] = leftover;

                    if (solve(1, N, K, used, ans, 0)) {
                        possible = true;
                    }

                    used[leftover] = false;
                }
            } else {
                possible = solve(1, N, K, used, ans, 0);
            }

            if (!possible) {
                System.out.println("-1");
            } else {
                for (int i = 0; i < N; i++) {
                    if (i > 0) {
                        System.out.print(" ");
                    }
                    System.out.print(ans[i]);
                }
                System.out.println();
            }
        }

        sc.close();
    }
}