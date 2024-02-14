import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // unfortunately Java's usual method of taking input (java.util.Scanner) is just very slow
        // Still, faster forms of input exist, as shown below

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        String inputString = input.readLine();
        
        int width, height, numPieces;
        width = inputString.charAt(0) - '0'; // silly way of converting a char to int
        height = inputString.charAt(2) - '0';
        
        inputString = input.readLine();
        numPieces = Integer.parseInt(inputString);
        
        HashMap<Integer, Character> col = new HashMap<Integer, Character>();
        HashMap<Integer, Character> row = new HashMap<Integer, Character>();
        HashMap<Integer, Character> posDiag = new HashMap<Integer, Character>();
        HashMap<Integer, Character> negDiag = new HashMap<Integer, Character>();
        
        for (int i = 0; i < numPieces; i++) {
            int x, y, pos, neg;
            char c;
            
            inputString = input.readLine();
            StringTokenizer splitter = new StringTokenizer(inputString);
            x = Integer.parseInt(splitter.nextToken());
            y = Integer.parseInt(splitter.nextToken());
            c = splitter.nextToken().charAt(0);
            
            pos = x + y;
            neg = x - y;
            
            if (col.containsKey(x)) {
                if (col.get(x) != c) {
                    System.out.println("ATTACK");
                    System.exit(0);
                }
            } else {
                col.put(x, c);
            }
            
            if (row.containsKey(y)) {
                if (row.get(y) != c) {
                    System.out.println("ATTACK");
                    System.exit(0);
                }
            } else {
                row.put(y, c);
            }
            
            if (posDiag.containsKey(pos)) {
                if (posDiag.get(pos) != c) {
                    System.out.println("ATTACK");
                    System.exit(0);
                }
            } else {
                posDiag.put(pos, c);
            }
            
            if (negDiag.containsKey(neg)) {
                if (negDiag.get(neg) != c) {
                    System.out.println("ATTACK");
                    System.exit(0);
                }
            } else {
                negDiag.put(neg, c);
            }
        }

        System.out.println("SAFE");
        
        input.close();
    }
}