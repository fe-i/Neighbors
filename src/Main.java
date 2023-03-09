import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Neighbors!");
        System.out.print("Player 1, please enter your name: ");
        String p1Name = scan.nextLine();

        System.out.println();

        System.out.print("Player 2, please enter your name: ");
        String p2Name = scan.nextLine();

        System.out.println();

        Board[] players = {new Board(p1Name), new Board(p2Name)};

        int turn = 0;
        int randomNumber = (int) (Math.random() * 10) + 1;
        do {
            players[turn % 2].printBoard();
            System.out.println(Colors.PURPLE + "The number is: " + Colors.RESET + Colors.CYAN + randomNumber + Colors.RESET);
            System.out.print(Colors.YELLOW + "Enter your move as a letter: " + Colors.RESET);
            String letter = scan.nextLine().toUpperCase();
            boolean placed = players[turn % 2].placeNum(letter, randomNumber);

            if (placed) {
                turn++;
                if (turn % 2 == 0) randomNumber = (int) (Math.random() * 10) + 1;
            }
            else {
                System.out.println(Colors.RED + "Invalid move!" + Colors.RESET);
            }
        } while (turn < 2 * Math.pow(5 /*Board Size*/, 2));

        System.out.println();

        Board[] results = new Board[2];
        if (players[0].getScore() > players[1].getScore()) {
            results[0] = players[0];
            results[1] = players[1];
        }
        else if (players[1].getScore() > players[0].getScore()) {
            results[0] = players[1];
            results[1] = players[0];
        }
        else {
            for (Board b: players) {
                b.printBoard();
            }
            System.out.println("The game was somehow a tie!???");
            System.exit(0);
        }

        for (Board b: players) {
            b.printBoard();
        }
        System.out.println(Colors.GREEN + results[0].getName() + " has won the game, achieving a score of " + results[0].getScore() + Colors.RESET);
        System.out.println(Colors.RED + results[1].getName() + " lost by " + (results[0].getScore() - results[1].getScore()) + " points! ;-;" + Colors.RESET);
    }
}