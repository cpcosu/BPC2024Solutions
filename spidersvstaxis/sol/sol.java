import java.util.*;

public class Main {

    static class PairWithDistance implements Comparable<PairWithDistance> {
        int x;
        int y;
        int distance;

        public PairWithDistance(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(PairWithDistance o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static boolean inBounds(boolean[][] board, PairWithDistance pair) {
        return 0 <= pair.x && pair.x < board.length &&
               0 <= pair.y && pair.y < board[0].length;
    }

    interface Mover {
        List<PairWithDistance> getNextLocations(boolean[][] board, PairWithDistance pair);
    }

    static class Spider implements Mover {
        @Override
        public List<PairWithDistance> getNextLocations(boolean[][] board, PairWithDistance pair) {
            List<PairWithDistance> nextLocations = new ArrayList<>();

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    PairWithDistance newPair;
                    if (!(dx == 0 && dy == 0)) {

                        /* for convenience, we double all distances so that we don't
                           have to deal with 1.5 as a float, or some kind of arbitrary
                           precision decimal number. */
                        int newDistance;
                        if (dx == 0 || dy == 0) {
                            newDistance = pair.distance + 2;
                        } else {
                            newDistance = pair.distance + 3;
                        }

                        newPair = new PairWithDistance(pair.x + dx, pair.y + dy, newDistance);
                        if (inBounds(board, newPair) && board[pair.x][pair.y]) {
                            nextLocations.add(newPair);
                        }
                    }
                }
            }

            return nextLocations;
        }
    }

    static class Taxi implements Mover {
        @Override
        public List<PairWithDistance> getNextLocations(boolean[][] board, PairWithDistance pair) {
            List<PairWithDistance> nextLocations = new ArrayList<>();

            int newDistance = pair.distance + 2;

            PairWithDistance up = new PairWithDistance(pair.x, pair.y - 1, newDistance);
            if (inBounds(board, up) && board[up.x][up.y]) {
                nextLocations.add(up);
                PairWithDistance moreUp = new PairWithDistance(pair.x, pair.y - 2, newDistance);
                if (inBounds(board, moreUp) && board[moreUp.x][moreUp.y]) {
                    nextLocations.add(moreUp);
                }
            }

            PairWithDistance down = new PairWithDistance(pair.x, pair.y + 1, newDistance);
            if (inBounds(board, down) && board[down.x][down.y]) {
                nextLocations.add(down);
                PairWithDistance moreDown = new PairWithDistance(pair.x, pair.y + 2, newDistance);
                if (inBounds(board, moreDown) && board[moreDown.x][moreDown.y]) {
                    nextLocations.add(moreDown);
                }
            }

            PairWithDistance left = new PairWithDistance(pair.x - 1, pair.y, newDistance);
            if (inBounds(board, left) && board[left.x][left.y]) {
                nextLocations.add(left);
                PairWithDistance moreLeft = new PairWithDistance(pair.x - 2, pair.y, newDistance);
                if (inBounds(board, moreLeft) && board[moreLeft.x][moreLeft.y]) {
                    nextLocations.add(moreLeft);
                }
            }

            PairWithDistance right = new PairWithDistance(pair.x + 1, pair.y, newDistance);
            if (inBounds(board, right) && board[right.x][right.y]) {
                nextLocations.add(right);
                PairWithDistance moreRight = new PairWithDistance(pair.x + 2, pair.y, newDistance);
                if (inBounds(board, moreRight) && board[moreRight.x][moreRight.y]) {
                    nextLocations.add(moreRight);
                }
            }

            return nextLocations;
        }
    }

    private static int getMinTime(boolean[][] board, Mover mover) {
        PriorityQueue<PairWithDistance> processing = new PriorityQueue<>();
        Set<Pair> out = new HashSet<>();
        processing.add(new PairWithDistance(0, 0, 0));
        while (processing.size() > 0) {
            PairWithDistance nextPair = processing.remove();
            if (nextPair.x == board.length - 1 && nextPair.y == board.length - 1) {
                return nextPair.distance;
            }
            Pair p = new Pair(nextPair.x, nextPair.y);
            if (!out.contains(p)) {
                out.add(p);
                List<PairWithDistance> nextLocations = mover.getNextLocations(board, nextPair);
                processing.addAll(nextLocations);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        scanner.nextLine();

        boolean[][] board = new boolean[size][size];

        for (int y = 0; y < size; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < size; x++) {
                board[x][y] = line.charAt(x) == '.';
            }
        }

//        System.out.println(Arrays.deepToString(board));

        Mover spiderMover = new Spider();
        int minSpiderTime = getMinTime(board, spiderMover);

        Mover taxiMover = new Taxi();
        int minTaxiTime = getMinTime(board, taxiMover);

        if (minSpiderTime < minTaxiTime) {
            System.out.println("spider");
        } else if (minTaxiTime < minSpiderTime) {
            System.out.println("taxicab");
        } else {
            System.out.println("tie");
        }
    }
}