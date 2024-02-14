import java.util.*;

public class sol {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    String line = input.nextLine();

    for(int i = line.length() - 1; i >= 0; i--) {
      System.out.print(line.charAt(i));
    }

    System.out.println();
  }
}