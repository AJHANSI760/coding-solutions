# SLMNTRV

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Salesman Travels

 *As always, Hyder seeks Order in Chaos. The cities are currently in complete chaos, and Hyder wants to bring some order to them by arranging them in a suitable route.* 

There are $N$ cities numbered from $1$ to $N$. Hyder wants to visit every city exactly once, in some order. Let this order be represented by a permutation $P$ of $[1,N]$.

Hyder starts at city $P_1$ and wants to reach city $P_N$. To bring order to the chaos, the route must be governed by the following rules:

- For every $i>0$ such that $2i+1 \leq N$, Hyder can travel from city $P_{2i}$ to city $P_{2i+1}$ without any restrictions.
- For every $i>0$ such that $2i \leq N$, Hyder can travel from city $P_{2i-1}$ to city $P_{2i}$ if and only if there exists a positive integer $X$ such that:
$$ \left|P_{2i} \cdot P_{2i-1} - X^2\right| \leq K $$

that is, the product of $P_{2i}$ and $P_{2i-1}$ is within a distance of $K$ from some square integer.

Note that different indices $i$ may use different values of $X$.

Your task is to determine any permutation $P$ for which Hyder can successfully travel from $P_1$ to $P_N$.

If there are multiple valid permutations, any one of them will be accepted.
If no such permutation exists, print $-1$ instead.

### Input Format
- The first line of input will contain a single integer $T$, denoting the number of test cases.
- Each test case consists of a single line of input. The only line of each test case contains two space-separated integers $N$ and $K$, denoting the number of cities and the allowed deviation from a perfect square.
### Output Format

For each test case,

- If no valid permutation exists, print $-1$.
- Otherwise, output on a new line $N$ space-separated integers representing a permutation $P$ of $[1,N]$ such that Hyder can travel from $P_1$ to $P_N$.

If multiple valid permutations exist, output any one of them.

### Constraints
- $1 \leq T \leq 2500$
- $1 \leq N \leq 50$
- $1 \leq K \leq 50$
### Sample 1:
Input
Output

```
2
2 3
5 2

```

```
1 2
3 2 5 1 4
```

### Explanation:

 **Test case $1$:**  There are only two cities, and the move $P_1 \to P_2$ is valid because $1\cdot 2 = 2$, and choosing $X = 2$ gives us $|2-2^2| = |2-4| = 2 \le 3 = K$.

 **Test case $2$:**  Consider $P = [4, 3, 2, 5, 1]$. The moves $2 \to 5$ and $1\to 4$ are unrestricted.

- For the move $3 \to 2$, taking $X = 2$ gives $|3 \cdot 2 - 2^2| = 2 \le K$.
- For the move $5 \to 1$, taking $X = 2$ gives $|5 \cdot 1 - 2^2| = 1 \le K$.

Hence the route is valid. Other valid permutations would also be accepted.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-07T15:34:30.817Z  

```java
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Blossom {
        int n;
        ArrayList<Integer>[] g;
        int[] match, parent, base;
        boolean[] used, blossom;
        int[] queue;

        Blossom(int n) {
            this.n = n;

            g = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }

            match = new int[n];
            parent = new int[n];
            base = new int[n];
            used = new boolean[n];
            blossom = new boolean[n];
            queue = new int[n];

            Arrays.fill(match, -1);
        }

        void addEdge(int u, int v) {
            g[u].add(v);
            g[v].add(u);
        }

        int lca(int a, int b) {
            boolean[] usedPath = new boolean[n];

            while (true) {
                a = base[a];
                usedPath[a] = true;

                if (match[a] == -1) {
                    break;
                }

                a = parent[match[a]];
            }

            while (true) {
                b = base[b];

                if (usedPath[b]) {
                    return b;
                }

                if (match[b] == -1) {
                    break;
                }

                b = parent[match[b]];
            }

            return -1;
        }

        void markPath(int v, int b, int child) {
            while (base[v] != b) {
                blossom[base[v]] = true;
                blossom[base[match[v]]] = true;

                parent[v] = child;
                child = match[v];

                v = parent[match[v]];
            }
        }

        int findPath(int root) {
            Arrays.fill(used, false);
            Arrays.fill(parent, -1);

            for (int i = 0; i < n; i++) {
                base[i] = i;
            }

            int head = 0;
            int tail = 0;

            queue[tail++] = root;
            used[root] = true;

            while (head < tail) {
                int v = queue[head++];

                for (int u : g[v]) {

                    if (base[v] == base[u] || match[v] == u) {
                        continue;
                    }

                    if (u == root ||
                        (match[u] != -1 && parent[match[u]] != -1)) {

                        int curBase = lca(v, u);

                        Arrays.fill(blossom, false);

                        markPath(v, curBase, u);
                        markPath(u, curBase, v);

                        for (int i = 0; i < n; i++) {
                            if (blossom[base[i]]) {
                                base[i] = curBase;

                                if (!used[i]) {
                                    used[i] = true;
                                    queue[tail++] = i;
                                }
                            }
                        }

                    } else if (parent[u] == -1) {

                        parent[u] = v;

                        if (match[u] == -1) {
                            return u;
                        }

                        int next = match[u];

                        if (!used[next]) {
                            used[next] = true;
                            queue[tail++] = next;
                        }
                    }
                }
            }

            return -1;
        }

        int maximumMatching() {
            int result = 0;

            for (int i = 0; i < n; i++) {
                if (match[i] != -1) {
                    continue;
                }

                int v = findPath(i);

                if (v == -1) {
                    continue;
                }

                while (v != -1) {
                    int pv = parent[v];

                    if (pv == -1) {
                        break;
                    }

                    int next = match[pv];

                    match[v] = pv;
                    match[pv] = v;

                    v = next;
                }

                result++;
            }

            return result;
        }
    }

    static boolean validPair(int a, int b, int K) {
        int product = a * b;

        int root = (int) Math.sqrt(product);

        // Check nearest square below
        if (root >= 1 &&
            Math.abs(product - root * root) <= K) {
            return true;
        }

        // Check nearest square above
        int next = root + 1;

        return Math.abs(product - next * next) <= K;
    }

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {

            int N = fs.nextInt();
            int K = fs.nextInt();

            /*
             * For odd N we add one dummy vertex.
             *
             * The dummy can pair with any one real city.
             * That real city becomes PN, where no restriction
             * is required for the final position.
             */
            int V = (N % 2 == 0) ? N : N + 1;
            int dummy = N;

            Blossom bm = new Blossom(V);

            // Build graph of valid restricted pairs.
            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {

                    if (validPair(i, j, K)) {
                        bm.addEdge(i - 1, j - 1);
                    }
                }
            }

            // Odd N: dummy can be paired with any city.
            if (N % 2 == 1) {
                for (int i = 0; i < N; i++) {
                    bm.addEdge(i, dummy);
                }
            }

            int matching = bm.maximumMatching();

            /*
             * We need floor(N/2) real pairs.
             */
            if (matching != N / 2) {
                out.append("-1\n");
                continue;
            }

            int[] answer = new int[N];
            boolean[] taken = new boolean[N];

            int pos = 0;
            int leftover = -1;

            for (int i = 0; i < N; i++) {

                if (taken[i]) {
                    continue;
                }

                int j = bm.match[i];

                // Dummy pair = leftover city.
                if (j == dummy) {
                    leftover = i + 1;
                    taken[i] = true;
                    continue;
                }

                if (j >= 0 && j < N) {

                    answer[pos++] = i + 1;
                    answer[pos++] = j + 1;

                    taken[i] = true;
                    taken[j] = true;
                }
            }

            if (N % 2 == 1) {
                answer[N - 1] = leftover;
            }

            for (int i = 0; i < N; i++) {
                if (i > 0) {
                    out.append(' ');
                }

                out.append(answer[i]);
            }

            out.append('\n');
        }

        System.out.print(out);
    }

    static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];

        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {

            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len <= 0) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {

            int c;

            do {
                c = read();
            } while (c <= ' ');

            int result = 0;

            while (c > ' ') {
                result = result * 10 + (c - '0');
                c = read();
            }

            return result;
        }
    }
}
```

---

[View on CodeChef](https://www.codechef.com/problems/SLMNTRV)