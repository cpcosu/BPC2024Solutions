import java.util.*;

public class sol {
    public static void main(String[] args) {
        // Create Scanner
        Scanner input = new Scanner(System.in);

        // Take input into 2D array
        int[][] sentences = new int[2][4];
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 4; c++) {
                sentences[r][c] = input.nextInt();
            }
        }

        // Find the magnitude of the change needed to break each decision
        int break_A_when_B_quiet = Math.abs(sentences[0][0] - sentences[1][0]);
        int break_A_when_B_testify = Math.abs(sentences[0][2] - sentences[1][2]);
        int break_B_when_A_quiet = Math.abs(sentences[0][1] - sentences[0][3]);
        int break_B_when_A_testify = Math.abs(sentences[1][1] - sentences[1][3]);

        // Print the minimum magnitude change
        System.out.println(Math.min(Math.min(break_A_when_B_quiet, break_A_when_B_testify),
                Math.min(break_B_when_A_quiet, break_B_when_A_testify)));

        // Close the Scanner
        input.close();
    }
}
