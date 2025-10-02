/**
 * Exercise sourced from Practice-It by the University of Washington.
 * Original problems available at: https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 */
public class IfElseMystery1 {
    public static void ifElseMystery1(int x, int y) {
        int z = 4;
        if (z <= x) {
            z = x + 1;
        } else {
            z = z + 9;
        }
        if (z <= y) {
            y++;
        }
        System.out.println(z + " " + y);
    }

    // TODO: For each call below, indicate what output is produced.
    public static void main(String[] args) {
        int x = 25/2;
        System.out.print(x);
    }
}

/* Before running the code, type your answer below.

TODO: Write output here

Then, click the green play button to check your work. */