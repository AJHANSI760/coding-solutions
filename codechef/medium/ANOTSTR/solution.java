import java.util.*;
import java.lang.*;
import java.io.*;

public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            String A = sc.next();
            String B = sc.next();

            int onesA = 0;
            int onesB = 0;

            for (int i = 0; i < N; i++) {
                if (A.charAt(i) == '1') {
                    onesA++;
                }

                if (B.charAt(i) == '1') {
                    onesB++;
                }
            }

            if (onesA % 2 == onesB % 2) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }