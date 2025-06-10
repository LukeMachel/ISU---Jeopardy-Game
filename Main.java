import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] questionCategories = new String[10];
        int[] questionPoints = new int[10];
        String[] questionTexts = new String[10];
        String[] questionAnswers = new String[10];
        boolean[] questionUsed = new boolean[10];
        boolean[] dailyDouble = new boolean[10];
        int questionCount = 0;
        System.out.println("Welcome to Jeopardy!");
        boolean running = true;
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("1. Play the game");
            System.out.println("2. Add a question");
            System.out.println("Enter an option: ");
            String choice = sc.nextLine();
            while (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("Invalid input. Please enter either 1 or 2: ");
                choice = sc.nextLine();
            }

            if (choice.equals("1")) {
                if (questionCount < 1) {
                    System.out.println("Please add some questions");
                }

                System.out.println("Enter number of players (2-4): ");
                int numOfPlayers = sc.nextInt();
                while (numOfPlayers < 2 || numOfPlayers > 4) {
                    System.out.println("Invalid entry. Please enter a number from 2 to 4: ");
                }

                String[] playerNames = new String[numOfPlayers];
                int[] playerScores = new int[numOfPlayers];

                for (int i = 0; i < numOfPlayers; i++) {
                    System.out.println("Please enter your name, Player #" + (i + 1) + ": ");
                    playerNames[i] = sc.nextLine();
                    playerScores[i] = 0;
                }






