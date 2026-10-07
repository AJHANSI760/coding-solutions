import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            String S = sc.next();

            int x = 0;
            int y = 0;

            for (char c : S.toCharArray()) {
                if (c == 'U') {
                    y++;
                } else if (c == 'D') {
                    y--;
                } else if (c == 'L') {
                    x--;
                } else if (c == 'R') {
                    x++;
                }
            }

            if ((Math.abs(x) == 2 && y == 0) ||
                (Math.abs(y) == 2 && x == 0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}
