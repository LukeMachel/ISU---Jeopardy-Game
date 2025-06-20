# ISU---Jeopardy-Game
Main arrays:
String[] questionCategories -- This stores the category for each question (e.g Sports, Movies, Politics)
int[] questionPoints -- stores the point value assigned to each question.
String[] questionTexts -- stores the text of the question.
String[] questionAnswers -- stores the correct answer to the question.
boolean[] questionUsed -- keeps track of whether or not a question has already been asked.
boolean[] dailyDouble -- marks which question is a Daily Double.
int questionCount -- keeps track of how many questions have been added.

Player arrays:
String[] playerNames -- stores the names of all players.
int[] playerScores -- keeps track of each player's score.

Control variables:
    boolean running -- controls if the game is still running.
    int totalQuestions -- tracks how many questions are left.
    int currentPlayer -- keeps track of whose turn it is.
    int dailyDoubleIndex -- randomly selects which question is the Daily Double.
