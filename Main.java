import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Declaring all the arrays here to store questions and game data
        String[] questionCategories = new String[10];
        int[] questionPoints = new int[10];
        String[] questionTexts = new String[10];
        String[] questionAnswers = new String[10];
        boolean[] questionUsed = new boolean[10];
        boolean[] dailyDouble = new boolean[10];
        int questionCount = 0;
        // Main menu below
        System.out.println("Welcome to Jeopardy!");
        boolean running = true; // This line keeps the main menu running until the end
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("1. Play the game");
            System.out.println("2. Add a question");
            System.out.println("Enter an option: ");
            String choice = sc.nextLine();
            // The line below makes sure that the user enters the right menu choice
            while (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("Invalid input. Please enter either 1 or 2: ");
                choice = sc.nextLine();
            }
            // Option #2 lets the user(s) add some questions. This is required before playing the actual game.
            if (choice.equals("2")) {
                if (questionCount >= 10) {
                    System.out.println("Question limit reached.");
                }
                System.out.println("Enter category: ");
                questionCategories[questionCount] = sc.nextLine();
                System.out.println("Enter point value: ");
                questionPoints[questionCount] = sc.nextInt();
                sc.nextLine(); // This eats up the leftover new line. It took me quite a while to realize my error and add this in!
                System.out.println("Enter question text: ");
                questionTexts[questionCount] = sc.nextLine();
                System.out.println("Enter correct answer: ");
                questionAnswers[questionCount] = sc.nextLine();
                questionUsed[questionCount] = false; // This line marks the question as unused
                dailyDouble[questionCount] = false; // This line makes sure the first question is not a Daily Double
                questionCount++; // This line increases the total questions a user can add
                System.out.println("You added a question successfully!");
            }
            // The next three lines make sure that the user enters some questions before starting the game
            if (choice.equals("1")) {
                if (questionCount < 1) {
                    System.out.println("Please add some questions");
                }
                // The code below is the player configuration, asking the user to enter # of players
                System.out.println("Enter number of players (2-4): ");
                int numOfPlayers = sc.nextInt();
                while (numOfPlayers < 2 || numOfPlayers > 4) {
                    System.out.println("Invalid entry. Please enter a number from 2 to 4: ");
                    numOfPlayers = sc.nextInt();
                }
                // The next six lines work to retrieve the players' names
                String[] playerNames = new String[numOfPlayers];
                int[] playerScores = new int[numOfPlayers];
                for (int i = 0; i < numOfPlayers; i++) {
                    System.out.println("Please enter your name, Player # " + (i + 1) + ": ");
                    playerNames[i] = sc.nextLine();
                    playerScores[i] = 0;
                }
                int totalQuestions = questionCount;
                int currentPlayer = 0;
                // Setting up the game board below, where the users start playing
                while (totalQuestions > 0) {
                    System.out.println("Game Board:");
                    for (int i = 0; i < questionCount; i++) {
                        if (!questionUsed[i]) {
                            System.out.println((i + 1) + ". " + questionCategories[i] + " - " + questionPoints[i] + " points");
                        }
                        else {
                            System.out.println((i + 1) + ". (Already used)");
                        }
                    }
                    System.out.println("It's " + playerNames[currentPlayer] + "'s turn. Your current score is: " + playerScores[currentPlayer]);
                    System.out.println("Select a question number: ");
                    int questionChoice = sc.nextInt();
                    sc.nextLine();
                    // This line below checks if the user picked a valid question
                    if (questionChoice < 1 || questionChoice > questionCount || questionUsed[questionChoice - 1]) {
                        System.out.println("Invalid entry. Please try again.");
                    }
                    int index = questionChoice - 1;
                    questionUsed[index] = true;
                    totalQuestions--;
                    // Setting up the Daily Double option here by randomly assigning one question to become the Daily Double
                    int dailyDoubleIndex = (int) (Math.random() * questionCount);
                    dailyDouble[dailyDoubleIndex] = true;
                    int wager = questionPoints[index]; // *just wanted to note that a standard wager is equal to the point value of a question
                    if (dailyDouble[index]) {
                        System.out.println(">>> Daily Doubles! <<<");
                        System.out.println("You can wager up to your current score: " + playerScores[currentPlayer]);
                        // Allowing the user to wager points
                        System.out.print("Enter your wager: ");
                        wager = sc.nextInt();
                        // The two lines below check for wager limits
                        if (wager < 0) wager = 0;
                        if (wager > playerScores[currentPlayer]) wager = playerScores[currentPlayer];
                    }
                    // Below, the system asks the question and the user must answer
                    System.out.println("Question: " + questionTexts[index]);
                    System.out.println("Your answer: ");
                    String userAnswer = sc.nextLine();
                    // The lines below check the answer
                    if (userAnswer.equals(questionAnswers[index])) {
                        System.out.println("Ding Ding Ding! Correct!");
                        playerScores[currentPlayer] = playerScores[currentPlayer] + wager;
                    }
                    else {
                        System.out.println("Wrong! The correct answer was: " + questionAnswers[index]);
                        playerScores[currentPlayer] = playerScores[currentPlayer] - wager;
                    }
                    // The code below shows the scores after each turn has passed
                    System.out.println("Current scores are:");
                    for (int i = 0; i < numOfPlayers; i++) {
                        System.out.println(playerNames[i] + ": " + playerScores[i]);
                    }
                    // The next three lines move the turn to the next player
                    currentPlayer++;
                    if (currentPlayer >= numOfPlayers) {
                        currentPlayer = 0;
                    }
                }
                // This code finds the winner after everything has been answered and scores have been recorded
                int maxScore = playerScores[0];
                int winnerIndex = 0;
                for (int i = 1; i < numOfPlayers; i++) {
                    if (playerScores[i] > maxScore) {
                        maxScore = playerScores[i];
                        winnerIndex = i;
                    }
                }
                System.out.println("Game Over! The winner is: " + playerNames[winnerIndex] + " with " + maxScore + " points. Congratulations!");
                // The line below asks the user if they want to play again
                System.out.println("Would you like to play again? Please answer yes or no: ");
                String playAgain = sc.nextLine();
                if (playAgain.equals("no")) {
                    running = false;
                    System.out.println("Thank you for playing, and have a great day!"); // Farewell message
                }
                else {
                    // The loop below resets the questions for the next round
                    for (int i = 0; i < questionCount; i++) {
                        questionUsed[i] = false;
                        dailyDouble[i] = false;
                    }
                }
            }
        }
    }
}









