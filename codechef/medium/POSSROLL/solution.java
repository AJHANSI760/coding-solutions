import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();
        int K = sc.nextInt();
        int Y = sc.nextInt();

        // Y must be a multiple of K
        // and must be among the first X multiples of K.
        if (Y % K == 0 && Y / K <= X) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        sc.close();
    }
}