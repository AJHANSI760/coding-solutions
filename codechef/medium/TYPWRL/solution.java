import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            String S = sc.next();
            String L = sc.next();

            boolean[] left = new boolean[26];

            for (char ch : L.toCharArray()) {
                left[ch - 'a'] = true;
            }

            int current = 0;
            int answer = 0;
            int previousHand = -1;

            for (char ch : S.toCharArray()) {
                int hand = left[ch - 'a'] ? 0 : 1;

                if (hand == previousHand) {
                    current++;
                } else {
                    current = 1;
                    previousHand = hand;
                }

                answer = Math.max(answer, current);
            }

            System.out.println(answer);
        }

        sc.close();
    }
}
}