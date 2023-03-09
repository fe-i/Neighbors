public class Board {
    private int[][] board;
    private String name;
    private final int BOARD_SIZE = 5; // Board Size

    public Board(String name) {
        this.name = name;
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public boolean placeNum(String letter, int newVal) {
        if (letter.length() > 0) letter = letter.substring(0, 1);
        else return false;

        int pos = getIdxFromLetter(letter);
        if (pos < 0 || pos > Math.pow(BOARD_SIZE, 2) - 1) return false;
        int r = pos / BOARD_SIZE;
        int c = pos % BOARD_SIZE;
        if (board[r][c] == 0) {
            board[r][c] = newVal;
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
    public String getLetterFromIdx(int idx) {
        return String.valueOf((char)(idx + 65));
    }

    public int getIdxFromLetter(String letter) {
        return letter.charAt(0) - 65;
    }

    public void printBoard() {
        System.out.println(name + "'s Board:");
        System.out.println("---------------------------");

        for (int r = 0; r < BOARD_SIZE; r++) {
            System.out.print("| ");
            for (int c = 0; c < BOARD_SIZE; c++) {
                int tile = board[r][c];
                if (tile != 0) System.out.print(tile + (tile < 10 ? " " : "") + " | ");
                else System.out.print(Colors.BLUE + getLetterFromIdx(r * BOARD_SIZE + c) + Colors.RESET + "  | ");
            }
            System.out.println("\n---------------------------");
        }
    }

    public int getScore() {
        int sum = 0;

        for (int r = 0; r < BOARD_SIZE; r++) {
            int streak = 1;
            for (int c = 1; c < BOARD_SIZE; c++) {
                if (board[r][c - 1] == board[r][c]) {
                    streak++;
                } else {
                    if (streak > 1) sum += board[r][c - 1] * streak;
                    streak = 1;
                }
            }
            if (streak > 1) sum += board[r][BOARD_SIZE - 1] * streak;
        }

        for (int c = 0; c < BOARD_SIZE; c++) {
            int streak = 1;
            for (int r = 1; r < BOARD_SIZE; r++) {
                if (board[r - 1][c] == board[r][c]) {
                    streak++;
                } else {
                    if (streak > 1) sum += board[r - 1][c] * streak;
                    streak = 1;
                }
            }
            if (streak > 1) sum += board[BOARD_SIZE - 1][c] * streak;
        }
        return sum;
    }
}
